package com.aguang.jinjuback.dto;

import com.aguang.jinjuback.pojo.Jinju;

import java.util.List;

public class JinjuListDto {

    List<Jinju> jinjuList;
    Integer total;

    public List<Jinju> getJinjuList() {
        return jinjuList;
    }

    public void setJinjuList(List<Jinju> jinjuList) {
        this.jinjuList = jinjuList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
