'use client'
import { getStudents } from '@/api/student'
import { StudentList } from '@/types'
import { useState } from 'react'

function Home() {
  const [students, setStudents] = useState<StudentList>([])
  async function getAllStudents() {
    try {
      const data = await getStudents()
      console.log(data)
      setStudents(data.data)
    } catch (error) {
      console.log(error)
    }
  }

  return (
    <>
      <button onClick={getAllStudents}>测试</button>
      <ul>
        {students.map((student, index) => (
          <li key={index}>{student.name}</li>
        ))}
      </ul>
    </>
  )
}

export default Home
