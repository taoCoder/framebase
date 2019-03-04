package com.tao.controller;

import com.google.common.base.Preconditions;
import com.tao.annotation.Login;
import com.tao.common.ClientInfo;
import com.tao.common.Result;
import com.tao.dto.UserDto;
import com.tao.entity.*;
import com.tao.enums.ErrorEnum;
import com.tao.utils.BeanValidators;
import com.tao.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.Valid;

/**
 * @author huangtao54
 * @description
 * @date 2018/10/16
 */
@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/addUser")
    @ResponseBody
    @Login
    public Result addUser(@Valid UserDto userDto, ClientInfo clientInfo) {
        Preconditions.checkNotNull(userDto.getPin());
        User user = userDto.convertToUser();
        String check = BeanValidators.check(user);
        if (check!=null){
            return Result.failed(ErrorEnum.PARAM_IllEGAL.getCode(),check);
        }
        log.info("param user={},clientInfo={}", JacksonUtils.toJson(user), JacksonUtils.toJson(clientInfo));
        return Result.ok(user);
    }
}
