package com.project.vendingMachine.service.impl;

import com.project.vendingMachine.model.Balance;
import com.project.vendingMachine.repository.BalanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MachineStateImpl implements com.project.vendingMachine.service.MachineState {

    private final BalanceRepository balanceRepository;
    @Autowired
    public MachineStateImpl(BalanceRepository balanceRepository){
        this.balanceRepository = balanceRepository;
    }
    @Override
    public Balance getBalance() {
        return balanceRepository.findAll().stream().findFirst().orElseThrow(() -> new IllegalStateException("No balance entries"));
    }

    @Override
    public void addBalance(double amount) {
        if(amount < 0){
            throw new IllegalStateException("Can not add negative balance");
        }
        balanceRepository.save(updateBalanceAdd(getBalance(),amount));
    }

    @Override
    @Transactional
    public void chargeAmount(double amount) {
        if(getBalance().getAmount() - amount < 0){
            throw new IllegalStateException("Insufficient balance");
        }
        balanceRepository.save(updateBalanceSub(getBalance(),amount));
    }

    @Override
    public Balance updateBalanceAdd(Balance b, double amount) {
        b.setAmount(b.getAmount() + amount);
        return b;
    }

    @Override
    public Balance updateBalanceSub(Balance b, double amount) {
        b.setAmount(b.getAmount() - amount);
        return b;
    }
}
