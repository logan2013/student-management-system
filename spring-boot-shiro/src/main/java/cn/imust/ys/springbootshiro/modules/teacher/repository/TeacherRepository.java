package cn.imust.ys.springbootshiro.modules.teacher.repository;

import cn.imust.ys.springbootshiro.modules.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

    Teacher findByTno(String tno);
}
