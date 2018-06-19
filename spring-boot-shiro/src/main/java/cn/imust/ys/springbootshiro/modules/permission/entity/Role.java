package cn.imust.ys.springbootshiro.modules.permission.entity;

import cn.imust.ys.springbootshiro.modules.teacher.entity.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties(value={"permissions","users"})
public class Role implements Serializable {

    @Id
    @GeneratedValue
    private Integer rid;
    @Column(length=20)
    private String rname;
    @Column(length=20)
    private String alias;
    private Date createTime;
    private Date updateTime;
    @Transient
    private int[] ps;

    @ManyToMany(fetch=FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})//使用hibernate注解级联保存和更新
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "role_id")},//JoinColumns定义本方在中间表的主键映射
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})//inverseJoinColumns定义另一在中间表的主键映射
    private Set<Permission> permissions = new HashSet<>(0);

    @ManyToMany(mappedBy = "roles")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)//使用hibernate注解级联保存和更新
    private Set<Teacher> teachers = new HashSet<>();

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Role(){}

    public Role(Integer rid){
        this.rid = rid;
    }

    public int[] getPs() {
        return ps;
    }

    public void setPs(int[] ps) {
        this.ps = ps;
    }
}
