/*
 * Copyright (c) 2021.  Mostafa Jamshid https://www.linkedin.com/in/mostafa-jamshid/
 */

package com.sfl.rest;

import com.sfl.mapper.OrderMapper;
import com.sfl.model.Order;
import com.sfl.model.ProductInOrder;
import com.sfl.rest.payload.request.OrderRequest;
import com.sfl.rest.payload.response.GenericResponse;
import com.sfl.rest.payload.response.OrderResponse;
import com.sfl.service.OrderService;
import com.sfl.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RESTOrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final OrderMapper orderMapper;

    @GetMapping("/orders")
    public GenericResponse getOrders() {
        List<Order> orders = orderService.findAll();
        return new GenericResponse(orderMapper.toOrderResponses(orders));
    }


    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponse saveOrder(@RequestBody @Valid OrderRequest orderRequest) {
        Order order = orderMapper.toOrder(orderRequest);
        List<ProductInOrder> productInOrderList = new ArrayList<>();
        for (int i = 0; i < orderRequest.getProducts().size(); i++) {
            ProductInOrder productInOrder = new ProductInOrder();
            productInOrder.setProduct(productService.findProduct(orderRequest.getProducts().get(i).getProductId()));
            productInOrder.setQuantity(orderRequest.getProducts().get(i).getQuantity());
            productInOrder.setAmount(productInOrder.getProduct().getPrice());
            productInOrderList.add(productInOrder);
        }
        order.setProducts(productInOrderList);
        orderService.save(order);
        return new GenericResponse(orderMapper.toOrderResponse(order));
    }



}
