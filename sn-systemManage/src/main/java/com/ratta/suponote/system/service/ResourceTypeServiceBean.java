package com.ratta.suponote.system.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ratta.suponote.dao.system.ResourceTypeDao;
import com.ratta.suponote.model.pagemodel.ResourceType;
import com.ratta.suponote.model.system.Tresourcetype;

/**
 * @author page 资源类型业务类 2018-10-31
 */
@Service("resourceTypeService")
@Transactional(rollbackFor = Exception.class)
public class ResourceTypeServiceBean implements ResourceTypeService {
	/**
	 * 日志记录
	 */
	private Logger logger = LoggerFactory.getLogger(ResourceTypeServiceBean.class);
	/**
	 * 资源类型持久层dao
	 */
	@Autowired
	private ResourceTypeDao resourceType;

	@Override
	public List<ResourceType> getResourceTypeList() {
		logger.info("调用查询所有资源类型");
		List<Tresourcetype> l = resourceType.findAll();
		List<ResourceType> rl = new ArrayList<ResourceType>();
		if (l != null && l.size() > 0) {
			for (Tresourcetype t : l) {
				ResourceType rt = new ResourceType();
				BeanUtils.copyProperties(t, rt);
				rl.add(rt);
			}
			logger.info("查询所有资源类型成功,总记录数:{}", rl.size());
		} else {
			logger.warn("资源类型列表为空");
		}
		return rl;
	}

}
