package io.renren.personel.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-11 09:27:28
 */
public class ClassEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer classId;
	//班级别名
	private String className;
	//备注
	private String remarks;
	//班级-类别，小班，晚辅,一对一
	private String classType;
	//
	private Date lastUpdate;
	//
	private Integer teacherId;

	/**
	 * 设置：
	 */
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	/**
	 * 获取：
	 */
	public Integer getClassId() {
		return classId;
	}
	/**
	 * 设置：班级别名
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * 获取：班级别名
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置：班级-类别，小班，晚辅,一对一
	 */
	public void setClassType(String classType) {
		this.classType = classType;
	}
	/**
	 * 获取：班级-类别，小班，晚辅,一对一
	 */
	public String getClassType() {
		return classType;
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
	/**
	 * 设置：
	 */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	/**
	 * 获取：
	 */
	public Integer getTeacherId() {
		return teacherId;
	}
}
