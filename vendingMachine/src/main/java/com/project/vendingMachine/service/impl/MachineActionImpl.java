package com.project.vendingMachine.service.impl;

import com.project.vendingMachine.model.Product;
import com.project.vendingMachine.repository.VendingMachineRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MachineActionImpl implements com.project.vendingMachine.service.MachineAction {
    private final VendingMachineRepository vendingMachineRepository;

    @Autowired
    public MachineActionImpl(VendingMachineRepository vendingMachineRepository) {
        this.vendingMachineRepository = vendingMachineRepository;
    }

    @Override
    @Transactional
    public void dispenseProduct(Product p) {
        if (p.getQuantity() <= 0){
            throw new IllegalStateException(p.getName() + " not in stock.");
        } else {
            p.setQuantity(p.getQuantity() - 1);
            vendingMachineRepository.save(p);
            System.out.println("Dispensed " + p.getName() + ".");
        }
    }
}
