package cn.imust.ys.springbootshiro.modules.student.service;

import cn.imust.ys.springbootshiro.modules.student.entity.Punishment;
import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import cn.imust.ys.springbootshiro.modules.student.repository.PunishmentRepository;
import cn.imust.ys.springbootshiro.modules.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
