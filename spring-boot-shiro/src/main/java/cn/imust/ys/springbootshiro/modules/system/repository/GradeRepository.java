package cn.imust.ys.springbootshiro.modules.system.repository;

import cn.imust.ys.springbootshiro.modules.system.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Integer> {
}
