package com.starnet.musicmanage.model;



public class Device {
	private int id;
	private String deviceID;//设备mac地址
	private String deviceName;//设备IP地址
	private int deviceType;//设备类型:0代表android手机端；1代表开发板设备
	private int type;//设备状态:0代表下线，1代表上线
	private String time;//设备最后操作起始时间
	private String adList;//刚刚列表
	private String songList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public int getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAdList() {
		return adList;
	}
	public void setAdList(String adList) {
		this.adList = adList;
	}
	public String getSongList() {
		return songList;
	}
	public void setSongList(String songList) {
		this.songList = songList;
	}
	
}
