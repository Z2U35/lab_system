<template>
  <div>

    <div class="operation">
      <span slot="reference" style="margin-left: auto;">当前学期：{{ curSemester.name }}</span>
    </div>

    <div class="operation">
      <el-button type="primary" plain @click="handleAdd">新增</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" strip @selection-change="handleSelectionChange">
        <el-table-column prop="id" label="序号" align="center" sortable></el-table-column>
        <el-table-column prop="name" label="开课学期" sortable></el-table-column>
        <el-table-column prop="weeks" label="周数"></el-table-column>
        <el-table-column label="操作" align="center" width="300">
          <template v-slot="scope">
            <el-button size="mini" type="primary" plain @click="handleEdit(scope.row)">编辑</el-button>
            <el-button v-if="scope.row.isCurSemester === 0" size="mini" type="danger" plain @click="del(scope.row.id)">删除</el-button>
            <el-button v-if="scope.row.isCurSemester === 0" size="mini" type="success" plain @click="updateCurSemester(scope.row)">设置为当前学期</el-button>
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


    <el-dialog title="学期" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="form" label-width="100px" style="padding-right: 50px" :rules="rules" ref="formRef">
        <el-form-item label="开课学期" prop="name">
          <el-input v-model="form.name" placeholder="开课学期"></el-input>
        </el-form-item>
        <el-form-item label="周数" prop="weeks">
          <el-input v-model="form.weeks" placeholder="周数"></el-input>
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
  name: "Semester",
  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      name: null,
      fromVisible: false,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      rules: {
        name: [
          {required: true, message: '请输入上课学期', trigger: 'blur'},
        ],
        weeks: [
          {required: true, message: '请输入周数', trigger: 'blur'},
        ],
      },
      ids: [],
      semesterData: [],
      curSemester: null
    }
  },
  created() {
    this.load(1)
    this.loadCurSemester()
  },
  methods: {
    loadCurSemester() {
      this.$request.get('/semester/selectCurSemester').then(res => {
        if (res.code === '200') {
          this.curSemester = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    updateCurSemester(row) {
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      this.$confirm('确定设置为当前学期吗？', '设置当前学期', {type: "warning"}).then(response => {
        this.$request.put('/semester/updateCurSemester', this.form).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
            this.loadCurSemester()
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    handleAdd() {   // 新增数据
      this.form = {}  // 新增数据的时候清空数据
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
            url: this.form.id ? '/semester/update' : '/semester/add',
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
        this.$request.delete('/semester/delete/' + id).then(res => {
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
    load(pageNum) {  // 分页查询
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/semester/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
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