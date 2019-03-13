package com.starnet.musicmanage.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starnet.musicmanage.mapper.ADMapper;
import com.starnet.musicmanage.model.Advertisement;

/*
 * 将当前类注释层一个spring的bean
 */
@Service("adServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class ADServiceImpl implements IADService {
	
	private static final Logger log = Logger.getLogger(ADServiceImpl.class);
	/*
	 * 自动注入ADMapper
	 */
	@Autowired
	ADMapper adMapper;
	public List<Advertisement> listAllAD() {
		// TODO Auto-generated method stub
		try {
			return adMapper.selectAllAD();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("listAllAD异常");
		}
		return null;
	}

	public int addAD(String context) {
		// TODO Auto-generated method stub
		try {
			return adMapper.insertAD(context);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("addAD异常");
		}
		return 0;
	}

	public int updateAD(Advertisement advertisement) {
		// TODO Auto-generated method stub
		try {
			return adMapper.updateAD(advertisement);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("updateAD异常");
		}
		return 0;
	}

	public int deleteAD(List<Integer> ids) {
		// TODO Auto-generated method stub
		try {
			if (adMapper.deleteAD(ids)==ids.size()) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("deleteAD异常");
		}
		return 0;
	}

}
