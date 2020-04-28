package com.bonc.upms.entity;

import java.io.Serializable;

/**
 * 资源表(SysResource)实体类
 *
 * @author makejava
 * @since 2020-04-21 21:48:31
 */
public class SysResource implements Serializable {
    private static final long serialVersionUID = 354706089926607710L;
    /**
    * id
    */
    private Integer resourceId;
    /**
    * 资源名称
    */
    private String name;
    /**
    * 资源链接
    */
    private String url;
    /**
    * 资源分类id
    */
    private Integer categoryId;
    /**
    * 描述
    */
    private String description;


    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}