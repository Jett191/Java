import axiosInstance from '@/app/api/client/axiosInstance'
import { StudentsData } from '@/app/api/types/types'

export const getStudents = async (params: StudentsData) => {
  try {
    const response = await axiosInstance.get('/students', { params })
    return {
      students: response.data.students,
      page: response.data.currentPage,
      pageSize: response.data.pageSize
    }
  } catch (error) {
    console.error('获取学生信息失败:', error)
    throw error
  }
}

// // 分页获取考试信息
// export const getExams = async (params: PaginationParams) => {
//   try {
//     return await axiosInstance.get('/students', { params })
//   } catch (error) {
//     console.error('获取考试信息失败:', error)
//     throw error
//   }
// }
//
// // 添加考试信息
// export const addExam = async (exam: Omit<Exam, 'id'>) => {
//   try {
//     return await axiosInstance.post('/students', exam)
//   } catch (error) {
//     console.error('添加考试信息失败:', error)
//     throw error
//   }
// }
//
// // 删除考试信息
// export const deleteExam = async (examId: number) => {
//   try {
//     return await axiosInstance.delete(`/students/${examId}`)
//   } catch (error) {
//     console.error('删除考试信息失败:', error)
//     throw error
//   }
// }
