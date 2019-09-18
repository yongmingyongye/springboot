package com.scan.sgindustry.service;

import java.util.List;

import com.scan.sgindustry.entity.CopyBrandDetails;
import com.scan.sgindustry.service.common.BaseService;

/**
 * 继承通用service接口
 * @author fx
 *
 * @param 
 */
public interface CopyBrandDetailsService extends BaseService<CopyBrandDetails> {

	/**
	 * 通过抄牌批次编号查询所有信息
	 * @param noticeNumber
	 * @return
	 */
	List<CopyBrandDetails> selectByBatcheId(String  noticeNumber);
	
	/**
	 * 通过炉号和捆号查询信息
	 * @param stoveno
	 * @param sheaf
	 * @return
	 */
	CopyBrandDetails selectByStovenoAndSheaf(String stoveno, String sheaf);
	
	/**
	 * 通过通知单号查询所有抄牌批次扫描的二维码信息
	 * @param noticeNumber
	 * @return
	 */
	List<CopyBrandDetails> selectByNoticeNumber(String noticeNumber);
	
}
