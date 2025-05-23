import { StudentList } from '@/types'
import { request } from '@/utils/axios'

export function getStudents() {
  return request<StudentList>({ url: '/students', method: 'GET' })
}
