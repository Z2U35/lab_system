<template>
  <div>

    <div class="operation">
      <el-button type="primary" plain @click="handleAdd">报修申请</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" strip @selection-change="handleSelectionChange">
        <el-table-column prop="id" label="序号" width="70" align="center" sortable></el-table-column>
        <el-table-column prop="labNumber" label="实验室编号" :filters="headFilters['labNumber']"
                         :filter-method="filterHandler"></el-table-column>
        <el-table-column prop="equipmentName" label="设备名称"></el-table-column>
        <el-table-column prop="description" label="故障说明"></el-table-column>
        <el-table-column prop="teacherName" label="报修人"></el-table-column>
        <el-table-column prop="applyDate" label="报修日期" sortable></el-table-column>
        <el-table-column prop="status" label="报修状态"  :filters="headFilters['status']"
                         :filter-method="filterHandler"></el-table-column>
        <el-table-column prop="endRepair" label="完成情况说明"></el-table-column>
        <el-table-column label="操作" align="center" width="180">
          <template v-slot="scope">
            <el-button v-if="scope.row.status === '未维修'" size="mini" type="primary" plain @click="handleEdit(scope.row)">编辑</el-button>
            <el-button v-if="scope.row.status === '未维修'" size="mini" type="danger" plain @click="del(scope.row.id)">删除</el-button>
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


    <el-dialog title="实验室设备报修" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="form" label-width="100px" style="padding-right: 50px" :rules="rules" ref="formRef">
        <el-form-item label="实验室编号" prop="labId">
          <el-select v-model="form.labId" placeholder="请选择实验室编号" style="width: 100%">
            <el-option  v-for="item in labData" :label="item.number" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="设备名称" prop="equipmentName">
          <el-input v-model="form.equipmentName" placeholder="设备名称"></el-input>
        </el-form-item>
        <el-form-item label="故障说明" prop="description">
          <el-input v-model="form.description" placeholder="故障说明"></el-input>
        </el-form-item>
        <el-form-item label="报修人" prop="teacherName">
          <el-input v-model="user.name" placeholder="报修人" disabled></el-input>
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
  name: "Repair",
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
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      rules: {
        labId: [
          {required: true, message: '请选择实验室编号', trigger: 'blur'},
        ],
        equipmentName: [
          {required: true, message: '请输入设备名称', trigger: 'blur'},
        ],
        description: [
          {required: true, message: '请输入故障说明', trigger: 'blur'},
        ],
      },
      ids: [],
      labData: [],
      list: [],
      headFilters: []
    }
  },
  created() {
    this.load(1)
    this.loadLab()
    this.getList()
  },
  methods: {
    getList() {
      this.$request.get('/repair/selectAll').then(res => {
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
    loadLab() {
      this.$request.get('/lab/selectAll').then(res => {
        if (res.code === '200') {
          this.labData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleAdd() {   // 新增数据
      this.form = {}  // 新增数据的时候清空数据
      this.form.teacherId = this.user.id
      this.form.teacherName = this.user.name
      console.log(this.form)
      this.fromVisible = true   // 打开弹窗
    },
    handleEdit(row) {   // 编辑数据
      this.isEditing = true
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      console.log(this.form)

      this.fromVisible = true   // 打开弹窗
    },
    save() {   // 保存按钮触发的逻辑  它会触发新增或者更新
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request({
            url: this.form.id ? '/repair/update' : '/repair/add',
            method: this.form.id ? 'PUT' : 'POST',
            data: this.form
          }).then(res => {
            if (res.code === '200') {  // 表示成功保存
              if (this.isEditing) {
                this.$message.success('保存成功')
              } else {
                this.$message.success('报修成功，请等待实验员处理')
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
        this.$request.delete('/repair/delete/' + id).then(res => {
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
        this.$request.delete('/repair/delete/batch', {data: this.ids}).then(res => {
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
      this.$request.get('/repair/selectPage', {
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