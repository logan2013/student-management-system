package cn.imust.ys.springbootshiro.modules.student.service;

import cn.imust.ys.springbootshiro.exception.CustomException;
import cn.imust.ys.springbootshiro.modules.student.entity.Job;
import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import cn.imust.ys.springbootshiro.modules.student.entity.Subsidize;
import cn.imust.ys.springbootshiro.modules.student.repository.StudentRepository;
import cn.imust.ys.springbootshiro.modules.student.repository.SubsidizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubsidizeService {

    @Autowired private SubsidizeRepository subsidizeRepository;
    @Autowired private StudentRepository studentRepository;

    public void save(Subsidize subsidize) {
        String sno = subsidize.getStudentNum();
        Student bySno = studentRepository.findBySno(sno);
        if(bySno !=null){
            subsidize.setStudent(bySno);
            subsidizeRepository.save(subsidize);
        }
    }

    public void update(Subsidize subsidize) {
        String sno = subsidize.getStudentNum();
        Student bySno = studentRepository.findBySno(sno);
        if(bySno !=null){
            subsidize.setStudent(bySno);
            subsidizeRepository.saveAndFlush(subsidize);
        }
    }

    public void batchSave(List<Map> listData) {
        Subsidize subsidize = null;
        for (Map map : listData) {
            subsidize = new Subsidize();
            // TODO templateMap.put("获得资助的时间", "stime"); 未录入
            String sno = (String) map.get("sno");
            Student bySno = studentRepository.findBySno(sno);
            if (bySno == null) {
                throw new CustomException("学号为：" + sno + "的学生未找到！请修改后重新导入");
            }
            subsidize.setStudent(bySno);
            subsidize.setLevel((String) map.get("level"));
            subsidize.setType((String) map.get("type"));
            subsidize.setInfo((String) map.get("info"));
//            subsidize.setStime((String) map.get("stime"));
            subsidizeRepository.save(subsidize);
        }
    }

    public Map<String, String> getTemplate() {
        Map templateMap = new LinkedHashMap();
        templateMap.put("姓名", "sname");
        templateMap.put("学号", "sno");
        templateMap.put("受助等级", "level");
        templateMap.put("受助类型", "type");
        templateMap.put("获得资助的详细情况", "info");
        templateMap.put("获得资助的时间", "stime");
        return templateMap;
    }
}
