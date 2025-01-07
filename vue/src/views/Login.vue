<template>
  <div class="container">
    <!-- 登录框 -->
    <div style="width: 400px; padding: 30px; background-color: white; border-radius: 5px;">
      <!-- 标题 -->
      <div style="text-align: center; font-size: 20px; margin-bottom: 20px; color: #333">
        欢迎登录计算机工程学院实验室管理系统
      </div>
      <!-- 表单 -->
      <el-form :model="form" :rules="rules" ref="formRef">
        <!-- 用户名 -->
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user" placeholder="请输入账号" v-model="form.username"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" placeholder="请输入密码" show-password v-model="form.password"></el-input>
        </el-form-item>
        <!-- 角色选择 -->
        <el-form-item>
          <el-select v-model="form.role" placeholder="请选择角色" style="width: 100%">
            <el-option label="系统管理员" value="ADMIN" />
            <el-option label="实验员" value="LABADMIN" />
            <el-option label="教师" value="TEACHER" />
            <el-option label="学生" value="STUDENT" />
          </el-select>
        </el-form-item>
        <!-- 登录按钮 -->
        <el-form-item>
          <el-button
              style="width: 100%; background-color: #bf0351; border-color: #bf0358; color: white"
              @click="login"
          >
            登 录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>


<script>
export default {
  name: "Login",
  data() {
    return {
      form: { },
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ]
      }
    }
  },
  created() {

  },
  methods: {
    login() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          // 如果验证通过，发送登录请求
          this.$request.post('/login', this.form).then(res => {
            if (res.code === '200') {
              localStorage.setItem("xm-user", JSON.stringify(res.data))  // 存储用户数据
              this.$router.push('/')  // 跳转主页
              this.$message.success('登录成功')
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.container {
  height: 100vh;
  overflow: hidden;
  background-image: url("@/assets/imgs/bg.jpg");
  background-size: cover; /* 确保背景图片始终覆盖整个容器 */
  background-position: center; /* 确保图片居中显示 */
  background-repeat: no-repeat; /* 防止图片重复 */
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
}

a {
  color: #2a60c9;
}
</style>