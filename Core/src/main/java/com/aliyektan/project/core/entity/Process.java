package com.aliyektan.project.core.entity;

import java.util.Date;

/**
 * Created by yektan on 16.12.2017.
 */
public class Process {
    private int processId;
    private String description;
    private Date processDate;
    private Date nextProcess;
    private int productId;
    private int processTypeId;
    private int partId;

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public Date getNextProcess() {
        return nextProcess;
    }

    public void setNextProcess(Date nextProcess) {
        this.nextProcess = nextProcess;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProcessTypeId() {
        return processTypeId;
    }

    public void setProcessTypeId(int processTypeId) {
        this.processTypeId = processTypeId;
    }
}
