'use client'
import { getStudents } from '@/api/student'
import { columns } from '@/components/studentColumn'
import { DataTable } from '@/components/studentDataTable'
import { StudentList } from '@/types'
import { useState } from 'react'

function Home() {
  const [students, setStudents] = useState<StudentList>([])
  async function getAllStudents() {
    try {
      const data = await getStudents()

      setStudents(data.data)
    } catch (error) {
      console.log(error)
    }
  }

  return (
    <>
      <button onClick={getAllStudents}>测试</button>
      <div className='container mx-auto py-10'>
        <DataTable columns={columns} data={students} />
      </div>
    </>
  )
}

export default Home
