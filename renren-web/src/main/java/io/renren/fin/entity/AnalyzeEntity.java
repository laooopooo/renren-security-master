package io.renren.fin.entity;

import java.io.Serializable;

public class AnalyzeEntity  implements Serializable{
	private static final long serialVersionUID = -6418143138954711917L;
	
	/**
	 * 金额类型名称
	 */
	private String typeName;
	
	/**
	 * 该类型的金额
	 */
	private Float typeAmount;
	
	/**
	 * 该类型的金额所占总金额的百分比
	 */
	private Float percent;
	
	/**
	 * 总收入
	 */
	private Float totalIn;
	
	/**
	 * 总支出
	 */
	private Float totalOut;
	
	/**
	 * 利润
	 */
	private Float profit;
	
	

	public Float getTotalIn() {
		return totalIn;
	}

	public void setTotalIn(Float totalIn) {
		this.totalIn = totalIn;
	}

	public Float getTotalOut() {
		return totalOut;
	}

	public void setTotalOut(Float totalOut) {
		this.totalOut = totalOut;
	}

	public Float getProfit() {
		return profit;
	}

	public void setProfit(Float profit) {
		this.profit = profit;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Float getTypeAmount() {
		return typeAmount;
	}

	public void setTypeAmount(Float typeAmount) {
		this.typeAmount = typeAmount;
	}

	public Float getPercent() {
		return percent;
	}

	public void setPercent(Float percent) {
		this.percent = percent;
	}

}
