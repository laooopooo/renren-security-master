package io.renren.personel.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-11 09:27:29
 */
public class StudentClassEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer studentClassId;
	//
	private Date lastUpdate;
	//
	private Integer studentId;
	//
	private Integer classId;

	/**
	 * 设置：
	 */
	public void setStudentClassId(Integer studentClassId) {
		this.studentClassId = studentClassId;
	}
	/**
	 * 获取：
	 */
	public Integer getStudentClassId() {
		return studentClassId;
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
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	/**
	 * 获取：
	 */
	public Integer getStudentId() {
		return studentId;
	}
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
}
