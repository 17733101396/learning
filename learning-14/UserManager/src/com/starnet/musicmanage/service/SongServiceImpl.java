package com.starnet.musicmanage.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starnet.musicmanage.mapper.SongMapper;
import com.starnet.musicmanage.model.Song;

/*
 * 将当前类注释为一个 Spring 的 bean
 */
@Service("songServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class SongServiceImpl implements ISongService {
	Logger log = Logger.getLogger(SongServiceImpl.class);
	/*
	 * 自动注入SongMapper
	 */
	@Autowired
	SongMapper songMapper;
	
	public int addSong(Song song) {
		// TODO Auto-generated method stub
		try {
			return songMapper.insertSong(song);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("addSonog异常");
		}
		return 0;
	}

	public List<Song> listAllSong() {
		// TODO Auto-generated method stub
		try {
			return songMapper.selectAllSong();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("listAllSong异常");
		}
		return null;
	}

	public int deleteSong(List<Integer> ids) {
		// TODO Auto-generated method stub
		try {
			if (songMapper.deleteSong(ids)==ids.size()) {
				return 1;
			}else {
				return 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("deleteSong异常");
		}
		return 0;
	}

	public int updateSong(Song song) {
		// TODO Auto-generated method stub
		try {
			return songMapper.updateSong(song);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("updateSong异常");
		}
		return 0;
	}

}
