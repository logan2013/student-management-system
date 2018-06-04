package cn.imust.ys.springbootshiro.modules.system.entity;

import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import cn.imust.ys.springbootshiro.modules.teacher.entity.Teacher;
import cn.imust.ys.springbootshiro.modules.teacher.entity.TeacherWithClass;
import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class SysClass {
	
	@Id @GeneratedValue
	private Integer scid;
	
	@Column(columnDefinition=("varchar(50) default null comment '班级信息--> 班级名称'"))
	private String name;

	@Column(columnDefinition=("varchar(50) default null comment '班级信息--> 班级全名称'"))
	private String allName;
	
	@OneToMany(targetEntity=Student.class,mappedBy="sysClass")
//	@JoinColumn(name="id",columnDefinition=("int default null comment '班级信息--> 往班级表中添加学生的主键'"))
	private Set<Student> students = new HashSet<>(0);
	
	@ManyToOne
	@JoinColumn(name="teacher_id",columnDefinition=("int default null comment '班级信息--> 添加老师外键列'"))
	private Teacher teacher;// 班级负责老师

	@ManyToMany(mappedBy = "sysClassSet")
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)//使用hibernate注解级联保存和更新
	private Set<TeacherWithClass> withClassSet = new HashSet<>(0);

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
}
