package com.sfl.service;

import com.sfl.model.Order;
import com.sfl.model.Product;
import com.sfl.repository.OrderRepository;
import com.sfl.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {

	private final OrderRepository orderRepository;


	public OrderService(@Qualifier("orderRepository") OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public List<Order> findAll(){
		List<Order> orders = new ArrayList<>();
		orders = orderRepository.findAll();
		return orders;
	}
	
	public Order findOrder(int id){
		return orderRepository.findOne(id);
	}
	
	public void save(Order order){
		orderRepository.save(order);
	}
	
	public void delete(int id){
		orderRepository.delete(id);

	}
}
