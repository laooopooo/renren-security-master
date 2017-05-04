package io.renren.fin.entity;

import java.io.Serializable;

public class AnalyzeEntity  implements Serializable{
	private static final long serialVersionUID = -6418143138954711917L;
	
	/**
	 * 金额类型名称
	 */
	private String typeName;
	/**
	 * 金额总额
	 */
	private Float finSum;
	
	/**
	 * 该类型的金额
	 */
	private Float typeAmount;
	
	/**
	 * 该类型的金额所占总金额的百分比
	 */
	private Float percent;

	public String getFinType() {
		return typeName;
	}

	public void setFinType(String typeName) {
		this.typeName = typeName;
	}

	public Float getFinSum() {
		return finSum;
	}

	public void setFinSum(Float finSum) {
		this.finSum = finSum;
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
