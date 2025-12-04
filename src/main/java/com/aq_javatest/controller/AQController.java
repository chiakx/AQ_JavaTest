package com.aq_javatest.controller;

import com.aq_javatest.dto.CryptoPairing;
import com.aq_javatest.dto.CryptoWallet;
import com.aq_javatest.dto.TradeRequest;
import com.aq_javatest.dto.TradingHistory;
import com.aq_javatest.service.AQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AQController {
    @Autowired
    private AQService aqService;

    @GetMapping("/bestPrices")
    public List<CryptoPairing> getBestPrices() {
        return aqService.getBestPrices();
    }

    @PostMapping("/trade")
    public String tradeCrypto(@RequestBody TradeRequest tradeRequest){
        return aqService.trade(tradeRequest.getUserId(), tradeRequest.getOrderType(), tradeRequest.getSymbol(), tradeRequest.getTradingPrice(), tradeRequest.getQuantity());
    }

    @GetMapping("/cryptoWallet")
    public List<CryptoWallet> getCryptoWallet(@RequestParam long userId){
        return aqService.getCryptoWallet(userId);
    }

    @GetMapping("/tradingHistory")
    public List<TradingHistory> getTradingHistory(@RequestParam long userId){
        return aqService.getTradingHistory(userId);
    }



}
