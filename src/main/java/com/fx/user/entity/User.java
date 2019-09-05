package com.fx.user.entity;

import com.fx.common.BaseEntity;
import com.fx.role.entity.SysRole;

import javax.persistence.*;
import java.util.List;

/**
 * des:
 *
 * @author fxiao
 * @date 2019/9/3 18:56
 */
@Entity
public class User extends BaseEntity {
    @Column(unique = true)
    private String account;
    private String name;
    private String password;
    private String salt;
    /**
     * 用户状态，0：创建未认证，等待验证（比如没有激活，没有输入验证码等等）；1：正常状态；2：用户被锁定。
     */
    private byte state;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="SysUserRole",
            joinColumns = {@JoinColumn(name="uId")},
            inverseJoinColumns = {@JoinColumn(name="roleId")}
    )
    private List<SysRole> roleList;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }
}
