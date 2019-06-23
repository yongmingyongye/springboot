package com.scan.sgindustry.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scan.sgindustry.entity.CopyBrand;
import com.scan.sgindustry.entity.CopyBrandDetails;
import com.scan.sgindustry.entity.TBWeightProduce;
import com.scan.sgindustry.entity.User;
import com.scan.sgindustry.service.CopyBrandDetailsService;
import com.scan.sgindustry.service.TBWeightProduceService;
import com.scan.sgindustry.tools.CompareUtils;
import com.scan.sgindustry.tools.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 抄牌对象控制类
 * @author fx
 *
 */

@Api(value="/copyBrandDetails", tags="/copyBrandDetails")
@RestController
@RequestMapping("/copyBrandDetails")
public class CopyBrandDetailsController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CopyBrandDetailsController.class);
	
	
	@Autowired
	TBWeightProduceService tbWeightProduceService;
	
	@Autowired
	CopyBrandDetailsService copyBrandDetailsService;
	
	
	@ApiOperation(value="保存二维码信息", notes="将二维码信息保存到数据库")
    @RequestMapping(value = "saveCopyBrand", method = RequestMethod.POST)
    public ResponseEntity<JsonResult<CopyBrandDetails>> saveCopyBrandDetail(HttpServletRequest request, @RequestBody CopyBrandDetails copyBrandDetail) {
    	LOGGER.info("保存二维码信息");
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			return ResponseEntity.ok(new JsonResult<>(1, "用户未登录"));
		}
		if(copyBrandDetail == null) {
			return ResponseEntity.ok(new JsonResult<>(1, "保存信息不能为空"));
		}
		List<TBWeightProduce> produceList = tbWeightProduceService.selectByIdAndReqcodeIsNull(copyBrandDetail.getWeightProduceId());
		if(produceList.size() == 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "该二维码信息有误"));
		}
		//查询出的结果只可能有一条数据
		TBWeightProduce produce = produceList.get(0);
		//比对二维码与erp数据库中的信息是否一致
		StringBuilder produceBuilder = new StringBuilder(produce.getSteelno())
										.append(produce.getWidth())
										.append(produce.getSuttle())
										.append(produce.getStoveno())
										.append(produce.getConcode())
										.append(produce.getSheaf())
										.append(produce.getQuantity())
										.append(produce.getId());
		StringBuilder detailBuilder = new StringBuilder(copyBrandDetail.getSteelno())
										.append(copyBrandDetail.getWidth())
										.append(copyBrandDetail.getSuttle())
										.append(copyBrandDetail.getStoveno())
										.append(copyBrandDetail.getConcode())
										.append(copyBrandDetail.getSheaf())
										.append(copyBrandDetail.getQuantity())
										.append(copyBrandDetail.getWeightProduceId());
		if(!CompareUtils.compareStr(produceBuilder.toString(),detailBuilder.toString())) {
			return ResponseEntity.ok(new JsonResult<>(1, "该二维码数据与数据库中的数据不一致"));
		}
		//判断是否存在该二维信息
		List<CopyBrandDetails> brandList = copyBrandDetailsService.selectByProduceId(copyBrandDetail.getWeightProduceId());
        if(brandList.size() > 0) {
        	return ResponseEntity.ok(new JsonResult<>(1, "该二维信息已保存", copyBrandDetail));
        }
        //获取session中的抄牌主表信息
        CopyBrand copyBrand = (CopyBrand) request.getSession().getAttribute("copyBrand");
        if(copyBrand == null) {
        	return ResponseEntity.ok(new JsonResult<>(1, "无抄牌主表信息"));
        }
        copyBrandDetail.setCopybrandId(copyBrand.getCopybrandId());
        copyBrandDetail.setNoticeNumber(copyBrand.getNoticeNumber());
    	copyBrandDetail.setCreateUser(user.getLoginName());
    	copyBrandDetail.setCreateTime(new Date());
    	copyBrandDetail.setStatus("0");
    	copyBrandDetailsService.saveAutoId(copyBrandDetail);
    	return ResponseEntity.ok(new JsonResult<>(0, "保存成功", copyBrandDetail));
    }
	
	@ApiOperation(value="更新抄牌从表状态", notes="更新抄牌从表状态信息")
	@RequestMapping(value = "updateCopyBrandDetailStatus", method = RequestMethod.POST)
	public ResponseEntity<JsonResult<CopyBrandDetails>> updateCopyBrandDetailStatus(@RequestBody CopyBrandDetails brandDetails) {
		if(brandDetails == null) {
			return ResponseEntity.ok(new JsonResult<>(1, "不允许更新空信息"));
		}
		LOGGER.info("更新抄牌从表状态为：" + brandDetails.getStatus());
		CopyBrandDetails detail;
		if(brandDetails.getId() == null) {
			return ResponseEntity.ok(new JsonResult<>(1, "ID错误"));
		}
		detail = copyBrandDetailsService.selectByKey(brandDetails.getId());
		if(detail == null) {
			return ResponseEntity.ok(new JsonResult<>(1, "ID不存在"));
		}
		detail.setStatus(brandDetails.getStatus());
		copyBrandDetailsService.update(detail);
		return ResponseEntity.ok(new JsonResult<>(0, "更新成功", detail));
	}
	
	@ApiOperation(value="分页查询", notes="按时间倒序分页查询")
	@RequestMapping(value = "selectPageAndOrderBy", method = RequestMethod.POST)
	public ResponseEntity<JsonResult<List<CopyBrandDetails>>> selectPageAndOrderBy(@RequestBody CopyBrandDetails copyBrandDetails, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
		LOGGER.info("按时间倒序分页查询");
		if(pageNum <= 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "页码不正确"));
		}
		if(pageSize <= 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "每页显示个数不能小于0"));
		}
		String orderby = new String("copybrand_id desc");
		List<CopyBrandDetails> brand = copyBrandDetailsService.selectPage(pageNum, pageSize, orderby, copyBrandDetails);
		if(brand == null || brand.size() == 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "不存在该计量通知号的抄牌信息"));
		}
		return ResponseEntity.ok(new JsonResult<>(0, "查询成功", brand));
	}
	
	@ApiOperation(value="分页查询", notes="按时间倒序分页查询")
	@RequestMapping(value = "selectPageAndOrderBy", method = RequestMethod.GET)
	public ResponseEntity<JsonResult<List<CopyBrandDetails>>> selectPageAndOrderBy(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
		LOGGER.info("按时间倒序分页查询");
		if(pageNum <= 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "页码不正确"));
		}
		if(pageSize <= 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "每页显示个数不能小于0"));
		}
		String orderby = new String("copybrand_id desc");
		List<CopyBrandDetails> brand = copyBrandDetailsService.selectPage(pageNum, pageSize, orderby, null);
		if(brand == null || brand.size() == 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "不存在该计量通知号的抄牌信息"));
		}
		return ResponseEntity.ok(new JsonResult<>(0, "查询成功", brand));
	}

}
