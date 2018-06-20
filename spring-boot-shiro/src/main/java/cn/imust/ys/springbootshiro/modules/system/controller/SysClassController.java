package cn.imust.ys.springbootshiro.modules.system.controller;

import cn.imust.ys.springbootshiro.modules.system.entity.SysClass;
import cn.imust.ys.springbootshiro.modules.system.repository.SysClassRepository;
import cn.imust.ys.springbootshiro.modules.teacher.entity.TeacherWithClass;
import cn.imust.ys.springbootshiro.modules.teacher.repository.TeacherWithClassRepository;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
        sysClassRepository.delete(sysClassRepository.findOne(sysClass.getScid()));
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("update")
    public Map update(@RequestBody SysClass sysClass){
        sysClassRepository.saveAndFlush(sysClass);
        return ControllerUtils.getSuccessMap();
    }

    @RequestMapping("findAll")
    public List<SysClass> findAll(){
        return sysClassRepository.findAll();
    }
}
