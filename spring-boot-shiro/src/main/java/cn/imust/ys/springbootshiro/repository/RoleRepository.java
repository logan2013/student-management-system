package cn.imust.ys.springbootshiro.repository;

import cn.imust.ys.springbootshiro.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
