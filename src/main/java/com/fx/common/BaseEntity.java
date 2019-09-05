package com.fx.common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

/**
 * des:
 *
 * @author fxiao
 * @date 2019/9/3 18:48
 */
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false,updatable = false,columnDefinition = "timestamp default current_timestamp")
    private Timestamp createTime;
    @Column(nullable = false,updatable = true,columnDefinition = "timestamp default current_timestamp")
    private Timestamp updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
