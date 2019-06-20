package com.fx.shiroDemo;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * des:
 * 验证jdbcream
 * @author fxiao
 * @date 2019/4/11 14:06
 */
public class JdbcReamTest {
    private final Logger log= LoggerFactory.getLogger(this.getClass());
//    方法一的测试的测试数据在：classpath：testDbData/jdbcReamDemo1Init.sql
//    方法二的测试数据在：classpath：testDbData/jdbcReamDemo2Init.sql
    DruidDataSource dataSource=new DruidDataSource();

    {
        dataSource.setUsername("fxiao");
        dataSource.setUrl("jdbc:mysql://192.168.187.18:3306/fx_db1");
        dataSource.setPassword("123");
    }
    /**
     * 使用默认jdbcReam自带的sql
     * @Author Fxiao
     * @Description
     * @Date 9:07 2019/4/12
     * @param
     * @return void
     **/
    @Test
    public void demo1(){
        JdbcRealm jdbcRealm=new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);

        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("fxiao","1234");
        subject.login(token);
        log.info("验证通过？"+subject.isAuthenticated());
        //检查角色
        try{
            subject.checkRole("admin");
        }catch(AuthorizationException e){
            log.error("不具备该角色",e);
        }
        //验证权限
        try{
            //这里有个大坑，jdbcReam需要设置setPermissionsLookupEnabled(true)才可以查询到权限，默认为false
            subject.checkPermissions("test:user:delete","test:user:update","test:user:insert");
        }catch(AuthorizationException e){
            log.error("该用户没有指定权限",e);
        }
    }
    /**
     * 采用自定义的sql语句查询
     * @Author Fxiao
     * @Description
     * @Date 9:39 2019/4/12
     * @param
     * @return void
     **/
    @Test
    public void demo2(){
        JdbcRealm jdbcReam=new JdbcRealm();
        jdbcReam.setDataSource(dataSource);
        jdbcReam.setPermissionsLookupEnabled(true);
        String authenticationQuerySql = "select password from test_users where username=?";
        jdbcReam.setAuthenticationQuery(authenticationQuerySql);//查询密码的自定义sql
        String userRolesQuerySql="select role_name from test_user_roles where username=?";
        jdbcReam.setUserRolesQuery(userRolesQuerySql);//查询角色的自定义语句
        String permissionsQuerySql="select permission from test_roles_permissions where role_name=?";
        jdbcReam.setPermissionsQuery(permissionsQuerySql);//查询权限的自定义语句

        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcReam);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("dachui","123");
//        登陆
        subject.login(token);
//        认证查询
        log.info("isAuthenticated?:{}",subject.isAuthenticated());
//        权限查询
        try{
            subject.checkRoles("user","admin");
        }catch (AuthorizationException e){
            log.error("该用户缺少角色！",e);
        }
//        查询权限
        try{
            subject.checkPermission("test:user:update12");
        }catch(AuthorizationException e){
            log.error("该用户缺少权限！",e);
        }


    }

}
