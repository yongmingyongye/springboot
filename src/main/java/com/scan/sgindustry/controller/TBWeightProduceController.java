package com.scan.sgindustry.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scan.sgindustry.entity.CopyBrandDetails;
import com.scan.sgindustry.entity.TBWeightProduce;
import com.scan.sgindustry.entity.User;
import com.scan.sgindustry.entity.WeightProduceSummaryAndDetailsVO;
import com.scan.sgindustry.entity.WeightProduceSummaryVO;
import com.scan.sgindustry.service.CopyBrandDetailsService;
import com.scan.sgindustry.service.TBWeightProduceService;
import com.scan.sgindustry.service.WeightProduceSummaryService;
import com.scan.sgindustry.tools.CompareUtils;
import com.scan.sgindustry.tools.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 抄牌二维码信息控制类
 * @author fx
 * @version 1.0.0
 */
@Api(value="/weightProduce", tags="/weightProduce")
@RestController
@RequestMapping("/weightProduce")
public class TBWeightProduceController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TBWeightProduceController.class);

	@Autowired
	TBWeightProduceService tbWeightProduceService;
	
	@Autowired
	CopyBrandDetailsService copyBrandDetailsService;
	
	@Autowired
	WeightProduceSummaryService weightProduceSummaryService;
	
	@ApiOperation(value="更新二维码信息", notes="将二维码对应通知单号保存到数据库")
    @PostMapping(value = "weightProduce")
    public ResponseEntity<JsonResult<List<CopyBrandDetails>>> weightProduce(HttpServletRequest request, @RequestBody CopyBrandDetails copyBrandDetail) {
    	LOGGER.info("保存二维码信息");
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			return ResponseEntity.ok(new JsonResult<>(1, "用户未登录"));
		}
		if(copyBrandDetail == null) {
			return ResponseEntity.ok(new JsonResult<>(1, "保存信息不能为空"));
		}
		List<TBWeightProduce> produceList = tbWeightProduceService.selectByIdAndReqcodeIsNull(copyBrandDetail.getId());
		if(produceList.size() == 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "该二维码信息有误"));
		}
		if(produceList.size() > 1) {
			String msg = "数据有误，同一个Id存在多条数据";
			return ResponseEntity.ok(new JsonResult<>(1, copyBrandDetail.getId() + msg));
		}
		//查询出的结果只可能有一条数据
		TBWeightProduce produce = produceList.get(0);
		if(StringUtils.isNotBlank(produce.getBy1())) {
			String msg = "改二维码信息已保存通知单号";
			return ResponseEntity.ok(new JsonResult<>(1, copyBrandDetail.getId() + msg));
		}
		//比对二维码与erp数据库中的信息是否一致
		StringBuilder produceBuilder = new StringBuilder(produce.getSteelno())
			.append(produce.getWidth())
			.append(produce.getSuttle())
			.append(produce.getStoveno().substring(0, 8))
			.append(produce.getStoveno().substring(produce.getStoveno().length() - 2))
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
			.append(copyBrandDetail.getId());
		if(!CompareUtils.compareStr(produceBuilder.toString(),detailBuilder.toString())) {
			return ResponseEntity.ok(new JsonResult<>(1, "该二维码数据与数据库中的数据不一致"));
		}
        produce.setBy1(copyBrandDetail.getNoticeNumber());
        produce.setBy2(user.getLoginName());
        produce.setBy3(user.getUserName());
        produce.setScanTime(new Date());
        tbWeightProduceService.update(produce);
        LOGGER.info("二维码信息已保存");
        List<CopyBrandDetails> copyBrandDetails = copyBrandDetailsService.selectByNoticeNumber(produce.getBy1());
    	return ResponseEntity.ok(new JsonResult<>(0, "保存成功", copyBrandDetails));
    }
	
	@ApiOperation(value="删除", notes="删除通知单信息")
	@DeleteMapping(value = "weightProduce")
	public ResponseEntity<JsonResult<CopyBrandDetails>> weightProduce(HttpServletRequest request, @RequestParam(value = "id") Integer id) {
		LOGGER.info("删除二维码通知单号信息");
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			return ResponseEntity.ok(new JsonResult<>(1, "用户未登录"));
		}
		TBWeightProduce weightProduce = tbWeightProduceService.selectByKey(id);
		if(weightProduce == null) {
			return ResponseEntity.ok(new JsonResult<>(1, "id不存在"));
		} else if(StringUtils.isBlank(weightProduce.getBy1())) {
			String msg = "没有被扫描，不能删除！";
			return ResponseEntity.ok(new JsonResult<>(1, id + msg));
		}
		weightProduce.setBy1("");
		tbWeightProduceService.update(weightProduce);
		return ResponseEntity.ok(new JsonResult<>(0, "删除成功"));
		
	}
	
	@ApiOperation(value="分页查询", notes="按时间倒序分页查询")
	@PostMapping(value = "weightProduces")
	public ResponseEntity<JsonResult<List<CopyBrandDetails>>> weightProduces(@RequestBody CopyBrandDetails copyBrandDetails, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
		LOGGER.info("按时间倒序分页查询");
		if(pageNum <= 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "页码不正确"));
		}
		if(pageSize <= 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "每页显示个数不能小于0"));
		}
		String orderby = new String("SCANTIME desc");
		List<CopyBrandDetails> brand = copyBrandDetailsService.selectPage(pageNum, pageSize, orderby, copyBrandDetails);
		if(brand == null || brand.size() == 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "不存在该计量通知号的抄牌信息"));
		}
		return ResponseEntity.ok(new JsonResult<>(0, "查询成功", brand));
	}
	
	@ApiOperation(value="分页查询", notes="按时间倒序分页查询")
	@GetMapping(value = "weightProduces")
	public ResponseEntity<JsonResult<List<CopyBrandDetails>>> weightProduces(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
		LOGGER.info("按时间倒序分页查询");
		if(pageNum <= 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "页码不正确"));
		}
		if(pageSize <= 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "每页显示个数不能小于0"));
		}
		String orderby = new String("SCANTIME desc");
		List<CopyBrandDetails> brand = copyBrandDetailsService.selectPage(pageNum, pageSize, orderby, null);
		if(brand == null || brand.size() == 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "不存在该计量通知号的抄牌信息"));
		}
		return ResponseEntity.ok(new JsonResult<>(0, "查询成功", brand));
	}
	
	@ApiOperation(value="查询统计与详情", notes="查询统计与详情")
    @GetMapping(value = "weightProduces/summary")
    public ResponseEntity<JsonResult<List<WeightProduceSummaryAndDetailsVO>>> weightProduceSummarys(@RequestParam(value = "noticeNumber") String noticeNumber) {
        LOGGER.info("按时间倒序分页查询");
        if(StringUtils.isBlank(noticeNumber)) {
            return ResponseEntity.ok(new JsonResult<>(1, "通知单号不能为空"));
        }
        //根据通知单号查询
        //按炉批号分组合并统计结果集
        List<WeightProduceSummaryVO> summarylist = weightProduceSummaryService.selectWeightProduceSummaryByNoticeNumber(noticeNumber);
        //查询所有结果集
        List<CopyBrandDetails> details = copyBrandDetailsService.selectByNoticeNumber(noticeNumber);
        Iterator<CopyBrandDetails> detailIterator;
        //统计结果与各个详情的对照集合
        List<WeightProduceSummaryAndDetailsVO> weightProduceSummaryAndDetails = new ArrayList<>();
        List<CopyBrandDetails> detailsList;
        for(int i = 0; i < summarylist.size(); i++) {
            WeightProduceSummaryAndDetailsVO summaryAndDetails = new WeightProduceSummaryAndDetailsVO();
            detailsList = new ArrayList<>();
            detailIterator = details.iterator();
            while(detailIterator.hasNext()) {
                CopyBrandDetails detail = detailIterator.next();
                if(StringUtils.isBlank(summarylist.get(i).getStoveno()) || StringUtils.isBlank(detail.getStoveno())) {
                    LOGGER.error("查询抄牌统计与详情出错，数据中炉批号存在空值");
                    ResponseEntity.ok(new JsonResult<>(1, "数据有误"));
                }
                if(summarylist.get(i).getStoveno().equals(detail.getStoveno())) {
                    detailsList.add(detail);
                    detailIterator.remove();
                }
            }
            summaryAndDetails.setWeightProduceSummary(summarylist.get(i));
            summaryAndDetails.setWeightProduceDetails(detailsList);
            weightProduceSummaryAndDetails.add(summaryAndDetails);
        }
        return ResponseEntity.ok(new JsonResult<>(0, "查询成功", weightProduceSummaryAndDetails));
    }
	
}
