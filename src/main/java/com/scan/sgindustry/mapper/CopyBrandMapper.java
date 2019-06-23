package com.scan.sgindustry.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Component;

import com.scan.sgindustry.entity.CopyBrand;
import com.scan.sgindustry.tools.MyBaseMapper;

/**
 * 继承通用Mapper获取CURD方法
 * @author Administrator
 *
 */
@Component
public interface CopyBrandMapper extends MyBaseMapper<CopyBrand> {
	
	/**
	 * 根据计量通知单号查询
	 * @param noticeNumber
	 * @return
	 */
	@Results({ 
        @Result(property = "copybrandId", column = "copybrand_id", id = true),
        @Result(property = "noticeNumber", column = "notice_number"), @Result(property = "userNumber", column = "user_number"),
        @Result(property = "vehicleNumber", column = "vehicle_number"), @Result(property = "status", column = "status"),
        @Result(property = "printCount", column = "print_count"), @Result(property = "createTime", column = "create_time")
    })
	@Select("select "
			+ "copybrand_id,"
			+ "notice_number,"
			+ "user_number,"
			+ "vehicle_number,"
			+ "status,"
			+ "print_count,"
			+ "create_time"
			+ " from t_erp_copybrand t where t.notice_number = #{noticeNumber,jdbcType=VARCHAR}")
	CopyBrand selectByNoticeNumber(String noticeNumber);
	
	/**
	 * 保存抄牌主表信息，自动生成Id
	 * @return
	 */
	@SelectKey(keyColumn = "copybrand_id", keyProperty = "copybrandId", before = true, resultType = int.class,
			statement = "select erp_copybrand_seq.nextval as copybrandId from dual" )
	@Insert("insert into t_erp_copybrand(copybrand_id,notice_number,user_number,vehicle_number,status,print_count,create_time)"
			+ "values(#{copybrandId},#{noticeNumber},#{userNumber},#{vehicleNumber},#{status},#{printCount},#{createTime})")
	@Options(useGeneratedKeys = true)
	Integer saveAutoId(CopyBrand copyBrand);
	
}
