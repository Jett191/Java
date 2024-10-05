import axios from 'axios'

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: 3000
})

// // 请求拦截器
// axiosInstance.interceptors.request.use(
//   (config) => {
//     // 添加认证 token 到请求头
//     const token = localStorage.getItem('token')
//     if (token) {
//       config.headers.Authorization = `Bearer ${token}`
//     }
//     return config
//   },
//   (error) => {
//     return Promise.reject(error)
//   }
// )
//
// // 响应拦截器
// axiosInstance.interceptors.response.use(
//   (response) => response.data,
//   (error) => {
//     console.error('请求出错:', error)
//     return Promise.reject(error)
//   }
// )

export default axiosInstance
