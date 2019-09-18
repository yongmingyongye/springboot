package com.scan.sgindustry.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scan.sgindustry.entity.CopyBrand;
import com.scan.sgindustry.entity.CopyBrandVO;
import com.scan.sgindustry.mapper.CopyBrandMapper;
import com.scan.sgindustry.service.CopyBrandService;
import com.scan.sgindustry.service.impl.common.BaseServiceImpl;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class CopyBrandServiceImpl extends BaseServiceImpl<CopyBrand> implements CopyBrandService {

    @Autowired
    private CopyBrandMapper copyBrandMapper;

    public CopyBrand selectByNoticeNumber(String noticeNumber) {
        return copyBrandMapper.selectByNoticeNumber(noticeNumber);
    }

    public Integer saveAutoId(CopyBrand copyBrand) {
        return copyBrandMapper.saveAutoId(copyBrand);
    }

    @Override
    public List<CopyBrand> selectAll() {
        // 写自定义查询条件
        Example example = new Example(CopyBrand.class);
        Criteria criteria = example.createCriteria();
        // 判断是否存在该二维信息
        criteria.andNotEqualTo("status", "99");
        example.setOrderByClause("create_time desc");
        return copyBrandMapper.selectByExample(example);
    }

    @Override
    public List<CopyBrand> selectAll(CopyBrandVO copyBrandVO) {
        // 写自定义查询条件
        Example example = new Example(CopyBrand.class);
        Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(copyBrandVO.getUserNumber()) && !"1".equals(copyBrandVO.getRole())) {
            criteria.andEqualTo("userNumber", copyBrandVO.getUserNumber());
        }
        if (StringUtils.isNotBlank(copyBrandVO.getVehicleNumber())) {
            criteria.andEqualTo("vehicleNumber", copyBrandVO.getVehicleNumber());
        }
        criteria.andGreaterThanOrEqualTo("createTime", copyBrandVO.getStartTime());
        criteria.andLessThanOrEqualTo("createTime", copyBrandVO.getEndTime());
        criteria.andNotEqualTo("status", "99");
        example.setOrderByClause("create_time desc");
        return copyBrandMapper.selectByExample(example);
    }

    @Override
    public Integer updateBatchByPrimaryKeySelective(List<CopyBrand> list) {
        return copyBrandMapper.updateBatchByPrimaryKeySelective(list);
    }


}
