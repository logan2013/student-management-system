package cn.imust.ys.springbootshiro.modules.student.service;

import cn.imust.ys.springbootshiro.modules.student.entity.Job;
import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import cn.imust.ys.springbootshiro.modules.student.repository.JobRepository;
import cn.imust.ys.springbootshiro.modules.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired private JobRepository jobRepository;
    @Autowired private StudentRepository studentRepository;

    public void save(Job job){
        String sno = job.getJobsno();
        Student bySno = studentRepository.findBySno(sno);
        if(bySno !=null){
            job.setStudent(bySno);
            jobRepository.save(job);
        }
    }

    public void update(Job job){
        String sno = job.getJobsno();
        Student bySno = studentRepository.findBySno(sno);
        if(bySno !=null){
            job.setStudent(bySno);
            jobRepository.saveAndFlush(job);
        }
    }
}
