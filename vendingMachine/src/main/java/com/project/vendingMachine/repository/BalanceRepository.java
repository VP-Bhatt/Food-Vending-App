package com.project.vendingMachine.repository;

import com.project.vendingMachine.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
}
