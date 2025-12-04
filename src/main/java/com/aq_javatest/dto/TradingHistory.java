package com.aq_javatest.dto;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
@Getter
@Setter
@Entity
@Table(name = "TradingHistory")
public class TradingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long userId;
    String cryptoPair;
    String orderType;
    double orderPrice;
    int orderQuantity;
    double totalPrice;
    LocalDateTime orderDate;

    @Override
    public String toString() {
        return "TradingHistory{" +
                "id=" + id +
                ", userId=" + userId +
                ", cryptoPair='" + cryptoPair + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderPrice=" + orderPrice +
                ", orderQuantity=" + orderQuantity +
                ", totalPrice=" + totalPrice +
                ", orderDate=" + orderDate +
                '}';
    }
}
