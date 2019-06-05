package com.ratta.spnote.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author page 操作日志注解 2018-10-31
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLogger {

	/**
	 * 描叙信息
	 * 
	 * @return
	 */
	String content() default "无描述信息";

	/**
	 * C:增加 R：查询 U：修改 D：删除 LOGIN_IN 登录, LOGIN_OU 登出 ,IMPORT 导入
	 * 
	 * @author TroyLiu
	 *
	 */
	enum OperationType {
		C, R, U, D, LOGIN_IN, LOGIN_OU, IMPORT, Batch_Operation
	}

	OperationType operationType() default OperationType.R;

	String table() default "";

}
