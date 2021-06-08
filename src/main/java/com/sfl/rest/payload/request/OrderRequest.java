/*
 * Copyright (c) 2021.  Mostafa Jamshid https://www.linkedin.com/in/mostafa-jamshid/
 */

package com.sfl.rest.payload.request;


import com.sfl.model.ProductInOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private List<ProductInOrderRequest> products;
    private String name;
    private String description;
    private Double totalAmount;
    private Boolean isOpen;
}
