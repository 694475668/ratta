package com.ratta.suponote.firmware.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ratta.suponote.firmware.model.FirmwareEquipment;

/**
 * @author page
 * 固件设备持久层
 * 2018-10-31
 */
@Repository("firmwareEquipDao")
public class FirmwareEquipDao extends SqlSessionDaoSupport{

	/**
	 * 
	 * <p>query</p>
	 * <p>查询固件版本设备绑定关系信息</p>
	 * @param params 
	 * p_begin 分页开始页(必填)
	 * p_end 分页结束页(必填)
	 * @return 固件版本列表
	 */
	public List<FirmwareEquipment> query(Map<String, Object> params){
		return getSqlSession().selectList("firmwareEquip.query", params);
	}
	/**
	 * 
	 * <p>count</p>
	 * <p>统计固件版本设备绑定关系信息</p>
	 * @param params 查询固件版本设备绑定关系
	 * @return 固件版本设备绑定关系统计数
	 */
	public Long count(Map<String, Object> params){
		return getSqlSession().selectOne("firmwareEquip.count", params);
	}
	/**
	 * 
	 * <p>deleteFirmwareEquipType</p>
	 * <p>根据版本号删除固件设备绑定记录</p>
	 * @param verison 版本号
	 * @return 
	 */
	public int deleteFirmwareEquipType(String version){
		return getSqlSession().delete("firmwareEquip.deleteFirmwareEquipType", version);
	}
	/**
	 * insertFirmwareEuip<br>
	 * 添加固件本跟设备关联信息<br>
	 * @param firmwareEuipList
	 * @return
	 */
	public int insertFirmwareEuip(List<FirmwareEquipment> firmwareEuipList){
		return getSqlSession().insert("firmwareEquip.insertFirmwareEuip", firmwareEuipList);
	}
}
