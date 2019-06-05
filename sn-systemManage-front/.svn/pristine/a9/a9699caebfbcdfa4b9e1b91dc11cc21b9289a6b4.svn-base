package com.ratta.spnote.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import com.ratta.spnote.util.ConfigUtil;
import com.ratta.suponote.model.pagemodel.SessionInfo;

/**
 * @author page 语言切换控制层 2018-10-31
 */
@Controller
@RequestMapping("/global")
public class GlobalController extends BaseController {

	@RequestMapping("/change")
	@ResponseBody
	public void change(HttpServletRequest request, HttpSession session) {

		String langType = request.getParameter("langType");
		Locale locale = null;
		if ("zh_CN".equals(langType)) {
			locale = new Locale("zh", "CN");
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
		} else if ("en".equals(langType)) {
			locale = new Locale("en", "US");
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
		} else {
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
					LocaleContextHolder.getLocale());
		}
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		if (sessionInfo != null) {
			sessionInfo.setLocale(locale);
		}
	}

}
