package com.homework2.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoResponse {
    String name;
    String userName;
    Double size;
    String type;
    Instant createdTime;
    Integer deleted;
}
