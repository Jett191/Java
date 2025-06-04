import { z } from 'zod'

const StudentInfoSchema = z.object({
  id: z.number().optional(),
  name: z.string(),
  age: z.number(),
  sex: z.string(),
  score: z.string(),
  deleted: z.number().optional()
})
export type StudentInfo = z.infer<typeof StudentInfoSchema>

export const StudentListSchema = z.array(StudentInfoSchema)
export type StudentList = z.infer<typeof StudentListSchema>
