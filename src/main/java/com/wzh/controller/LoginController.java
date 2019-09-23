package com.wzh.controller;


import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {
    private Logger logger= LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/")
    public String showHome() {
        //获取用户名
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        return "home.html";

    }

    @RequestMapping("/login")
    public String showLogin() {
        return "login.html";
    }

   /* @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String printAdmin() {
        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
    }*/


   @RequestMapping("/admin")
   @ResponseBody
   @PreAuthorize("hasPermission('/admin','r')")
   public String printadminr(){
       return "如果你看到这句话说明你有查看权限";
   }

    @RequestMapping("/admin/c")
    @ResponseBody
    @PreAuthorize("hasPermission('/admin','c')")
    public String printadminc(){
        return "如果你看到这句话说明你有创建权限";
    }





    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }


    @ResponseBody
    @RequestMapping("/login/invalid")
   // @ResponseStatus(value = HttpStatus.UNAUTHORIZED,reason = "Giao！吖吼")
    public String stimeout(){
        return "Session 已过期，请重新登录";
    }

   /* @RequestMapping("/about")
    public String about(){
        return "about.html";
    }*/

}
