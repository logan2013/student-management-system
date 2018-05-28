package cn.imust.ys.springbootshiro.controller;

import cn.imust.ys.springbootshiro.entity.Role;
import cn.imust.ys.springbootshiro.service.RoleService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired private RoleService roleService;

    @GetMapping("findAll")
    public String findAll(){
        final List<Role> all = roleService.findAll();
        return JSON.toJSONString(all);
    }

}
