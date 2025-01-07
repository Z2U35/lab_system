<template>
  <div>

    <div class="operation">
      <el-button type="primary" plain @click="handleAdd">排课登记</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" strip @selection-change="handleSelectionChange">
        <el-table-column prop="id" label="序号" align="center" sortable></el-table-column>
        <el-table-column prop="semesterName" label="上课学期" width="100" sortable></el-table-column>
        <el-table-column prop="startWeek" label="开始周"></el-table-column>
        <el-table-column prop="endWeek" label="结束周"></el-table-column>
        <el-table-column prop="week" label="星期"></el-table-column>
        <el-table-column prop="sessionName" label="节次"></el-table-column>
        <el-table-column prop="courseName" label="课程名" width="130"></el-table-column>
        <el-table-column prop="clazz" label="上课班级"  :filters="headFilters['clazz']"
                         :filter-method="filterHandler"></el-table-column>
        <el-table-column prop="number" label="上课人数"></el-table-column>
        <el-table-column prop="labType" label="实验室类型"></el-table-column>
        <el-table-column prop="status" label="排课状态"  :filters="headFilters['status']"
                         :filter-method="filterHandler"></el-table-column>
        <el-table-column label="操作" align="center" width="180">
          <template v-slot="scope">
            <el-button v-if="scope.row.status === '未排课'" size="mini" type="primary" plain @click="handleEdit(scope.row)">编辑</el-button>
            <el-button v-if="scope.row.status === '未排课'" size="mini" type="danger" plain @click="del(scope.row.id)">删除</el-button>
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


    <el-dialog title="实验室申请登记" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="form" label-width="100px" style="padding-right: 50px" :rules="rules" ref="formRef">
        <el-form-item label="上课学期" prop="semesterName">
          <el-input v-if="isEditing" v-model="form.semesterName"></el-input>
          <el-input v-else :value="curSemester.name" disabled></el-input>
        </el-form-item>
        <el-form-item label="开始周" prop="startWeek">
          <el-input v-model="form.startWeek" placeholder="开始周"></el-input>
        </el-form-item>
        <el-form-item label="结束周" prop="endWeek">
          <el-input v-model="form.endWeek" placeholder="结束周"></el-input>
        </el-form-item>
        <el-form-item label="星期" prop="week">
          <el-select v-model="form.week" placeholder="星期" style="width: 100%">
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
        <el-form-item label="课程名" prop="courseName">
          <el-input v-model="form.courseName" placeholder="课程名"></el-input>
        </el-form-item>
        <el-form-item label="上课班级" prop="clazz">
          <el-input v-model="form.clazz" placeholder="上课班级"></el-input>
        </el-form-item>
        <el-form-item label="上课人数" prop="number">
          <el-input v-model="form.number" placeholder="上课人数"></el-input>
        </el-form-item>
        <el-form-item label="实验室类型" prop="labType">
          <el-select v-model="form.labType" placeholder="实验室类型" style="width: 100%">
            <el-option  label="软件" value="软件"></el-option>
            <el-option  label="硬件" value="硬件"></el-option>
            <el-option  label="网络" value="网络"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="申请教师" prop="teacherName">
          <el-input v-model="user.name" placeholder="申请教师" disabled></el-input>
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
  name: "Schedule",
  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      name: null,
      number: null,
      fromVisible: false,
      form: {},
      isEditing: null,
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      curSemester: null,
      rules: {
        startWeek: [
          {required: true, message: '请输入开始周', trigger: 'blur'},
        ],
        endWeek: [
          {required: true, message: '请输入结束周', trigger: 'blur'},
        ],
        week: [
          {required: true, message: '请选择星期', trigger: 'blur'},
        ],
        sessionId: [
          {required: true, message: '请选择节次', trigger: 'blur'},
        ],
        courseName: [
          {required: true, message: '请输入课程名', trigger: 'blur'},
        ],
        clazz: [
          {required: true, message: '请输入上课班级', trigger: 'blur'},
        ],
        number: [
          {required: true, message: '请输入上课人数', trigger: 'blur'},
        ],
        labType: [
          {required: true, message: '请选择实验室类型', trigger: 'blur'},
        ],
      },
      ids: [],
      sessions: [],
      list: [],
      headFilters: []
    }
  },
  created() {
    this.load(1)
    this.loadCurSemester()
    this.loadSessions()
    this.getList()
  },
  methods: {
    getList() {
      this.$request.get('/schedule/selectAll').then(res => {
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
    loadCurSemester() {
      this.$request.get('/semester/selectCurSemester').then(res => {
        if (res.code === '200') {
          this.curSemester = res.data
          // console.log(res.data.name)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleAdd() {   // 新增数据
      this.isEditing = false
      this.form = {}  // 新增数据的时候清空数据
      this.form.semesterId = this.curSemester.id
      this.form.semesterName = this.curSemester.name
      this.form.teacherId = this.user.id
      this.form.teacherName = this.user.name
      this.fromVisible = true   // 打开弹窗
    },
    handleEdit(row) {   // 编辑数据
      this.isEditing = true
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      this.fromVisible = true   // 打开弹窗
    },
    save() {   // 保存按钮触发的逻辑  它会触发新增或者更新
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request({
            url: this.form.id ? '/schedule/update' : '/schedule/add',
            method: this.form.id ? 'PUT' : 'POST',
            data: this.form
          }).then(res => {
            if (res.code === '200') {  // 表示成功保存
              if (this.isEditing) {
                this.$message.success('保存成功')
              } else {
                this.$message.success('申请成功，请等待管理员审核')
              }
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
        this.$request.delete('/schedule/delete/' + id).then(res => {
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
    handleSelectionChange(rows) {   // 当前选中的所有的行数据
      this.ids = rows.map(v => v.id)
    },
    delBatch() {   // 批量删除
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定批量删除这些数据吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/schedule/delete/batch', {data: this.ids}).then(res => {
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
      this.$request.get('/schedule/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          number: this.number,
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
  }
}
</script>

<style scoped>

</style>