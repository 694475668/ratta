package com.ratta.suponote.model.system;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author page
 * 用来在对象的get方法上加入的annotation，通过该annotation说明某个属性所对应的标题
 * 2018-10-31
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelResources {
	/**
	 * 属性的标题名称
	 * @return 标题
	 */
	String title() default "";
	/**
	 * 在excel的顺序
	 * @return 顺序
	 */
	int order() default 9999;
}
