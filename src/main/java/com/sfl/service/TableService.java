package com.sfl.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.sfl.model.Table;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sfl.repository.TableRepository;

@Service
@Transactional
public class TableService {

	private final TableRepository tableRepository;
	

	public TableService(@Qualifier("tableRepository") TableRepository tableRepository) {
		this.tableRepository = tableRepository;
	}
	
	public List<Table> findAll(){
		List<Table> tables = new ArrayList<>();
		tables = tableRepository.findAll();
		return tables;
	}
	
	public Table findTable(int id){
		return tableRepository.getById(id);
	}
	
	public void save(Table table){
		tableRepository.save(table);
	}
	
	public void delete(int id){
		tableRepository.deleteById(id);

	}
}
