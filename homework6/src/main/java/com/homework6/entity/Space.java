package com.homework6.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Space {
    private Integer id;
    private Integer userId;
    private Long totalSpace;
    private Long usedSpace;
    private Date lastUpdated;
}
