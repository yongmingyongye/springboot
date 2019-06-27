package com.scan.sgindustry.service;

import java.util.List;

import com.scan.sgindustry.entity.CopyBrand;
import com.scan.sgindustry.service.common.BaseService;

/**
 * 继承通用service接口
 * @author fx
 *
 * @param 
 */
public interface CopyBrandService extends BaseService<CopyBrand> {

    /**
     * 根据通知单号查询抄牌信息
     * @param noticeNumber
     * @return
     */
	CopyBrand selectByNoticeNumber(String noticeNumber);
	
	/**
	 * 保存抄牌信息
	 * @param copyBrand
	 * @return
	 */
	Integer saveAutoId(CopyBrand copyBrand);
	
	/**
	 * 查询除作废外所有抄牌信息，并按时间倒序排序
	 * @return
	 */
	List<CopyBrand> selectAll();
	
	/**
	 * 按条件查询除作废外所有抄牌信息，并按时间倒序排序
	 * @return
	 */
	List<CopyBrand> selectAll(CopyBrand copyBrand);
	
	
	/**
	 * 根据id批量更新
	 * @return
	 */
	Integer updateBatchByPrimaryKeySelective(List<CopyBrand> list);
	
}
