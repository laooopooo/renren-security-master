package io.renren.personel.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-29 09:43:34
 */
public class SubjectEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer subjectId;
	//
	private String name;
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
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	/**
	 * 获取：
	 */
	public Integer getSubjectId() {
		return subjectId;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
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
