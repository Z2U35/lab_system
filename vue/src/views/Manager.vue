<template>
  <div class="manager-container">
    <!-- 头部区域 -->
    <div class="manager-header">
      <!-- 左侧部分：Logo 和系统标题 -->
      <div class="manager-header-left">
        <img class="logo" src="@/assets/imgs/logo.png" alt="Logo" />
        <div class="title">实验室管理系统</div>
      </div>

      <!-- 中间部分：面包屑导航 -->
      <div class="manager-header-center">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <!-- 第一项固定为首页 -->
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <!-- 第二项根据当前路由动态更新 -->
          <el-breadcrumb-item :to="{ path: $route.path }">{{ $route.meta.name }}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>

      <!-- 右侧部分：用户信息及操作菜单 -->
      <div class="manager-header-right">
        <el-dropdown placement="bottom">
          <div class="avatar">
            <!-- 显示用户头像，若没有头像则使用默认图片 -->
            <img :src="user.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" />
            <!-- 显示用户名称，若未提供则显示“管理员” -->
            <div>{{ user.name || '管理员' }}</div>
          </div>
          <!-- 用户操作菜单 -->
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="goToPerson">个人信息</el-dropdown-item>
            <el-dropdown-item @click.native="$router.push('/password')">修改密码</el-dropdown-item>
            <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>

    <!-- 主体区域 -->
    <div class="manager-main">
      <!-- 左侧侧边栏 -->
      <div class="manager-main-left">
        <el-menu
            :default-openeds="['info', 'user']"
            router
            style="border: none"
            :default-active="$route.path"
        >
          <!-- 系统首页 -->
          <el-menu-item index="/home">
            <i class="el-icon-s-home"></i>
            <span slot="title">系统首页</span>
          </el-menu-item>

          <!-- 信息管理菜单 -->
          <el-submenu index="info">
            <template slot="title">
              <i class="el-icon-menu"></i><span>信息管理</span>
            </template>
            <!-- 根据角色动态显示不同的菜单项 -->
            <el-menu-item index="/semester" v-if="user.role === 'ADMIN'">学期信息</el-menu-item>
            <el-menu-item index="/lab" v-if="user.role === 'ADMIN'">实验室信息</el-menu-item>
            <el-menu-item index="/doBorrow" v-if="user.role === 'ADMIN'">实验室借用审批</el-menu-item>
            <el-menu-item index="/doSchedule" v-if="user.role === 'ADMIN'">实验室排课审批</el-menu-item>
            <el-menu-item index="/doRepair" v-if="user.role === 'LABADMIN'">设备报修处理</el-menu-item>
            <el-menu-item index="/schedule" v-if="user.role === 'TEACHER'">实验课申请登记</el-menu-item>
            <el-menu-item index="/repair" v-if="user.role === 'TEACHER'">实验室设备报修</el-menu-item>
            <el-menu-item index="/borrow" v-if="user.role === 'STUDENT'">实验室借用申请</el-menu-item>
          </el-submenu>

          <!-- 用户管理菜单（仅管理员可见） -->
          <el-submenu index="user" v-if="user.role === 'ADMIN'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>用户管理</span>
            </template>
            <el-menu-item index="/admin">管理员信息</el-menu-item>
            <el-menu-item index="/labAdmin">实验员信息</el-menu-item>
            <el-menu-item index="/teacher">教师信息</el-menu-item>
            <el-menu-item index="/student">学生信息</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>

      <!-- 右侧主内容区域 -->
      <div class="manager-main-right">
        <!-- 渲染当前路由对应的组件 -->
        <router-view @update:user="updateUser" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Manager", // 组件名称
  data() {
    return {
      // 从 localStorage 获取用户信息，若不存在则返回空对象
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
    };
  },
  created() {
    // 当组件被创建时检查用户是否登录
    if (!this.user.id) {
      // 若用户未登录，则跳转到登录页面
      this.$router.push('/login');
    }
  },
  methods: {
    // 更新用户信息
    updateUser() {
      // 从 localStorage 重新获取最新的用户数据
      this.user = JSON.parse(localStorage.getItem('xm-user') || '{}');
    },
    // 跳转到对应角色的个人信息页面
    goToPerson() {
      if (this.user.role === 'ADMIN') {
        this.$router.push('/adminPerson');
      } else if (this.user.role === 'LABADMIN') {
        this.$router.push('/labAdminPerson');
      } else if (this.user.role === 'TEACHER') {
        this.$router.push('/teacherPerson');
      } else if (this.user.role === 'STUDENT') {
        this.$router.push('/studentPerson');
      }
    },
    // 退出登录
    logout() {
      // 清除本地存储中的用户信息
      localStorage.removeItem('xm-user');
      // 跳转到登录页面
      this.$router.push('/login');
    },
  },
};
</script>

<style scoped>
@import "@/assets/css/manager.css"; /* 引入全局样式表 */

/* Logo 样式 */
.logo {
  max-width: 100%;
  height: auto;
  object-fit: contain;
}
</style>
