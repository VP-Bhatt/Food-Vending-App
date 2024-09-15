package com.project.vendingMachine.repository;

import com.project.vendingMachine.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendingMachineRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByName(String name);

}
