package cn.imust.ys.springbootshiro.modules.student.repository;

import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

    Student findBySno(String sno);

    List<Student> findBySnoIsNotNull();
}
