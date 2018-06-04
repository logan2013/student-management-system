package cn.imust.ys.springbootshiro.modules.teacher.repository;

import cn.imust.ys.springbootshiro.modules.teacher.entity.TeacherWithClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherWithClassRepository extends JpaRepository<TeacherWithClass,Integer> {
}
