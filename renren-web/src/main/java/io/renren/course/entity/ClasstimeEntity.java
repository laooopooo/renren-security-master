package io.renren.course.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;



/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-19 16:24:29
 */
public class ClasstimeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Integer classtimeId;
	
	private String classtimeName;
	
	@JSONField (format="HH:mm")
	private Date startTime;
	
	@JSONField (format="HH:mm")
	private Date endTime;
	
	private Integer tenantId;

	
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 设置：
	 */
	public void setClasstimeId(Integer classtimeId) {
		this.classtimeId = classtimeId;
	}
	/**
	 * 获取：
	 */
	public Integer getClasstimeId() {
		return classtimeId;
	}

	public String getClasstimeName() {
		return classtimeName;
	}
	public void setClasstimeName(String classtimeName) {
		this.classtimeName = classtimeName;
	}
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	/**
	 * 获取：
	 */
	public Integer getTenantId() {
		return tenantId;
	}
}
