package cn.imust.ys.springbootshiro.modules.student.service;

import cn.imust.ys.springbootshiro.exception.CustomException;
import cn.imust.ys.springbootshiro.modules.student.entity.Job;
import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import cn.imust.ys.springbootshiro.modules.student.repository.JobRepository;
import cn.imust.ys.springbootshiro.modules.student.repository.StudentRepository;
import cn.imust.ys.springbootshiro.utils.ImportUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class JobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired private CommonService commonService;

    public void save(Job job) {
        String sno = job.getJobsno();
        Student bySno = studentRepository.findBySno(sno);
        // TODO 为子表的保存方法添加自定义异常
        if (bySno != null) {
            job.setStudent(bySno);
            jobRepository.save(job);
        }
    }

    public void batchSave(List<Map> listData) {
        Job job = null;
        for (Map map : listData) {
            job = new Job();
            // TODO templateMap.put("就业时间","jtime") 未录入
            String sno = (String) map.get("sno");
            Student bySno = studentRepository.findBySno(sno);
            if (bySno == null) {
                throw new CustomException("学号为：" + sno + "的学生未找到！请修改后重新导入");
            }
            job.setStudent(bySno);
            job.setJobunit((String) map.get("jobunit"));
            job.setFileAddress((String) map.get("fileAddress"));
            job.setJobwhere((String) map.get("jobwhere"));
            job.setMode((String) map.get("mode"));
            jobRepository.save(job);
        }
    }

    public void update(Job job) {
        String sno = job.getJobsno();
        Student bySno = studentRepository.findBySno(sno);
        if (bySno != null) {
            job.setStudent(bySno);
            jobRepository.saveAndFlush(job);
        }
    }

    public Map<String, String> getTemplate() {
        Map templateMap = new LinkedHashMap();
        templateMap.put("姓名", "sname");
        templateMap.put("学号", "sno");
        templateMap.put("方式", "mode");
        templateMap.put("就业单位", "jobunit");
        templateMap.put("就业时间", "jtime");
        templateMap.put("档案转递地址", "fileAddress");
        templateMap.put("就业去向", "jobwhere");
        return templateMap;
    }

    public List<Job> filter(String params) {
        Student student = new Student();

        Map<String, Object> paramsMap = JSON.parseObject(params, Map.class);
        JSONArray classId = (JSONArray) paramsMap.get("classId");
        commonService.swarpClass(student,classId);
        String sno = (String) paramsMap.get("sno");
        String sname = (String) paramsMap.get("sname");
        String status = (String) paramsMap.get("status");
        String jobMode = (String) paramsMap.get("jobMode");

        student.setSno(sno != null ? sno.trim() : sno);
        student.setSname(sname != null ? sname.trim() : sname);
        student.setStatus(status);

        Job job = new Job();
        job.setStudent(student);
        job.setMode(jobMode);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("student.sname", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("student.sno", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<Job> ex = Example.of(job,matcher);

        List<Job> all = jobRepository.findAll(ex);
        return all;
    }
}
