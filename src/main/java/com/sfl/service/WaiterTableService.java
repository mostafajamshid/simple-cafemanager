package com.sfl.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.sfl.model.Table;
import com.sfl.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sfl.repository.UserTableRepository;
import com.sfl.model.WaiterTable;

@Service
@Transactional
public class WaiterTableService {

	private final UserTableRepository userTableRepository;

	public WaiterTableService(@Qualifier("userTableRepository") UserTableRepository userTableRepository) {
		this.userTableRepository = userTableRepository;
	}
	
	public List<WaiterTable> findAll(){
		List<WaiterTable> waiterTables = new ArrayList<>();
		waiterTables = userTableRepository.findAll();
		return waiterTables;
	}
	
	public WaiterTable findUserTable(int id){
		return userTableRepository.getById(id);
	}
	
	public void save(WaiterTable waiterTable){
		userTableRepository.save(waiterTable);
	}
	
	public void delete(int id){
		userTableRepository.deleteById(id);

	}

	public List<WaiterTable> findByTable(Table table) {
		return userTableRepository.findByTable(table);
	}
	public List<WaiterTable> findByUser(User user) {
		return userTableRepository.findByUser(user);
	}
}
