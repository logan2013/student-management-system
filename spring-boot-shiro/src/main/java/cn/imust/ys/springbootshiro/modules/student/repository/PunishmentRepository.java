package cn.imust.ys.springbootshiro.modules.student.repository;

import cn.imust.ys.springbootshiro.modules.student.entity.Punishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PunishmentRepository extends JpaRepository<Punishment, Integer>{

    @Query("select ptime from Punishment group by ptime")
    List<String> groupStime();

}
