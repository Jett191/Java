export interface Student {
  id: number
  age: number
  java: number
  android: number
  javaee: number
  total: number
  name: string
  photo: string
  photoUrl?: string // 添加这一行
}

export interface StudentsData {
  page: number
  pageSize: number
}
