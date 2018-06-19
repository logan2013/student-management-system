package cn.imust.ys.springbootshiro.modules.student.repository;

import cn.imust.ys.springbootshiro.modules.student.entity.Subsidize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubsidizeRepository extends JpaRepository<Subsidize, Integer>{

    @Query("select stime from Subsidize group by stime")
    List<String> groupStime();
}
