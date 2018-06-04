package cn.imust.ys.springbootshiro.modules.system.controller;

import cn.imust.ys.springbootshiro.modules.system.entity.SysClass;
import cn.imust.ys.springbootshiro.modules.system.repository.SysClassRepository;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("sysclass")
public class SysClassController {

    @Autowired private SysClassRepository sysClassRepository;

    @PostMapping("save")
    public Map save(@RequestBody SysClass sysClass){
        sysClassRepository.save(sysClass);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("delete")
    public Map delete(@RequestBody SysClass sysClass){
        sysClassRepository.delete(sysClass);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("update")
    public Map update(@RequestBody SysClass sysClass){
        sysClassRepository.saveAndFlush(sysClass);
        return ControllerUtils.getSuccessMap();
    }

    @RequestMapping("findAll")
    public Map findAll(){
        return ControllerUtils.getSuccessMap(sysClassRepository.findAll());
    }
}
