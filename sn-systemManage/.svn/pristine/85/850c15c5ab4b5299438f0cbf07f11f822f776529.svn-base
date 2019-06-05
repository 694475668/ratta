package com.ratta.suponote.feedback.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.ratta.suponote.feedback.dao.FeedbackProblemTypeDao;
import com.ratta.suponote.feedback.model.FeedbackProblemType;
import com.ratta.suponote.feedback.result.FeedbackProblemTypeRseult;
import com.ratta.suponote.feedback.service.FeedbackProblemTypeService;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 反馈问题类型业务层 2018-10-31
 */
@Service("feedbackProblemTypeService")
@Transactional(rollbackFor = Exception.class)
public class FeedbackProblemTypeServiceBean implements FeedbackProblemTypeService {

	private static Logger logger = LoggerFactory.getLogger(FeedbackProblemTypeServiceBean.class);
	@Autowired
	private FeedbackProblemTypeDao feedbackProblemTypeDao;
	
	@Override
	public DataGrid query(PageHelper ph, Map<String, Object> params) {
		logger.info("查询反馈问题类型信息,查询参数:{}", BaseUtil.map2String(params));
		DataGrid dg = new DataGrid();
		if (ph == null) {
			logger.error("分页数据为空");
			return dg;
		}
		if (params == null) {
			params = new HashMap<String, Object>(32);
		}
		params.put("p_begin", (ph.getPage() - 1) * ph.getRows());
		params.put("p_end", ph.getRows());
		try {
			dg.setRows(feedbackProblemTypeDao.query(params));
			dg.setTotal(feedbackProblemTypeDao.count(params));
			logger.info("查询反馈问题类型信息成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	@Override
	@Transactional
	public FeedbackProblemTypeRseult save(FeedbackProblemType feedbackProblemType, SessionInfo sessionInfo) {
		logger.info("添加反馈问题类型信息:{}", feedbackProblemType);
		if (feedbackProblemType == null) {
			logger.error("数据反馈问题类型为空");
			return FeedbackProblemTypeRseult.FEEDBACKPROBLEMTYPE_NULL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return FeedbackProblemTypeRseult.SESSIONINFO_NULL;
		}
		try {
			if (feedbackProblemTypeDao.query(feedbackProblemType.getTypeId().trim()) != null) {
				logger.error("该反馈问题类型已经存在！");
				return FeedbackProblemTypeRseult.TYPE_ID_EXISTS;
			}
			feedbackProblemType.setOpUser(sessionInfo.getUsername());
			int result = feedbackProblemTypeDao.save(feedbackProblemType);
			logger.info("添加操作员信息成功,数据库影响行数:{}", result);
			return FeedbackProblemTypeRseult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return FeedbackProblemTypeRseult.DATABASE_ERROR;
		}
	}
	
	@Override
	@Transactional
	public FeedbackProblemTypeRseult delete(String typeId, SessionInfo sessionInfo) {
		logger.info("删除id={}的反馈问题类型信息", typeId);
		if (StringUtils.isEmpty(typeId)) {
			logger.error("数据反馈问题类型id为空");
			return FeedbackProblemTypeRseult.TYPEID_NULL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return FeedbackProblemTypeRseult.SESSIONINFO_NULL;
		}
		try {
			int result = 0;
			List<FeedbackProblemType> feedbackProblemTypeList = feedbackProblemTypeDao.queryListById(typeId);
			for (FeedbackProblemType feedbackProblemType : feedbackProblemTypeList) {
				result = feedbackProblemTypeDao.delete(feedbackProblemType.getTypeId());
			}
			logger.info("删除id={}的反馈问题类型信息成功,数据库影响行数:{}", typeId, result);
			return FeedbackProblemTypeRseult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return FeedbackProblemTypeRseult.DATABASE_ERROR;
		}
	}
	
	@Override
	@Transactional
	public FeedbackProblemTypeRseult update(FeedbackProblemType feedbackProblemType, SessionInfo sessionInfo) {
		logger.info("修改反馈问题类型信息:{}", feedbackProblemType);
		if (feedbackProblemType == null) {
			logger.error("数据反馈问题类型为空");
			return FeedbackProblemTypeRseult.FEEDBACKPROBLEMTYPE_NULL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return FeedbackProblemTypeRseult.SESSIONINFO_NULL;
		}
		try {
			feedbackProblemType.setOpUser(sessionInfo.getUsername());
			int result = feedbackProblemTypeDao.update(feedbackProblemType);
			logger.info("修改操作员信息成功,数据库影响行数:{}", result);
			return FeedbackProblemTypeRseult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return FeedbackProblemTypeRseult.DATABASE_ERROR;
		}
	}
	
	@Override
	public FeedbackProblemType query(String typeId) {
		logger.info("根据id:{}查询反馈问题类型信息", typeId);
		if (StringUtils.isEmpty(typeId)) {
			logger.error("typeId为空");
			return null;
		}
		try {
			FeedbackProblemType feedbackProblemType = feedbackProblemTypeDao.query(typeId);
			logger.info("查询反馈问题类型信息成功:{}", feedbackProblemType);
			return feedbackProblemType;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<FeedbackProblemType> queryValueOne() {
		return feedbackProblemTypeDao.queryValueOne();
	}

	@Override
	public List<FeedbackProblemType> queryValueTwo(String typeId) {
		return feedbackProblemTypeDao.queryValueTwo(typeId);
	}
	@Override
	public List<FeedbackProblemType> queryValueThree(String typeId) {
		return feedbackProblemTypeDao.queryValueThree(typeId);
	}

}
