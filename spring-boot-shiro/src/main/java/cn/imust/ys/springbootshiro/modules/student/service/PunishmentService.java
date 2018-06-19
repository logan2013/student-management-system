package cn.imust.ys.springbootshiro.modules.student.service;

import cn.imust.ys.springbootshiro.exception.CustomException;
import cn.imust.ys.springbootshiro.modules.student.entity.Job;
import cn.imust.ys.springbootshiro.modules.student.entity.Punishment;
import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import cn.imust.ys.springbootshiro.modules.student.repository.PunishmentRepository;
import cn.imust.ys.springbootshiro.modules.student.repository.StudentRepository;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PunishmentService {

    @Autowired private PunishmentRepository punishmentRepository;
    @Autowired private StudentRepository studentRepository;
    @Autowired private CommonService commonService;

    public void update(Punishment punishment) {
        String sno = punishment.getStudentNum();
        Student bySno = studentRepository.findBySno(sno);
        if(bySno !=null){
            punishment.setStudent(bySno);
            punishmentRepository.save(punishment);
        }
    }

    public void save(Punishment punishment) {
        String sno = punishment.getStudentNum();
        Student bySno = studentRepository.findBySno(sno);
        if(bySno !=null){
            punishment.setStudent(bySno);
            punishmentRepository.saveAndFlush(punishment);
        }
    }

    public void batchSave(List<Map> listData) {
        Punishment punishment = null;
        for (Map map : listData) {
            punishment = new Punishment();
            // TODO templateMap.put("就业时间","jtime") 未录入
            String sno = (String) map.get("sno");
            Student bySno = studentRepository.findBySno(sno);
            if (bySno == null) {
                throw new CustomException("学号为：" + sno + "的学生未找到！请修改后重新导入");
            }
            punishment.setStudent(bySno);
            punishment.setPno((String) map.get("pno"));
            punishment.setPtype((String) map.get("ptype"));
//            punishment.setPtime((String) map.get("ptime"));
            punishment.setInfo((String) map.get("info"));
//            punishment.setInfo((String) map.get("removeTime"));
            punishmentRepository.save(punishment);
        }
    }

    public Map<String, String> getTemplate() {
        Map templateMap = new LinkedHashMap();
        templateMap.put("姓名", "sname");
        templateMap.put("学号", "sno");
        templateMap.put("发文号", "pno");
        templateMap.put("处分种类", "ptype");
        templateMap.put("处分时间", "ptime");
        templateMap.put("受处分原因描述", "info");
        templateMap.put("解除处分时间", "removeTime");
        return templateMap;
    }

    public List<Punishment> filter(String params) {
        Student student = new Student();

        Map<String, Object> paramsMap = JSON.parseObject(params, Map.class);
        JSONArray classId = (JSONArray) paramsMap.get("classId");
        commonService.swarpClass(student,classId);
        String sno = (String) paramsMap.get("sno");
        String sname = (String) paramsMap.get("sname");
        String status = (String) paramsMap.get("status");
        String ptype = (String) paramsMap.get("ptype");
        String ptime = (String) paramsMap.get("ptime");

        student.setSno(sno != null ? sno.trim() : sno);
        student.setSname(sname != null ? sname.trim() : sname);
        student.setStatus(status);

        Punishment punishment = new Punishment();
        punishment.setStudent(student);
        punishment.setPtype(ptype);
        punishment.setPtime(ptime);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("student.sname", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("student.sno", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<Punishment> ex = Example.of(punishment,matcher);

        List<Punishment> all = punishmentRepository.findAll(ex);
        return all;
    }
}
