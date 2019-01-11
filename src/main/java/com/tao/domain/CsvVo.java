package com.tao.domain;

import com.tao.annotation.Column;

import java.util.Date;

/**
 * @author huangtao54
 * @description csv
 * @date 2019/1/10
 */
public class CsvVo {
    @Column(name = "id")
    private int id;
    @Column(name = "姓名")
    private String name;
    @Column(name = "时间")
    private Date date;
    private String no;

    public CsvVo(int id, String name, Date date, String no) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.no = no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
