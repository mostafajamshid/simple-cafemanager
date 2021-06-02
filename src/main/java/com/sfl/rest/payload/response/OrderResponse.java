/*
 * Copyright (c) 2021.  Mostafa Jamshid https://www.linkedin.com/in/mostafa-jamshid/
 */

package com.sfl.rest.payload.response;

import com.sfl.model.ProductInOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private int id;
    private Set<ProductInOrder> products = new HashSet<>();
    private Double totalAmount;
    private Boolean isOpen;
    private Date dateCreated;
}
