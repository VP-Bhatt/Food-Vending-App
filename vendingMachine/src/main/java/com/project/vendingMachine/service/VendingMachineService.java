package com.project.vendingMachine.service;

import com.project.vendingMachine.model.Balance;
import com.project.vendingMachine.model.Product;
import java.util.List;
import java.util.Optional;

public interface VendingMachineService {
    List<Product> getAllProducts();

    Product getProduct(Long id);

    void deleteProduct(Long id);

    void updateProduct(Long productId, String name, Integer quantity, double price);

    void addBalance(double amount);

    void dispenseItem(Long id);

    Balance getBalance();

    Product addProduct(Product p);
}
