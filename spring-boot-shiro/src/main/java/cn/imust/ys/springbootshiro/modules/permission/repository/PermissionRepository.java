package cn.imust.ys.springbootshiro.modules.permission.repository;

import cn.imust.ys.springbootshiro.modules.permission.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Integer> {

    List<Permission> findByGeneratemenu(boolean generatemenu);
}
