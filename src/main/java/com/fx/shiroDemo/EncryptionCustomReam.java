package com.fx.shiroDemo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * des:
 *  对密码加密的自定义ream
 * @author fxiao
 * @date 2019/4/12 15:13
 */
public class EncryptionCustomReam extends AuthorizingRealm {
    Map<String,String> userMap =new HashMap();
    {
//        userMap.put("fxiao","202cb962ac59075b964b07152d234b70");//123 所对应的md5加密后的密文
        userMap.put("fxiao","e00230fd32b03a4d99aae2894afa6372");//123 所对应的加盐后的密文
        super.setName("customReam");
    }
    //用来做授权方面的支持
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName= (String) principals.getPrimaryPrincipal();
        //从数据库或缓存中取出角色和权限信息
        Set<String>roles=getRolesByUserName(userName);
        Set<String>permissions=getPermissionsByUserName(userName);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.setStringPermissions(permissions);
        info.setRoles(roles);
        return info;
    }

    private Set<String> getPermissionsByUserName(String userName) {
        Set<String>permissions=new HashSet<String>();
        permissions.add("test:user:insert");
        permissions.add("test:user:delete");
        permissions.add("test:user:update");
        return permissions;
    }

    private Set<String> getRolesByUserName(String userName) {
        Set<String>roles=new HashSet<String>();
        roles.add("admin");
        roles.add("user");
        return roles;
    }

    //认证方面的支持
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //通过主体传过来的信息中取得用户名
        String userName= (String) token.getPrincipal();
        String passpord=getPassByUsername(userName);
        if(passpord==null) return null;
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(userName,passpord,super.getName());
//        如果密码经过了加盐处理，在这步需要设置盐是什么
        info.setCredentialsSalt(ByteSource.Util.bytes("a1b2c3"));
        return info;
    }
    /**
     * 模拟到数据库中查得密码
     * @Author Fxiao
     * @Description
     * @Date 11:27 2019/4/12
     * @param userName 用户名
     * @return java.lang.String
     **/
    private String getPassByUsername(String userName) {
        return userMap.get(userName);
    }

}
