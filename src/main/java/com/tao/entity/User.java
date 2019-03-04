package com.tao.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tao.serializer.DateDeserializer;
import com.tao.serializer.DateSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author huangtao54
 * @description
 * @date 2018/10/17
 */
@Setter
@Getter
public class User {
    private String pin;

    @NotNull(message="名字不能为空")
    private String name;

    @NotNull(message="年龄不能为空")
    private Integer age;

    @NotNull(message = "work不能为空")
    private String work;

    private Date date;
}
