package cn.imust.ys.springbootshiro.modules.teacher.entity;

import cn.imust.ys.springbootshiro.modules.student.entity.Subsidize;
import cn.imust.ys.springbootshiro.modules.system.entity.SysClass;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 老师和班级的关联表，用于存储老师的带班记录
 * */
@Entity
public class TeacherWithClass {

    @Id @GeneratedValue
    private Integer id;

    private Date startTime;

    private Date endTime;

    @ManyToOne(targetEntity=Teacher.class)
    // ,columnDefinition=("int default null comment '老师带班信息--> 添加老师外键列'")
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @ManyToMany(fetch=FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})//使用hibernate注解级联保存和更新
    @JoinTable(name = "teacherwithsysclass_sysclass",
            joinColumns = {@JoinColumn(name = "twc_id")},//JoinColumns定义本方在中间表的主键映射
            inverseJoinColumns = {@JoinColumn(name = "sysclass_id")})//inverseJoinColumns定义另一在中间表的主键映射)
    private Set<SysClass> sysClassSet = new HashSet<>(0);

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<SysClass> getSysClassSet() {
        return sysClassSet;
    }

    public void setSysClassSet(Set<SysClass> sysClassSet) {
        this.sysClassSet = sysClassSet;
    }
}
