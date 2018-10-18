package com.tao.controller;

import com.tao.annotation.Login;
import com.tao.domain.ClientInfo;
import com.tao.domain.Result;
import com.tao.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author huangtao54
 * @description
 * @date 2018/10/16
 */
@Controller
public class BaseController {

    @RequestMapping("/hello")
    @ResponseBody
    @Login
    public Result sayHello(User user, ClientInfo clientInfo){
        user.setDate(new Date());
        return Result.ok(user);
    }
}
