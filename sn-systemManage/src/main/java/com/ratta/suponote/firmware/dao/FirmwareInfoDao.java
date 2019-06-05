package com.ratta.suponote.firmware.dao;

import java.util.List;
import java.util.Map;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.ratta.suponote.firmware.model.FirmwareInfo;
import com.ratta.suponote.firmware.model.FirmwareInfoLine;

/**
 * @author page
 * 固件信息管理持久层
 * 2018-10-31
 */
@Repository("firmwareInfoDao")
public class FirmwareInfoDao extends SqlSessionDaoSupport{

	/**
	 * 
	 * <p>query</p>
	 * <p>查询固件版本</p>
	 * @param params 查询固件版本
	 * p_begin 分页开始页(必填)
	 * p_end 分页结束页(必填)
	 * @return 固件版本列表
	 */
	public List<FirmwareInfo> query(Map<String, Object> params){
		return getSqlSession().selectList("firmware.query", params);
	}
	
	/**
	 * 
	 * <p>count</p>
	 * <p>统计固件版本信息</p>
	 * @param params 查询固件版本
	 * @return 固件版本统计数
	 */
	public Long count(Map<String, Object> params){
		return getSqlSession().selectOne("firmware.count", params);
	}
	/**
	 * firmwareInfoLine
	 * 查询固件版本子类列表
	 * @return
	 */
	public List<FirmwareInfoLine> firmwareInfoLine(Map<String, Object> params) {
		List<FirmwareInfoLine> firmwareInfo = getSqlSession().selectList("firmware.firmwareInfoLine",params);
		return firmwareInfo;
	}

	/**
	 * countFirmwareInfoLine
	 * 根据固件版本子类列表
	 * @return
	 */
	public Long countFirmwareInfoLine(Map<String, Object> params) {
		return getSqlSession().selectOne("firmware.countFirmwareInfoLine", params);
	}
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询固件版本信息</p>
	 * @param id
	 * @return 固件版本信息
	 */
	public FirmwareInfo query(Long id){
		return getSqlSession().selectOne("firmware.queryById", id);
	}
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询固件版本子类信息</p>
	 * @param id
	 * @return 固件版本子类信息
	 */
	public FirmwareInfoLine queryLine(Long id){
		return getSqlSession().selectOne("firmware.queryLine", id);
	}
	/**
	 * 
	 * <p>delete</p>
	 * <p>根据id查询删除固件版本信息</p>
	 * @param id
	 * @return 
	 */
	public int delete(Long id){
		return getSqlSession().delete("firmware.delete", id);
	}
	/**
	 * 
	 * <p>deleteLine</p>
	 * <p>根据id查询删除固件版本子类信息</p>
	 * @param id
	 * @return 
	 */
	public int deleteLine(Long id){
		return getSqlSession().delete("firmware.deleteLine", id);
	}
	/**
	 * 
	 * <p>commitAudit</p>
	 * <p>固件审核</p>
	 * @param firmwareInfo 固件信息
	 * @return 结果
	 */
	public int commitAudit(FirmwareInfo firmwareInfo){
		return getSqlSession().update("firmware.commitAudit", firmwareInfo);
	}
	/**
	 * 
	 * <p>updateStatus</p>
	 * <p> 根据版本号更改固件状态</p>
	 * @param firmwareInfo
	 * @return 结果
	 */
	public int updateStatus(FirmwareInfo firmwareInfo){
		return getSqlSession().update("firmware.updateStatus", firmwareInfo);
	}
	/**
	 * 
	 * <p>addFirmware</p>
	 * <p>添加大固件</p>
	 * @param id
	 * @return 
	 */
	public int addFirmware(FirmwareInfo firmwareInfo){
		return getSqlSession().insert("firmware.insertFirmware", firmwareInfo);
	}
	/**
	 * 
	 * <p>addFirmwareInfoLine</p>
	 * <p>添加小固件</p>
	 * @param id
	 * @return 
	 */
	public int addFirmwareInfoLine(FirmwareInfoLine firmwareInfoLine){
		return getSqlSession().insert("firmware.insertFirmwareInfoLine", firmwareInfoLine);
	}
	
	/**
	 * queryFirmwareInfoList
	 * 查询固件列表信息
	 * @return
	 */
	public List<FirmwareInfo> queryFirmwareInfoList(Map<String, Object> params) {
		List<FirmwareInfo> firmwareInfoList = getSqlSession().selectList("firmware.firmwareInfoList",params);
		return firmwareInfoList;
	}
	/**
	 * firmwareInfoLineList
	 * 查询固件子类信息列表
	 * @return
	 */
	public List<FirmwareInfoLine> firmwareInfoLineList(long firmId) {
		List<FirmwareInfoLine> firmwareInfoLineList = getSqlSession().selectList("firmware.firmwareInfoLineList",firmId);
		return firmwareInfoLineList;
	}
	/**
	 * 
	 * <p>firmwareInfoLin</p>
	 * <p>根据参数询相应的小固件 </p>
	 * @param 
	 * @return 小固件信息
	 */
	public FirmwareInfoLine queryfirmwareInfoLin(Map<String, Object> params){
		return getSqlSession().selectOne("firmware.firmwareInfoLin", params);
	}
}
