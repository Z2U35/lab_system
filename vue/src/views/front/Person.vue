<template>
  <div class="main-content">
    <!-- 用户信息卡片 -->
    <el-card style="width: 50%; margin: 30px auto">
      <!-- 修改密码按钮 -->
      <div style="text-align: right; margin-bottom: 20px">
        <el-button type="primary" @click="updatePassword">修改密码</el-button>
      </div>

      <!-- 用户信息表单 -->
      <el-form :model="user" label-width="80px" style="padding-right: 20px">
        <!-- 上传头像 -->
        <div style="margin: 15px; text-align: center">
          <el-upload
              class="avatar-uploader"
              :action="$baseUrl + '/files/upload'"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
          >
            <!-- 显示用户头像 -->
            <img v-if="user.avatar" :src="user.avatar" class="avatar" />
            <!-- 如果没有头像，则显示上传图标 -->
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>

        </div>

        <!-- 用户名 (不可编辑) -->
        <el-form-item label="用户名" prop="username">
          <el-input v-model="user.username" placeholder="用户名" disabled></el-input>
        </el-form-item>

        <!-- 姓名 -->
        <el-form-item label="姓名" prop="name">
          <el-input v-model="user.name" placeholder="姓名"></el-input>
        </el-form-item>

        <!-- 电话 -->
        <el-form-item label="电话" prop="phone">
          <el-input v-model="user.phone" placeholder="电话"></el-input>
        </el-form-item>

        <!-- 邮箱 -->
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="user.email" placeholder="邮箱"></el-input>
        </el-form-item>

        <!-- 保存按钮 -->
        <div style="text-align: center; margin-bottom: 20px">
          <el-button type="primary" @click="update">保 存</el-button>
        </div>
      </el-form>
    </el-card>

    <!-- 修改密码弹窗 -->
    <el-dialog title="修改密码" :visible.sync="dialogVisible" width="30%" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="user" label-width="80px" style="padding-right: 20px" :rules="rules" ref="formRef">
        <!-- 原始密码 -->
        <el-form-item label="原始密码" prop="password">
          <el-input show-password v-model="user.password" placeholder="原始密码"></el-input>
        </el-form-item>

        <!-- 新密码 -->
        <el-form-item label="新密码" prop="newPassword">
          <el-input show-password v-model="user.newPassword" placeholder="新密码"></el-input>
        </el-form-item>

        <!-- 确认密码 -->
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input show-password v-model="user.confirmPassword" placeholder="确认密码"></el-input>
        </el-form-item>
      </el-form>

      <!-- 弹窗底部操作按钮 -->
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>


<script>
export default {
  data() {
    // 自定义密码验证规则
    const validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请确认密码'));  // 确认密码不能为空
      } else if (value !== this.user.newPassword) {
        callback(new Error('确认密码错误'));  // 确认密码需与新密码一致
      } else {
        callback(); // 验证通过
      }
    };

    return {
      // 当前用户信息，从 localStorage 加载
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),

      // 控制修改密码弹窗的显示
      dialogVisible: false,

      // 表单验证规则
      rules: {
        password: [
          { required: true, message: '请输入原始密码', trigger: 'blur' },
        ],
        newPassword: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
        ],
        confirmPassword: [
          { validator: validatePassword, required: true, trigger: 'blur' },
        ],
      }
    };
  },
  methods: {
    // 保存用户信息
    update() {
      this.$request.put('/admin/update', this.user).then(res => {
        if (res.code === '200') {
          this.$message.success('保存成功');  // 提示保存成功
          localStorage.setItem('xm-user', JSON.stringify(this.user)); // 更新缓存
          this.$emit('update:user');  // 通知父组件刷新用户信息
        } else {
          this.$message.error(res.msg);  // 显示错误信息
        }
      });
    },

    // 上传头像成功后更新用户头像
    handleAvatarSuccess(response, file, fileList) {
      this.$set(this.user, 'avatar', response.data);  // 更新头像地址
    },

    // 打开修改密码弹窗
    updatePassword() {
      this.dialogVisible = true;
    },

    // 提交修改密码表单
    save() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request.put('/updatePassword', this.user).then(res => {
            if (res.code === '200') {
              this.$message.success('修改密码成功');  // 提示修改成功
              this.$router.push('/login');  // 重定向到登录页面
            } else {
              this.$message.error(res.msg);  // 显示错误信息
            }
          });
        }
      });
    }
  }
};
</script>


<style scoped>
/deep/.el-form-item__label {
  font-weight: bold;
}
/deep/.el-upload {
  border-radius: 50%;
}
/deep/.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  border-radius: 50%;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
  border-radius: 50%;
}
.avatar {
  width: 120px;
  height: 120px;
  display: block;
  border-radius: 50%;
}
</style>
