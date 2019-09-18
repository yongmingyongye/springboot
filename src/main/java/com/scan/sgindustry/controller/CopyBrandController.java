package com.scan.sgindustry.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scan.sgindustry.entity.CopyBrand;
import com.scan.sgindustry.entity.CopyBrandBatches;
import com.scan.sgindustry.entity.CopyBrandVO;
import com.scan.sgindustry.entity.MeterageNotice;
import com.scan.sgindustry.entity.TBWeight;
import com.scan.sgindustry.entity.User;
import com.scan.sgindustry.service.CopyBrandBatchesService;
import com.scan.sgindustry.service.CopyBrandDetailsService;
import com.scan.sgindustry.service.CopyBrandService;
import com.scan.sgindustry.service.MeterageNoticeService;
import com.scan.sgindustry.service.TBWeightService;
import com.scan.sgindustry.tools.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 抄牌对象控制类
 * 
 * @author fx
 *
 */

@Api(value = "/copyBrand", tags = "/copyBrand")
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

    @Autowired
    CopyBrandDetailsService copyBrandDetailsService;
    
    @Autowired
    CopyBrandBatchesService copyBrandBatchesService;

    @ApiOperation(value = "查询计量通知", notes = "根据通知号的最后6位查询计量通知")
    @RequestMapping(value = "selectMeterageNoticeByLastId", method = RequestMethod.GET)
    public ResponseEntity<JsonResult<MeterageNotice>> selectMeterageNoticeByLastId(
            @RequestParam(value = "xsccId") String xsccId) {
        LOGGER.info("查询计量通知");
        if (xsccId.length() < 6) {
            return ResponseEntity.ok(new JsonResult<>("请输入至少6个字符(倒数6位及以上)"));
        }
        MeterageNotice notice = meterageNoticeService.selectByLastId(xsccId);
        if (notice == null) {
            return ResponseEntity.ok(new JsonResult<>("输入的计量通知号不存在"));
        }
        return ResponseEntity.ok(new JsonResult<>(0, "查询成功", notice));
    }

    @ApiOperation(value = "新建抄牌", notes = "根据通知单号和车船号创建抄牌")
    @RequestMapping(value = "saveCopyBrand", method = RequestMethod.POST)
    public ResponseEntity<JsonResult<CopyBrand>> saveCopyBrand(HttpServletRequest request,
            @RequestBody CopyBrand copyBrand) {
        LOGGER.info("新建抄牌");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "104"));
        }
        if (copyBrand.getNoticeNumber().length() < 6) {
            return ResponseEntity.ok(new JsonResult<>(1, "201"));
        }
        MeterageNotice notice = meterageNoticeService.selectByLastId(copyBrand.getNoticeNumber());
        if (notice == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "202"));
        }
        List<TBWeight> tbWeightList = tbWeightService.selectByReqcode(copyBrand.getNoticeNumber());
        if (tbWeightList.size() > 0) {
            return ResponseEntity.ok(new JsonResult<>(1, "203"));
        }
        CopyBrand brand = copyBrandService.selectByNoticeNumber(notice.getId());
        if (brand == null) {
            copyBrand.setNoticeNumber(notice.getId());
            copyBrand.setUserNumber(user.getLoginName());
            copyBrand.setCreateTime(new Date());
            copyBrand.setStatus("0");
            copyBrand.setPrintCount(0);
            copyBrandService.saveAutoId(copyBrand);
            request.getSession().setAttribute("copyBrand", copyBrand);
            return ResponseEntity.ok(new JsonResult<>(0, "保存成功", copyBrand));
        } else {
            return ResponseEntity.ok(new JsonResult<>(1, "204"));
        }
    }

    @ApiOperation(value = "查询抄牌", notes = "根据通知号的最后6位查询抄牌")
    @RequestMapping(value = "selectByNoticeNumber", method = RequestMethod.GET)
    public ResponseEntity<JsonResult<CopyBrand>> selectByNoticeNumber(
            @RequestParam(value = "noticeNumber") String noticeNumber) {
        LOGGER.info("查询抄牌");
        if (noticeNumber.length() < 6) {
            return ResponseEntity.ok(new JsonResult<>("201"));
        }
        MeterageNotice notice = meterageNoticeService.selectByLastId(noticeNumber);
        if (notice == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "202"));
        }
        CopyBrand brand = copyBrandService.selectByNoticeNumber(notice.getId());
        if (brand == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "205"));
        }
        return ResponseEntity.ok(new JsonResult<>(0, "查询成功", brand));
    }

    @ApiOperation(value = "查询所有", notes = "查询所有抄牌信息")
    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    public ResponseEntity<JsonResult<List<CopyBrand>>> selectAll() {
        LOGGER.info("查询所有抄牌信息");
        List<CopyBrand> brand = copyBrandService.selectAll();
        if (brand == null || brand.size() == 0) {
            return ResponseEntity.ok(new JsonResult<>(1, "205"));
        }
        return ResponseEntity.ok(new JsonResult<>(0, "查询成功", brand));
    }

    @ApiOperation(value = "按条件查询所有", notes = "查询所有抄牌信息")
    @RequestMapping(value = "selectAllByExample", method = RequestMethod.POST)
    public ResponseEntity<JsonResult<List<CopyBrand>>> selectAllByExample(HttpServletRequest request,
            @RequestBody CopyBrandVO copyBrandVO) {
        LOGGER.info("根据条件查询所有抄牌信息");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "104"));
        }
        if (copyBrandVO == null) {
            copyBrandVO = new CopyBrandVO();
        }
        // 按操作人查询
        //copyBrandVO.setUserNumber(user.getLoginName());
        //copyBrandVO.setRole(user.getRole());
        List<CopyBrand> brand = copyBrandService.selectAll(copyBrandVO);
        if (brand == null || brand.size() == 0) {
            return ResponseEntity.ok(new JsonResult<>(1, "205"));
        }
        return ResponseEntity.ok(new JsonResult<>(0, "查询成功", brand));
    }

    @ApiOperation(value = "分页查询", notes = "按时间倒序分页查询")
    @RequestMapping(value = "selectPageAndOrderBy", method = RequestMethod.POST)
    public ResponseEntity<JsonResult<List<CopyBrand>>> selectPageAndOrderBy(@RequestBody CopyBrand copyBrand,
            @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        LOGGER.info("按时间倒序分页查询");
        if (pageNum <= 0) {
            return ResponseEntity.ok(new JsonResult<>(1, "901"));
        }
        if (pageSize <= 0) {
            return ResponseEntity.ok(new JsonResult<>(1, "902"));
        }
        String orderby = new String("copybrand_id desc");
        List<CopyBrand> brand = copyBrandService.selectPage(pageNum, pageSize, orderby, copyBrand);
        if (brand == null || brand.size() == 0) {
            return ResponseEntity.ok(new JsonResult<>(1, "205"));
        }
        return ResponseEntity.ok(new JsonResult<>(0, "查询成功", brand));
    }

    @ApiOperation(value = "分页查询", notes = "按时间倒序分页查询")
    @RequestMapping(value = "selectPageAndOrderBy", method = RequestMethod.GET)
    public ResponseEntity<JsonResult<List<CopyBrand>>> selectPageAndOrderBy(
            @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        LOGGER.info("按时间倒序分页查询");
        if (pageNum <= 0) {
            return ResponseEntity.ok(new JsonResult<>(1, "901"));
        }
        if (pageSize <= 0) {
            return ResponseEntity.ok(new JsonResult<>(1, "902"));
        }
        String orderby = new String("copybrand_id desc");
        List<CopyBrand> brand = copyBrandService.selectPage(pageNum, pageSize, orderby, null);
        if (brand == null || brand.size() == 0) {
            return ResponseEntity.ok(new JsonResult<>(1, "205"));
        }
        return ResponseEntity.ok(new JsonResult<>(0, "查询成功", brand));
    }

    @ApiOperation(value = "更新抄牌", notes = "更新抄牌主表信息")
    @RequestMapping(value = "updateCopyBrand", method = RequestMethod.POST)
    public ResponseEntity<JsonResult<CopyBrand>> updateCopyBrand(HttpServletRequest request,
            @RequestBody CopyBrand copyBrand) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "104"));
        }
        LOGGER.info("更新抄牌主表信息");
        if (copyBrand == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "206"));
        }
        copyBrandService.update(copyBrand);
        return ResponseEntity.ok(new JsonResult<>(0, "更新成功", copyBrand));
    }

    @ApiOperation(value = "更新抄牌状态", notes = "更新抄牌主表状态信息")
    @RequestMapping(value = "updateCopyBrandStatus", method = RequestMethod.POST)
    public ResponseEntity<JsonResult<CopyBrand>> updateCopyBrandStatus(HttpServletRequest request,
            @RequestBody CopyBrand copyBrand) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "104"));
        }
        if (copyBrand == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "206"));
        }
        LOGGER.info("更新抄牌主表状态为：" + copyBrand.getStatus());
        CopyBrand brand;
        if (StringUtils.isBlank(copyBrand.getNoticeNumber())) {
            return ResponseEntity.ok(new JsonResult<>(1, "207"));
        }
        brand = copyBrandService.selectByNoticeNumber(copyBrand.getNoticeNumber());
        if (brand == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "202"));
        }
        brand.setStatus(copyBrand.getStatus());
        copyBrandService.update(brand);
        return ResponseEntity.ok(new JsonResult<>(0, "更新成功", brand));
    }

    @ApiOperation(value = "作废抄牌", notes = "作废抄牌主表信息")
    @RequestMapping(value = "deleteCopyBrand", method = RequestMethod.POST)
    public ResponseEntity<JsonResult<List<CopyBrand>>> deleteCopyBrand(HttpServletRequest request,
            @RequestBody CopyBrand copyBrand) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "104"));
        }
        if (copyBrand == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "206"));
        }
        if (StringUtils.isBlank(copyBrand.getNoticeNumber())) {
            return ResponseEntity.ok(new JsonResult<>(1, "401"));
        }
        LOGGER.info("作废抄牌主表通知单号为：" + copyBrand.getNoticeNumber());
        CopyBrand brand;
        brand = copyBrandService.selectByNoticeNumber(copyBrand.getNoticeNumber());
        if (brand == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "402"));
        }
        // 查询此抄牌下所有抄牌批次信息是否全删除，若没有全删除则不能删除此抄牌
        List<CopyBrandBatches> copyBrandBatchesList = copyBrandBatchesService.selectByNoticeNumber(brand.getNoticeNumber()); 
        if (copyBrandBatchesList != null && copyBrandBatchesList.size() > 0) {
            ResponseEntity.ok(new JsonResult<>(1, "403"));
        }
        // 标记作废
        brand.setStatus("99");
        copyBrandService.update(brand);
        // 清除抄牌详情表相关对照
