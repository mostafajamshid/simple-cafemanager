/*
 * Copyright (c) 2021.  Mostafa Jamshid https://www.linkedin.com/in/mostafa-jamshid/
 */

package com.sfl.rest.payload.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCriteria {
    private String name;
    private String description;
    private Double totalAmount;
    private Boolean isOpen;
}
