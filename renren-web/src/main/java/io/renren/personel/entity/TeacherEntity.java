package io.renren.personel.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;



/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-11 13:30:01
 */
public class TeacherEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer teacherId;
	//姓名
	private String name;
	//教龄
	private Float teachAge;
	//
	private Date lastUpdate;
	//出生日期
	@JSONField (format="yyyy-MM-dd")
	private Date born;
	//性别
	private String sex;
	//主教科目id
	private Integer subjectId;
	
	private String subjectName;
	
	//
	private Integer positionId;
	
	private String positionName;
	
	private String graduateSchool;
	
	private Integer tenantId;

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	
	public String getGraduateSchool() {
		return graduateSchool;
	}
	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}
	/**
	 * 电话号码
	 */
	private String phone;
	
	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 是否是全职
	 */
	private String isFulltime;
	
	public String getIsFulltime() {
		return isFulltime;
	}
	public void setIsFulltime(String isFulltime) {
		this.isFulltime = isFulltime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 设置：
	 */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	/**
	 * 获取：
	 */
	public Integer getTeacherId() {
		return teacherId;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：教龄
	 */
	public void setTeachAge(Float teachAge) {
		this.teachAge = teachAge;
	}
	/**
	 * 获取：教龄
	 */
	public Float getTeachAge() {
		return teachAge;
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
	 * 设置：出生日期
	 */
	public void setBorn(Date born) {
		this.born = born;
	}
	/**
	 * 获取：出生日期
	 */
	public Date getBorn() {
		return born;
	}
	/**
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：主教科目
	 */
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	/**
	 * 获取：主教科目
	 */
	public Integer getSubjectId() {
		return subjectId;
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
}
