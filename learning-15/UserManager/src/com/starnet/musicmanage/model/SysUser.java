package com.starnet.musicmanage.model;

public class SysUser {
	private Long id;//id
	private String userName;//用户名
	private String sex;//性别
	private Integer age;//年龄
	private String phone;//电话
	private String address;//住址
	private String password;//密码
	private String power;//权限
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
