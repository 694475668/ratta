package com.ratta.suponote.firmware.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ratta.suponote.firmware.model.FirmwareZip;

/**
 * @author page
 * 固件重组压缩包管理持久层
 * 2018-10-31
 */
@Repository("firmwareZipDao")
public class FirmwareZipDao extends SqlSessionDaoSupport{

	/**
	 * insertFirmwareZip<br>
	 * 添加版本号相同的组合大固件<br>
	 * @param firmwareZipList
	 * @return
	 */
	public int insertFirmwareZip(FirmwareZip firmwareZip){
		return getSqlSession().insert("firmwareZip.insertFirmwareZip", firmwareZip);
	}
	
	/**
	 * delete<br>
	 * 删除固件信息<br>
	 * @param version
	 * @return
	 */
	public int delete(String version){
		return getSqlSession().delete("firmwareZip.delete", version);
	}
	/**
	 * query<br>
	 * 查找固件<br>
	 * @param version
	 * @return
	 */
	public List<FirmwareZip> query(String version){
		return getSqlSession().selectList("firmwareZip.query", version);
	}
	
}
