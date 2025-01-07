<template>
  <div>

    <div class="table">
      <el-table :data="tableData" strip @selection-change="handleSelectionChange">
        <el-table-column prop="id" label="序号" width="70" align="center" sortable></el-table-column>
        <el-table-column prop="labNumber" label="实验室编号" :filters="headFilters['labNumber']"
                         :filter-method="filterHandler"></el-table-column>
        <el-table-column prop="semesterName" label="学期" sortable></el-table-column>
        <el-table-column prop="week" label="周次"></el-table-column>
        <el-table-column prop="day" label="星期"></el-table-column>
        <el-table-column prop="sessionName" label="节次"></el-table-column>
        <el-table-column prop="studentName" label="申请学生" :filters="headFilters['student']"
                         :filter-method="filterHandler"></el-table-column>
        <el-table-column prop="reason" label="申请原因"></el-table-column>
        <el-table-column prop="applyDate" label="申请日期" sortable></el-table-column>
        <el-table-column prop="status" label="状态" :filters="headFilters['status']"
                         :filter-method="filterHandler"></el-table-column>

        <el-table-column label="操作" align="center" width="180">
          <template v-slot="scope">
            <el-button v-if="scope.row.status === '未审核'" size="mini" type="primary" plain @click="pass(scope.row)">通过</el-button>
            <el-button v-if="scope.row.status === '未审核'" size="mini" type="danger" plain @click="noPass(scope.row)">不通过</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination">
        <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5, 10, 20]"
            :page-size="pageSize"
            layout="total, prev, pager, next"
            :total="total">
        </el-pagination>
      </div>
    </div>


    <el-dialog title="实验室预约" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="form" label-width="100px" style="padding-right: 50px" :rules="rules" ref="formRef">
        <el-form-item label="实验室编号" prop="labId">
          <el-select v-model="form.labId" placeholder="请选择实验室编号" style="width: 100%">
            <el-option  v-for="item in labData" :label="item.number" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="借用学期" prop="semesterName">
          <el-input :value="curSemester.name" disabled></el-input>
        </el-form-item>
        <el-form-item label="周次" prop="week">
          <el-input v-model="form.week" placeholder="周次"></el-input>
        </el-form-item>
        <el-form-item label="星期" prop="day">
          <el-select v-model="form.day" placeholder="星期" style="width: 100%">
            <el-option  label="周一" value="周一"></el-option>
            <el-option  label="周二" value="周二"></el-option>
            <el-option  label="周三" value="周三"></el-option>
            <el-option  label="周四" value="周四"></el-option>
            <el-option  label="周五" value="周五"></el-option>
            <el-option  label="周六" value="周六"></el-option>
            <el-option  label="周日" value="周日"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="节次" prop="sessionId">
          <el-select v-model="form.sessionId" placeholder="请选择节次" style="width: 100%">
            <el-option  v-for="item in sessions" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="申请学生" prop="studentName">
          <el-input v-model="user.name" placeholder="申请学生" disabled></el-input>
        </el-form-item>
        <el-form-item label="申请原因" prop="reason">
          <el-input v-model="form.reason" placeholder="申请原因"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
export default {
  name: "DoBorrow",
  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      title: null,
      fromVisible: false,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      curSemester: null,
      rules: {
        labId: [
          {required: true, message: '请选择实验室编号', trigger: 'blur'},
        ],
        week: [
          {required: true, message: '请选择借用周', trigger: 'blur'},
        ],
        day: [
          {required: true, message: '请选择借用星期', trigger: 'blur'},
        ],
        session: [
          {required: true, message: '请选择借用节次', trigger: 'blur'},
        ],
        reason: [
          {required: true, message: '请输入借用原因', trigger: 'blur'},
        ]
      },
      ids: [],
      labData: [],
      sessions: [],
      list: [],
      headFilters: []
    }
  },
  created() {
    this.load(1)
    this.loadLab()
    this.loadCurSemester()
    this.loadSessions()
    this.getList()
  },
  methods: {
    getList() {
      this.$request.get('/borrow/selectAll').then(res => {
        if (res.code === '200') {
          this.list = res.data
          this.tableFilter(this.list)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    tableFilter(list){ // 传入表格数据
      let filters = {}
      if (list.length) {
        Object.keys(list[0]).forEach(item => { // 拿到第一条数据，将key值组成数组，并将key给filters对象作为键名，值为空数组
          filters[item] = []
        })
        list.forEach(item => { // 遍历表格的数据数组
          for (let key in item) { // 遍历数据数组的每一项(对象)
            if (filters.hasOwnProperty(key) && !filters[key].find(i => i.text == item[key])) { // 如果filters对象中有当前键名（它的值是数组）,并且该数组中不含当前值的对象
              filters[key].push({text: item[key], value: item[key]}) // filters当前键名对应的值（数组），再push该值组成的对象（el-table筛选条件的格式）
            }
          }
        })
      }
      this.headFilters = filters
    },
    filterHandler(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    },
    loadSessions() {
      this.$request.get('/session/selectAll').then(res => {
        if (res.code === '200') {
          this.sessions = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    loadLab() {
      this.$request.get('/lab/selectAll').then(res => {
        if (res.code === '200') {
          this.labData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    loadCurSemester() {
      this.$request.get('/semester/selectCurSemester').then(res => {
        if (res.code === '200') {
          this.curSemester = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleAdd() {   // 新增数据
      this.form = {}  // 新增数据的时候清空数据
      this.form.semesterId = this.curSemester.id
      this.form.semesterName = this.curSemester.name
      this.form.studentId = this.user.id
      this.form.studentName = this.user.name
      this.fromVisible = true   // 打开弹窗
    },
    handleEdit(row) {   // 编辑数据
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      this.fromVisible = true   // 打开弹窗
    },
    save() {   // 保存按钮触发的逻辑  它会触发新增或者更新
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request({
            url: this.form.id ? '/borrow/update' : '/borrow/add',
            method: this.form.id ? 'PUT' : 'POST',
            data: this.form
          }).then(res => {
            if (res.code === '200') {  // 表示成功保存
              this.$message.success('保存成功')
              this.load(1)
              this.fromVisible = false
            } else {
              this.$message.error(res.msg)  // 弹出错误的信息
            }
          })
        }
      })
    },
    del(id) {   // 单个删除
      this.$confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/borrow/delete/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    pass(row) { // 通过审核
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      this.$confirm('是否通过审核？', '通过审核', {type: "warning"}).then(response => {
        this.$request.put('/borrow/pass', this.form).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      })
    },
    noPass(row) { // 驳回审核
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      this.$confirm('是否驳回审核？', '驳回审核', {type: "warning"}).then(response => {
        this.$request.put('/borrow/noPass', this.form).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      })
    },
    handleSelectionChange(rows) {   // 当前选中的所有的行数据
      this.ids = rows.map(v => v.id)   //  [1,2]
    },
    delBatch() {   // 批量删除
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定批量删除这些数据吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/lab/delete/batch', {data: this.ids}).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    load(pageNum) {  // 分页查询
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/borrow/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          title: this.title,
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    reset() {
      this.title = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
  }
}
</script>

<style scoped>

</style>
