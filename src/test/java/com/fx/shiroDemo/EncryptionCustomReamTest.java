package com.fx.shiroDemo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * des:
 *  对密码加密的自定义ream 的测试类
 * @author fxiao
 * @date 2019/4/12 15:15
 */
public class EncryptionCustomReamTest {
    private final Logger log= LoggerFactory.getLogger(this.getClass());
    /**
     * 算MD5值
     * @Author Fxiao
     * @Description
     * @Date 15:18 2019/4/12
     * @param
     * @return void
     **/
    @Test
    public void demo1(){
        String password="123";
        Md5Hash md5Hash=new Md5Hash(password);
        log.info("MD5加密后的密文为：{}",md5Hash.toString());
        Md5Hash md5Hash1=new Md5Hash(password,"a1b2c3");
        log.info("加盐后的密码经MD5加密后的密文为：{}",md5Hash1.toString());
    }

    @Test
    public void demo2(){
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        EncryptionCustomReam encryptionCustomReam=new EncryptionCustomReam();
//        加密部分的代码开始
//       Credentials 资格证书 美 [krəˈdenʃlz]，整体翻译为：哈希证书匹配器
        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");//加密方式
        matcher.setHashIterations(1);//加密次数
        encryptionCustomReam.setCredentialsMatcher(matcher);
//        加密部分的代码结束
        defaultSecurityManager.setRealm(encryptionCustomReam);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject=SecurityUtils.getSubject();
//        这里传入的是明文
        UsernamePasswordToken token=new UsernamePasswordToken("fxiao","123");
        subject.login(token);
        log.info("风晓已登录？{}",subject.isAuthenticated());
    }
}