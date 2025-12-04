package com.aq_javatest.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TradeRequest {
    long userId;
    String orderType;
    String symbol;
    double tradingPrice;
    int quantity;
}
