package io.renren.fin.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;



/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-04 14:57:23
 */
public class FinanceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer financeId;
	//收入或支出
	private Integer payOrIncome;
	//财务类型
	private String finType;
	//
	@JSONField (format="yyyy-MM-dd")
	private Date finDate;
	//收支金额
	private Float finAmount;
	//
	private String remarks;
	//
	private Integer finQuarter;
	
	private String finQuarterName;
	//
	private Date lastUpdate;
	
	@JSONField (format="yyyy")
	private String finYear;
	
	
	public String getFinYear() {
		return finYear;
	}
	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}
	public void setFinanceId(Integer financeId) {
		this.financeId = financeId;
	}
	public String getFinQuarterName() {
		return finQuarterName;
	}
	public void setFinQuarterName(String finQuarterName) {
		this.finQuarterName = finQuarterName;
	}
	/**
	 * 获取：
	 */
	public Integer getFinanceId() {
		return financeId;
	}
	/**
	 * 设置：收入或支出
	 */
	public void setPayOrIncome(Integer payOrIncome) {
		this.payOrIncome = payOrIncome;
	}
	/**
	 * 获取：收入或支出
	 */
	public Integer getPayOrIncome() {
		return payOrIncome;
	}
	/**
	 * 设置：财务类型
	 */
	public void setFinType(String finType) {
		this.finType = finType;
	}
	/**
	 * 获取：财务类型
	 */
	public String getFinType() {
		return finType;
	}
	/**
	 * 设置：
	 */
	public void setFinDate(Date finDate) {
		this.finDate = finDate;
	}
	/**
	 * 获取：
	 */
	public Date getFinDate() {
		return finDate;
	}
	/**
	 * 设置：收支金额
	 */
	public void setFinAmount(Float finAmount) {
		this.finAmount = finAmount;
	}
	/**
	 * 获取：收支金额
	 */
	public Float getFinAmount() {
		return finAmount;
	}
	/**
	 * 设置：
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置：
	 */
	public void setFinQuarter(Integer finQuarter) {
		this.finQuarter = finQuarter;
	}
	/**
	 * 获取：
	 */
	public Integer getFinQuarter() {
		return finQuarter;
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
