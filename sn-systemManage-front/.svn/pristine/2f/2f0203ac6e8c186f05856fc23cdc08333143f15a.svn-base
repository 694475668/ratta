package com.ratta.spnote.interceptors;

import java.util.Locale;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * @author page 自定义使用本机语言作为spring的默认语言 2018-10-31
 */
public class DefaultLanguage extends SessionLocaleResolver {

	public DefaultLanguage() {
		System.out.println("spring language is : " + Locale.SIMPLIFIED_CHINESE);
		super.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
	}

}
