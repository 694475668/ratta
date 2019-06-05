package com.ratta.suponote.system.service;

import java.util.List;

import com.ratta.suponote.model.pagemodel.ResourceType;

/**
 * @author page
 * 资源类型服务
 * 2018-10-31
 */
public interface ResourceTypeService {
	/**
	 * 
			* <p>getResourceTypeList</p>
			* <p>获取所有的资源类型</p>
			* @return 资源类型列表
	 */
	List<ResourceType> getResourceTypeList();

}
