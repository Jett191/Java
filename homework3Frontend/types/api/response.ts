/* eslint-disable @typescript-eslint/no-unused-vars */

import { z } from 'zod'
import * as Student from '../model/student'

const ResponseDataSchema = <T extends z.ZodTypeAny>(dataSchema: T) =>
  z.object({
    code: z.number(),
    msg: z.string(),
    data: dataSchema
  })

const StudentListResponseSchema = ResponseDataSchema(Student.StudentListSchema)

export type StudentListResponse = z.infer<typeof StudentListResponseSchema>
