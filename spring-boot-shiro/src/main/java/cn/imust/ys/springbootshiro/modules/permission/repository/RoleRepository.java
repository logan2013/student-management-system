package cn.imust.ys.springbootshiro.modules.permission.repository;

import cn.imust.ys.springbootshiro.modules.permission.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
