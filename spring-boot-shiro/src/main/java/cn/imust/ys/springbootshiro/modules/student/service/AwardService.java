package cn.imust.ys.springbootshiro.modules.student.service;

import cn.imust.ys.springbootshiro.exception.CustomException;
import cn.imust.ys.springbootshiro.modules.student.entity.Award;
import cn.imust.ys.springbootshiro.modules.student.entity.Job;
import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import cn.imust.ys.springbootshiro.modules.student.repository.AwardRepository;
import cn.imust.ys.springbootshiro.modules.student.repository.StudentRepository;
import cn.imust.ys.springbootshiro.modules.system.repository.SysClassRepository;
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
public class AwardService {

    @Autowired private AwardRepository awardRepository;
    @Autowired private StudentRepository studentRepository;
    @Autowired private CommonService commonService;

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
        }else{
            throw new CustomException("学号为：" + sno + " 的学生未找到!");
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

    public List<Award> filter(String params) {
        Student student = new Student();

        Map<String, Object> paramsMap = JSON.parseObject(params, Map.class);
        JSONArray classId = (JSONArray) paramsMap.get("classId");
        commonService.swarpClass(student,classId);
        String sno = (String) paramsMap.get("sno");
        String sname = (String) paramsMap.get("sname");
        String status = (String) paramsMap.get("status");
        String awardTime = (String) paramsMap.get("awardTime");

        student.setSno(sno != null ? sno.trim() : sno);
        student.setSname(sname != null ? sname.trim() : sname);
        student.setStatus(status);

        Award award = new Award();
        award.setStudent(student);
        award.setAwardTime(awardTime);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("student.sname", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("student.sno", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<Award> ex = Example.of(award,matcher);
        List<Award> all = awardRepository.findAll(ex);

        return all;
    }

}
