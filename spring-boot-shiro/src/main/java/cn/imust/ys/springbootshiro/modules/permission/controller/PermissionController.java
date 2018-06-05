package cn.imust.ys.springbootshiro.modules.permission.controller;

import cn.imust.ys.springbootshiro.modules.permission.entity.Permission;
import cn.imust.ys.springbootshiro.modules.permission.service.PermissionService;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("permission")
public class PermissionController {

    @Autowired private PermissionService permissionService;

    @RequestMapping("findAll")
    public Map findAll(){
        return ControllerUtils.getSuccessMap(permissionService.findAll());
    }

    @RequestMapping("menu")
    public Map getMenuList(){
        Map<String,Object> app = permissionService.getMenu();
        return app;
    }

    @RequestMapping("save")
    public Map save(@RequestBody Permission permission){
        permissionService.save(permission);
        return ControllerUtils.getSuccessMap();
    }

    @RequestMapping("ref")
    public Map ref(){
        return ControllerUtils.getSuccessMap(permissionService.ref());
    }
}
