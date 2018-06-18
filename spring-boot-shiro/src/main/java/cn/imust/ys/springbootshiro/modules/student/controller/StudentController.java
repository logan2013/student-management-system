package cn.imust.ys.springbootshiro.modules.student.controller;

import cn.imust.ys.springbootshiro.exception.CustomException;
import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import cn.imust.ys.springbootshiro.modules.student.repository.StudentRepository;
import cn.imust.ys.springbootshiro.modules.student.service.StudentService;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import cn.imust.ys.springbootshiro.utils.ImportUtils;
import cn.imust.ys.springbootshiro.utils.SessionUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("cancelImport")
    public Map cancelImport(){
        SessionUtils.getSession().removeAttribute("data");
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("import")
    public Map importData(@RequestBody String params){
        Map data = ImportUtils.analytical(params,studentService.getTemplate());
        SessionUtils.getSession().setAttribute("data",data);
        return ControllerUtils.getSuccessMap(data);
    }

    @GetMapping("template")
    public Map template(){
        List dt = ImportUtils.getDownloadTemplate(studentService.getTemplate());
        return ControllerUtils.getSuccessMap(dt);
    }

    @PostMapping("saveImport")
    public Map saveImport(@RequestBody String params){
        Map data = (Map)SessionUtils.getSession().getAttribute("data");
        Map<String, Object> paramsMap = JSON.parseObject(params, Map.class);
        Integer classId = (Integer) paramsMap.get("classId");
        studentService.batchSave((List)data.get("listData"),classId);
        return ControllerUtils.getSuccessMap();
    }
}
