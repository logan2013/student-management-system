package cn.imust.ys.springbootshiro.modules.student.service;

import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import cn.imust.ys.springbootshiro.modules.student.entity.Subsidize;
import cn.imust.ys.springbootshiro.modules.student.repository.StudentRepository;
import cn.imust.ys.springbootshiro.modules.student.repository.SubsidizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
