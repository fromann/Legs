package com.legs.common;

import com.legs.pojo.User;

import java.util.ArrayList;
import java.util.List;

//返回结果类
public class ResultMap<T>{
    private boolean status;//标志
    private String message;//失败的原因
    private int type;
    private long total;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private List<T> list=new ArrayList<>();

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
