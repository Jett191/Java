export interface StudentInfo {
  id?: number
  name: string
  age: number
  sex: string
  score: string
  deleted?: number
}

export type StudentList = StudentInfo[]
