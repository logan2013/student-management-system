package cn.imust.ys.springbootshiro.controller;

import cn.imust.ys.springbootshiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TestController {

    @RequestMapping("/permission")
    public int[] getPermission(){
        int[] arr = new int[]{200, 2001, 2003};
        return arr;
    }

    @RequestMapping("myerror")
    public int error(){
        int a = 10 / 0 ;
        return a;
    }


}
