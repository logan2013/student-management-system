package cn.imust.ys.springbootshiro.modules.student.service;

import cn.imust.ys.springbootshiro.exception.CustomException;
import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import cn.imust.ys.springbootshiro.modules.student.repository.StudentRepository;
import cn.imust.ys.springbootshiro.modules.system.entity.SysClass;
import cn.imust.ys.springbootshiro.modules.system.repository.SysClassRepository;
import cn.imust.ys.springbootshiro.modules.teacher.entity.Teacher;
import cn.imust.ys.springbootshiro.modules.teacher.repository.TeacherRepository;
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
public class StudentService {

    @Autowired private StudentRepository studentRepository;
    @Autowired private SysClassRepository sysClassRepository;
    @Autowired private TeacherRepository teacherRepository;
    @Autowired private CommonService commonService;

    public void save(Student student){
        studentRepository.save(student);
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public void update(Student student) {
        studentRepository.saveAndFlush(student);
    }

    public void batchSave(List<Map> listData,Integer classId) {
        Student student = null;
        SysClass one = sysClassRepository.findOne(classId);
        for (Map map : listData) {
            student = new Student();
            // TODO templateMap.put("出生日期", "birthday"); 未录入
            String sno = (String) map.get("sno");
            Student bySno = studentRepository.findBySno(sno);
            if (bySno != null) {
                throw new CustomException("学号为：" + sno + "的学生已存在！不可重新导入...");
            }
            student.setSysClass(one);
            student.setGrade(one.getGrade().getName());
            student.setMajor(one.getGrade().getMajor().getName());
            student.setSno((String) map.get("sno"));
            student.setSname((String) map.get("sname"));
            student.setGender((String) map.get("gender"));
            student.setDorm((String) map.get("dorm"));
            student.setPhoneNum((String) map.get("phoneNum"));
            student.setQqNum((String) map.get("qqNum"));
            student.setRemark((String) map.get("remark"));
            student.setAddress((String) map.get("address"));
            student.setIdcard((String) map.get("idcard"));
            student.setWechat((String) map.get("wechat"));
//            student.setBirthday((String) map.get("birthday"));
            student.setNation((String) map.get("nation"));
            student.setStatus((String) map.get("status"));
            student.setPoliticalStatus((String) map.get("politicalStatus"));
            studentRepository.save(student);
        }
    }

    public Map<String, String> getTemplate() {
        Map templateMap = new LinkedHashMap();
        templateMap.put("学号", "sno");
        templateMap.put("姓名", "sname");
        templateMap.put("性别", "gender");
        templateMap.put("宿舍号", "dorm");
        templateMap.put("联系电话", "phoneNum");
        templateMap.put("qq", "qqNum");
        templateMap.put("备注", "remark");
        templateMap.put("家庭详细地址", "address");
        templateMap.put("身份证号", "idcard");
        templateMap.put("微信号", "wechat");
        templateMap.put("出生日期", "birthday");
        templateMap.put("民族", "nation");
        templateMap.put("在校状态", "status");
        templateMap.put("政治面貌", "politicalStatus");
        return templateMap;
    }

    public List<Student> filter(String params) {

        Student student = new Student();

        Map<String, Object> paramsMap = JSON.parseObject(params, Map.class);
        JSONArray classId = (JSONArray) paramsMap.get("classId");
        commonService.swarpClass(student,classId);
        String sno = (String) paramsMap.get("sno");
        String sname = (String) paramsMap.get("sname");
        String status = (String) paramsMap.get("status");
        String politicalStatus = (String) paramsMap.get("politicalStatus");

        student.setSno(sno);
        student.setSname(sname);
        student.setStatus(status);
        student.setPoliticalStatus(politicalStatus);
        Integer tid = (Integer) paramsMap.get("tid");
        if(tid !=null){
            Teacher one = teacherRepository.findOne(tid);
            student.setSysClass(new SysClass(one));
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("sname", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("sno", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<Student> ex = Example.of(student,matcher);
        List<Student> all = studentRepository.findAll(ex);
        return all;
    }
}

