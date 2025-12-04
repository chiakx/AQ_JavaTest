package com.aq_javatest.service;

import com.aq_javatest.dto.*;
import com.aq_javatest.repository.CryptoPairPricingRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Component
public class AQScheduledTasks {
    @Autowired
    private CryptoPairPricingRepository cryptoPairPricingRepository;

    private static final Logger log = LoggerFactory.getLogger(AQScheduledTasks.class);
    private static final String BINANCE_URL = "https://api.binance.com/api/v3/ticker/bookTicker";
    private static final String HUOBI_URL = "https://api.huobi.pro/market/tickers";
    RestClient restClient = RestClient.create();
    ObjectMapper mapper = new ObjectMapper();

    @Scheduled(fixedRate = 10000)
    public void retrievePricing() throws IOException {
        String resultBinance = restClient.get().uri(BINANCE_URL).retrieve().body(String.class);
        List<BinancePairsDTO> binanceResponse = mapper.readValue(resultBinance, new TypeReference<>() {});
        List<BinancePairsDTO> binancePairsDTOList = binanceResponse.stream()
                .filter(binancePair ->
                        binancePair.getSymbol().equals("ETHUSDT") || binancePair.getSymbol().equals("BTCUSDT"))
                .sorted(Comparator.comparing(BinancePairsDTO::getSymbol))
                .toList();

        String resultHuoBi = restClient.get().uri(HUOBI_URL).retrieve().body(String.class);
        HuobiResponse huobiResponse = mapper.readValue(resultHuoBi, new TypeReference<>() {});
        List<HuobiPairsDTO> huobiPairsDTOList = huobiResponse.getData().stream()
                .filter(huobiPairsDTO -> huobiPairsDTO.getSymbol().equalsIgnoreCase("ETHUSDT") || huobiPairsDTO.getSymbol().equalsIgnoreCase("BTCUSDT"))
                .sorted(Comparator.comparing(HuobiPairsDTO::getSymbol))
                .toList();

        List<CryptoPairing> bestPairPricingList = getBestPrice(binancePairsDTOList, huobiPairsDTOList);
        log.info(bestPairPricingList.toString());
        cryptoPairPricingRepository.saveAll(bestPairPricingList);
        //add to H2 DB

    }

    private List<CryptoPairing> getBestPrice(List<BinancePairsDTO> binancePairsDTOList, List<HuobiPairsDTO> huobiPairsDTOList){
        List<CryptoPairing> bestPairPricing = new ArrayList<>();
        for (int i = 0 ; i < binancePairsDTOList.size(); i++){
            CryptoPairing cryptoPairing = new CryptoPairing();
            cryptoPairing.setSymbol(binancePairsDTOList.get(i).getSymbol());

            if (Double.parseDouble(binancePairsDTOList.get(i).getAskPrice()) >= Double.parseDouble(huobiPairsDTOList.get(i).getAsk())){
                cryptoPairing.setAskPrice(binancePairsDTOList.get(i).getAskPrice());
            } else{
                cryptoPairing.setAskPrice(huobiPairsDTOList.get(i).getAsk());
            }
            if (Float.parseFloat(binancePairsDTOList.get(i).getBidPrice()) <= Float.parseFloat(huobiPairsDTOList.get(i).getBid())){
                cryptoPairing.setBidPrice(binancePairsDTOList.get(i).getBidPrice());
            } else{
                cryptoPairing.setBidPrice(huobiPairsDTOList.get(i).getBid());
            }
            bestPairPricing.add(cryptoPairing);
        }
        return bestPairPricing;
    }

}
