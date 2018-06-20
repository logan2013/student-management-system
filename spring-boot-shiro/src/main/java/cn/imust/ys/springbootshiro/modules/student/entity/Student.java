package cn.imust.ys.springbootshiro.modules.student.entity;

import cn.imust.ys.springbootshiro.modules.system.entity.SysClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties(value={"sysClass"})
public class Student {
    @Id @GeneratedValue
    private Integer id; // 学生表 ID
    // columnDefinition=("varchar(20) default null comment '学生信息--> 学号'"),
    @Column(name = "studentid")
    private String sno;
    // columnDefinition=("varchar(20) default null comment '学生信息--> 姓名'"),
    @Column(name="studentname")
    private String sname;

    private String sid;
    private String cid;
    // columnDefinition=("varchar(5) default null comment '学生信息--> 性别'"),
    @Column(name="sex")
    private String gender;
    // columnDefinition=("varchar(50) default null comment '学生信息--> 宿舍号'"),
    @Column(name="dorm")
    private String dorm;
    // columnDefinition=("varchar(11) default null comment '学生信息--> 联系电话'"),
    @Column(name="m_tel")
    private String phoneNum;
//    @Column(columnDefinition=("varchar(15) default null comment '学生信息--> qq'"))
    private String qqNum;
    // columnDefinition=("varchar(100) default null comment '学生信息--> 备注'"),
    @Column(name="remark")
    private String remark;
    // columnDefinition=("varchar(100) default null comment '学生信息--> 家庭详细地址'"),
    @Column(name="address")
    private String address;
    // columnDefinition=("varchar(20) default null comment '学生信息--> 身份证号'"),
    @Column(name="idcard")
    private String idcard;
//    @Column(columnDefinition=("varchar(20) default null comment '学生信息--> 微信号'"))
    private String wechat;
    // columnDefinition=("date default null comment '学生信息--> 出生日期'"),
    @Column(name="birth")
    private String birthday;
    // columnDefinition=("varchar(10) default null comment '学生信息--> 民族'"),
    @Column(name="nation")
    private String nation;
//    @Column(columnDefinition=("varchar(20) default null comment '学生信息--> 状态 表示的是学生的学籍状态，有“在校”，“休学”，“退学”，“降级”'"))
    private String status;// 状态 表示的是学生的学籍状态，有“在校”，“休学”，“退学”，“降级”
//    @Column(columnDefinition=("varchar(50) default null comment '学生信息--> 政治面貌'"))
    private String politicalStatus;
    // columnDefinition=("varchar(20) default null comment '学生信息--> 年级'"),
    @Column(name="gid")
    private String grade;
//    @Column(columnDefinition=("varchar(50) default null comment '学生信息--> 专业名称'"))
    private String major;

    @OneToMany(targetEntity=Home.class,mappedBy="student")//指定一对多关系
    @Cascade(value={CascadeType.SAVE_UPDATE})         //设定级联关系
    private Set<Home> homes = new HashSet<>(0);//家庭信息

    @OneToMany(targetEntity=Award.class,mappedBy="student")
    private Set<Award> awards = new HashSet<>(0);//获奖情况

    @OneToMany(targetEntity=Punishment.class,mappedBy="student")
    private Set<Punishment> punishments = new HashSet<>(0);//处分情况

    @OneToMany(targetEntity=Subsidize.class,mappedBy="student")
    private Set<Subsidize> subsidizes = new HashSet<>(0);// 资助情况

    @OneToMany(targetEntity=Job.class,mappedBy="student")
    private Set<Job> jobs = new HashSet<>(0);//就业情况

    @ManyToOne(targetEntity=SysClass.class)
    private SysClass sysClass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public Set<Home> getHomes() {
        return homes;
    }

    public void setHomes(Set<Home> homes) {
        this.homes = homes;
    }

    public Set<Award> getAwards() {
        return awards;
    }

    public void setAwards(Set<Award> awards) {
        this.awards = awards;
    }

    public Set<Punishment> getPunishments() {
        return punishments;
    }

    public void setPunishments(Set<Punishment> punishments) {
        this.punishments = punishments;
    }

    public Set<Subsidize> getSubsidizes() {
        return subsidizes;
    }

    public void setSubsidizes(Set<Subsidize> subsidizes) {
        this.subsidizes = subsidizes;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public SysClass getSysClass() {
        return sysClass;
    }

    public void setSysClass(SysClass sysClass) {
        this.sysClass = sysClass;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSysClassAllName(){
        if(this.sysClass != null){
            return  this.sysClass.getAllName();
        }
        return "";
    }

    public String getSysClassName(){
        if(this.sysClass != null){
            return  this.sysClass.getName();
        }
        return "";
    }

    public String getJobwhere(){
        return "";
    }
}

