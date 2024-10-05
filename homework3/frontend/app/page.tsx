'use client'

import React, { useState, useEffect } from 'react'
import { getAllStudents, getStudent } from './api/services/examService'
import { StudentsData, Student } from '@/app/types/types'
import Image from 'next/image'
import SearchPanel from '../components/SearchPanel'

function Home() {
  const [students, setStudents] = useState<Student[]>([])
  const [currentPage, setCurrentPage] = useState(1)
  const [loading, setLoading] = useState(false)
  const [totalPages, setTotalPages] = useState(1)
  const [searchCriteria, setSearchCriteria] = useState({
    id: '',
    name: '',
    grade: '',
    java: '',
    android: '',
    javaee: ''
  })

  const fetchStudents = async (page: number) => {
    setLoading(true)
    try {
      const params: StudentsData = {
        page: page,
        pageSize: 10
      }
      const data = await getAllStudents(params)
      const studentsWithPhotoUrls = data.students.map((student: Student) => ({
        ...student,
        photoUrl: `data:image/jpeg;base64,${student.photo}`
      }))
      setStudents(studentsWithPhotoUrls)
      setCurrentPage(data.page)
      setTotalPages(10)
    } catch (error) {
      console.error('获取学生数据失败:', error)
    } finally {
      setLoading(false)
    }
  }

  const handleSearch = async () => {
    setLoading(true)
    try {
      const data = await getStudent(searchCriteria)
      const studentsWithPhotoUrls = data.students.map((student: Student) => ({
        ...student,
        photoUrl: `data:image/jpeg;base64,${student.photo}`
      }))
      setStudents(studentsWithPhotoUrls)
    } catch (error) {
      console.error('查询学生数据失败:', error)
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    fetchStudents(currentPage)
  }, [currentPage])

  const handlePrevPage = () => {
    if (currentPage > 1) {
      setCurrentPage((prev) => prev - 1)
    }
  }

  const handleNextPage = () => {
    if (currentPage < totalPages) {
      setCurrentPage((prev) => prev + 1)
    }
  }

  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target
    setSearchCriteria((prev) => ({ ...prev, [name]: value }))
  }

  return (
    <div className='container mx-auto h-screen max-w-5xl bg-white p-6 shadow-lg'>
      <h1 className='mb-12 mt-8 text-center text-3xl font-bold text-gray-800'>学生成绩查询</h1>

      <SearchPanel
        searchCriteria={searchCriteria}
        onSearchChange={handleSearchChange}
        onSearch={handleSearch}
      />

      {loading ? (
        <p className='text-center text-gray-600'>加载中...</p>
      ) : (
        <>
          <div className='overflow-x-auto'>
            <table className='w-full border-collapse text-sm'>
              <thead>
                <tr className='table-header bg-gray-100'>
                  <th className='p-2 text-left text-gray-700'>照片</th>
                  <th className='p-2 text-left text-gray-700'>ID</th>
                  <th className='p-2 text-left text-gray-700'>姓名</th>
                  <th className='p-2 text-left text-gray-700'>年龄</th>
                  <th className='p-2 text-left text-gray-700'>Java</th>
                  <th className='p-2 text-left text-gray-700'>Android</th>
                  <th className='p-2 text-left text-gray-700'>JavaEE</th>
                  <th className='p-2 text-left text-gray-700'>总分</th>
                </tr>
              </thead>

              <tbody>
                {students.map((student) => (
                  <tr key={student.id} className='table-row hover:bg-gray-50'>
                    <td className='p-2'>
                      {student.photoUrl && (
                        <Image
                          src={student.photoUrl}
                          alt={`${student.name}的照片`}
                          width={40}
                          height={40}
                          className='rounded-full'
                        />
                      )}
                    </td>
                    <td className='p-2'>{student.id}</td>
                    <td className='p-2'>{student.name}</td>
                    <td className='p-2'>{student.age}</td>
                    <td className='p-2'>{student.java}</td>
                    <td className='p-2'>{student.android}</td>
                    <td className='p-2'>{student.javaee}</td>
                    <td className='p-2'>{student.total}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
          <div className='mt-4 flex items-center justify-between'>
            <button
              onClick={handlePrevPage}
              disabled={currentPage === 1}
              className='btn bg-blue-500 text-white hover:bg-blue-600'>
              上一页
            </button>
            <span className='text-gray-600'>
              第 {currentPage} 页，共 {totalPages} 页
            </span>
            <button
              onClick={handleNextPage}
              disabled={currentPage === totalPages}
              className='btn bg-blue-500 text-white hover:bg-blue-600'>
              下一页
            </button>
          </div>
        </>
      )}
    </div>
  )
}

export default Home