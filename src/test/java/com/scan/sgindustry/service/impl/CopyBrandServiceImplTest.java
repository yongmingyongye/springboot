package com.scan.sgindustry.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.scan.sgindustry.SpringBootDruidApplication;
import com.scan.sgindustry.entity.CopyBrandDetails;
import com.scan.sgindustry.entity.TBWeightProduce;
import com.scan.sgindustry.service.CopyBrandDetailsService;
import com.scan.sgindustry.service.TBWeightProduceService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDruidApplication.class)
public class CopyBrandServiceImplTest {
    
    @Autowired
    CopyBrandDetailsService copyBrandDetailsService;
    
    @Autowired
    TBWeightProduceService tbWeightProduceService;
    
    //@Test
    public void testSelectByNoticeNumber() {
        String noticeNumber = "XS0000206103";
        List<CopyBrandDetails> copyBrandDetails = copyBrandDetailsService.selectByNoticeNumber(noticeNumber);
        System.out.println(copyBrandDetails);
    }
    
    @Test
    public void updateBatchByPrimaryKeySelective() {
        String noticeNumber = "XS0000206773";
        List<CopyBrandDetails> copyBrandDetails = copyBrandDetailsService.selectByNoticeNumber(noticeNumber);
        int id = copyBrandDetails.get(0).getId();
        for(CopyBrandDetails details : copyBrandDetails) {
            details.setNoticeNumber("");
        }
        System.out.println("通过通知单号查到的修改前个数：" + copyBrandDetails.size());
        List<TBWeightProduce> tb = tbWeightProduceService.selectByIdAndReqcodeIsNull(id);
        System.out.println("通过id查到的个数：" + tb.size());
        tb = tbWeightProduceService.selectByIdAndReqcodeIsNull(1381209);
        System.out.println("通过id查到的个数：" + tb.size());
        copyBrandDetailsService.updateBatchByPrimaryKeySelective(copyBrandDetails);
        copyBrandDetails = copyBrandDetailsService.selectByNoticeNumber(noticeNumber);
        System.out.println("通过通知单号查到的修改后个数：" + copyBrandDetails.size());
        tb = tbWeightProduceService.selectByIdAndReqcodeIsNull(id);
        System.out.println("通过id查到的个数：" + tb.size());
    }
    
}
