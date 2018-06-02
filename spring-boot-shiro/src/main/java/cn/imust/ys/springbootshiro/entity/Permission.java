package cn.imust.ys.springbootshiro.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties(value={"permission","permissions","roles"})
public class Permission implements Serializable {
    @Id @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name="pid")
    private Permission permission;
    @Column(length=30)
    private String text;
    @Column(length=20)
    // 作为权限编号 ， 可以国际化
    private String i18n;
    @Column(name ="pgroup" ,length=1)
    private Boolean group;
    @Column(length=1)
    private Boolean hideInBreadcrumb;
    @Column(length=1)
    private Boolean shortcut_root;
    @Column(length=40)
    private String acl;
    @Column(length=40)
    private String link;
    @Column(length=30)
    private String icon;
    @Column(length=1)
    private Boolean generatemenu;

    @OneToMany(targetEntity=Permission.class, mappedBy = "permission")//指定一对多关系
    private Set<Permission> permissions = new HashSet<>(0);

    @ManyToMany(mappedBy = "permissions")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)//使用hibernate注解级联保存和更新
    private Set<Role> roles = new HashSet<>(0);

    @Transient
    private boolean isDown;

    public Integer getPid() {
        if(permission != null){
            return permission.getId();
        }else{
            return 0;
        }
    }

    public void setPid(Integer pid){
        if(permission == null){
            Permission permission = new Permission();
            permission.setId(pid);
            this.permission = permission;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getI18n() {
        return i18n;
    }

    public void setI18n(String i18n) {
        this.i18n = i18n;
    }

    public Boolean isGroup() {
        return group;
    }

    public void setGroup(Boolean group) {
        this.group = group;
    }

    public Boolean isHideInBreadcrumb() {
        return hideInBreadcrumb;
    }

    public void setHideInBreadcrumb(Boolean hideInBreadcrumb) {
        this.hideInBreadcrumb = hideInBreadcrumb;
    }

    public Boolean isShortcut_root() {
        return shortcut_root;
    }

    public void setShortcut_root(Boolean shortcut_root) {
        this.shortcut_root = shortcut_root;
    }

    public String getAcl() {
        return acl;
    }

    public void setAcl(String acl) {
        this.acl = acl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean isGeneratemenu() {
        return generatemenu;
    }

    public void setGeneratemenu(Boolean generatemenu) {
        this.generatemenu = generatemenu;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isDown() {
        return isDown;
    }

    public void setDown(boolean down) {
        isDown = down;
    }
}
