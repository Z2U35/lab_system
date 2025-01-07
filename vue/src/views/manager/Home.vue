<template>
  <div>
    <div class="card" style="padding: 15px">
      您好，{{ user?.name }}！欢迎使用本系统

    </div>


    <div class="table">
      <div style="text-align: center" >
        实验室排课表
      </div>
      <el-table :data="tableData" border=true :span-method="objectSpanMethod">
        <el-table-column prop="week" label=" " width ="60"></el-table-column>
        <el-table-column prop="name" label="实验室" width ="60"></el-table-column>
        <el-table-column prop="number" label="机房" width ="60"></el-table-column>
        <el-table-column prop="t1" label="1-2"></el-table-column>
        <el-table-column prop="t2" label="3-5"></el-table-column>
        <el-table-column prop="t3" label="6-7"></el-table-column>
        <el-table-column prop="t4" label="8-9"></el-table-column>
        <el-table-column prop="t5" label="10-12"></el-table-column>
        <el-table-column prop="t6" label="13-15"></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Home',
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      tableData: []
    }
  },
  created() {
    this.$request.get('/course/selectAll').then(res => {
      this.tableData = res.data || []
    })
  }
}
</script>
}