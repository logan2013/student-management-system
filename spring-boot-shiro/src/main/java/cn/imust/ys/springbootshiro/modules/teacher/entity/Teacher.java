package cn.imust.ys.springbootshiro.modules.teacher.entity;

import cn.imust.ys.springbootshiro.modules.permission.entity.Role;
import cn.imust.ys.springbootshiro.modules.student.entity.Subsidize;
import cn.imust.ys.springbootshiro.modules.system.entity.SysClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * 老师信息
 * */
@Entity
@Table(name = "ys_teacher")
@JsonIgnoreProperties(value={"roles"})
public class Teacher implements Serializable {
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

	@Column(length=50)
	private String password;

	@ManyToMany(fetch=FetchType.EAGER)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})//使用hibernate注解级联保存和更新
	@JoinTable(name = "teacher_role",
			joinColumns = {@JoinColumn(name = "teacher_id")},//JoinColumns定义本方在中间表的主键映射
			inverseJoinColumns = {@JoinColumn(name = "role_id")})//inverseJoinColumns定义另一在中间表的主键映射
	private Set<Role> roles = new HashSet<>();

	@OneToMany(targetEntity=TeacherWithClass.class,mappedBy="teacher")
	private Set<TeacherWithClass> teacherWithClasses = new HashSet<>(0);

	@OneToMany(targetEntity=SysClass.class,mappedBy="teacher")
	private Set<SysClass> sysClasses = new HashSet<>(0);

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
	public Teacher() {
		super();
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

	public Set<TeacherWithClass> getTeacherWithClasses() {
		return teacherWithClasses;
	}

	public void setTeacherWithClasses(Set<TeacherWithClass> teacherWithClasses) {
		this.teacherWithClasses = teacherWithClasses;
	}

	public Set<SysClass> getSysClasses() {
		return sysClasses;
	}

	public void setSysClasses(Set<SysClass> sysClasses) {
		this.sysClasses = sysClasses;
	}
}
