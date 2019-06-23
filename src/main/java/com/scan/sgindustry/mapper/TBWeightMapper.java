package com.scan.sgindustry.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.scan.sgindustry.entity.TBWeight;
import com.scan.sgindustry.tools.MyBaseMapper;

@Component
public interface TBWeightMapper extends MyBaseMapper<TBWeight> {
	
	/**
	 * 通过计量通知号查询
	 * @param reqcode 计量通知号
	 * @return
	 */
	@Select(value = "select * from TB_WEIGHT where reqcode = #{reqcode,jdbcType=VARCHAR}")
	List<TBWeight> selectByReqcode(String reqcode);
	
}
