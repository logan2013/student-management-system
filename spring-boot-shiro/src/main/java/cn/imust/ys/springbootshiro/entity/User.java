package cn.imust.ys.springbootshiro.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

@Entity
@JsonIgnoreProperties(value={"roles"})
public class User implements Serializable {

    @Id @GeneratedValue
    private Integer uid;

    private String username;

    private String password;

    @ManyToMany(fetch=FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})//使用hibernate注解级联保存和更新
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},//JoinColumns定义本方在中间表的主键映射
            inverseJoinColumns = {@JoinColumn(name = "role_id")})//inverseJoinColumns定义另一在中间表的主键映射
    private Set<Role> roles = new HashSet<>();

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User(){

    }

    public User(Integer uid, String username){
        this.uid = uid;
        this.username = username;
    }
}
