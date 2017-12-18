package com.aliyektan.project.core.entity;

/**
 * Created by yektan on 16.12.2017.
 */
public class ProcessType {
    private int processTypeId;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProcessTypeId() {
        return processTypeId;
    }

    public void setProcessTypeId(int processTypeId) {
        this.processTypeId = processTypeId;
    }
}
