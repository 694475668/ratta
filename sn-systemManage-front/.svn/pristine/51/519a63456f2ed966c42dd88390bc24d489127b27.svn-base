package com.ratta.spnote.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ratta.spnote.util.HttpReqRspUtil;
import com.ratta.spnote.util.IpUtil;
import com.ratta.spnote.util.Json;
import com.ratta.spnote.util.OperateLogger;
import com.ratta.suponote.model.pagemodel.Resource;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.model.pagemodel.User;
import com.ratta.suponotes.util.PK;
import com.ratta.suponote.model.system.maint.Audit;
import com.ratta.suponote.service.system.maint.AuditService;
import com.ratta.suponote.system.service.ResourceService;

/**
 * @author page 日志记录 2018-10-31
 */
@Aspect
@Component
public class LogAspect {

	private Logger logger = LoggerFactory.getLogger(LogAspect.class);

	@Autowired
	private AuditService auditService;

	@Autowired
	private ResourceService resourceService;

	@Pointcut(" execution(* com.ratta.spnote.controller..*.*(..))")
	public void log() {

	}

	@Around("log() && @annotation(ol)")
	public Object logAround(final ProceedingJoinPoint joinPoint, final OperateLogger ol) throws Throwable {
		SessionInfo sessionInfo = HttpReqRspUtil.getSessionInfo();
		if (sessionInfo == null && ol.operationType() == OperateLogger.OperationType.LOGIN_OU) {
			return joinPoint.proceed();
		}
		if (sessionInfo == null && ol.operationType() != OperateLogger.OperationType.LOGIN_IN) {
			logger.error("session 超时,不记录日志");
			return null;
		}
		String logInfo = "[" + joinPoint.getTarget().getClass().getName() + "]类, [" + joinPoint.getSignature().getName()
				+ "]方法";
		logger.info(logInfo + "开始开始执行." + " service : " + auditService);

//		HttpServletRequest request = this.getRequest() ;
//		HttpSession session = request.getSession() ;

		// 参数集合
		final Object[] params = joinPoint.getArgs();
//		for (Object object : params) {
//			System.out.print(object+"\t");
//		}
		Object deleteEntity = null;
		if (ol.operationType() == OperateLogger.OperationType.D) {
			// 删除，则在方法执行之前，首先去数据库获取删除的数据

			// 新增，第一个参数，可能是 String id 也可能是 Entity
			if (params.length > 0) {
				deleteEntity = this.invokeGetBeanMethod(joinPoint.getThis(), params[0], params[0].getClass());
			}
		}

		Object updateEntity = null;
		if (ol.operationType() == OperateLogger.OperationType.U) {
			// 判断方法传入参数类型
			if (isBaseType(params[0])) {
				updateEntity = this.invokeGetBeanMethod(joinPoint.getThis(), params[0], params[0].getClass());
			} else {
				updateEntity = this.invokeGetBeanMethod(joinPoint.getThis(), this.getEntityPK(params[0]),
						this.getEntityPKClass(params[0]));
			}

		}

		Audit audit = new Audit();

		Resource resource = resourceService.getByURL(this.getUrl(), sessionInfo);

		if (ol.operationType() == OperateLogger.OperationType.LOGIN_IN) {
			audit.setOp_user(((User) params[0]).getUsername());
		} else {
			audit.setOp_user(sessionInfo != null ? sessionInfo.getUsername() : "admin");
		}
		audit.setResource_id(resource == null ? "0" : (resource.getPid() == null ? "0" : resource.getPid()));

		audit.setClient_ip(IpUtil.getIpAddr());

		audit.setOp_item(ol.content());

		Object returnValue = null;
		try {
			returnValue = joinPoint.proceed();
		} catch (Exception e) {
			logger.error(logInfo + "运行异常:\n" + e.getMessage());
			// 必须将异常抛出，不然子方法抛出的异常，调用者将捕获不到
			throw e;
		}

		if (returnValue != null && returnValue instanceof Json) {
			Json j = (Json) returnValue;
			// 如果执行失败，则直接返回，不记录日志
			if (!j.isSuccess()) {
				return returnValue;
			}
		}

		switch (ol.operationType()) {
		case C:
			// Create
			audit.setOp_type("2");

			// 新增，第一个参数，一般是 entity
			if (params.length > 0 && !(params[0] instanceof String)) {

			}

			auditService.save(audit);
			break;
		case U:
			// Update
			audit.setOp_type("3");
//			Object param = params[0] ;

			// 获取更新之后的记录 Entity
			Object afterEntity = null;
			if (isBaseType(params[0])) {
				// 如果是授权、审核、启用、停用，则查询数据库去的 new record
				afterEntity = this.invokeGetBeanMethod(joinPoint.getThis(), params[0], params[0].getClass());
			} else {
				afterEntity = params[0];
			}

			auditService.save(audit);
			break;
		case R:
			// Retrive
			audit.setOp_type("1");

			auditService.save(audit);
			break;
		case D:
			// Delete
			audit.setOp_type("4");

			auditService.save(audit);
			break;

		case LOGIN_IN:
			audit.setOp_type("5");
			auditService.save(audit);
			break;
		case LOGIN_OU:
			audit.setOp_type("6");
			auditService.save(audit);
			break;
		case IMPORT:
			audit.setOp_type("7");
			auditService.save(audit);
			break;
		case Batch_Operation:
			audit.setOp_type("8");
			auditService.save(audit);
			break;
		}

		return returnValue;

	}

	/** 根据 request 获取 URL */
	private String getUrl() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		return requestUri.substring(contextPath.length());
	}

	private <T> T getEntityPK(Object obj) {
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			Arrays.toString(fields);
			for (Field f : fields) {
				if (f.isAnnotationPresent(PK.class)) {
					String getMethodName = new StringBuilder().append("get")
							.append(f.getName().substring(0, 1).toUpperCase()).append(f.getName().substring(1))
							.toString();

					logger.info("getMethodName = " + getMethodName);
					Method method = obj.getClass().getMethod(getMethodName);
					return (T) method.invoke(obj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Class getEntityPKClass(Object obj) {
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field f : fields) {
				if (f.isAnnotationPresent(PK.class)) {
					return f.getType();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return String.class;
	}

	private Object invokeGetBeanMethod(Object obj, Object id, Class clazz) {
		try {

			if (String.valueOf(id).contains(",")) {
				// 逗号分割，调用 getBean(String[] id) 方法
				Method getBeanMethod = obj.getClass().getMethod("getBean", new Class[] { String[].class });
				return getBeanMethod.invoke(obj, new Object[] { String.valueOf(id).split(",") });
			}

			Method getBeanMethod = obj.getClass().getMethod("getBean", clazz);
			return getBeanMethod.invoke(obj, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private boolean isBaseType(Object obj) {
		if (obj == null) {
			return false;
		}
		return obj instanceof String || obj instanceof Long || obj instanceof Integer || obj instanceof Double
				|| obj instanceof Byte || obj instanceof Boolean || obj instanceof Character || obj instanceof Short
				|| obj instanceof Float;
	}

	public LogAspect getBean(Long id) {
		return new LogAspect();
	}

}
