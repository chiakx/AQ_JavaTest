package com.aq_javatest.repository;

import com.aq_javatest.dto.CryptoPairing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoPairPricingRepository extends JpaRepository<CryptoPairing, String> {

}
