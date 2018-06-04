package cn.imust.ys.springbootshiro.modules.student.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="student_job")
@JsonIgnoreProperties(value={"student"})
public class Job {
    @Id @GeneratedValue
    private Integer jid; // 学生就业情况的主键 id

    @Column(columnDefinition=("date default null comment '就业信息--> 就业的时间'"))
    private Date jtime;
    @Column(columnDefinition=("varchar(150) default null comment '就业信息--> 就业单位'"))
    private String jobunit;
    @Column(columnDefinition=("varchar(155) default null comment '就业信息--> 档案转递地址'"))
    private String fileAddress;
    @Column(columnDefinition=("varchar(155) default null comment '就业信息--> 就业去向'"))
    private String jobwhere;
    @Column(columnDefinition=("varchar(100) default null comment '就业信息--> 就业方式'"))
    private String mode;

    @Transient
    private String jobsno;

    @ManyToOne
    @JoinColumn(name="student_id",columnDefinition=("int default null comment '就业信息--> 添加学生外键列'"))
    private Student student;

    public Integer getJid() {
        return jid;
    }

    public void setJid(Integer jid) {
        this.jid = jid;
    }

    public Date getJtime() {
        return jtime;
    }

    public void setJtime(Date jtime) {
        this.jtime = jtime;
    }

    public String getJobunit() {
        return jobunit;
    }

    public void setJobunit(String jobunit) {
        this.jobunit = jobunit;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public String getJobwhere() {
        return jobwhere;
    }

    public void setJobwhere(String jobwhere) {
        this.jobwhere = jobwhere;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getJobsno() {
        return jobsno;
    }

    public void setJobsno(String jobsno) {
        this.jobsno = jobsno;
    }

    public String getSysClassName(){
        if(student !=null){
            return this.student.getSysClass().getName();
        }
        return "";
    }

    public String getGradeName(){
        if(student !=null){
            return this.student.getGrade();
        }
        return "";
    }

    public String getMajorName(){
        if(student !=null){
            return this.student.getMajor();
        }
        return "";
    }

    public String getSno(){
        if(student !=null){
            return this.student.getSno();
        }
        return "";
    }

    public String getSname(){
        if(student !=null){
            return this.student.getSname();
        }
        return "";
    }

}
