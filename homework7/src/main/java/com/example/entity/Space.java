package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer spaceId;
    private Integer userId;
    private String spaceName;
    private Long spaceSize;
    private Long usedSpace;


    public Long getUsedSpace() {
        return usedSpace;
    }

    public void setUsedSpace(Long usedSpace) {
        this.usedSpace = usedSpace;
    }

    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public Long getSpaceSize() {
        return spaceSize;
    }

    public void setSpaceSize(Long spaceSize) {
        this.spaceSize = spaceSize;
    }
}
