package cn.imust.ys.springbootshiro.modules.teacher.controller;

import cn.imust.ys.springbootshiro.modules.teacher.entity.Teacher;
import cn.imust.ys.springbootshiro.modules.teacher.repository.TeacherRepository;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
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
    public Map update(@RequestBody Teacher teacher, HttpSession session){
        Teacher one = teacherRepository.findOne(teacher.getTid());
        one.setDept(teacher.getDept());
        one.setPoliticalStatus(teacher.getPoliticalStatus());
        one.setSchooling(teacher.getSchooling());
        one.setTname(teacher.getTname());
        one.setPhoneNum(teacher.getPhoneNum());
        one.setTitle(teacher.getTitle());
        teacherRepository.saveAndFlush(one);
        session.setAttribute("user",one);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("updatePassword")
    public Map updatePassword(@RequestBody Teacher teacher,HttpSession session){
        teacherRepository.saveAndFlush(teacher);
        session.setAttribute("user",teacher);
        return ControllerUtils.getSuccessMap();
    }

    @RequestMapping("findAll")
    public List<Teacher> findAll(){
        return teacherRepository.findAll();
    }
}
