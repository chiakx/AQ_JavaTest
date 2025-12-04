package com.aq_javatest.repository;

import com.aq_javatest.dto.UserAssets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAssetsRepository extends JpaRepository<UserAssets, Long> {
    List<UserAssets> findByUserId(long userId);
}
