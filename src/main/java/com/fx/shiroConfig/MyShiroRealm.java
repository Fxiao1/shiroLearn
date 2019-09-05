package com.fx.shiroConfig;

import com.fx.user.entity.User;
import com.fx.user.ser.UserSer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * des:
 *  权限配置
 * @author fxiao
 * @date 2019/9/5 18:22
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserSer userSer;

    /**
     * 告诉shiro 当前用户拥有哪些权限和角色
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        User user=(User)principals.getPrimaryPrincipal();
        user.getRoleList().forEach(role->{
            authorizationInfo.addRole(role.getName());
            role.getPermissionList().forEach(sysPermission -> {
                authorizationInfo.addStringPermission(sysPermission.getCode());
            });
        });
        return authorizationInfo;
    }

    /**
     * 主要用来进行身份验证，也就是说验证用户输入的用户名和密码是否正确
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /**
         * 本方法思路
         * 1，通过用户名获取到用户对象
         * 2，将数据库查询到的用户密码交给shiro的Authentication信息认证对象
         * 3，一些附属操作，比如清除之前的授权信息、将当前用户放入session中
         * 4，返回当前的认证信息对象
         */

        //获取到用户输入的账号
        String account=(String)token.getPrincipal();
        //获取用户的密码
        User user=userSer.findByAccount(account);
        if(user==null){
            return null;
        }
        //进行认证，将正确数据给shiro处理
        //密码不用自己对比，Authenticationinfo认证信息对象，一个接口，new他的实现类对象SimpleAuthenticationInfo

        /**
         * 第一个参数随便放，可以放user对象，程序可在任意位置获取放入的对象
         * 第二个参数必须放密码
         * 第三个参数放当前realm的名字，因为可能有多个realm
         */
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
                user.getName(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                super.getName()
        );
        //清除之前的授权信息
        super.clearCachedAuthenticationInfo(authenticationInfo.getPrincipals());
        //存入用户对象
        SecurityUtils.getSubject().getSession().setAttribute("login",user);
        //返回给安全管理器 securityManager,由securityManager比对数据库查询出的密码和页面提交的密码
        //如有问题，向上抛异常，一直抛到控制器
        return authenticationInfo;
    }
}
