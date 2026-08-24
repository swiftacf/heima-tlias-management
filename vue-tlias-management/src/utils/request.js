import axios from 'axios'
import { ElMessage } from 'element-plus'

//创建axios实例对象
const request = axios.create({
  baseURL: '/api',
  timeout: 600000
})

//请求拦截器：每次请求自动从localStorage中取出token，添加到请求头中
request.interceptors.request.use(
  config => {
    const loginUserStr = localStorage.getItem('loginUser')
    if (loginUserStr) {
      const loginUser = JSON.parse(loginUserStr)
      if (loginUser.token) {
        config.headers.token = loginUser.token
      }
    }
    return config
  }
)

//响应拦截器：统一处理响应结果
request.interceptors.response.use(
  response => {
    //业务成功：后端约定code=1为成功，0为失败
    if (response.data.code === 1) {
      return response.data
    }
    //业务失败：提示错误并跳转登录页
    ElMessage.error(response.data.msg || '接口访问异常')
    import('@/router').then(m => m.default.push('/login'))
    return Promise.reject(response.data)
  },
  error => {
    //HTTP错误：401未授权跳转登录，其他提示网络异常
    if (error.response && error.response.status === 401) {
      ElMessage.error('登录已过期，请重新登录')
      localStorage.removeItem('loginUser')
      import('@/router').then(m => m.default.push('/login'))
    } else {
      ElMessage.error('网络异常，请稍后重试')
    }
    return Promise.reject(error)
  }
)

export default request
