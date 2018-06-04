package cn.imust.ys.springbootshiro.modules.teacher.controller;

import cn.imust.ys.springbootshiro.modules.teacher.entity.Teacher;
import cn.imust.ys.springbootshiro.modules.teacher.repository.TeacherRepository;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Autowired private TeacherRepository teacherRepository;

    @PostMapping("save")
    public Map save(@RequestBody Teacher teacher){
        teacherRepository.save(teacher);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("delete")
    public Map delete(@RequestBody Teacher teacher){
        teacherRepository.delete(teacher);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("update")
    public Map update(@RequestBody Teacher teacher){
        teacherRepository.saveAndFlush(teacher);
        return ControllerUtils.getSuccessMap();
    }

    @RequestMapping("findAll")
    public Map findAll(){
        return ControllerUtils.getSuccessMap(teacherRepository.findAll());
    }
}
