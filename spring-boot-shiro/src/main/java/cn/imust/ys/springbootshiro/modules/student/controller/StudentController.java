package cn.imust.ys.springbootshiro.modules.student.controller;

import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import cn.imust.ys.springbootshiro.modules.student.repository.StudentRepository;
import cn.imust.ys.springbootshiro.modules.student.service.StudentService;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired private StudentService studentService;

    @PostMapping("save")
    public Map save(@RequestBody Student student){
        studentService.save(student);
        return ControllerUtils.getSuccessMap();
    }

    @RequestMapping("findAll")
    public Map findAll(){
        return ControllerUtils.getSuccessMap(studentService.findAll());
    }

    @PostMapping("update")
    public Map update(@RequestBody Student student){
        studentService.update(student);
        return ControllerUtils.getSuccessMap(student);
    }
}
