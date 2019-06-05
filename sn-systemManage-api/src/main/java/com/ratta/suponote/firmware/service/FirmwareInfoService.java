package com.ratta.suponote.firmware.service;

import java.util.List;
import java.util.Map;

import com.ratta.suponote.firmware.model.FirmwareInfo;
import com.ratta.suponote.firmware.model.FirmwareInfoLine;
import com.ratta.suponote.firmware.result.FirmwareInfoResult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;

/**
 * @author page
 * 固件信息管理服务
 * 2018-10-31
 */
public interface  FirmwareInfoService{

	/**
	 * 
	 * <p>query</p>
	 * <p>查询固件版本信息</p>
	 * @param ph 分页帮助类
	 * @param params 查询参数
	 * @return easyui datagrid数据模型
	 */
	DataGrid query(PageHelper ph,Map<String, Object> params);
	/**
	 * 
			* <p>firmwareInfoLine</p>
			* <p>加载固件版本子类列表(非合入)</p>
			* @param ph 分页用的模型
			* @param params 查询参数
			* @return 页面easyui  datagrid模型
	 */
	DataGrid firmwareInfoLine(PageHelper ph,Map<String, Object> params);
	/**
	 * 
			* <p>commitAudit</p>
			* <p>提交审核</p>
			* @param firmwareInfo 固件信息
			* @param sessionInfo 用户sessionInfo
			* @return 返回枚举
	 */
	FirmwareInfoResult commitAudit(FirmwareInfo firmwareInfo,SessionInfo sessionInfo);
	/**
	 * 
			* <p>firmwareInfoLine</p>
			* <p>加载固件版本子类列表(合入)</p>
			* @param params 参数
			* @return 固件信息集合
	 */
	 List<FirmwareInfoLine> firmwareInfoLine(Map<String, Object> params);
	/**
	 * 
	 * <p>query</p>
	 * <p>根据ID查询固件版本信息</p>
	 * @param id id
	 * @return 固件信息
	 */
	FirmwareInfo query(Long id);
	/**
	 * 
	 * <p>query</p>
	 * <p>根据ID查询固件版本信息</p>
	 * @param id id
	 * @return 固件信息子类
	 */
	FirmwareInfoLine queryLine(Long id);
	/**
	 * 
	 * <p>queryNoPage</p>
	 * <p>不分页查询已审核通过的固件版本信息</p>
	 * @return 固件信息集合
	 */
	List<FirmwareInfo> queryNoPage();
	/**
	 * 
	 * <p>delete</p>
	 * <p>根据ID查询删除固件版本信息</p>
	 * @param id id
	 * @param sessionInfo 用户sessionInfo
	 * @return 返回枚举
	 */
	FirmwareInfoResult delete(Long id,SessionInfo sessionInfo);
	/**
	 * 
	 * <p>deleteAuditTest</p>
	 * <p>根据设备号删除固件审核测试任务</p>
	 * @param equipment_no 设备号
	 * @param sessionInfo 用户sessionInfo
	 * @return 返回枚举
	 */
	FirmwareInfoResult deleteAuditTest(String equipment_no,SessionInfo sessionInfo);
	/**
	 * 
	 * <p>addFirmwareInfo</p>
	 * <p>添加大固件信息</p>
	 * @param  firmware 固件
	 * @param  firmwareInfo 固件子类
	 * @param sessionInfo 用户sessionInfo
	 * @return 固件信息
	 */
	FirmwareInfo addFirmwareInfo(FirmwareInfo firmware,FirmwareInfo firmwareInfo,SessionInfo sessionInfo);
	/**
	 * 
	 * <p>addFirmwareInfoLin</p>
	 * <p>添加小固件信息</p>
	 * @param firmwareInfoLine 固件子类
	 * @return 返回枚举
	 */
	FirmwareInfoResult addFirmwareInfoLin(FirmwareInfoLine firmwareInfoLine);
	/**
	 * 
	 * <p>queryFirmwareInfoLine</p>
	 * <p>查询小固件信息</p>
	 * @param firm_id 固件ID
	 * @param type 类型
	 * @return 固件子类信息
	 */
	FirmwareInfoLine queryFirmwareInfoLine(String firm_id,String type);
	/**
	 * queryFirmwareInfoList<br>
	 * 查询条件固件数据<br>
	 * @param version  本版本号(模糊查询)
	 * @param area  区域
	 * @param custom 定制类型
	 * @param configuration 用途
	 * @return 固件信息集合
	 */
	List<FirmwareInfo> queryFirmwareInfoList(String version,String area,String custom, String configuration);
	/**
	 * updateStatus
	 * 更新固件审核状态
	 * @param firmwareInfo 固件信息
	 * @return 结果
	 */
	int updateStatus(FirmwareInfo firmwareInfo);
	/**
	 * deleteLine
	 * 删除固件小类记录
	 * @param id 大固件包ID
	 * @return 结果
	 */
	int deleteLine(Long id);
	/**
	 * firmwareInfoLineList<br>
	 * 查询条件固件子类信息<br>
	 * @param id  固件ID
	 * @return 固件子类信息集合
	 */
	List<FirmwareInfoLine> firmwareInfoLineList(long id);
}