//        List<CopyBrandDetails> copyBrandDetails = copyBrandDetailsService.selectByNoticeNumber(brand.getNoticeNumber());
//        if (copyBrandDetails.size() > 0) {
//            for (CopyBrandDetails details : copyBrandDetails) {
//                // 设置为空字符串，设置为null无法执行更新
//                details.setNoticeNumber("");
//            }
//            copyBrandDetailsService.updateBatchByPrimaryKeySelective(copyBrandDetails);
//        }
        List<CopyBrand> brands = copyBrandService.selectAll();
        return ResponseEntity.ok(new JsonResult<>(0, "删除成功", brands));
    }

    @ApiOperation(value = "/savePrintCount", notes = "打印抄牌，打印次数增加1")
    @GetMapping(value = "/savePrintCount")
    public ResponseEntity<JsonResult<CopyBrand>> savePrintCount(HttpServletRequest request,
            @RequestParam(value = "noticeNumber") String noticeNumber) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "104"));
        }
        CopyBrand brand = copyBrandService.selectByNoticeNumber(noticeNumber);
        if (brand == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "202"));
        }
        brand.setPrintCount(brand.getPrintCount() + 1);
        copyBrandService.update(brand);
        LOGGER.info("打印抄牌：" + brand.getNoticeNumber() + ",打印次数为" + brand.getPrintCount());
        return ResponseEntity.ok(new JsonResult<>(0, "打印成功", brand));
    }
    
    @ApiOperation(value = "/finishCopyBrand", notes = "抄牌全部完成")
    @GetMapping(value = "/finishCopyBrand")
    public ResponseEntity<JsonResult<CopyBrand>> finishCopyBrand(HttpServletRequest request,
            @RequestParam(value = "noticeNumber") String noticeNumber) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "104"));
        }
        CopyBrand brand = copyBrandService.selectByNoticeNumber(noticeNumber);
        if (brand == null) {
            return ResponseEntity.ok(new JsonResult<>(1, "202"));
        }
        brand.setStatus("1");
        copyBrandService.update(brand);
        LOGGER.info("抄牌：" + brand.getNoticeNumber() + "已全部完成");
        return ResponseEntity.ok(new JsonResult<>(0, "抄牌已全部完成", brand));
    }
    
}
