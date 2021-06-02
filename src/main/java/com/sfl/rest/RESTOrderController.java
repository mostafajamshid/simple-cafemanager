/*
 * Copyright (c) 2021.  Mostafa Jamshid https://www.linkedin.com/in/mostafa-jamshid/
 */

package com.sfl.rest;

import com.sfl.mapper.OrderMapper;
import com.sfl.model.Order;
import com.sfl.rest.payload.response.GenericResponse;
import com.sfl.rest.payload.response.OrderResponse;
import com.sfl.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RESTOrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping("/orders")
    public GenericResponse getOrders() {
        List<Order> orders = orderService.findAll();
        return new GenericResponse(orderMapper.toOrderResponses(orders));
    }

}
