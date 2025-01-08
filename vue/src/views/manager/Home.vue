<template>
  <div>
    <div class="card" style="padding: 15px">
      您好，{{ user?.name }}！欢迎使用本系统
    </div>

    <div class="table">
      <div style="text-align: center">
        实验室排课表
      </div>
      <el-table :data="tableData" border>
        <el-table-column prop="week" label="星期" min-width="80" show-overflow-tooltip align="center"></el-table-column>
        <el-table-column prop="name" label="实验室" min-width="120" show-overflow-tooltip align="center"></el-table-column>
        <el-table-column prop="number" label="机房" min-width="120" show-overflow-tooltip align="center"></el-table-column>
        <el-table-column prop="t1" label="1-2节" min-width="120" show-overflow-tooltip align="center"></el-table-column>
        <el-table-column prop="t2" label="3-5节" min-width="120" show-overflow-tooltip align="center"></el-table-column>
        <el-table-column prop="t3" label="6-7节" min-width="120" show-overflow-tooltip align="center"></el-table-column>
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
