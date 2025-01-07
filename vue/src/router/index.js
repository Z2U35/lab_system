import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

// 解决 Vue-Router 3.0+ 版本中导航菜单频繁点击时报错的问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err) // 捕获错误并返回，避免 Uncaught 错误
}

// 定义路由规则
const routes = [
  {
    // 根路径
    path: '/',
        name: 'Manager',
    component: () => import('../views/Manager.vue'), // 懒加载 Manager 主组件
    redirect: '/home', // 默认重定向到系统首页
    children: [
      // 子路由定义
      { path: '403', name: 'NoAuth', meta: { name: '无权限' }, component: () => import('../views/manager/403') },
      { path: 'home', name: 'Home', meta: { name: '系统首页' }, component: () => import('../views/manager/Home') },
      { path: 'admin', name: 'Admin', meta: { name: '管理员信息' }, component: () => import('../views/manager/Admin') },
      { path: 'adminPerson', name: 'AdminPerson', meta: { name: '个人信息' }, component: () => import('../views/manager/AdminPerson') },
      { path: 'password', name: 'Password', meta: { name: '修改密码' }, component: () => import('../views/manager/Password') },
      { path: 'labAdmin', name: 'LabAdmin', meta: { name: '实验员信息' }, component: () => import('../views/manager/LabAdmin') },
      { path: 'teacher', name: 'Teacher', meta: { name: '教师信息' }, component: () => import('../views/manager/Teacher') },
      { path: 'student', name: 'Student', meta: { name: '学生信息' }, component: () => import('../views/manager/Student') },
      { path: 'labAdminPerson', name: 'LabAdminPerson', meta: { name: '个人信息' }, component: () => import('../views/manager/LabAdminPerson') },
      { path: 'teacherPerson', name: 'TeacherPerson', meta: { name: '个人信息' }, component: () => import('../views/manager/TeacherPerson') },
      { path: 'studentPerson', name: 'StudentPerson', meta: { name: '个人信息' }, component: () => import('../views/manager/StudentPerson') },
      { path: 'semester', name: 'Semester', meta: { name: '学期信息' }, component: () => import('../views/manager/Semester') },
      { path: 'lab', name: 'Lab', meta: { name: '实验室信息' }, component: () => import('../views/manager/Lab') },
      { path: 'schedule', name: 'Schedule', meta: { name: '实验课申请登记' }, component: () => import('../views/manager/Schedule') },
      { path: 'repair', name: 'Repair', meta: { name: '实验室设备报修' }, component: () => import('../views/manager/Repair.vue') },
      { path: 'borrow', name: 'Borrow', meta: { name: '实验室借用申请' }, component: () => import('../views/manager/Borrow') },
      { path: 'doBorrow', name: 'DoBorrow', meta: { name: '实验室借用审批' }, component: () => import('../views/manager/DoBorrow') },
      { path: 'doSchedule', name: 'DoSchedule', meta: { name: '实验室排课审批' }, component: () => import('../views/manager/DoSchedule') },
      { path: 'doRepair', name: 'DoRepair', meta: { name: '设备报修处理' }, component: () => import('../views/manager/DoRepair') }
    ]
  },
  {
    // 前台路由
    path: '/front',
    name: 'Front',
    component: () => import('../views/Front.vue'),
    children: [
      { path: 'home', name: 'Home', meta: { name: '系统首页' }, component: () => import('../views/front/Home') },
      { path: 'person', name: 'Person', meta: { name: '个人信息' }, component: () => import('../views/front/Person') }
    ]
  },
  {
    // 登录页
    path: '/login',
    name: 'Login',
    meta: { name: '登录' },
    component: () => import('../views/Login.vue')
  },
  {
    // 404 页面
    path: '*',
    name: 'NotFound',
    meta: { name: '无法访问' },
    component: () => import('../views/404.vue')
  }
]

// 创建路由实例
const router = new VueRouter({
  mode: 'history', // 使用 HTML5 History 模式
  base: process.env.BASE_URL, // 基础路径（默认取自环境变量）
  routes // 路由表
})

// 路由守卫示例（已注释）
// 该守卫根据用户角色跳转到前台或后台页面
// router.beforeEach((to, from, next) => {
//   let user = JSON.parse(localStorage.getItem("xm-user") || '{}'); // 从本地存储获取用户信息
//   if (to.path === '/') { // 根路径时的处理逻辑
//     if (user.role) {
//       if (user.role === 'USER') { // 普通用户跳转前台
//         next('/front/home');
//       } else { // 其他角色跳转后台
//         next('/home');
//       }
//     } else {
//       next('/login'); // 无角色时跳转到登录页面
//     }
//   } else {
//     next(); // 正常放行
//   }
// })

export default router
