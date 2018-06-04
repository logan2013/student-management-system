package cn.imust.ys.springbootshiro.modules.system.controller;

import cn.imust.ys.springbootshiro.modules.system.entity.Grade;
import cn.imust.ys.springbootshiro.modules.system.repository.GradeRepository;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("grade")
public class GradeController {

    @Autowired private GradeRepository gradeRepository;

    @PostMapping("save")
    public Map save(@RequestBody Grade grade){
        gradeRepository.save(grade);
        return ControllerUtils.getSuccessMap(grade);
    }

    @PostMapping("delete")
    public Map delete(@RequestBody Grade grade){
        gradeRepository.delete(grade);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("update")
    public Map update(@RequestBody Grade grade){
        gradeRepository.saveAndFlush(grade);
        return ControllerUtils.getSuccessMap();
    }

    @RequestMapping("findAll")
    public Map findAll(){
        return ControllerUtils.getSuccessMap(gradeRepository.findAll());
    }

}
