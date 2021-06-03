package com.sfl.model;


import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;


@Entity
@Data
@Table(name = "product_order")
public class ProductInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Product product;
    private Double amount;
    private  int quantity;



}
