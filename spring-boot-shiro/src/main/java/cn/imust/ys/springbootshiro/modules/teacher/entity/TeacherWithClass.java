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

    private String nzStatus; // 带班状态 ， 完成， 中断 ， 进行中

    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name="class_id")
    private SysClass sysClass;

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

}
