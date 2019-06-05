package com.ratta.spnote.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ratta.suponote.model.pagemodel.SessionInfo;

/**
 * @author page http请求帮助类2 2018-10-31
 */
public class HttpReqRspUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpReqRspUtil.class);

	public static void write(HttpServletResponse response, String result) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"));
			bw.write(result);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String read(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String name = enumeration.nextElement();
			sb.append(name);
		}
		String result = sb.toString();
		if (StringUtils.isEmpty(result)) {
			logger.error("方法1没有读取到参数");
			result = read2(request);
			logger.info("方法2读取参数:" + result);
		}
		return result;
	}

	public static String read2(HttpServletRequest request) {
		BufferedReader br = null;
		try {
			br = request.getReader();
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			sb = null;
		}
		return sb == null ? null : sb.toString();
	}

	public static SessionInfo getSessionInfo() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attrs != null) {
			HttpSession session = attrs.getRequest().getSession();
			if (session != null) {
				return (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
			}
		}
		return null;
	}
}
