package com.starnet.musicmanage.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.starnet.musicmanage.mapper.ADMapper;
import com.starnet.musicmanage.mapper.DeviceMapper;
import com.starnet.musicmanage.mapper.SongMapper;
import com.starnet.musicmanage.model.Advertisement;
import com.starnet.musicmanage.model.ClientSong;
import com.starnet.musicmanage.model.Device;
import com.starnet.musicmanage.model.Song;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
public class TestUnit {
	private static final Logger log = Logger.getLogger(TestUnit.class);

	@Autowired
	DeviceMapper deviceMapper;
	@Autowired
	SongMapper songMapper;
	@Autowired
	ADMapper adMapper;
	
	//@Test
	public void selectContext() {
		String adList = deviceMapper.selectAdListByDeviceID("ij");
		List<Integer> list = new ArrayList<Integer>();
		log.debug(adList);
		for (String i : adList.split(",")) {
			list.add(Integer.parseInt(i));
		}
		log.debug(list);
		List<Advertisement> list2 = adMapper.selectADById(list);
		JSONArray jsonArray = new JSONArray();
		for (Advertisement ad:list2) {
			jsonArray.add(ad.getContext());
		}
		log.debug(jsonArray);
	}
	
	//@Test
	public void selectSong() {
		String songList = deviceMapper.selectSongListByDeviceID("ij");
		List<Integer> list = new ArrayList<Integer>();
		log.debug(songList);
		for (String i : songList.split(",")) {
			list.add(Integer.parseInt(i));
		}
		log.debug(list);
		List<Song> list2 = songMapper.selectSongById(list);
		JSONArray jsonArray = new JSONArray();
		for (Song song:list2) {
			jsonArray.add(song);
		}
		log.debug(jsonArray);
	}
	
	//@Test
	public void selectAllAD() {
		List<Advertisement> list = adMapper.selectAllAD();
		JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
		for (Object o : jsonArray) {
			log.debug(o);
		}
	}
	
	//@Test
	public void addAD() {
		int res = adMapper.insertAD("hello");
		log.debug(res);
	}
	
	//@Test
	public void updateAD() {
		Advertisement advertisement= new Advertisement();
		advertisement.setId(2);
		advertisement.setContext("How do you do!");
		int res = adMapper.updateAD(advertisement);
		log.debug(res);
	}
	
	//@Test
	public void deleteAD() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		int res = adMapper.deleteAD(list);
		log.debug(res);
	}
	
	//@Test
	public void loginUpdateType(){
		Device device = new Device();
		device.setDeviceID("ig");
		device.setTime("2019-07-09");
		int res = deviceMapper.loginUpdateType(device);
		log.debug(res);
	}
	
	//@Test
	public void logoutUpdateType() {
		Device device = new Device();
		device.setDeviceID("ig");
		device.setTime("2019-07-20");
		int res = deviceMapper.logoutUpdateType(device);
		log.debug(res);
	}
	
	//@Test
	public void listAll() {
		List<Device> list = deviceMapper.selectAllDevice(1);
		JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
		for (Object o : jsonArray) {
			log.debug(o);
		}
	}
	
	//@Test
	public void addDevice() {
		Device device = new Device();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(new Date());
		device.setDeviceID("5555555");
		device.setDeviceName("aaaaaaaa");
		device.setDeviceType(0);
		device.setType(0);
		device.setTime(time);
		int ret = deviceMapper.insertDevice(device);
		log.debug(ret);
	}
	
	//@Test
	public void updateDevice() {
		Device device =new Device();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(new Date());
		device.setId(13);
		device.setDeviceID("5555sss");
		device.setDeviceName("aassss");
		device.setDeviceType(0);
		device.setType(0);
		device.setTime(time);
		int ret = deviceMapper.updateDevice(device);
		log.debug(ret);
	}
	
	//@Test
	public void selectAllSong() {
		List<Song> list = songMapper.selectAllSong();
		JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
		for (Object o : jsonArray) {
			log.debug(o);
		}
	}
	
	//@Test
	public void updateSong() {
		Song song = new Song();
		song.setSongID(1);
		song.setSongName("生日快乐");
		log.debug(songMapper.updateSong(song));
	}
	
	//@Test
	public void addSong() {
		Song song = new Song();
		song.setSongName("生日快乐");
		log.debug(songMapper.insertSong(song));
	}
	
	//@Test
	public void deleteSong() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(3);
		log.debug(songMapper.deleteSong(list));
	}
	
	//@Test
	public void testSong() {
		String songList = deviceMapper.selectSongListByDeviceID("ij");
		log.debug(songList);
		List<Integer> list = new ArrayList<Integer>();
		List<Song> list2 = new ArrayList<Song>();
		List<ClientSong> list3 = new ArrayList<ClientSong>();
		log.debug(songList);
		if (!songList.isEmpty()) {
			for (String i : songList.split(",")) {
				list.add(Integer.parseInt(i));
			}
			log.debug(list);
		}else {
			log.debug("list为空");
		}
		list2 = songMapper.selectAllSong();
		log.debug(list2);
		for (Song song : list2) {
			ClientSong clientSong = JSONObject.parseObject(JSONObject.toJSONString(song),ClientSong.class);
			int songID = song.getSongID();
			if (list.contains(songID)) {
				clientSong.setCollection(1);
			}else {
				clientSong.setCollection(0);
			}
			list3.add(clientSong);
		}
		log.debug(list3);
	}
	
	//@Test
	public void testSongCollection() {
		String deviceID = "ij";
		int songID = 5;
		int col = 1;
		String songList = deviceMapper.selectSongListByDeviceID(deviceID);
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		String list3 = null;
		log.debug(songList);
		if (!songList.isEmpty()) {
			for (String i : songList.split(",")) {
				list.add(Integer.parseInt(i));
			}
			log.debug(list);
		}
		if (col==0) {
			for (Integer integer : list) {
				if (integer!=songID) {
					list2.add(integer);
				}
			}
			list3 = StringUtils.join(list2.toArray(),",");
		} else if (col==1) {
			if (!list.contains(songID)) {
				list.add(songID);
			}else {
				log.debug("没变");
			}
			list3 = StringUtils.join(list.toArray(),",");
		} 

		log.debug(list3);
		Device device = new Device();
		device.setDeviceID(deviceID);
		device.setSongList(list3);
		deviceMapper.updateDeviceSongList(device);
	}
	
	@Test
	public void cancleCollection() {
		String deviceID="ij";
		String[] songList = {"4"};//接受的取消收藏歌曲数组
		String songList2 = deviceMapper.selectSongListByDeviceID(deviceID);//设备收藏的歌曲数组
		List<Integer> songList3 = new ArrayList<Integer>();//songList2转化的List
		String songList4= null;//提交给数据库songList4转化的字符串
		for (String id : songList2.split(",")) {
			songList3.add(Integer.valueOf(id));
		}
		log.debug(songList3);
		for (String id : songList) {
			for (int i = 0; i < songList3.size(); i++) {
				if (songList3.get(i)==Integer.parseInt(id)) {
					songList3.remove(i);
				}
			}
		}
		log.debug(songList3);
		songList4 = StringUtils.join(songList3.toArray(),",");
		Device device = new Device();
		device.setDeviceID(deviceID);
		device.setSongList(songList4);
		int ret =deviceMapper.updateDeviceSongList(device);
		log.debug(ret);
	}
	
}
