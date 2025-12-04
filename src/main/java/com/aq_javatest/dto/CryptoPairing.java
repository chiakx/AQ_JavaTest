package com.aq_javatest.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Data
@Component
@Getter
@Setter
@Entity
@Table(name = "CryptoPairing")
public class CryptoPairing {
    @Id
    String symbol;
    String askPrice;
    String bidPrice;

    @Override
    public String toString() {
        return "CryptoPairPricing{" +
                "symbol='" + symbol + '\'' +
                ", askPrice='" + askPrice + '\'' +
                ", bidPrice='" + bidPrice + '\'' +
                '}';
    }
}
