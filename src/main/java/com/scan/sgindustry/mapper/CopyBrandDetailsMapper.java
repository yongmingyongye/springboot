package com.scan.sgindustry.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Component;

import com.scan.sgindustry.entity.CopyBrandDetails;
import com.scan.sgindustry.tools.MyBaseMapper;

/**
 * 继承通用Mapper获取CURD方法
 * @author fx
 *
 */
@Component
public interface CopyBrandDetailsMapper extends MyBaseMapper<CopyBrandDetails> {
	
	/**
	 * 保存抄牌从表信息，自动生成Id
	 * @return
	 */
	@SelectKey(keyColumn = "id", keyProperty = "id", before = true, resultType = int.class,
			statement = "select erp_copybrandDetails_seq.nextval as id from dual" )
	@Insert("insert into "
			+ "t_erp_copybrand_details(id,copybrand_id,notice_number,steelno,width,suttle,stoveno,concode,sheaf,quantity,weight_produce_id,status,create_time,create_user)"
			+ "values(#{id},#{copybrandId},#{noticeNumber},#{steelno},#{width},#{suttle},#{stoveno},#{concode},#{sheaf},#{quantity},#{weightProduceId},#{status},#{createTime},#{createUser})")
	@Options(useGeneratedKeys = true)
	Integer saveAutoId(CopyBrandDetails copyBrandDetails);
	
}
