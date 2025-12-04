package com.aq_javatest.service;

import com.aq_javatest.dto.*;
import com.aq_javatest.repository.CryptoPairPricingRepository;
import com.aq_javatest.repository.TradingHistoryRepository;
import com.aq_javatest.repository.UserAssetsRepository;
import com.aq_javatest.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class AQServiceImpl implements AQService {

    @Autowired
    private TradingHistoryRepository tradingHistoryRepository;

    @Autowired
    private CryptoPairPricingRepository cryptoPairPricingRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserAssetsRepository userAssetsRepository;

    @Override
    public List<CryptoPairing> getBestPrices() {
        return cryptoPairPricingRepository.findAll();
    }

    @Override
    public String trade(long userId, String orderType, String symbol, double tradingPrice, int quantity) {
        double tradingAmount = tradingPrice * quantity;
        Optional<Users> user = usersRepository.findById(userId);
        if (user.isEmpty()){
            return "User not found";
        }
        Users usr = user.get();
        double walletBalance  = usr.getWalletBalance();
        if (orderType.equals("BUY")){
            if (walletBalance >= tradingAmount) {
                //allow trade
                processBuyTrade(tradingAmount, orderType, userId, symbol, tradingPrice, quantity);

                usr.setWalletBalance(walletBalance - tradingAmount);
                usersRepository.save(usr);

                return "Trade Success";
            } else {
                return "Trade Failed - Insufficient Funds";
            }
        } else if (orderType.equals("SELL")){
            String tradeStatus = processSellTrade(tradingAmount, orderType, userId, symbol, tradingPrice, quantity);

            if (tradeStatus.equals("Trade Success")){
                usr.setWalletBalance(walletBalance + tradingAmount);
                usersRepository.save(usr);

                return "Trade Success";
            }
            return "Trade Failed - Insufficient Funds";
        }
        return "Wrong Order Type";
    }

    private String processSellTrade(double tradingAmount, String orderType, long userId, String symbol, double tradingPrice, int quantity) {
        List<UserAssets> userAssets = userAssetsRepository.findByUserId(userId);
        List<UserAssets> userAssetsList = userAssets.stream().filter(userAsset ->
                userAsset.getSymbol().equals(symbol)).toList();
        if (userAssetsList.isEmpty()){
            return "Trade Failed - Insufficient Funds";
        }
        UserAssets userAsset = userAssetsList.get(0);
        int cryptoBalance = userAsset.getCryptoBalance();
        if(cryptoBalance < quantity){
            return "Trade Failed - Insufficient Funds";
        }

        userAsset.setCryptoBalance(cryptoBalance - quantity);
        userAssetsRepository.save(userAsset);

        TradingHistory tradingHistory = new TradingHistory();
        tradingHistory.setCryptoPair(symbol);
        tradingHistory.setOrderDate(LocalDateTime.now());
        tradingHistory.setOrderPrice(tradingPrice);
        tradingHistory.setOrderQuantity(quantity);
        tradingHistory.setUserId(userId);
        tradingHistory.setTotalPrice(tradingAmount);
        tradingHistory.setOrderType(orderType);
        tradingHistoryRepository.save(tradingHistory);

        return "Trade Success";
    }

    private void processBuyTrade(double tradingAmount, String orderType, long userId, String symbol, double tradingPrice, int quantity) {
        List<UserAssets> userAssets = userAssetsRepository.findByUserId(userId);
        List<UserAssets> userAssetsList = userAssets.stream().filter(userAsset ->
            userAsset.getSymbol().equals(symbol)).toList();
        UserAssets newUserAsset = new UserAssets();
        if (userAssetsList.isEmpty()){
            newUserAsset.setSymbol(symbol);
            newUserAsset.setUserId(userId);
            newUserAsset.setCryptoBalance(quantity);
        } else{
            newUserAsset = userAssetsList.get(0);
            newUserAsset.setCryptoBalance(newUserAsset.getCryptoBalance() + quantity);
        }
        userAssetsRepository.save(newUserAsset);

        TradingHistory tradingHistory = new TradingHistory();
        tradingHistory.setCryptoPair(symbol);
        tradingHistory.setOrderDate(LocalDateTime.now());
        tradingHistory.setOrderPrice(tradingPrice);
        tradingHistory.setOrderQuantity(quantity);
        tradingHistory.setUserId(userId);
        tradingHistory.setTotalPrice(tradingAmount);
        tradingHistory.setOrderType(orderType);
        tradingHistoryRepository.save(tradingHistory);


    }

    @Override
    public List<CryptoWallet> getCryptoWallet(long userId) {
        List<UserAssets> userAssets = userAssetsRepository.findByUserId(userId);
        List<CryptoWallet> cryptoWallets = new ArrayList<>();
        userAssets.forEach(userAssets1 -> {
            CryptoWallet cryptoWallet = new CryptoWallet();
            cryptoWallet.setCryptoPairing(userAssets1.getSymbol());
            cryptoWallet.setCryptoBalance(userAssets1.getCryptoBalance());
            cryptoWallets.add(cryptoWallet);
        });
        return cryptoWallets;
    }

    @Override
    public List<TradingHistory> getTradingHistory(long userId) {
        return tradingHistoryRepository.findByUserId(userId).stream().sorted(Comparator.comparing(TradingHistory::getOrderDate).reversed()).toList();
    }
}
