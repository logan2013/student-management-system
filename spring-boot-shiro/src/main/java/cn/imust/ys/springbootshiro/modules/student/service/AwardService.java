package cn.imust.ys.springbootshiro.modules.student.service;

import cn.imust.ys.springbootshiro.modules.student.entity.Award;
import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import cn.imust.ys.springbootshiro.modules.student.repository.AwardRepository;
import cn.imust.ys.springbootshiro.modules.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
