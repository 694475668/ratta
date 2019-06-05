package com.ratta.spnote.interceptors;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ratta.spnote.util.ConfigUtil;
import com.ratta.suponote.model.pagemodel.SessionInfo;

/**
 * @author page 权限拦截器 2018-10-31
 */
public class SecurityInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);

	private List<String> excludeUrls;// 不需要拦截的资源

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	/**
	 * 完成页面的render后调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) throws Exception {

	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		Locale locale = Locale.SIMPLIFIED_CHINESE;
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		logger.info(url);
		if (url.indexOf("/baseController/") > -1 || excludeUrls.contains(url)) {// 如果要访问的资源是不需要验证的

			if ("/captchaController/createImage".equals(url) || "/captchaController/code".equals(url)
					|| "/userController/login".equals(url) || "/resourceController/tree".equals(url)) {
				return true;
			} else {
				SessionInfo sessionInfo = (SessionInfo) request.getSession()
						.getAttribute(ConfigUtil.getSessionInfoName());
				if (sessionInfo == null || sessionInfo.getId().equalsIgnoreCase("")) {
					// 如果没有登录或登录超时
					if (locale.getCountry() == "CN") {
						request.setAttribute("msg", "会话过期");
						request.setAttribute("msg1", "");
						request.setAttribute("msg2", "秒后自动跳转到登录页面!");
					}
					if (locale.getCountry() == "US") {
						request.setAttribute("msg", "timed out, refresh");
						request.setAttribute("msg1", "");
						request.setAttribute("msg2", "seconds!");
					}
					request.getRequestDispatcher("/error/noSession.jsp").forward(request, response);
					return false;
				}
			}

			return true;
		}

		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		if (sessionInfo == null || sessionInfo.getId().equalsIgnoreCase("")) {// 如果没有登录或登录超时

			if (locale.getCountry() == "CN") {
				request.setAttribute("msg", "会话过期");
				request.setAttribute("msg1", "");
				request.setAttribute("msg2", "秒后自动跳转到登录页面!");
			}
			if (locale.getCountry() == "US") {
				request.setAttribute("msg", "timed out, refresh");
				request.setAttribute("msg1", "");
				request.setAttribute("msg2", "seconds!");
			}
			request.getRequestDispatcher("/error/noSession.jsp").forward(request, response);
			return false;
		}

		if (url.lastIndexOf("dataGrid") == -1 && !sessionInfo.getResourceList().contains(url)) {//
			// 如果当前用户没有访问此资源的权限
			request.setAttribute("msg", "您没有访问此资源的权限！<br/>请联系超管赋予您<br/>[" + url + "]<br/>的资源访问权限！");
			request.getRequestDispatcher("/error/noSecurity.jsp").forward(request, response);
			return false;
		}
		return true;
	}

}
