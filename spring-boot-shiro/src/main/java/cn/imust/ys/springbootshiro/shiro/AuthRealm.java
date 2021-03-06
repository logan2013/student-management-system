package cn.imust.ys.springbootshiro.shiro;

import cn.imust.ys.springbootshiro.modules.permission.entity.Permission;
import cn.imust.ys.springbootshiro.modules.permission.entity.Role;
import cn.imust.ys.springbootshiro.modules.permission.entity.User;
import cn.imust.ys.springbootshiro.modules.teacher.entity.Teacher;
import cn.imust.ys.springbootshiro.modules.teacher.repository.TeacherRepository;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private TeacherRepository teacherRepository;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Teacher teacher = (Teacher) principals.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();
        Set<Role> roleSet = teacher.getRoles();
        if (CollectionUtils.isNotEmpty(roleSet)) {
            for(Role role : roleSet) {
                roleNameList.add(role.getAlias());
                Set<Permission> permissionSet = role.getPermissions();
                if (CollectionUtils.isNotEmpty(permissionSet)) {
                    for (Permission permission : permissionSet) {
                        permissionList.add(permission.getText());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRoles(roleNameList);
        return info;
    }

    // 认证登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        Teacher teacher = teacherRepository.findByTno(username);
        return new SimpleAuthenticationInfo(teacher, teacher.getPassword(), this.getClass().getName());
    }
}
