package com.aliyektan.project.core.entity;

/**
 * Created by yektan on 16.12.2017.
 */
public class Part {
    private int partId;
    private int partTypeId;
    private String description;

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getPartTypeId() {
        return partTypeId;
    }

    public void setPartTypeId(int partTypeId) {
        this.partTypeId = partTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
