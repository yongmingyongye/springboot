package com.scan.sgindustry.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.scan.sgindustry.entity.MeterageNotice;
import com.scan.sgindustry.tools.MyBaseMapper;


/**
 * 继承通用Mapper获取CURD方法
 * @author Administrator
 *
 */
//@Mapper 实体进行了手动映射，不在使用
@Component
public interface MeterageNoticeMapper extends MyBaseMapper<MeterageNotice> {

    @Select("select ID, HTH, DJH, DWH, LH, CKH, CH, TZSJ, BDH, JLBZ, PRINTTIMES, DYRQ, JLJSRQ, ZL, JS, FHBY1, FHBY2, ZT, PERCENT, NETVALUE, gh, dwmc, DBGSMC, HPSH, CC1, CC2, CC3, XZ, CPYZ "
			+ "from VI_XSCC t where instr(t.ID,#{xsccId})=length(t.ID)-length(#{xsccId})+1 and t.JLBZ <> '89'")
	MeterageNotice selectByLastId(String xsccId);
}
