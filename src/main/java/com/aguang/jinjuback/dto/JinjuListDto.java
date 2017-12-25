package com.aguang.jinjuback.dto;

import com.aguang.jinjuback.model.Jinju;

import java.util.List;

public class JinjuListDto {

    List<Jinju> jinjuList;
    int total;

    public List<Jinju> getJinjuList() {
        return jinjuList;
    }

    public void setJinjuList(List<Jinju> jinjuList) {
        this.jinjuList = jinjuList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
