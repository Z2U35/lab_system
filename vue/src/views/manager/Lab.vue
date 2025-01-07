<template>
  <div>
    <div class="search">
      <el-input placeholder="请输入实验室编号" style="width: 200px" v-model="number"></el-input>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <div class="operation">
      <el-button type="primary" plain @click="handleAdd">新增</el-button>
      <el-button type="danger" plain @click="delBatch">批量删除</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" strip @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="id" label="序号" width="70" align="center" sortable></el-table-column>
        <el-table-column prop="number" label="编号"></el-table-column>
        <el-table-column prop="name" label="名称"></el-table-column>
        <el-table-column prop="type" label="类型" :filters="headFilters['type']"
                         :filter-method="filterHandler"></el-table-column>
        <el-table-column prop="equipmentCount" label="设备数"></el-table-column>
        <el-table-column prop="labadminName" label="实验员" :filters="headFilters['labadminName']"
                         :filter-method="filterHandler"></el-table-column>
        <el-table-column label="操作" align="center" width="180">
          <template v-slot="scope">
            <el-button size="mini" type="primary" plain @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" plain @click="del(scope.row.id)">删除</el-button>
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


    <el-dialog title="实验室" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="form" label-width="100px" style="padding-right: 50px" :rules="rules" ref="formRef">
        <el-form-item label="编号" prop="number">
          <el-input v-model="form.number" placeholder="编号"></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="名称"></el-input>
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" placeholder="类型" style="width: 100%">
            <el-option  label="软件" value="软件"></el-option>
            <el-option  label="硬件" value="硬件"></el-option>
            <el-option  label="网络" value="网络"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="设备数" prop="equipmentCount">
          <el-input v-model="form.equipmentCount" placeholder="设备数"></el-input>
        </el-form-item>
        <el-form-item label="实验员" prop="labadminId">
          <el-select v-model="form.labadminId" placeholder="请选择实验员" style="width: 100%">
            <el-option  v-for="item in labadminData" :label="item.name" :value="item.id"></el-option>
          </el-select>
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
  name: "Lab",
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
        number: [
          {required: true, message: '请输入实验室编号', trigger: 'blur'},
        ],
        name: [
          {required: true, message: '请输入实验室名称', trigger: 'blur'},
        ],
        type: [
          {required: true, message: '请输入实验室类型', trigger: 'blur'},
        ],
        equipmentCount: [
          {required: true, message: '请输入设备数', trigger: 'blur'},
        ],
        labadminId: [
          {required: true, message: '请选择实验室管理员', trigger: 'blur'},
        ],
      },
      ids: [],
      labadminData: [],
      list: [],
      headFilters: []
    }
  },
  created() {
    this.load(1)
    this.loadLabadmin()
    this.getList()
  },
  methods: {
    getList() {
      this.$request.get('/lab/selectAll').then(res => {
        if (res.code === '200') {
          this.list = res.data
          this.tableFilter(this.list)
          console.log(this.headFilters)
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
    loadLabadmin() {
      this.$request.get('/labAdmin/selectAll').then(res => {
        if (res.code === '200') {
          this.labadminData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleAdd() {   // 新增数据
      this.form = {}  // 新增数据的时候清空数据
      this.fromVisible = true   // 打开弹窗
    },
    handleEdit(row) {   // 编辑数据
      console.log(row.id)
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      this.fromVisible = true   // 打开弹窗
    },
    save() {   // 保存按钮触发的逻辑  它会触发新增或者更新
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request({
            url: this.form.id ? '/lab/update' : '/lab/add',
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
        this.$request.delete('/lab/delete/' + id).then(res => {
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
      this.$request.get('/lab/selectPage', {
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
    reset() {
      this.number = null
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