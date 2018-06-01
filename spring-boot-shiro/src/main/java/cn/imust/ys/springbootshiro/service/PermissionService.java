package cn.imust.ys.springbootshiro.service;

import cn.imust.ys.springbootshiro.entity.Permission;
import cn.imust.ys.springbootshiro.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PermissionService {

    @Autowired private PermissionRepository permissionRepository;


    public List<Map<String,Object>> findAll() {
        List<Permission> all = permissionRepository.findAll();
        List<Map<String,Object>> list = new ArrayList();
        Map<String,Object> map = null;
        for(Permission permission : all){
            // 不是根节点，属于 children
            if(permission.getPid() != 0){
                continue;
            }
            map = new HashMap();
            map.put("id",permission.getId());
            map.put("text",permission.getText());
            map.put("i18n",permission.getI18n());
            map.put("group",permission.isGroup());
            map.put("hideInBreadcrumb",permission.isHideInBreadcrumb());
            map.put("shortcut_root",permission.isShortcut_root());
            map.put("acl",permission.getAcl());
            map.put("link",permission.getLink());
            map.put("generatemenu",permission.isGeneratemenu());
            Set<Permission> permissions = permission.getPermissions();
            if(permissions != null && permissions.size()>0){
                map.put("children",getNode(permissions));
            }
            list.add(map);
        }
        return list;
    }

    private List<Map<String,Object>> getNode(Set<Permission> permissions){
        List<Map<String,Object>> nodes = new ArrayList(0);
        Map<String,Object> node = null;
        for (Permission permission: permissions){
            node = new HashMap<>();
            node.put("id",permission.getId());
            node.put("text",permission.getText());
            node.put("i18n",permission.getI18n());
            node.put("group",permission.isGroup());
            node.put("hideInBreadcrumb",permission.isHideInBreadcrumb());
            node.put("shortcut_root",permission.isShortcut_root());
            node.put("acl",permission.getAcl());
            node.put("link",permission.getLink());
            node.put("generatemenu",permission.isGeneratemenu());
            Set<Permission> permissionsSet = permission.getPermissions();
            if(permissionsSet !=null && permissionsSet.size() >0){
                node.put("children" , getNode(permissionsSet));
            }
            nodes.add(node);
        }
        return nodes;
    }

    public void save(Permission permission) {
        permissionRepository.save(permission);
    }

    public List<Map<String,Object>> ref() {
        List<Permission> all = permissionRepository.findAll();
        List<Map<String,Object>> list = new ArrayList(0);
        Map<String,Object> map = null;
        for(Permission permission : all){
            // 不是根节点，属于 children
            if(permission.getPid() != 0){
                continue;
            }
            map = new HashMap();
            map.put("title",permission.getText());
            map.put("key",permission.getId());
            Set<Permission> permissions = permission.getPermissions();
            if(permissions != null && permissions.size() > 0 ){
                map.put("children",getChildren(permissions));
            }
            list.add(map);
        }
        return list;
    }

    private List<Map<String,Object>> getChildren (Set<Permission> permissions){
        List<Map<String,Object>> childrens = new ArrayList(0);
        Map<String,Object> children = null;
        for (Permission permiss: permissions){
            children = new HashMap<>();
            children.put("title",permiss.getText());
            children.put("key",permiss.getId());
            Set<Permission> permissionsSet = permiss.getPermissions();
            if(permissionsSet !=null && permissionsSet.size() >0){
                children.put("children" , getChildren(permissionsSet));
            }
            childrens.add(children);
        }
        return childrens;
    }
}
