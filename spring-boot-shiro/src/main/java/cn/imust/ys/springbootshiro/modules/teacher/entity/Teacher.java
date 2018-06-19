package cn.imust.ys.springbootshiro.modules.teacher.entity;

import cn.imust.ys.springbootshiro.modules.system.entity.SysClass;

import java.util.Date;

import javax.persistence.*;

/**
 * 老师信息
 * */
@Entity
@Table(name = "ys_teacher")
public class Teacher {
	@Id @GeneratedValue
	private Integer tid; 
//	@Column(columnDefinition=("varchar(50) default null comment '老师信息--> 职工号'"))
	private String tno;
//	@Column(columnDefinition=("varchar(50) default null comment '老师信息--> 姓名'"))
	private String tname;
//	@Column(columnDefinition=("varchar(50) default null comment '老师信息--> 性别'"))
	private String gender;
//	@Column(columnDefinition=("varchar(255) default null comment '老师信息--> 名族'"))
	private String nation;
//	@Column(columnDefinition=("date default null comment '老师信息--> 出生年月'"))
	private Date birthday;
//	@Column(columnDefinition=("varchar(100) default null comment '老师信息--> 政治面貌'"))
	private String politicalStatus;
//	@Column(columnDefinition=("varchar(100) default null comment '老师信息--> 学历'"))
	private String schooling;
//	@Column(columnDefinition=("varchar(100) default null comment '老师信息--> 职称'"))
	private String title;
//	@Column(columnDefinition=("varchar(100) default null comment '老师信息--> 手机号'"))
	private String phoneNum;
//	@Column(columnDefinition=("varchar(100) default null comment '老师信息--> 所在部门'"))
	private String dept;
//	@Column(columnDefinition=("date default null comment '老师信息--> 参加工作时间'"))
	private Date inTime;

	@ManyToOne(targetEntity=SysClass.class)
	// ,columnDefinition=("int default null comment '资助信息--> 添加学生外键列'")
	@JoinColumn(name="sysclass_id")
	private SysClass sysClass;
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	public String getSchooling() {
		return schooling;
	}
	public void setSchooling(String schooling) {
		this.schooling = schooling;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public SysClass getSysClass() {
		return sysClass;
	}
	public void setSysClass(SysClass sysClass) {
		this.sysClass = sysClass;
	}
	public Teacher() {
		super();
	}
	
}
