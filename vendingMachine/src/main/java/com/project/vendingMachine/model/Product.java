package com.project.vendingMachine.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private Integer quantity;

    private double price;

    public Product(String name, Integer quantity, double price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

}
