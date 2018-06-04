package cn.imust.ys.springbootshiro.modules.system.entity;

import javax.persistence.*;

/**
 * 年级信息
 * */
@Entity
@Table(name = "sys_grade")
public class Grade {
	
	@Id @GeneratedValue
	private Integer id;
	@Column(columnDefinition=("varchar(100) default null comment '年级信息--> 年级名称'"))
	private String name;
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
	public Grade() {
		super();
	}
	
}
