package com.homework2.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {
  private Integer fileId;
  private String name;
  private String path;
  private Double size;
  private Integer userId;
  private String type;
  private Instant createdTime;
  private Instant deletedTime;
  private Integer deleted;
}
