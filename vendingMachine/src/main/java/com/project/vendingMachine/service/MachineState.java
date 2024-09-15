package com.project.vendingMachine.service;

import com.project.vendingMachine.model.Balance;

public interface MachineState {
    Balance getBalance();
    void addBalance(double amount);
    void chargeAmount(double amount);
    Balance updateBalanceAdd(Balance b, double amount);
    Balance updateBalanceSub(Balance b, double amount);

}