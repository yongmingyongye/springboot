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
import com.scan.sgindustry.entity.CopyBrandBatches;
import com.scan.sgindustry.entity.CopyBrandDetails;
import com.scan.sgindustry.entity.User;
import com.scan.sgindustry.service.CopyBrandBatchesService;
import com.scan.sgindustry.service.CopyBrandDetailsService;
import com.scan.sgindustry.service.CopyBrandService;
import com.scan.sgindustry.tools.JsonResult;
import com.scan.sgindustry.tools.MyStringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 分批抄牌控制类 记录同一计量通知号不同时段抄牌的二维码信息的中间记录
 * 
 * @author Administrator
 * @date 2019年8月7日
 * @version 1.0
 *
 */
@Api(value = "/copyBrandBatches", tags = "/copyBrandBatches")
@RestController
@RequestMapping("/copyBrandBatches")
public class CopyBrandBatchesController {

    private final static Logger LOGGER = LoggerFactory.getLogger(CopyBrandBatchesController.class);

    @Autowired
    CopyBrandBatchesService copyBrandBatchesService;
    
    @Autowired
    CopyBrandService copyBrandService;
    
    @Autowired
    CopyBrandDetailsService copyBrandDetailsService;

    @ApiOperation(value = "新增抄牌批次", notes = "根据通知单号新增抄牌批次")
    @RequestMapping(value = "saveCopyBrandBatches", method = RequestMethod.POST)
    public ResponseEntity<JsonResult<CopyBrandBatches>> saveCopyBrandBatches(HttpServletRequest request,
            @RequestBody CopyBrandBatches copyBrandBatches) {
        LOGGER.info("新增抄牌批次");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "104"));
        }
        CopyBrand copyBrand = copyBrandService.selectByNoticeNumber(copyBrandBatches.getNoticeNumber());
        if(copyBrand == null) {
            LOGGER.info("保存抄牌批次失败，没找到对应的抄牌主表信息");
            return ResponseEntity.ok(new JsonResult<>(1, "301"));
        }
        if("1".equals(copyBrand.getStatus())) {
            LOGGER.info("保存抄牌批次失败，该抄牌已完成");
            return ResponseEntity.ok(new JsonResult<>(1, "302"));
        }
        copyBrandBatches.setOperator(user.getLoginName());
        copyBrandBatches.setOperatorName(user.getUserName());
        copyBrandBatches.setOperationTime(new Date());
        copyBrandBatches.setStatus("0");
        List<CopyBrandBatches> batches = copyBrandBatchesService
                .selectByNoticeNumber(copyBrandBatches.getNoticeNumber());
        if (batches != null && batches.size() > 0) {
            String[] str = batches.get(0).getBatcheId().split("_");
            String s;
            try {
                // 自增后的抄牌批次编号
                s = copyBrandBatches.getNoticeNumber() + "_" + MyStringUtils.stringAutoincrement(str[1]);
            } catch (Exception e) {
                LOGGER.error("获取自增后的抄牌批次编号错误", e);
                return ResponseEntity.ok(new JsonResult<>(1, "303"));
            }
            copyBrandBatches.setBatcheId(s);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(copyBrandBatches.getNoticeNumber()).append("_").append("01");
            copyBrandBatches.setBatcheId(sb.toString());
        }
        try {
            copyBrandBatchesService.save(copyBrandBatches);
        } catch (Exception e) {
            LOGGER.error("抄牌批次" + copyBrandBatches.getBatcheId() + "保存失败", e);
            return ResponseEntity.ok(new JsonResult<>(1, "304"));
        }
        return ResponseEntity.ok(new JsonResult<>(0, "抄牌批次保存成功", copyBrandBatches));
    }

    @ApiOperation(value = "查询单个抄牌批次", notes = "根据抄牌批次编号查询抄牌批次信息")
    @RequestMapping(value = "getBrandBatches", method = RequestMethod.GET)
    public ResponseEntity<JsonResult<CopyBrandBatches>> getBrandBatches(HttpServletRequest request,
            @RequestParam(value = "batcheId") String batcheId) {
        LOGGER.info("查询抄牌批次");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "104"));
        }
        CopyBrandBatches copyBrandBatches;
        try {
            copyBrandBatches = copyBrandBatchesService.selectByBatcheId(batcheId);
        } catch (Exception e) {
            LOGGER.error("抄牌批次" + batcheId + "查询失败", e);
            return ResponseEntity.ok(new JsonResult<>(1, "305"));
        }
        request.getSession().setAttribute("copyBrandBatches", copyBrandBatches);
        return ResponseEntity.ok(new JsonResult<>(0, "抄牌批次查询成功", copyBrandBatches));
    }

    @ApiOperation(value = "查询所有抄牌批次", notes = "根据通知单号查询所有的抄牌批次信息")
    @RequestMapping(value = "selectBrandBatchesAll", method = RequestMethod.GET)
    public ResponseEntity<JsonResult<List<CopyBrandBatches>>> selectBrandBatchesAll(HttpServletRequest request,
            @RequestParam(value = "noticeNumber") String noticeNumber) {
        LOGGER.info("根据通知单号查询抄牌批次");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "104"));
        }
        List<CopyBrandBatches> copyBrandBatchesList;
        try {
            copyBrandBatchesList = copyBrandBatchesService.selectByNoticeNumber(noticeNumber);
        } catch (Exception e) {
            LOGGER.error("通知单号" + noticeNumber + "查询失败", e);
            return ResponseEntity.ok(new JsonResult<>(1, "208"));
        }
        request.getSession().setAttribute("copyBrandBatches", copyBrandBatchesList);
        return ResponseEntity.ok(new JsonResult<>(0, "通过通知单号查询抄牌批次信息成功", copyBrandBatchesList));
    }

    @ApiOperation(value = "作废抄牌批次", notes = "根据抄牌批次编号作废该抄牌批次")
    @RequestMapping(value = "deleteBrandBatches", method = RequestMethod.POST)
    public ResponseEntity<JsonResult<CopyBrandBatches>> deleteBrandBatches(HttpServletRequest request,
            @RequestBody CopyBrandBatches copyBrandBatches) {
        LOGGER.info("查询抄牌批次");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "104"));
        }
        try {
            copyBrandBatches = copyBrandBatchesService.selectByBatcheId(copyBrandBatches.getBatcheId());
            // 清除当前抄牌批次扫描的二维码信息
            List<CopyBrandDetails> copyBrandDetails = copyBrandDetailsService.selectByNoticeNumber(copyBrandBatches.getBatcheId());
            if (copyBrandDetails.size() > 0) {
                for (CopyBrandDetails details : copyBrandDetails) {
                    // 设置为空字符串，设置为null无法执行更新
                    details.setNoticeNumber("");
                }
                copyBrandDetailsService.updateBatchByPrimaryKeySelective(copyBrandDetails);
            }
            copyBrandBatchesService.delete(copyBrandBatches);
        } catch (Exception e) {
            LOGGER.error("抄牌批次" + copyBrandBatches.getBatcheId() + "作废失败", e);
            return ResponseEntity.ok(new JsonResult<>(1, "306"));
        }
        request.getSession().setAttribute("copyBrandBatches", copyBrandBatches);
        return ResponseEntity.ok(new JsonResult<>(0, "抄牌批次作废成功", copyBrandBatches));
    }
    
    @ApiOperation(value = "完成此次抄牌批次", notes = "根据抄牌批次编号完成该抄牌批次")
    @RequestMapping(value = "finishBrandBatches", method = RequestMethod.POST)
    public ResponseEntity<JsonResult<CopyBrandBatches>> finishBrandBatches(HttpServletRequest request,
            @RequestBody CopyBrandBatches copyBrandBatches) {
        LOGGER.info("完成此次抄牌批次");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "104"));
        }
        try {
            copyBrandBatches = copyBrandBatchesService.selectByBatcheId(copyBrandBatches.getBatcheId());
            copyBrandBatches.setStatus("1");
            copyBrandBatchesService.update(copyBrandBatches);
        } catch (Exception e) {
            LOGGER.error("抄牌批次" + copyBrandBatches.getBatcheId() + "完成失败", e);
            return ResponseEntity.ok(new JsonResult<>(1, "307"));
        }
        request.getSession().setAttribute("copyBrandBatches", copyBrandBatches);
        return ResponseEntity.ok(new JsonResult<>(0, "抄牌批次已完成", copyBrandBatches));
    }

}
