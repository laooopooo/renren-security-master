package io.renren.course.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-18 19:15:21
 */
public class ClassroomEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer classroomId;
	//教室名称
	private String roomName;
	//教室容量
	private Integer roomCapacity;
	//
	private Date lastUpdate;
	
	private Integer tenantId;
	
	

	public Integer getTenantId() {
		return tenantId;
	}
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	/**
	 * 设置：
	 */
	public void setClassroomId(Integer classroomId) {
		this.classroomId = classroomId;
	}
	/**
	 * 获取：
	 */
	public Integer getClassroomId() {
		return classroomId;
	}
	/**
	 * 设置：教室名称
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	/**
	 * 获取：教室名称
	 */
	public String getRoomName() {
		return roomName;
	}
	/**
	 * 设置：教室容量
	 */
	public void setRoomCapacity(Integer roomCapacity) {
		this.roomCapacity = roomCapacity;
	}
	/**
	 * 获取：教室容量
	 */
	public Integer getRoomCapacity() {
		return roomCapacity;
	}
	/**
	 * 设置：
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	/**
	 * 获取：
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}
}
