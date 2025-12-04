package com.aq_javatest.dto;

public class HuobiPairsDTO {
    String symbol;
    String open;
    String high;
    String low;
    String close;
    String amount;
    String vol;
    String count;
    String bid;
    String bidSize;
    String ask;
    String askSize;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBidSize() {
        return bidSize;
    }

    public void setBidSize(String bidSize) {
        this.bidSize = bidSize;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getAskSize() {
        return askSize;
    }

    public void setAskSize(String askSize) {
        this.askSize = askSize;
    }

    @Override
    public String toString() {
        return "HuobiPairsDTO{" +
                "symbol='" + symbol + '\'' +
                ", open='" + open + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", close='" + close + '\'' +
                ", amount='" + amount + '\'' +
                ", vol='" + vol + '\'' +
                ", count='" + count + '\'' +
                ", bid='" + bid + '\'' +
                ", bidSize='" + bidSize + '\'' +
                ", ask='" + ask + '\'' +
                ", askSize='" + askSize + '\'' +
                '}';
    }
}
