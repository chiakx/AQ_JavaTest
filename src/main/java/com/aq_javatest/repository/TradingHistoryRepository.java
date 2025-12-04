package com.aq_javatest.repository;

import com.aq_javatest.dto.TradingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TradingHistoryRepository extends JpaRepository<TradingHistory, Long> {
    List<TradingHistory> findByUserId(long userId);
}
