package cn.imust.ys.springbootshiro.modules.system.repository;

import cn.imust.ys.springbootshiro.modules.system.entity.SysClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysClassRepository extends JpaRepository<SysClass,Integer> {
    List<SysClass> findByAllName(String allName);
}
