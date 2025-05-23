import { ResponseData } from '@/types'
import axios, { AxiosRequestConfig } from 'axios'

const instance = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_BASE,
  timeout: 5000
})

instance.interceptors.response.use(
  (res) => {
    const data = res.data
    if (data.code !== 0) {
      return Promise.reject(new Error(data.msg || '请求失败'))
    }
    return data
  },
  (error) => Promise.reject(error)
)

export function request<T>(config: AxiosRequestConfig): Promise<ResponseData<T>> {
  return instance(config)
}
