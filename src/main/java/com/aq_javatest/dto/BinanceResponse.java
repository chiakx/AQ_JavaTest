package com.aq_javatest.dto;

import java.util.List;

public class BinanceResponse {
    List<BinancePairsDTO> binancePairsDTOList;

    public List<BinancePairsDTO> getBinancePairsDTOList() {
        return binancePairsDTOList;
    }

    public void setBinancePairsDTOList(List<BinancePairsDTO> binancePairsDTOList) {
        this.binancePairsDTOList = binancePairsDTOList;
    }

    @Override
    public String toString() {
        return "BinanceResponse{" +
                "binancePairsDTOList=" + binancePairsDTOList +
                '}';
    }
}
