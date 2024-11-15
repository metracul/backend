package com.example.demo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    @Query("SELECT u.balance FROM UserData u WHERE u.vkId   = :vkId")
    Integer findUserBalanceById(@Param("vkId") Long vkId);

    @Modifying
    @Transactional
    @Query("UPDATE UserData u SET u.balance = :balance WHERE u.vkId = :vkId")
    int updateUserBalance(@Param("vkId") Long vkId, @Param("balance") int balance);
}
