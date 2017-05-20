package io.renren.course.entity;

import java.io.Serializable;

public class RemainRoomEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 213325728363206263L;
	
	private Integer weekId;
	private Integer remainNumber;
	public Integer getWeekId() {
		return weekId;
	}
	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}
	public Integer getRemainNumber() {
		return remainNumber;
	}
	public void setRemainNumber(Integer remainNumber) {
		this.remainNumber = remainNumber;
	}
	
	

}
