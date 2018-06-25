package cn.imust.ys.springbootshiro.modules.system.entity;

import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import cn.imust.ys.springbootshiro.modules.teacher.entity.Teacher;
import cn.imust.ys.springbootshiro.modules.teacher.entity.TeacherWithClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(value={"teacher","grade","students"})
public class SysClass implements Serializable {

	@Id @GeneratedValue
	private Integer scid;
	
//	@Column(columnDefinition=("varchar(50) default null comment '班级信息--> 班级名称'"))
	private String name;

//	@Column(columnDefinition=("varchar(50) default null comment '班级信息--> 班级全名称'"))
	private String allName;
	
	@OneToMany(targetEntity=Student.class,mappedBy="sysClass")
	private Set<Student> students = new HashSet<>(0);
	
	@ManyToOne
	@JoinColumn(name="teacher_id")
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Teacher teacher;// 班级负责老师

	@ManyToOne
	@JoinColumn(name="grade_id")
	private Grade grade;

	@OneToMany(targetEntity=TeacherWithClass.class,mappedBy="sysClass")
	private Set<TeacherWithClass> withClassSet = new HashSet<>(0);

	public SysClass(Teacher teacher) {
		this.teacher = teacher;
	}

	public Integer getScid() {
		return scid;
	}

	public void setScid(Integer scid) {
		this.scid = scid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public SysClass() {
		super();
	}

	public String getAllName() {
		return allName;
	}

	public void setAllName(String allName) {
		this.allName = allName;
	}

	public Set<TeacherWithClass> getWithClassSet() {
		return withClassSet;
	}

	public void setWithClassSet(Set<TeacherWithClass> withClassSet) {
		this.withClassSet = withClassSet;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public String getGradeName(){
		if(grade != null && grade.getMajor() != null) {
			return grade.getMajor().getName() + "/" + grade.getName();
		}else{
			return "";
		}
	}

	public void setClassGradeId(int classGradeId){
		if(this.grade == null){
			Grade grade = new Grade();
			grade.setId(classGradeId);
			this.grade = grade;
		}
	}

	public int[] getClassGradeId(){
		int[] arr = new int[2];
		if(this.grade != null && grade.getMajor() != null){
			arr[0] = grade.getMajor().getId();
			arr[1] = grade.getId();
		}
		return arr;
	}

	public String getTeacherName(){
		if(teacher !=null ){
			return teacher.getTname();
		}
		return "";
	}

	public void setTid(int tid){
		if(teacher == null){
			Teacher teacher = new Teacher();
			teacher.setTid(tid);
			this.teacher = teacher;
		}
	}

}
