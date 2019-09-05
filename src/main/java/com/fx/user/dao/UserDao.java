package com.fx.user.dao;

import com.fx.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * des:
 *
 * @author fxiao
 * @date 2019/9/5 19:31
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    public User findByAccount(String account);
}
