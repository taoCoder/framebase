package com.tao.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Converter;
import com.tao.entity.User;
import com.tao.serializer.DateDeserializer;
import com.tao.serializer.DateSerializer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author huangtao54
 * @description user
 * @date 2019/3/4
 */
@Setter
@Getter
public class UserDto {
    private String pin;
    @NotNull(message="名字不能为空")
    private String name;

    private Integer age;

    private String work;

    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializer.class)
    private Date date;

    public User convertToUser(){
        UserDtoConverter userDtoConverter = new UserDtoConverter();
        User user = userDtoConverter.convert(this);
        return user;
    }

    private static class UserDtoConverter extends Converter<UserDto,User>{
        @Override
        protected User doForward(UserDto userDto) {
            User user = new User();
            BeanUtils.copyProperties(userDto,user);
            return user;
        }

        @Override
        protected UserDto doBackward(User user) {
            UserDto dto = new UserDto();
            BeanUtils.copyProperties(user,dto);
            return dto;
        }
    }
}
