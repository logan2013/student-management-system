package cn.imust.ys.springbootshiro.modules.system.controller;

import cn.imust.ys.springbootshiro.modules.system.entity.Grade;
import cn.imust.ys.springbootshiro.modules.system.entity.Major;
import cn.imust.ys.springbootshiro.modules.system.repository.MajorRepository;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("major")
public class MajorController {

    @Autowired private MajorRepository majorRepository;

    @PostMapping("save")
    public Map save(@RequestBody Major major){
        return ControllerUtils.getSuccessMap(majorRepository.save(major));
    }

    @PostMapping("delete")
    public Map delete(@RequestBody Major major){
        majorRepository.delete(major);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("update")
    public Map update(@RequestBody Major major){
        majorRepository.saveAndFlush(major);
        return ControllerUtils.getSuccessMap();
    }

    @RequestMapping("findAll")
    public Map findAll(){
        return ControllerUtils.getSuccessMap(majorRepository.findAll());
    }

    @RequestMapping("classRef")
    public List classRef() {
        List list = new ArrayList();
        List childrens = null;
        Map map = null;
        Map hmap = null;
        List<Major> majors = majorRepository.findAll();
        if (majors != null && majors.size() > 0) {
            for (Major major : majors) {
                map = new HashMap();
                map.put("value", major.getId());
                map.put("label", major.getName());
                Set<Grade> grades = major.getGrades();
                if (grades != null && grades.size() > 0) {
                    childrens = new ArrayList();
                    for (Grade grade : grades) {
                        hmap = new HashMap();
                        hmap.put("value", grade.getId());
                        hmap.put("label", grade.getName());
                        hmap.put("isLeaf", true);
                        childrens.add(hmap);
                    }
                    map.put("children", childrens);
                    list.add(map);
                }
            }
        }
        return list;
    }
}
