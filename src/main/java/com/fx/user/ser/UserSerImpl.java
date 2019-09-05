package com.fx.user.ser;

import com.fx.user.dao.UserDao;
import com.fx.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * des:
 *
 * @author fxiao
 * @date 2019/9/5 18:31
 */
@Service
public class UserSerImpl implements UserSer {
    @Autowired
    private UserDao dao;
    @Override
    public User findByAccount(String account) {
        return dao.findByAccount(account);
    }
}
