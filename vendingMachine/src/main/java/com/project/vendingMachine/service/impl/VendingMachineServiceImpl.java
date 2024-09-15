package com.project.vendingMachine.service.impl;

import com.project.vendingMachine.model.Balance;
import com.project.vendingMachine.model.Product;
import com.project.vendingMachine.repository.VendingMachineRepository;
import com.project.vendingMachine.service.MachineAction;
import com.project.vendingMachine.service.MachineState;
import com.project.vendingMachine.service.VendingMachineService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VendingMachineServiceImpl implements VendingMachineService {

    private final VendingMachineRepository vendingMachineRepository;
    private final MachineAction machineAction;
    private final MachineState machineState;

    @Autowired
    public VendingMachineServiceImpl(
            VendingMachineRepository vendingMachineRepository,
            MachineAction machineAction,
            MachineState machineState
    ){
        this.vendingMachineRepository = vendingMachineRepository;
        this.machineAction = machineAction;
        this.machineState = machineState;
    }
    @Override
    public List<Product> getAllProducts() {
        return vendingMachineRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return vendingMachineRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("Product with Id " + id + " does not exist."));

    }

    @Override
    public void deleteProduct(Long id) {
        var exists = vendingMachineRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("product with id " + id + " does not exist" );
        }
        vendingMachineRepository.deleteById(id);
    }

    @Transactional
    public void updateProduct(Long productId, String name, Integer quantity, double price) {
        Product product = vendingMachineRepository.findById(productId).orElseThrow(() -> new IllegalStateException(
                "Product with Id " + productId + " does not exist."));

        if(name!=null && !name.isEmpty() && !Objects.equals(product.getName(), name)){
            product.setName(name);
        }

        if(!(quantity > 100) && quantity.equals(product.getQuantity()) && !(quantity < 0)){
            product.setQuantity(quantity);
        }

        if (!(price <= 0) &&
                !Objects.equals(product.getPrice(),price)){
            product.setPrice(price);
        }

    }

    @Override
    public void addBalance(double amount) {
        machineState.addBalance(amount);
    }

    @Override
    public void dispenseItem(Long id) {
        Product p = getProduct(id);
        machineState.chargeAmount(p.getPrice());
        machineAction.dispenseProduct(p);
    }

    @Override
    public Balance getBalance() {
        return machineState.getBalance();
    }

    @Override
    public Product addProduct(Product p) {
    Optional<Product> productOptional = vendingMachineRepository.findProductByName(p.getName());
        if (productOptional.isPresent()){
        throw new IllegalStateException("Product " + p.getName() + " already in the machine.");
    }
        return vendingMachineRepository.save(p);
}
}
