package com.cto.freemarker.entity.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 树节点
 * @author Zhang Yongwei
 */
public class TreeNode implements Serializable {
    
    private String id;
    private Long dataId;
    private String name;
    private String url;
    private String state;
    private boolean checked;
    private Long parentId;
    private Map<String,Object> attributes = new HashMap<String, Object>();
    private List<TreeNode> children;
    private String description;
    
    private String icon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

/**
     * @param dataId the dataId to set
     */
    /**
     * @return the text
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * @return the state
     */
    public String getState() {
        return state;
    }
    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }
    /**
     * @return the checked
     */
    public boolean isChecked() {
        return checked;
    }
    /**
     * @param checked the checked to set
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    /**
     * @return the parentId
     */
    public Long getParentId() {
        return parentId;
    }
    /**
     * @param parentId the parentId to set
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    /**
     * @return the attributes
     */
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    /**
     * @param attributes the attributes to set
     */
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    /**
     * @return the children
     */
    public List<TreeNode> getChildren() {
        return children;
    }
    /**
     * @param children the children to set
     */
    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }
    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
