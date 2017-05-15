package io.renren.personel.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-11 13:29:59
 */
public class PositionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer positionId;
	//
	private String position;
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
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	/**
	 * 获取：
	 */
	public Integer getPositionId() {
		return positionId;
	}
	/**
	 * 设置：
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * 获取：
	 */
	public String getPosition() {
		return position;
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
