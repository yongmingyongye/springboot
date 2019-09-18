package com.scan.sgindustry.tools;

import java.util.HashMap;
import java.util.Map;

public class ErrorCode {
    
    public static Map<String, String> codeMessage = new HashMap<>();
    
    static {
        codeMessage.put("101", "用户名或密码不为空");
        codeMessage.put("102", "用户名或密码错误");
        codeMessage.put("103", "数据库用户异常，请与管理员联系");
        codeMessage.put("104", "用户未登录");
        codeMessage.put("201", "请输入至少6个字符(倒数6位及以上)");
        codeMessage.put("202", "输入的计量通知号不存在");
        codeMessage.put("203", "该通知号为对外磅点");
        codeMessage.put("204", "已存在该计量通知号的抄牌信息");
        codeMessage.put("205", "不存在该计量通知号的抄牌信息");
        codeMessage.put("206", "信息不能为空");
        codeMessage.put("207", "ID错误");
        codeMessage.put("208", "通知单号查询失败");
        codeMessage.put("301", "保存失败，抄牌主表信息不存在");
        codeMessage.put("302", "该抄牌已完成，不能再新增批次");
        codeMessage.put("303", "保存失败，抄牌批次编号生成错误");
        codeMessage.put("304", "抄牌批次保存失败");
        codeMessage.put("305", "抄牌批次查询失败");
        codeMessage.put("306", "抄牌批次作废失败");
        codeMessage.put("307", "抄牌批次完成失败");
        codeMessage.put("308", "该通知单号详情信息不存在");
        codeMessage.put("401", "删除失败");
        codeMessage.put("402", "数据不存在，删除失败");
        codeMessage.put("403", "删除失败，存在抄牌批次信息不能删除");
        codeMessage.put("501", "二维码信息保存失败，未检测抄牌批次信息");
        codeMessage.put("502", "二维码信息保存失败，无法查到数据库对应信息");
        codeMessage.put("503", "数据有误，同一个Id存在多条数据");
        codeMessage.put("504", "该二维码信息已保存通知单号");
        codeMessage.put("505", "该二维码的钢号或规格型号信息与抄牌的信息不一致");
        codeMessage.put("506", "该二维码数据与数据库中的数据不一致");
        codeMessage.put("507", "不存在该炉号和捆号对应的信息");
        codeMessage.put("901", "页码不正确");
        codeMessage.put("902", "每页显示个数不能小于0");
    }

}
