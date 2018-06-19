package cn.imust.ys.springbootshiro.modules.student.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

import javax.persistence.*;

/**
 * 处分信息
 * */
@Entity
@Table(name="student_punishment")
@JsonIgnoreProperties(value={"student"})
public class Punishment {

	@Id @GeneratedValue
	private Integer pid; // 处分信息表的主键 ID
	
//	@Column(columnDefinition=("date default null comment '处分信息--> 处分时间'"))
	private String ptime;
//	@Column(columnDefinition=("varchar(255) default null comment '处分信息--> 处分种类'"))
	private String ptype;
//	@Column(columnDefinition=("varchar(255) default null comment '处分信息--> 发文号'"))
	private String pno;
//	@Column(columnDefinition=("varchar(255) default null comment '处分信息--> 受处分原因描述'"))
	private String info;
//	@Column(columnDefinition=("date default null comment '处分信息--> 解除处分时间'"))
	private Date removeTime;

	@Transient
	private String studentNum;

	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;

	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPtime() {
		return ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}

	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public String getPno() {
		return pno;
	}
	public void setPno(String pno) {
		this.pno = pno;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Date getRemoveTime() {
		return removeTime;
	}
	public void setRemoveTime(Date removeTime) {
		this.removeTime = removeTime;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Punishment() {
		super();
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
