package com.starnet.musicmanage.model;

public class ClientSong extends Song {
	private String deviceID;//设备ID
    private int collection;//是否收藏

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public int getCollection() {
		return collection;
	}

	public void setCollection(int collection) {
		this.collection = collection;
	}
    
}
