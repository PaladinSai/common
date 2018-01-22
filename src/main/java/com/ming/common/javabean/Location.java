package com.ming.common.javabean;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * 类名：Location<br>
 * 功能：位置类<br>
 * 版本：1.0<br>
 * 日期：2017-02-28<br>
 * 说明：暂无
 * 
 * @author Ming
 *
 */
public class Location implements Serializable {

	private static final long serialVersionUID = 5913071257251619905L;

	/** 经度 */
	private Double longitude;
	/** 纬度 */
	private Double latitude;
	/** 距离 */
	private Double distance;

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = Double.valueOf(new DecimalFormat("#.00").format(distance));
	}
}
