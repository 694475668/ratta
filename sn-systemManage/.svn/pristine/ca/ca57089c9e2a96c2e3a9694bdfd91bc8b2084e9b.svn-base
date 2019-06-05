package com.ratta.suponote.firmware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ratta.suponote.firmware.dao.FirmwareZipDao;
import com.ratta.suponote.firmware.model.FirmwareZip;
import com.ratta.suponote.firmware.result.FirmwareInfoResult;

/**
 * @author page 固件重组压缩包服务 2018-10-31
 */
@Service("firmwareZipService")
@Transactional(rollbackFor = Exception.class)
public class FirmwareZipBean implements FirmwareZipService {

	private static Logger logger = LoggerFactory.getLogger(FirmwareZipBean.class);
	@Autowired
	private FirmwareZipDao firmwareZipDao;

	@Override
	@Transactional
	public FirmwareInfoResult addFirmwareZip(FirmwareZip firmwareZip) {
		try {
			firmwareZipDao.insertFirmwareZip(firmwareZip);
			return FirmwareInfoResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return FirmwareInfoResult.REQUEST_FAIL;
		}
	}

	@Override
	@Transactional
	public FirmwareInfoResult delete(String version) {
		try {
			firmwareZipDao.delete(version);
			return FirmwareInfoResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return FirmwareInfoResult.REQUEST_FAIL;
		}
	}

	@Override
	public List<FirmwareZip> firmwareZipList(String version) {
		try {
			List<FirmwareZip> firmwareZipList = firmwareZipDao.query(version);
			return firmwareZipList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return null;
		}
	}

}
