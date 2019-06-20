package com.fx.shiroDemo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * TODO:
 *
 * @author fxiao
 * @date 2019/4/9 16:09
 */
public class SimpleAccountRealmTest {
    //realm:领域，范围
    SimpleAccountRealm simpleAccountRealm=new SimpleAccountRealm();
    Subject subject=null;
    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("fxiao","123","admin","user");
        //创建SecurityManager环境
        //security:安全性
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        //获得主体
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        subject= SecurityUtils.getSubject();
    }
    /**
     * 认证功能
     * @Author Fxiao
     * @Description
     * @Date 9:59 2019/4/11
     * @param
     * @return void
     **/
    @Test
    public void testAuthentication(){
        UsernamePasswordToken token=new UsernamePasswordToken("fxiao","123");
        subject.login(token);
        //Authenticated:认证的
        System.out.println("是否已认证："+subject.isAuthenticated());
//        注销的方法
        subject.logout();
        System.out.println("isAuthenticated:"+subject.isAuthenticated());
    }
    /**
     * 检查是否具有特定权限
     * @Author Fxiao
     * @Description
     * @Date 10:04 2019/4/11
     * @param
     * @return void
     **/
    @Test
    public void test2(){
        System.out.println("检查是否具有特定权限");
        UsernamePasswordToken token=new UsernamePasswordToken("fxiao","123");
        subject.login(token);
//        如果没有此权限，则抛出org.apache.shiro.authz.UnauthorizedException
        subject.checkRole("admin");
        subject.checkRoles("admin","user","user1");
    }
}