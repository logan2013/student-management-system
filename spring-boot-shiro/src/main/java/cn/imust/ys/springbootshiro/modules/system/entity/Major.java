package cn.imust.ys.springbootshiro.modules.system.entity;

import javax.persistence.*;

/**
 * 专业
 * */
@Entity
@Table(name="sys_major")
public class Major {
	
	@Id @GeneratedValue
	private Integer id;
	@Column(columnDefinition=("varchar(100) default null comment '专业信息--> 专业名称'"))
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
	public Major() {
		super();
	}

}
