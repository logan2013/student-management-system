package cn.imust.ys.springbootshiro.modules.system.entity;

import cn.imust.ys.springbootshiro.modules.student.entity.Award;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 专业
 * */
@Entity
@Table(name="sys_major")
public class Major implements Serializable {
	
	@Id @GeneratedValue
	private Integer id;
//	@Column(columnDefinition=("varchar(100) default null comment '专业信息--> 专业名称'"))
	private String name;

	@OneToMany(targetEntity=Grade.class,mappedBy="major")
	private Set<Grade> grades = new HashSet<>(0);
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Major() {
		super();
	}

	public Set<Grade> getGrades() {
		return grades;
	}

	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}


}
