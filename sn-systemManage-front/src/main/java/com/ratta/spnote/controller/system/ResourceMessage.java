package com.ratta.spnote.controller.system;

import com.ratta.spnote.util.Message;

/**
 * @author page
 * 资源信息
 * 2018-10-31
 */
public class ResourceMessage {

	/**
	 * 添加成功!
	 */
	public static final Message ADD_SUCCESS= Message.createInstance("添加成功!",  "record added");
	
	/**
	 * 添加失败,错误:{0}
	 */
	public static final Message ADD_ERROR=Message.createInstance("添加失败,错误:{0}",  "record insert failure, error {0}");
	
	/**
	 * 编辑成功!
	 */
	public static final Message EDIT_SUCCESS=Message.createInstance("编辑成功!", "record modified");
	
	/**
	 * 编辑失败,错误:{0}
	 */
	public static final Message EDIT_ERROR=Message.createInstance("编辑失败,错误:{0}",  "record modification failure, error {0}");
	
	/**
	 * 删除成功!
	 */
	public static final Message DEL_SUCCESS=Message.createInstance("删除成功!",  "record removed");
	
	/**
	 * 删除失败,错误:{0}
	 */
	public static final Message DEL_ERROR=Message.createInstance("删除失败,错误:{0}", "record remove failuire, error {0}");
}
