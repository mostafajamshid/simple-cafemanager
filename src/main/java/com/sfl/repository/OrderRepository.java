package com.sfl.repository;

/**
 * Created by Mostafa Jamshid on 27.05.2021.
 */

import com.sfl.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findById(int id);
    List<Order> findByNameContainingIgnoreCase(String name);

    List<Order> findByDescriptionContainingIgnoreCase(String description);

    List<Order> findByTotalAmountEquals(Double totalAmount);

    List<Order> findByIsOpenIs(Boolean isOpen);

    List<Order> findByNameContainingOrDescriptionContainingOrTotalAmountEqualsOrIsOpenIs(String name, String description, Double totalAmount, Boolean isOpen);

    List<Order> findByNameContainingAndDescriptionContainingAndTotalAmountEquals(String name, String description, Double totalAmount);

    List<Order> findByNameContainingAndDescriptionContainingAndTotalAmountEqualsAndIsOpenIs(String name, String description, Double totalAmount, Boolean isOpen);

    List<Order> findByNameContainingAndDescriptionContainingAndIsOpenIs(String name, String description, Boolean isOpen);

    List<Order> findByNameContainingAndTotalAmountEqualsAndIsOpenIs(String name, Double totalAmount, Boolean isOpen);

    List<Order> findByDescriptionContainingAndIsOpenIs(String description, Boolean isOpen);

    List<Order> findByTotalAmountEqualsAndIsOpenIs(Double totalAmount, Boolean isOpen);

    List<Order> findByDescriptionContainingAndTotalAmountEqualsAndIsOpenIs(String description, Double totalAmount, Boolean isOpen);
}
