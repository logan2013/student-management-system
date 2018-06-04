package cn.imust.ys.springbootshiro.modules.teacher.controller;

import cn.imust.ys.springbootshiro.modules.teacher.entity.TeacherWithClass;
import cn.imust.ys.springbootshiro.modules.teacher.repository.TeacherWithClassRepository;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("twc")
public class TeacherWithClassController {

    @Autowired private TeacherWithClassRepository twcRepository;

    @PostMapping("save")
    public Map save(@RequestBody TeacherWithClass teacherWithClass){
        twcRepository.save(teacherWithClass);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("delete")
    public Map delete(@RequestBody TeacherWithClass teacherWithClass){
        twcRepository.delete(teacherWithClass);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("update")
    public Map update(@RequestBody TeacherWithClass teacherWithClass){
        twcRepository.saveAndFlush(teacherWithClass);
        return ControllerUtils.getSuccessMap();
    }

    @RequestMapping("findAll")
    public Map findAll(){
        return ControllerUtils.getSuccessMap(twcRepository.findAll());
    }
}
