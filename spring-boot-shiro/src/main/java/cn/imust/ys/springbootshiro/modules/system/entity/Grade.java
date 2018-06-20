package cn.imust.ys.springbootshiro.modules.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 年级信息
 * */
@Entity
@Table(name = "sys_grade")
@JsonIgnoreProperties(value={"major"})
public class Grade implements Serializable {
	
	@Id @GeneratedValue
	private Integer id;
//	@Column(columnDefinition=("varchar(100) default null comment '年级信息--> 年级名称'"))
	private String name;

	@ManyToOne
	@JoinColumn(name="major_id")
	private Major major;

	@OneToMany(targetEntity=SysClass.class,mappedBy="grade")
	private Set<SysClass> sysClazz = new HashSet<>(0);

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

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public void setGradeMajorId(int gradeMajorId){
		if(this.major == null){
			Major major = new Major();
			major.setId(gradeMajorId);
			this.major = major;
		}
	}

	public Set<SysClass> getSysClazz() {
		return sysClazz;
	}

	public void setSysClazz(Set<SysClass> sysClazz) {
		this.sysClazz = sysClazz;
	}

	public String getMajorName(){
		if(major != null){
			return major.getName();
		}else{
			return "";
		}
	}

	public int getMajorId(){
		if(major != null){
			return major.getId();
		}else{
			return 0;
		}
	}
}
