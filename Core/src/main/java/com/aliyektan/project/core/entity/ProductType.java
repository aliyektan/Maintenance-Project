package com.aliyektan.project.core.entity;

/**
 * Created by yektan on 16.12.2017.
 */
public class ProductType {
    private int productTypeId;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }
}
