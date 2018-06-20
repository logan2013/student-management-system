package cn.imust.ys.springbootshiro.modules.teacher.entity;

import cn.imust.ys.springbootshiro.modules.student.entity.Subsidize;
import cn.imust.ys.springbootshiro.modules.system.entity.SysClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 老师和班级的关联表，用于存储老师的带班记录
 * */
@Entity
@JsonIgnoreProperties(value={"teacher","sysClass"})
public class TeacherWithClass implements Serializable {

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

    public String getNzStatus() {
        return nzStatus;
    }

    public void setNzStatus(String nzStatus) {
        this.nzStatus = nzStatus;
    }

    public SysClass getSysClass() {
        return sysClass;
    }

    public void setSysClass(SysClass sysClass) {
        this.sysClass = sysClass;
    }

    public int getNzPercent(){
        double all_between_days=(endTime.getTime() - startTime.getTime())/(1000*3600*24);
        double new_between_days=(new Date().getTime() - startTime.getTime())/(1000*3600*24);
        return Integer.parseInt(String.valueOf((int) Math.floor( new_between_days/all_between_days*100)));
    }

    public String getClassAllName(){
        if( this.sysClass != null){
            return sysClass.getAllName();
        }else{
            return "";
        }
    }
}
