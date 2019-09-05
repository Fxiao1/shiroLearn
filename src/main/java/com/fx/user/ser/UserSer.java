package com.fx.user.ser;

import com.fx.user.entity.User;

/**
 * des:
 *
 * @author fxiao
 * @date 2019/9/5 18:29
 */
public interface UserSer {
    public User findByAccount(String account);
}
