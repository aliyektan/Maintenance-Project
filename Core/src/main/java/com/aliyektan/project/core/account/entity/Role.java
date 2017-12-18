package com.aliyektan.project.core.account.entity;

/**
 * Created by yektan on 16.12.2017.
 */
public class Role {
    private int roleId;
    private String description;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
