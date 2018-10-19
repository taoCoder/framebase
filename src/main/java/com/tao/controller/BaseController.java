package com.tao.controller;

import com.tao.annotation.Login;
import com.tao.dao.mapper.CouponRecordMapper;
import com.tao.domain.*;
import com.tao.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author huangtao54
 * @description
 * @date 2018/10/16
 */
@Controller
public class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private CouponRecordMapper orderReadMapper;

    @RequestMapping("/hello")
    @ResponseBody
    @Login
    public Result sayHello(User user, ClientInfo clientInfo) {
        CouponRecord record = orderReadMapper.selectByPrimaryKey(1);
        log.info("param user={},clientInfo={}", JacksonUtils.toJson(user), JacksonUtils.toJson(clientInfo));
        return Result.ok(record);
    }
}
