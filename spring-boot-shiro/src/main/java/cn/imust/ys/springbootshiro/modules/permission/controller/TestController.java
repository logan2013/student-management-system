package cn.imust.ys.springbootshiro.modules.permission.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
