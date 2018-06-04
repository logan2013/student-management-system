package cn.imust.ys.springbootshiro.modules.student.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

import javax.persistence.*;

/**
 * 资助情况
 */
@Entity
@Table(name="student_subsidize")
@JsonIgnoreProperties(value={"student"})
public class Subsidize {
	
	@Id @GeneratedValue
	private Integer sid;  //资助情况主键 id
	
	@Column(columnDefinition=("date default null comment '资助信息--> 获得资助的时间'"))
	private Date stime;
	@Column(columnDefinition=("varchar(255) default null comment '资助信息--> 获得资助的详细情况'"))
	private String info;
	@Column(columnDefinition=("varchar(255) default null comment '资助信息--> 受助类型'"))
	private String type;
	@Column(columnDefinition=("varchar(255) default null comment '资助信息--> 受助等级'"))
	private String level;

	@Transient
	private String studentNum;
	
	@ManyToOne
	@JoinColumn(name="student_id",columnDefinition=("int default null comment '资助信息--> 添加学生外键列'"))
	private Student student;

	public Subsidize() {
		super();
	}

	public Subsidize(Date stime, String info, String type, String level, Student student) {
		super();
		this.stime = stime;
		this.info = info;
		this.type = type;
		this.level = level;
		this.student = student;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Date getStime() {
		return stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
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
