package cn.imust.ys.springbootshiro.modules.student.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

import javax.persistence.*;

/**
 * 获奖情况
 * */
@Entity
@Table(name="student_award")
@JsonIgnoreProperties(value={"student"})
public class Award {
	
	@Id @GeneratedValue
	private Integer aid; // 表的主键 id
	@Column(columnDefinition=("date default null comment '获奖信息--> 获奖时间'"))
	private Date awardTime;
	@Column(columnDefinition=("varchar(255) default null comment '获奖信息--> 获奖详情'"))
	private String info;

	@Transient
	private String studentNum;
	
	@ManyToOne
	@JoinColumn(name="student_id",columnDefinition=("int default null comment '获奖信息--> 添加学生外键列'"))
	private Student student;

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Date getAwardTime() {
		return awardTime;
	}

	public void setAwardTime(Date awardTime) {
		this.awardTime = awardTime;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Award() {
		super();
	}

	public Award(Date awardTime, String info, Student student) {
		super();
		this.awardTime = awardTime;
		this.info = info;
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
