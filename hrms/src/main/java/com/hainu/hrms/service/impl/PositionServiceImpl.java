package com.hainu.hrms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hainu.hrms.mapper.PositionMapper;
import com.hainu.hrms.model.Position;
import com.hainu.hrms.service.PositionService;

@Service("positionService")
public class PositionServiceImpl implements PositionService {
	
	@Autowired
	PositionMapper positionMapper;
	@Override
	public List<Position> selectPositions() {
		// TODO Auto-generated method stub
		return positionMapper.selectPositions();
	}

	@Override
	public Integer deletePositions(List<Integer> list) {
		// TODO Auto-generated method stub
		return positionMapper.deletePositions(list);
	}

	@Override
	public Integer addPosition(Position position) {
		// TODO Auto-generated method stub
		return positionMapper.addPosition(position);
	}

	@Override
	public Integer updatePosition(Position position) {
		// TODO Auto-generated method stub
		return positionMapper.updatePosition(position);
	}

}
