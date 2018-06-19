package cn.imust.ys.springbootshiro.modules.student.service;

import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import cn.imust.ys.springbootshiro.modules.system.repository.SysClassRepository;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommonService {

    @Autowired private SysClassRepository sysClassRepository;

    public void swarpClass(Student student, JSONArray classId) {
        if (classId != null) {
            List<String> classIds = JSON.parseArray(classId.toJSONString(), String.class);
            student.setMajor(classIds.get(0));
            if(classIds.size() == 2){
                student.setGrade(classIds.get(1));
            }
            if(classIds.size() == 3){
                student.setGrade(classIds.get(1));
                Integer cid = Integer.parseInt(classIds.get(2));
                student.setSysClass(sysClassRepository.findOne(cid));
            }
        }
    }

}
