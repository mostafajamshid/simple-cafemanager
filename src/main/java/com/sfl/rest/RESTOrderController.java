/*
 * Copyright (c) 2021.  Mostafa Jamshid https://www.linkedin.com/in/mostafa-jamshid/
 */

package com.sfl.rest;

import com.sfl.mapper.OrderMapper;
import com.sfl.model.Order;
import com.sfl.model.ProductInOrder;
import com.sfl.rest.payload.request.OrderCriteria;
import com.sfl.rest.payload.request.OrderRequest;
import com.sfl.rest.payload.response.GenericResponse;
import com.sfl.rest.payload.response.SearchResponse;
import com.sfl.service.OrderService;
import com.sfl.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RESTOrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final OrderMapper orderMapper;

    @GetMapping("/orders")
    public SearchResponse getOrders() {
        List<Order> orders = orderService.findAll();
        return new SearchResponse(orders.size(), orderMapper.toOrderResponses(orders));
    }

    @PostMapping("/orders/search")
    public SearchResponse searchOrders(@RequestBody OrderCriteria orderCriteria) {
        List<Order> orders = orderService.findBySearchCriteria(orderCriteria);
        return new SearchResponse(orders.size(), orderMapper.toOrderResponses(orders));
    }

    @GetMapping("/orders/{id}")
    public GenericResponse getOrder(@PathVariable int id) {
        Order order = orderService.findOrder(id);
        return new GenericResponse(orderMapper.toOrderResponse(order));
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable int id) {
        orderService.delete(id);
    }

    @PutMapping("/orders/{id}/close")
    public GenericResponse closeOrder(@PathVariable int id) {
        Order order = orderService.findOrder(id);
        if (order.getIsOpen()) {
            order.setIsOpen(Boolean.FALSE);
            orderService.save(order);
        }
        return new GenericResponse(orderMapper.toOrderResponse(order));
    }

    @PutMapping("/orders/{id}/open")
    public GenericResponse openOrder(@PathVariable int id) {
        Order order = orderService.findOrder(id);
        if (!order.getIsOpen()) {
            order.setIsOpen(Boolean.TRUE);
            orderService.save(order);
        }
        return new GenericResponse(orderMapper.toOrderResponse(order));
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponse saveOrder(@RequestBody @Valid OrderRequest orderRequest) {
        Order order = orderMapper.toOrder(orderRequest);
        Double total = new Double(0);
        List<ProductInOrder> productInOrderList = new ArrayList<>();
        for (int i = 0; i < orderRequest.getProducts().size(); i++) {
            ProductInOrder productInOrder = new ProductInOrder();
            productInOrder.setProduct(productService.findProduct(orderRequest.getProducts().get(i).getProductId()));
            productInOrder.setQuantity(orderRequest.getProducts().get(i).getQuantity());
            productInOrder.setAmount(productInOrder.getProduct().getPrice());
            productInOrderList.add(productInOrder);
            total += (productInOrder.getAmount() * productInOrder.getQuantity());
        }
        order.setProducts(productInOrderList);
        order.setTotalAmount(total.doubleValue());
        order.setDateCreated(new Date());
        orderService.save(order);
        return new GenericResponse(orderMapper.toOrderResponse(order));
    }

    @PutMapping("/orders/{id}")
    public GenericResponse updateOrder(@PathVariable int id, @RequestBody @Valid OrderRequest orderRequest) {
        Order order = orderService.findOrder(id);
        List<ProductInOrder> productInOrderList = new ArrayList<>();
        if (orderRequest.getProducts() != null &&  orderRequest.getProducts().size() > 1){
            BigDecimal total = BigDecimal.valueOf(0);
            for (int i = 0; i < orderRequest.getProducts().size(); i++) {
                ProductInOrder productInOrder = new ProductInOrder();
                productInOrder.setProduct(productService.findProduct(orderRequest.getProducts().get(i).getProductId()));
                productInOrder.setQuantity(orderRequest.getProducts().get(i).getQuantity());
                productInOrder.setAmount(productInOrder.getProduct().getPrice());
                productInOrderList.add(productInOrder);
                total.add(BigDecimal.valueOf(productInOrder.getAmount()));
            }
            order.setTotalAmount(total.doubleValue());
            order.setProducts(productInOrderList);
        }

        order.setName(orderRequest.getName());
        order.setDescription(orderRequest.getDescription());
        order.setIsOpen(orderRequest.getIsOpen());
        orderService.save(order);
        return new GenericResponse(orderMapper.toOrderResponse(order));
    }
}
