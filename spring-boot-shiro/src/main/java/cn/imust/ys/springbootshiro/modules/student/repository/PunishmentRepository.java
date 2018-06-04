package cn.imust.ys.springbootshiro.modules.student.repository;

import cn.imust.ys.springbootshiro.modules.student.entity.Punishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PunishmentRepository extends JpaRepository<Punishment, Integer>{

}
