package com.yc.projects.yc74ibike.dao;


import com.yc.projects.yc74ibike.bean.Bike;

/**
 * 单车的操作
 * 
 * @author Soledad
 * @date 2020年6月13日
 * 
 */

public interface BikeDao {

	/**
	 * 新增一辆车入库
	 * 
	 * @param bike
	 * @return
	 */
	public Bike addBike(Bike bike);

	/**
	 * 更新操作（对应业务中的入库，上线，解锁）
	 * 
	 * @param bike
	 */
	public void updateBike(Bike bike);

	/**
	 * 根据id查车
	 *
	 * @param bid
	 * @return
	 */
	public Bike findBike(String bid);
}
