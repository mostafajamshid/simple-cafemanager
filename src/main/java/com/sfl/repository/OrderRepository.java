package com.sfl.repository;

/**
 * Created by Mostafa Jamshid on 27.05.2021.
 */
import com.sfl.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
