package com.homework2.entity;


import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {

  private Integer fileId;
  private String name;
  private String path;
  private Integer size;
  private Integer userId;
  private String type;
  private Instant createdTime;
  private Instant deletedTime;
  private Integer deleted;

}
