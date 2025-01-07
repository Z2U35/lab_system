import axios from 'axios'
import router from "@/router"

// 创建一个新的 axios 实例
const request = axios.create({
    baseURL: process.env.VUE_APP_BASEURL,   // 设置后端的基础接口地址，通常从环境变量中获取，http://localhost:9090
    timeout: 30000                          // 设置超时时间为 30 秒
})

// 请求拦截器
// 作用：在请求发送到服务器之前进行一些统一的处理
request.interceptors.request.use(config => {
    // 设置请求头的内容类型为 JSON 格式
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    // 从 localStorage 中获取缓存的用户信息
    let user = JSON.parse(localStorage.getItem("xm-user") || '{}');

    // 如果用户已登录，设置 token 到请求头中
    config.headers['token'] = user.token;

    // 返回处理后的请求配置
    return config;
}, error => {
    // 如果请求在发送前出现错误，打印错误日志并拒绝请求
    console.error('request error: ' + error); // 用于调试
    return Promise.reject(error); // 返回 Promise.reject 终止请求
});

// 响应拦截器
// 作用：在服务器返回数据后，对响应数据进行统一处理
request.interceptors.response.use(
    response => {
        // 获取响应数据
        let res = response.data;

        // 兼容服务端返回的数据为字符串的情况，尝试将其解析为 JSON 格式
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res;
        }

        // 如果服务端返回的状态码为 '401'（未授权或登录过期）
        if (res.code === '401') {
            router.push('/login'); // 跳转到登录页面
        }

        // 返回处理后的响应数据
        return res;
    },
    error => {
        // 如果响应返回错误，打印错误日志
        console.error('response error: ' + error); // 用于调试
        return Promise.reject(error); // 返回 Promise.reject 以便调用方处理错误
    }
)

export default request; // 导出封装好的 axios 实例，供其他模块使用
