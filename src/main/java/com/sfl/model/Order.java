/*
 * Copyright (c) 2021.  Mostafa Jamshid https://www.linkedin.com/in/mostafa-jamshid/
 */

package com.sfl.model;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "cafe_order")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductInOrder> products;
    private Double totalAmount;
    private Boolean isOpen;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ProductInOrder> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInOrder> products) {
        this.products = products;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}

