package com.hainu.hrms.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hainu.hrms.model.Position;


@Repository
public interface PositionMapper {
	
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
