package com.fx.home;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * des:
 *
 * @author fxiao
 * @date 2019/9/5 19:09
 */
@Controller
public class HomeController {
    private final Logger log= LoggerFactory.getLogger(this.getClass());
    @RequestMapping({"/","/index"})
    public String index(){
        return "/index";
    }
    @RequestMapping("login")
    public String login(HttpServletRequest request, Map<String,String> map){
        //此方法不做登陆成功处理，有shiro进行处理，本方法仅仅处理期间发生的异常
        /**
         * 如果发生了异常，从request中获取shiro处理的异常信息，shiroLoginFailure就是获取异常类的类全名
         */
        String exceptionType=(String)request.getAttribute("shiroLoginFailure");
        log.info("exception={}",exceptionType);
        String msg="";
        if(exceptionType!=null){
            if(UnknownAccountException.class.getName().equals(exceptionType)){
                log.info("账号不存在");
                msg="账号不存在";
            }else if(
                    IncorrectCredentialsException.class.getName().equals(exceptionType)
            ){
                log.info("密码错误");
                msg="密码或账号错误";
            }else if("kaptchaValidateFailed".equals(exceptionType)){
                log.info("验证码错误");
                msg="验证码错误";
            }else{
                log.info("其他错误："+exceptionType);
                msg="其他错误："+exceptionType;
            }
        }
        map.put("msg",msg);
        return "/login";

    }
    @RequestMapping("/403")
    public String unAuthorizedRole(){
        log.info("没有权限");
        return "/403";
    }
}
