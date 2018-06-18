package cn.imust.ys.springbootshiro.modules.student.service;

import cn.imust.ys.springbootshiro.exception.CustomException;
import cn.imust.ys.springbootshiro.modules.student.entity.Job;
import cn.imust.ys.springbootshiro.modules.student.entity.Punishment;
import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import cn.imust.ys.springbootshiro.modules.student.repository.PunishmentRepository;
import cn.imust.ys.springbootshiro.modules.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PunishmentService {

    @Autowired private PunishmentRepository punishmentRepository;
    @Autowired private StudentRepository studentRepository;

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

}
