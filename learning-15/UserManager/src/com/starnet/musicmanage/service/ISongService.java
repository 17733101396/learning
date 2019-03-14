package com.starnet.musicmanage.service;

import java.util.List;

import com.starnet.musicmanage.model.Song;

public interface ISongService {
	/*
	 * 添加歌曲
	 * @param song
	 * @return 添加成功返回1,失败返回0
	 */
	public int addSong(Song song);
	
	/*
	 * 查询所有歌曲
	 * @return 查询到的所有song对象的 list
	 */
	public List<Song> listAllSong();
	
	/*
	 * 删除歌曲
	 * @param ids
	 * @return 删除成功返回1,失败返回0
	 */
	public int deleteSong(List<Integer> ids);
	
	/*
	 * 更新歌曲
	 * @param song
	 * @return 更新成功返回1,失败返回0
	 */
	public int updateSong(Song song);
}
