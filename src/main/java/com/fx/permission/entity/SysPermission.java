package com.fx.permission.entity;

import com.fx.common.BaseEntity;
import com.fx.role.entity.SysRole;

import javax.persistence.*;
import java.util.List;

/**
 * des:
 *
 * @author fxiao
 * @date 2019/9/3 19:18
 */
@Entity
public class SysPermission extends BaseEntity {
    private String name;
    /**
     * 资源类型（menu or button)
     */
    @Column(columnDefinition = "enum('menu','button')" )
    private String resourceType;
    /**
     * 资源路径
     */
    private String url;
    /**
     * 权限字符串
     */
    private String code;
    /**
     * 父编号
     */
    private int parentId;
    /**
     * 父编号列表
     */
    private String parentIds;
    private Boolean available=Boolean.FALSE;
    @ManyToMany
    @JoinTable(
            name = "SysRolePermission",
            joinColumns = {@JoinColumn(name = "permissionId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")}
    )
    private List<SysRole>roleList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }
}
