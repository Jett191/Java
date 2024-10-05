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
  grade: number
}

export interface StudentsData {
  page: number
  pageSize: number
}

export interface StudentSearchParams {
  id?: string;
  name?: string;
  grade?: string;
  java?: string;
  android?: string;
  javaee?: string;
}
