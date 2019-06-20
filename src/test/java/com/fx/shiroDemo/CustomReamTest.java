package com.fx.shiroDemo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * des:
 *
 * @author fxiao
 * @date 2019/4/12 11:38
 */
public class CustomReamTest {
    private final Logger log=LoggerFactory.getLogger(this.getClass());
    @Test
    public void demo1(){
        CustomReam customReam=new CustomReam();
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customReam);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("fxiao","777");
        subject.login(token);
        log.info("用户认证通过？：{}",subject.isAuthenticated());
        try{
            subject.checkRoles("user","admin","gorji");
        }catch(AuthorizationException e){
            log.error("该用户缺少角色！",e);
        }
        try{
            subject.checkPermissions("test:user:insert","test:user:update","test:user:view");
        }catch (AuthorizationException e){
            log.error("该用户缺少权限！",e);
        }

    }
}
