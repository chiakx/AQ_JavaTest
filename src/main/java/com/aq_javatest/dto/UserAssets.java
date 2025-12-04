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
@Table(name = "UserAssets")
public class UserAssets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long assetId;
    long userId;
    String symbol;
    int cryptoBalance;

}
