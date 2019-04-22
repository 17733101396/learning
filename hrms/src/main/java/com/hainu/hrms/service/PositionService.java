package com.hainu.hrms.service;

import java.util.List;

import com.hainu.hrms.model.Position;


public interface PositionService {
	
	/**selectPositions
	 * 
	 * @param 
	 * @return Position的list
	 */
	public List<Position> selectPositions();
	
	/**deletePositions
	 * 
	 * @param id的list
	 * @return
	 */
	public Integer deletePositions(List<Integer> list);
	
		
	/**addPosition
	 * 
	 * @param position
	 * @return
	 */
	public Integer addPosition(Position position);
	
	/**updatePosition
	 * 
	 * @param position
	 * @return
	 */
	public Integer updatePosition(Position position);
}
