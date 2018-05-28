package cn.imust.ys.springbootshiro.service;

import cn.imust.ys.springbootshiro.entity.Role;
import cn.imust.ys.springbootshiro.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired private RoleRepository roleRepository;

    public List<Role> findAll(){
        return roleRepository.findAll();
    }
}
