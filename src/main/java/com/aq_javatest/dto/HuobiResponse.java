package com.aq_javatest.dto;

import java.util.List;

public class HuobiResponse {

    List<HuobiPairsDTO> data;
    String status;
    long ts;

    public List<HuobiPairsDTO> getData() {
        return data;
    }

    public void setData(List<HuobiPairsDTO> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }
    @Override
    public String toString() {
        return "HuobiResponse{" +
                "data=" + data +
                ", status='" + status + '\'' +
                ", ts=" + ts +
                '}';
    }
}
