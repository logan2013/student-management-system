package cn.imust.ys.springbootshiro.modules.system.repository;

import cn.imust.ys.springbootshiro.modules.system.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRepository extends JpaRepository<Major,Integer> {
}
