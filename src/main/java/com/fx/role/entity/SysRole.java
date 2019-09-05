package com.fx.role.entity;

import com.fx.common.BaseEntity;
import com.fx.permission.entity.SysPermission;
import com.fx.user.entity.User;

import javax.persistence.*;
import java.util.List;

/**
 * des:
 *
 * @author fxiao
 * @date 2019/9/3 19:05
 */
@Entity
public class SysRole extends BaseEntity {
    private String name;
    private String description;
    /**
     * 是否可用
     */
    private Boolean available =Boolean.FALSE;
    @ManyToMany
    @JoinTable(
            name = "SysUserRole",
            joinColumns = {@JoinColumn(name="roleId")},
            inverseJoinColumns = @JoinColumn(name="uId")
    )
    private List<User> userList;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "SysRolePermission",
            joinColumns = {@JoinColumn(name = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "permissionId")}
    )
    private List<SysPermission>permissionList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<SysPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<SysPermission> permissionList) {
        this.permissionList = permissionList;
    }
}
