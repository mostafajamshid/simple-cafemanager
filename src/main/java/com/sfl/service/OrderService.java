package com.sfl.service;

import com.sfl.model.Order;
import com.sfl.model.Product;
import com.sfl.repository.OrderRepository;
import com.sfl.repository.ProductRepository;
import com.sfl.rest.payload.request.OrderCriteria;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;


    public OrderService(@Qualifier("orderRepository") OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        orders = orderRepository.findAll();
        return orders;
    }

    public List<Order> findBySearchCriteria(OrderCriteria searchCriteria) {
        List<Order> orders = new ArrayList<>();

        if (searchCriteria.getName() == null
                && searchCriteria.getDescription() == null
                && searchCriteria.getTotalAmount() == null)
            return findAll();

        System.out.println("search Criteria" + searchCriteria);
        if (searchCriteria.getName() != null && searchCriteria.getDescription() != null && searchCriteria.getTotalAmount() != null && searchCriteria.getIsOpen() != null) {
            orders = orderRepository.findByNameContainingAndDescriptionContainingAndTotalAmountEqualsAndIsOpenIs(searchCriteria.getName(), searchCriteria.getDescription(), searchCriteria.getTotalAmount(), searchCriteria.getIsOpen());
        } else if (searchCriteria.getDescription() != null && searchCriteria.getTotalAmount() != null && searchCriteria.getIsOpen() != null) {
            orders = orderRepository.findByDescriptionContainingAndTotalAmountEqualsAndIsOpenIs(searchCriteria.getDescription(), searchCriteria.getTotalAmount(), searchCriteria.getIsOpen());
        } else if (searchCriteria.getName() != null && searchCriteria.getTotalAmount() != null && searchCriteria.getIsOpen() != null) {
            orders = orderRepository.findByNameContainingAndTotalAmountEqualsAndIsOpenIs(searchCriteria.getName(), searchCriteria.getTotalAmount(), searchCriteria.getIsOpen());
        } else if (searchCriteria.getTotalAmount() != null && searchCriteria.getIsOpen() != null) {
            orders = orderRepository.findByTotalAmountEqualsAndIsOpenIs(searchCriteria.getTotalAmount(), searchCriteria.getIsOpen());
        } else if (searchCriteria.getName() != null && searchCriteria.getDescription() != null && searchCriteria.getIsOpen() != null) {
            orders = orderRepository.findByNameContainingAndDescriptionContainingAndIsOpenIs(searchCriteria.getName(), searchCriteria.getDescription(), searchCriteria.getIsOpen());
        } else if (searchCriteria.getDescription() != null && searchCriteria.getIsOpen() != null) {
            orders = orderRepository.findByDescriptionContainingAndIsOpenIs(searchCriteria.getDescription(), searchCriteria.getIsOpen());
        } else if (searchCriteria.getName() != null && searchCriteria.getDescription() != null && searchCriteria.getTotalAmount() != null) {
            orders = orderRepository.findByNameContainingAndDescriptionContainingAndTotalAmountEquals(searchCriteria.getName(), searchCriteria.getDescription(), searchCriteria.getTotalAmount());
        } else if (searchCriteria.getName() != null && !searchCriteria.getName().isEmpty()) {
            orders = orderRepository.findByNameContainingIgnoreCase(searchCriteria.getName());
        } else if (searchCriteria.getDescription() != null) {
            orders = orderRepository.findByDescriptionContainingIgnoreCase(searchCriteria.getDescription());
        } else if (searchCriteria.getTotalAmount() != null) {
            orders = orderRepository.findByTotalAmountEquals(searchCriteria.getTotalAmount());
        } else if (searchCriteria.getIsOpen() != null) {
            orders = orderRepository.findByIsOpenIs(searchCriteria.getIsOpen());
        } else if (searchCriteria.getName() != null || searchCriteria.getDescription() != null || searchCriteria.getTotalAmount() != null || searchCriteria.getIsOpen() != null) {
            orders = orderRepository.findByNameContainingOrDescriptionContainingOrTotalAmountEqualsOrIsOpenIs(searchCriteria.getName(), searchCriteria.getDescription(), searchCriteria.getTotalAmount(), searchCriteria.getIsOpen());
        }
        return orders;
    }


    public Order findOrder(int id) {
        return orderRepository.getById(id);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void delete(int id) {
        orderRepository.deleteById(id);

    }
}
