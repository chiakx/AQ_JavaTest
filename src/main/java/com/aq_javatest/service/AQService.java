package com.aq_javatest.service;

import com.aq_javatest.dto.CryptoPairing;
import com.aq_javatest.dto.CryptoWallet;
import com.aq_javatest.dto.TradingHistory;

import java.util.List;

public interface AQService {

    List<CryptoPairing> getBestPrices();

    String trade(long userId, String orderType, String symbol, double tradingPrice, int quantity);

    List<CryptoWallet> getCryptoWallet(long userId);

    List<TradingHistory> getTradingHistory(long userId);
}
