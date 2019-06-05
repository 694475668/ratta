package com.ratta.suponote.feedback.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.ratta.suponote.feedback.dao.FeedbackProblemTypeDao;
import com.ratta.suponote.feedback.dao.FeedbackRecordDao;
import com.ratta.suponote.feedback.model.FeedbackRecord;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.snuser.dao.UserEquipmentDao;
import com.ratta.suponote.usersn.model.UserEquipment;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 反馈问题记录业务层 2018-10-31
 */
@Service("feedbackRecordService")
@Transactional(rollbackFor = Exception.class)
public class FeedbackRecordServiceBean implements FeedbackRecordService {

	private static Logger logger = LoggerFactory.getLogger(FeedbackRecordServiceBean.class);
	@Autowired
	private FeedbackRecordDao feedbackRecordDao;
	@Autowired
	private UserEquipmentDao userEquipmentDao;
	@Autowired
	private FeedbackProblemTypeDao feedbackProblemTypeDao;
	
	@Override
	public DataGrid query(PageHelper ph, String equipment_no, String contact, String valueOne, String valueTwo, 
			String valueThree) {
		logger.info("查询反馈问题记录信息,查询参数");
		DataGrid dg = new DataGrid();
		int totalSize = 0;
		if (ph == null) {
			logger.error("分页数据为空");
			return dg;
		}
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("p_begin", (ph.getPage() - 1) * ph.getRows());
		params.put("p_end", ph.getRows());
		params.put("contact", contact);
		if(valueThree!=null && !"".equals(valueThree.trim())) {
			params.put("typeId", valueThree);
		}else {
			if(valueTwo!=null && !"".equals(valueTwo.trim())) {
				params.put("typeId", valueTwo);
			}else {
				params.put("typeId", valueOne);
			}
		}
		try {
			StringBuffer stringBuffer  = new StringBuffer();
			List<FeedbackRecord> feedbackRecordList = feedbackRecordDao.query(params);
			for (FeedbackRecord feedbackRecord : feedbackRecordList) {
				Long userID = feedbackRecord.getUserId();
				String typeId = feedbackRecord.getTypeId();
				String [] typeIds = typeId.split("\\.");
				if(typeIds.length == 1) {
					feedbackRecord.setValueOne(feedbackProblemTypeDao.query(typeIds[0]).getValueCn());
				}
				if(typeIds.length == 2) {
					feedbackRecord.setValueOne(feedbackProblemTypeDao.query(typeIds[0]).getValueCn());
					feedbackRecord.setValueTwo(feedbackProblemTypeDao.query(typeId).getValueCn());
				}
				if(typeIds.length == 3) {
					feedbackRecord.setValueOne(feedbackProblemTypeDao.query(typeIds[0]).getValueCn());
					feedbackRecord.setValueTwo(feedbackProblemTypeDao.query(typeIds[0]+"."+typeIds[1]).getValueCn());
					feedbackRecord.setValueThree(feedbackProblemTypeDao.query(typeId).getValueCn());
				}
				List<UserEquipment> userEquipmentList = userEquipmentDao.getUserEquipmentByUserId(userID);
				for (UserEquipment userEquipment : userEquipmentList) {
					stringBuffer.append(userEquipment.getEquipment_number()+" ");
				}
				feedbackRecord.setEquipment_no(stringBuffer.toString());
				stringBuffer.setLength(0);
			}
			
			Iterator<FeedbackRecord> iterator = feedbackRecordList.iterator(); 
			while (iterator.hasNext()){ 
				FeedbackRecord feedbackRecordIterator = iterator.next(); 
				if(equipment_no!=null && !"".equals(equipment_no.trim())){
					if(!feedbackRecordIterator.getEquipment_no().contains(equipment_no)) {
						iterator.remove(); 
						totalSize++;
					}
				}
			}
			dg.setRows(feedbackRecordList);
			dg.setTotal(feedbackRecordDao.count(params)-totalSize);
			logger.info("查询反馈问题记录信息成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}
	
	@Override
	public List<FeedbackRecord> queryNoPage(Map<String, Object> params) {
		logger.info("查询反馈问题记录信息,查询参数:{}", BaseUtil.map2String(params));
		return feedbackRecordDao.query(params);
	}

	@Override
	public FeedbackRecord query(int id) {
		logger.info("根据id:{}查询反馈问题记录信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("id为空");
			return null;
		}
		try {
			FeedbackRecord feedbackRecord = feedbackRecordDao.query(id);
			logger.info("查询反馈问题记录信息成功:{}", feedbackRecord);
			return feedbackRecord;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return null;
	}
}
