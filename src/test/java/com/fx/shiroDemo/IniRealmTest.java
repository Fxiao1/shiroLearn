package com.fx.shiroDemo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fxiao
 * @date 2019/4/11 10:11
 */
public class IniRealmTest {
    private final Logger log= LoggerFactory.getLogger(this.getClass());
    /**
     * 认证测试
     * @Author Fxiao
     * @Description
     * @Date 10:22 2019/4/11
     * @param
     * @return void
     **/
    @Test
    public void Test1(){
//        user.ini内部的格式为:
//          标签“[users]”;标签内部为用户：用户名=密码，角色…
//          标签“[roles]”;标签内部为权限：角色名=权限标识符;权限表示符的写法规范为：模块：资源：动作
        IniRealm iniRealm=new IniRealm("classpath:user.ini");
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken markToken=new UsernamePasswordToken("Mark","123");
        try{
            subject.login(markToken);
        }catch(UnknownAccountException e){
            log.error("Mark用户登陆失败",e);
        }
        log.info("Mark用户是否已认证？："+subject.isAuthenticated());
        UsernamePasswordToken fxiaoToken=new UsernamePasswordToken("fxiao","456");
        try{
            subject.login(fxiaoToken);
        }catch(UnknownAccountException e){
            log.error("fxiao用户登陆失败",e);
        }catch (IncorrectCredentialsException e){
            log.error("fxiao用户登陆失败",e);
        }
        log.info("fxiao用户是否已认证？"+subject.isAuthenticated());
        //检查角色
        try{
            subject.checkRole("admin");
        }catch (AuthorizationException e){
            log.error("fxiao用户没有“admin”角色",e);
        }
        //检查权限
        try{
            subject.checkPermissions("test:user:delete","test:user:update");
        }catch ( AuthorizationException e){
            log.error("fxiao用户缺少必要权限",e);
        }
    }
}
