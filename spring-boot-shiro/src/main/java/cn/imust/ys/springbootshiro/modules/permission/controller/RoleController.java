package cn.imust.ys.springbootshiro.modules.permission.controller;

import cn.imust.ys.springbootshiro.modules.permission.entity.Role;
import cn.imust.ys.springbootshiro.modules.permission.service.RoleService;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired private RoleService roleService;

    @GetMapping("findAll")
    public Map findAll(){
        return ControllerUtils.getSuccessMap(roleService.findAll());
    }

    @RequestMapping("save")
    public Map save(@RequestBody Role role){
        roleService.save(role);
        return ControllerUtils.getSuccessMap(null);
    }

    @RequestMapping("delete")
    public Map delete(@RequestBody Role role){
        roleService.delete(role);
        return ControllerUtils.getSuccessMap(null);
    }

    @RequestMapping("setPermission")
    public Map setPermission(@RequestBody Role role){
        roleService.setPermission(role);
        return ControllerUtils.getSuccessMap(null);
    }
}
