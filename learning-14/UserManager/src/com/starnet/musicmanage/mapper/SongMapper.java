package com.starnet.musicmanage.mapper;

import java.util.List;

import com.starnet.musicmanage.model.Song;

public interface SongMapper {
	
	/*
	 * 根据id查询歌曲
	 * @param list 歌曲id列表
	 * @param list 歌曲信息列表
	 */
	public List<Song> selectSongById(List<Integer> list);
	
	/*
	 * 查询全部歌曲
	 * @return List<Song> 查询到的所有 song对象的 list
	 */
	public List<Song> selectAllSong() ;
	
	/*
	 * 添加歌曲
	 * @param song
	 * @return 插入成功返回1,失败返回0
	 */
    public int insertSong(Song song);
    
    /*
     * 删除歌曲
     * @param ids id的List
     * @return 删除成功返回1,失败返回0
     */
    public int deleteSong(List<Integer> ids) ;
    
    /*
     * 更新歌曲信息
     * @param song
     * @return 更新成功返回1,失败返回0
     */
    public int updateSong(Song song);
}
