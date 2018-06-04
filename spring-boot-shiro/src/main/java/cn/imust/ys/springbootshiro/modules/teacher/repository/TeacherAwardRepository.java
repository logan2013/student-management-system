package cn.imust.ys.springbootshiro.modules.teacher.repository;

import cn.imust.ys.springbootshiro.modules.teacher.entity.TeacherAward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherAwardRepository extends JpaRepository<TeacherAward,Integer> {
}
