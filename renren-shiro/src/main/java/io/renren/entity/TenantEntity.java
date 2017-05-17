package io.renren.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;



/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-16 08:09:52
 */
public class TenantEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer tenantId;
	//
	private String tenantPhone;
	//
	private Date createTime;
	//
	private Date lastUpdate;
	//
	private String tenantName;
	//省份证
	private String tenantIdcard;
	//机构的图片
	private String tenantImg;
	//真实姓名
	private String trueName;
	//地址
	private String tenantAddress;
	//租用日期
	@JSONField (format="yyyy-MM-dd")
	private Date hireDate;
	//缴费金额
	private Float payMoney;
	//密码
	private String tenantPassword;
	
	@JSONField (format="yyyy-MM-dd")
	private Date expirationDate;
	
	private String email;
	

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	/**
	 * 设置：
	 */
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	/**
	 * 获取：
	 */
	public Integer getTenantId() {
		return tenantId;
	}
	/**
	 * 设置：
	 */
	public void setTenantPhone(String tenantPhone) {
		this.tenantPhone = tenantPhone;
	}
	/**
	 * 获取：
	 */
	public String getTenantPhone() {
		return tenantPhone;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
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
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	/**
	 * 获取：
	 */
	public String getTenantName() {
		return tenantName;
	}
	/**
	 * 设置：省份证
	 */
	public void setTenantIdcard(String tenantIdcard) {
		this.tenantIdcard = tenantIdcard;
	}
	/**
	 * 获取：省份证
	 */
	public String getTenantIdcard() {
		return tenantIdcard;
	}
	/**
	 * 设置：机构的图片
	 */
	public void setTenantImg(String tenantImg) {
		this.tenantImg = tenantImg;
	}
	/**
	 * 获取：机构的图片
	 */
	public String getTenantImg() {
		return tenantImg;
	}
	/**
	 * 设置：真实姓名
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	/**
	 * 获取：真实姓名
	 */
	public String getTrueName() {
		return trueName;
	}
	/**
	 * 设置：地址
	 */
	public void setTenantAddress(String tenantAddress) {
		this.tenantAddress = tenantAddress;
	}
	/**
	 * 获取：地址
	 */
	public String getTenantAddress() {
		return tenantAddress;
	}
	/**
	 * 设置：租用日期
	 */
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	/**
	 * 获取：租用日期
	 */
	public Date getHireDate() {
		return hireDate;
	}
	/**
	 * 设置：缴费金额
	 */
	public void setPayMoney(Float payMoney) {
		this.payMoney = payMoney;
	}
	/**
	 * 获取：缴费金额
	 */
	public Float getPayMoney() {
		return payMoney;
	}
	/**
	 * 设置：密码
	 */
	public void setTenantPassword(String tenantPassword) {
		this.tenantPassword = tenantPassword;
	}
	/**
	 * 获取：密码
	 */
	public String getTenantPassword() {
		return tenantPassword;
	}
}
