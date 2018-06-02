package cn.imust.ys.springbootshiro.service;

import cn.imust.ys.springbootshiro.entity.Permission;
import cn.imust.ys.springbootshiro.entity.Role;
import cn.imust.ys.springbootshiro.repository.PermissionRepository;
import cn.imust.ys.springbootshiro.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired private RoleRepository roleRepository;
    @Autowired private PermissionRepository permissionRepository;

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public void save(Role role) {
        role.setCreateTime(new Date());
        roleRepository.save(role);
    }

    public void delete(Role role) {
        roleRepository.delete(role);
    }

    public void setPermission(Role role) {
        int[] ps = role.getPs();
        // ps 里只有最顶层，和最底层的权限
        for (int p :ps){
            Permission one = permissionRepository.findOne(p);
            if(one.getPermission() == null){
                one.setDown(true);
            }else{
                one.setDown(false);
            }
            setRolePermissions(role,one);
        }
        roleRepository.saveAndFlush(role);
    }

    private void setRolePermissions(Role role,Permission one){
        // 顶层, 往下搜索
        if(one.isDown()){
            role.getPermissions().add(one);
            Set<Permission> permissions = one.getPermissions();
            for (Permission permission : permissions) {
                role.getPermissions().add(permission);
                // 递归一直往下搜索 ...
                permission.setDown(true);
                setRolePermissions(role,permission);
            }
        }
        if(!one.isDown()) {
            // 底层
            role.getPermissions().add(one);
            Permission permission = one.getPermission();
            if(permission !=null){
                permission.setDown(false);
                setRolePermissions(role,permission);
            }
        }
    }

}
