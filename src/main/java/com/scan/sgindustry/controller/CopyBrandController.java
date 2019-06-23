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
import com.scan.sgindustry.entity.MeterageNotice;
import com.scan.sgindustry.entity.TBWeight;
import com.scan.sgindustry.entity.User;
import com.scan.sgindustry.service.CopyBrandService;
import com.scan.sgindustry.service.MeterageNoticeService;
import com.scan.sgindustry.service.TBWeightService;
import com.scan.sgindustry.tools.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 抄牌对象控制类
 * @author fx
 *
 */

@Api(value="/copyBrand", tags="/copyBrand")
@RestController
@RequestMapping("/copyBrand")
public class CopyBrandController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CopyBrandController.class);
	
	@Autowired
	TBWeightService tbWeightService;
	
	@Autowired
	MeterageNoticeService meterageNoticeService;
	
	@Autowired
	CopyBrandService copyBrandService;
	
	@ApiOperation(value="查询计量通知", notes="根据通知号的最后6位查询计量通知")
	@RequestMapping(value = "selectMeterageNoticeByLastId", method = RequestMethod.GET)
	public ResponseEntity<JsonResult<MeterageNotice>> selectMeterageNoticeByLastId(@RequestParam(value = "xsccId") String xsccId) {
		LOGGER.info("查询计量通知");
		if(xsccId.length() < 6) {
			return ResponseEntity.ok(new JsonResult<>("请输入至少6个字符(倒数6位及以上)"));
		}
		MeterageNotice notice = meterageNoticeService.selectByLastId(xsccId);
		if(notice == null) {
			return ResponseEntity.ok(new JsonResult<>("输入的计量通知号不存在"));
		}
		return ResponseEntity.ok(new JsonResult<>(0, "查询成功", notice));
	}
	
	
	@ApiOperation(value="新建抄牌", notes="根据通知单号和车船号创建抄牌")
    @RequestMapping(value = "saveCopyBrand", method = RequestMethod.POST)
    public ResponseEntity<JsonResult<CopyBrand>> saveCopyBrand(HttpServletRequest request, @RequestBody CopyBrand copyBrand) {
    	LOGGER.info("新建抄牌");
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			return ResponseEntity.ok(new JsonResult<>(1, "用户未登录"));
		}
    	if(copyBrand.getNoticeNumber().length() < 6) {
			return ResponseEntity.ok(new JsonResult<>(1, "请输入至少6个字符(倒数6位及以上)"));
		}
		MeterageNotice notice = meterageNoticeService.selectByLastId(copyBrand.getNoticeNumber());
        if(notice == null) {
        	return ResponseEntity.ok(new JsonResult<>(1, "输入的计量通知号不存在"));
        }
        List<TBWeight> tbWeightList = tbWeightService.selectByReqcode(copyBrand.getNoticeNumber());
        if(tbWeightList.size() > 0) {
        	return ResponseEntity.ok(new JsonResult<>(1, "该通知号为对外磅点"));
        }
        CopyBrand brand = copyBrandService.selectByNoticeNumber(notice.getId());
        if(brand == null) {
        	copyBrand.setNoticeNumber(notice.getId());
        	copyBrand.setUserNumber(user.getLoginName());
        	copyBrand.setCreateTime(new Date());
        	copyBrand.setStatus("0");
        	copyBrand.setPrintCount(0);
        	copyBrandService.saveAutoId(copyBrand);
        	request.getSession().setAttribute("copyBrand", copyBrand);
        	return ResponseEntity.ok(new JsonResult<>(0, "保存成功", copyBrand));
        } else {
        	return ResponseEntity.ok(new JsonResult<>(1, "已存在该计量通知号的抄牌信息"));
        }
    }
	
	@ApiOperation(value="查询抄牌", notes="根据通知号的最后6位查询抄牌")
    @RequestMapping(value = "selectByNoticeNumber", method = RequestMethod.GET)
	public ResponseEntity<JsonResult<CopyBrand>> selectByNoticeNumber(@RequestParam(value = "noticeNumber") String noticeNumber) {
		LOGGER.info("查询抄牌");
		if(noticeNumber.length() < 6) {
			return ResponseEntity.ok(new JsonResult<>("请输入至少6个字符(倒数6位及以上)"));
		}
		MeterageNotice notice = meterageNoticeService.selectByLastId(noticeNumber);
        if(notice == null) {
        	return ResponseEntity.ok(new JsonResult<>(1, "输入的计量通知号不存在"));
        }
        CopyBrand brand = copyBrandService.selectByNoticeNumber(notice.getId());
        if(brand == null) {
        	return ResponseEntity.ok(new JsonResult<>(1, "不存在该计量通知号的抄牌信息"));
        }
		return ResponseEntity.ok(new JsonResult<>(0, "查询成功", brand));
	}

	@ApiOperation(value="分页查询", notes="按时间倒序分页查询")
	@RequestMapping(value = "selectPageAndOrderBy", method = RequestMethod.POST)
	public ResponseEntity<JsonResult<List<CopyBrand>>> selectPageAndOrderBy(@RequestBody CopyBrand copyBrand, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
		LOGGER.info("按时间倒序分页查询");
		if(pageNum <= 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "页码不正确"));
		}
		if(pageSize <= 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "每页显示个数不能小于0"));
		}
		String orderby = new String("copybrand_id desc");
		List<CopyBrand> brand = copyBrandService.selectPage(pageNum, pageSize, orderby, copyBrand);
		if(brand == null || brand.size() == 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "不存在该计量通知号的抄牌信息"));
		}
		return ResponseEntity.ok(new JsonResult<>(0, "查询成功", brand));
	}
	
	@ApiOperation(value="分页查询", notes="按时间倒序分页查询")
	@RequestMapping(value = "selectPageAndOrderBy", method = RequestMethod.GET)
	public ResponseEntity<JsonResult<List<CopyBrand>>> selectPageAndOrderBy(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
		LOGGER.info("按时间倒序分页查询");
		if(pageNum <= 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "页码不正确"));
		}
		if(pageSize <= 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "每页显示个数不能小于0"));
		}
		String orderby = new String("copybrand_id desc");
		List<CopyBrand> brand = copyBrandService.selectPage(pageNum, pageSize, orderby, null);
		if(brand == null || brand.size() == 0) {
			return ResponseEntity.ok(new JsonResult<>(1, "不存在该计量通知号的抄牌信息"));
		}
		return ResponseEntity.ok(new JsonResult<>(0, "查询成功", brand));
	}
	
	
	@ApiOperation(value="更新抄牌", notes="更新抄牌主表信息")
	@RequestMapping(value = "updateCopyBrand", method = RequestMethod.POST)
	public ResponseEntity<JsonResult<CopyBrand>> updateCopyBrand(@RequestBody CopyBrand copyBrand) {
		LOGGER.info("更新抄牌主表信息");
		if(copyBrand == null) {
			return ResponseEntity.ok(new JsonResult<>(1, "不允许更新空信息"));
		}
		copyBrandService.update(copyBrand);
		return ResponseEntity.ok(new JsonResult<>(0, "更新成功", copyBrand));
	}
	
	@ApiOperation(value="更新抄牌状态", notes="更新抄牌主表状态信息")
	@RequestMapping(value = "updateCopyBrandStatus", method = RequestMethod.POST)
	public ResponseEntity<JsonResult<CopyBrand>> updateCopyBrandStatus(@RequestBody CopyBrand copyBrand) {
		if(copyBrand == null) {
			return ResponseEntity.ok(new JsonResult<>(1, "不允许更新空信息"));
		}
		LOGGER.info("更新抄牌主表状态为：" + copyBrand.getStatus());
		CopyBrand brand;
		if(copyBrand.getCopybrandId() == null) {
			return ResponseEntity.ok(new JsonResult<>(1, "ID错误"));
		}
		brand = copyBrandService.selectByKey(copyBrand.getCopybrandId());
		if(brand == null) {
			return ResponseEntity.ok(new JsonResult<>(1, "ID不存在"));
		}
		brand.setStatus(copyBrand.getStatus());
		copyBrandService.update(brand);
		return ResponseEntity.ok(new JsonResult<>(0, "更新成功", brand));
	}
}
