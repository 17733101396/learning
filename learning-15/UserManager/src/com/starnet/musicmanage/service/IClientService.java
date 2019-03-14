package com.starnet.musicmanage.service;

import java.util.List;

import com.starnet.musicmanage.model.Advertisement;
import com.starnet.musicmanage.model.ClientSong;
import com.starnet.musicmanage.model.Device;
import com.starnet.musicmanage.model.Song;

public interface IClientService {
	/*
	 * 设备上线
	 * @param device
	 * @return 上线成功返回1,失败返回0
	 */
	public int login(Device device);
	
	/*
	 * 设备下线
	 * @param device
	 * @return 下线成功返回1,失败返回0
	 */
	public int logout(Device device);
	
	/*
	 * 设备获取广告内容
	 * @param deviceID 设备ID
	 * @return List<Advertisement>
	 */
	public List<Advertisement> selectAD(String deviceID);
	
	/*
	 * 设备获取歌曲列表
	 * @param deviceID 设备ID
	 * @return List<Song> 歌曲数组
	 */
	public List<Song> selectSong(String deviceID);
	
	/*
	 * 查询全部歌曲并且添加一个收藏属性
	 * @param deviceID
	 * @return List<ClientSong>
	 */
	public List<ClientSong> selectAllSong(String deviceID);
	
	/*
	 * 客户端歌曲收藏管理
	 * @param ClientSong 
	 * @return 成功返回1失败返回0
	 */
	public int collectionManage(ClientSong clientSong);
	
	/*
	 * 批量取消收藏
	 * @param 参数1:deviceID 参数2:songList[]
	 * @return 成功返回1 失返回0
	 */
	public int cancleCollection(String deviceID,String[] songList);
}
