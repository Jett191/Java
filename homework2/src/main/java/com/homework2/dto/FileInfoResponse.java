package com.homework2.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoResponse {
    Integer fileId;
    String name;
    String userName;
    Double size;
    String type;
    Integer count;
    Instant createdTime;
    Integer deleted;
}
