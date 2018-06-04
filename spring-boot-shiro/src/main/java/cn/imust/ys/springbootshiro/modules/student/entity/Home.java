package cn.imust.ys.springbootshiro.modules.student.entity;

import javax.persistence.*;

/**
 * 学生家庭信息
 * */
@Entity
@Table(name="student_home")
public class Home {
	@Id @GeneratedValue
	private Integer hid; // 家庭信息的主键 

	@Column(columnDefinition=("varchar(50) default null comment '家庭信息--> 姓名'"))
	private String name;
	@Column(columnDefinition=("varchar(255) default null comment '家庭信息--> 工作单位'"))
	private String workplace;
	@Column(columnDefinition=("varchar(255) default null comment '家庭信息--> 家庭住址'"))
	private String address;
	@Column(columnDefinition=("varchar(50) default null comment '家庭信息--> 联系电话'"))
	private String telNum;
	@Column(columnDefinition=("varchar(50) default null comment '家庭信息--> 微信号'"))
	private String wechat;
	
	@ManyToOne
	@JoinColumn(name="student_id",columnDefinition=("int default null comment '家庭信息--> 往家庭表中添加学生的主键'"))
	private Student student;
	
	public Integer getHid() {
		return hid;
	}
	public void setHid(Integer hid) {
		this.hid = hid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWorkplace() {
		return workplace;
	}
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public Home() {
		super();
	}
	
}
