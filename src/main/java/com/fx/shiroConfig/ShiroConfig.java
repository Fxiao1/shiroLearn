package com.fx.shiroConfig;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * des:
 *
 * @author fxiao
 * @date 2019/9/5 18:07
 */
@Configuration
public class ShiroConfig {
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroReam());
        return securityManager;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFile(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<String,String>();
        //配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**","anon");
        //配置退出 过滤器，其中的具体的退出代码shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout","logout");
        //过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->教程作者说这是一个坑呢，一不小心代码就不好使了；但是具体什么意思，我现在也还不懂
        filterChainDefinitionMap.put("/**","authc");
        //设置登陆后的页面，如果不设置默认会寻找web工程根目录下的“/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    @Bean
    public MyShiroRealm myShiroReam(){
        MyShiroRealm myShiroRealm=new MyShiroRealm();
        return myShiroRealm;
    }


}
