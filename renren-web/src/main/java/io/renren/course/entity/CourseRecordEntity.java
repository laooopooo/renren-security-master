package io.renren.course.entity;

import java.io.Serializable;



/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-09 18:43:47
 */
public class CourseRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer courseRecordId;
	//课程编号
	private Integer courseId;
	//
	private Integer studentId;
	
	//账单编号
	private Integer financeId;
	
	//实际缴费
	private Float actualPrice;
	
	/**
	 * 退款金额
	 */
	private Float quitMoney;
	
	private String quitReason;
	
	
	/**
	 * 用于保存学生选课的状态，正常为1，退课状态为0
	 */
	private Integer status;
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getQuitReason() {
		return quitReason;
	}
	public void setQuitReason(String quitReason) {
		this.quitReason = quitReason;
	}
	public Float getQuitMoney() {
		return quitMoney;
	}
	public void setQuitMoney(Float quitMoney) {
		this.quitMoney = quitMoney;
	}
	public Float getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(Float actualPrice) {
		this.actualPrice = actualPrice;
	}
	/**
	 * 设置：
	 */
	public void setCourseRecordId(Integer courseRecordId) {
		this.courseRecordId = courseRecordId;
	}
	/**
	 * 获取：
	 */
	public Integer getCourseRecordId() {
		return courseRecordId;
	}
	/**
	 * 设置：课程编号
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	/**
	 * 获取：课程编号
	 */
	public Integer getCourseId() {
		return courseId;
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
	 * 设置：账单编号
	 */
	public void setFinanceId(Integer financeId) {
		this.financeId = financeId;
	}
	/**
	 * 获取：账单编号
	 */
	public Integer getFinanceId() {
		return financeId;
	}
}
