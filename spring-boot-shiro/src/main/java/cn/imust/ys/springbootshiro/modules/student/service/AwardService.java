package cn.imust.ys.springbootshiro.modules.student.service;

import cn.imust.ys.springbootshiro.exception.CustomException;
import cn.imust.ys.springbootshiro.modules.student.entity.Award;
import cn.imust.ys.springbootshiro.modules.student.entity.Job;
import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import cn.imust.ys.springbootshiro.modules.student.repository.AwardRepository;
import cn.imust.ys.springbootshiro.modules.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AwardService {

    @Autowired private AwardRepository awardRepository;
    @Autowired private StudentRepository studentRepository;

    public void update(Award award) {
        String sno = award.getStudentNum();
        Student bySno = studentRepository.findBySno(sno);
        if(bySno !=null){
            award.setStudent(bySno);
            awardRepository.save(award);
        }
    }

    public void save(Award award) {
        String sno = award.getStudentNum();
        Student bySno = studentRepository.findBySno(sno);
        if(bySno !=null){
            award.setStudent(bySno);
            awardRepository.saveAndFlush(award);
        }
    }

    public void batchSave(List<Map> listData) {
        Award award = null;
        for (Map map : listData) {
            award = new Award();
            // TODO templateMap.put("获奖时间", "awardTime"); 未录入
            String sno = (String) map.get("sno");
            Student bySno = studentRepository.findBySno(sno);
            if (bySno == null) {
                throw new CustomException("学号为：" + sno + "的学生未找到！请修改后重新导入");
            }
            award.setStudent(bySno);
            award.setInfo((String) map.get("info"));
            awardRepository.save(award);
        }
    }

    public Map<String, String> getTemplate() {
        Map templateMap = new LinkedHashMap();
        templateMap.put("姓名", "sname");
        templateMap.put("学号", "sno");
        templateMap.put("获奖时间", "awardTime");
        templateMap.put("获奖详情", "info");
        return templateMap;
    }
}
