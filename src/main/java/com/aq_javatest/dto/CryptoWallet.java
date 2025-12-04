package com.aq_javatest.dto;

public class CryptoWallet {

    String cryptoPairing;
    int cryptoBalance;

    public String getCryptoPairing() {
        return cryptoPairing;
    }

    public void setCryptoPairing(String cryptoPairing) {
        this.cryptoPairing = cryptoPairing;
    }

    public int getCryptoBalance() {
        return cryptoBalance;
    }

    public void setCryptoBalance(int cryptoBalance) {
        this.cryptoBalance = cryptoBalance;
    }

    @Override
    public String toString() {
        return "CryptoWallet{" +
                "cryptoPairing='" + cryptoPairing + '\'' +
                ", cryptoBalance=" + cryptoBalance +
                '}';
    }
}
