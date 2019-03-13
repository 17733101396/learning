package com.starnet.musicmanage.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.starnet.musicmanage.mapper.ADMapper;
import com.starnet.musicmanage.mapper.DeviceMapper;
import com.starnet.musicmanage.mapper.SongMapper;
import com.starnet.musicmanage.model.Advertisement;
import com.starnet.musicmanage.model.ClientSong;
import com.starnet.musicmanage.model.Device;
import com.starnet.musicmanage.model.Song;


/*
 * 将当前类注释为一个 Spring 的 bean
 */
@Service("clientServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class ClientServiceImpl implements IClientService {
	
	private static final Logger log = Logger.getLogger(ClientServiceImpl.class);
	@Autowired
	DeviceMapper deviceMapper;
	@Autowired
	ADMapper adMapper;
	@Autowired
	SongMapper songMapper;
	
	public int login(Device device) {
		// TODO Auto-generated method stub
		try {
			return deviceMapper.loginUpdateType(device);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("DeviceServiceImpl-login异常");
		}
		return 0;
	}
	

	public int logout(Device device) {
		// TODO Auto-generated method stub
		try {
			return deviceMapper.logoutUpdateType(device);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("DeviceServiceImpl-logout异常");
		}
		return 0;
	}

	public List<Advertisement> selectAD(String deviceID) {
		// TODO Auto-generated method stub
		try {
			String adList = deviceMapper.selectAdListByDeviceID(deviceID);//获取该设备的官高id列表
			List<Integer> list = new ArrayList<Integer>();
			List<Advertisement> list2 = null;
			log.debug(adList);
			if (!adList.isEmpty()) {
				//将广告id列表转化为List<Integer>
				for (String i : adList.split(",")) {
					list.add(Integer.parseInt(i));
				}
				log.debug(list);
				//根据广告id列表查询广告
				list2 = adMapper.selectADById(list);
				return list2;
			}else {
				log.debug("list为空");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("selectAD异常");
		}
		return null;
	}


	public List<Song> selectSong(String deviceID) {
		// TODO Auto-generated method stub
		try {
			String songList = deviceMapper.selectSongListByDeviceID(deviceID);//根据设备id获取该设备的歌曲列表
			List<Integer> list = new ArrayList<Integer>();
			List<Song> list2 = new ArrayList<Song>();
			log.debug(songList);
			//将歌曲id列表转化为List<Integer> 
			if (!songList.isEmpty()) {
				for (String i : songList.split(",")) {
					list.add(Integer.parseInt(i));
				}
				log.debug(list);
				//根据歌曲id列表查询歌曲
				list2 = songMapper.selectSongById(list);
				log.debug(list2);
			}else {
				log.debug("list为空");
			}
			return list2;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("ClientServiceImpl-selectSong异常");
		}
		return null;
	}


	public List<ClientSong> selectAllSong(String deviceID) {
		// TODO Auto-generated method stub
		try {
			String songList = deviceMapper.selectSongListByDeviceID(deviceID);//根据设备id获取该设备的歌曲列表
			List<Integer> list = new ArrayList<Integer>();
			List<Song> list2 = new ArrayList<Song>();
			List<ClientSong> list3 = new ArrayList<ClientSong>();
			log.debug(songList);
			if (!songList.isEmpty()) {//将歌曲id列表转化为List<Integer> 
				for (String i : songList.split(",")) {
					list.add(Integer.parseInt(i));
				}
				log.debug(list);
			}else {
				log.debug("list为空");
			}
			list2 = songMapper.selectAllSong();//查询全部歌曲
			log.debug(list2);
			//遍历曲库的歌曲并加上是否被收藏的属性
			for (Song song : list2) {
				//把Song类对象转化为子类ClientSong并为收藏属性赋值
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
			return list3;	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("ClientServiceImpl-selectAllSong异常");
		}
		return null;
	}


	public int collectionManage(ClientSong clientSong) {
		// TODO Auto-generated method stub
		try {
			String deviceID = clientSong.getDeviceID();
			int songID = clientSong.getSongID();
			int col = clientSong.getCollection();
			String songList = deviceMapper.selectSongListByDeviceID(deviceID);//根据设备id获取该设备的歌曲列表
			List<Integer> list = new ArrayList<Integer>();
			List<Integer> list2 = new ArrayList<Integer>();
			String list3 = null;
			log.debug(songList);
			if (!songList.isEmpty()) {//将歌曲id列表转化为List<Integer> 
				for (String i : songList.split(",")) {
					list.add(Integer.parseInt(i));
				}
				log.debug(list);
			}
			if (col==0) {//如果是取消收藏,创建一个新数组并且加入除取消的songID之外的所有songID
				for (Integer integer : list) {
					if (integer!=songID) {
						list2.add(integer);
					}
				}
				list3 = StringUtils.join(list2.toArray(),",");//将数组转化为逗号分隔的字符串
			} else if (col==1) {//如果是添加收藏,收藏列表直接添加该songID
				if (!list.contains(songID)) {
					list.add(songID);
				}else {
					log.debug("没变");
				}
				list3 = StringUtils.join(list.toArray(),",");//将数组转化为逗号分隔的字符串
			} 

			log.debug(list3);
			Device device = new Device();
			device.setDeviceID(deviceID);
			device.setSongList(list3);
			return deviceMapper.updateDeviceSongList(device);//更新数据库设备收藏的歌曲列表
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("ClientServiceImpl-collectionManage异常");
		}
		return 0;
	}


	public int cancleCollection(String deviceID, String[] songList) {
		// TODO Auto-generated method stub
		try {
			String songList2 = deviceMapper.selectSongListByDeviceID(deviceID);//设备收藏的歌曲数组
			List<Integer> songList3 = new ArrayList<Integer>();//songList2转化的List
			String songList4= null;//提交给数据库songList4转化的字符串
			//将收藏的歌曲列表转化为List<Integer>
			for (String id : songList2.split(",")) {
				songList3.add(Integer.valueOf(id));
			}
			log.debug(songList3);
			//遍历取消收藏的歌曲列表,并从收藏的列表中移除
			for (String id : songList) {
				for (int i = 0; i < songList3.size(); i++) {
					if (songList3.get(i)==Integer.parseInt(id)) {
						songList3.remove(i);
					}
				}
			}
			log.debug(songList3);
			songList4 = StringUtils.join(songList3.toArray(),",");//将新的收藏列表转化为逗号分隔的字符串
			Device device = new Device();
			device.setDeviceID(deviceID);
			device.setSongList(songList4);
			int ret =deviceMapper.updateDeviceSongList(device);//数据库更新设备的歌曲收藏列表
			log.debug(ret);
			return ret;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("ClientServiceImpl-cancleCollection异常");
		}
		return 0;
	}
	
	
}
