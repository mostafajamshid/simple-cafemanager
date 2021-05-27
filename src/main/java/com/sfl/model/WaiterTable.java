package com.sfl.model;

/**
 * Created by Mostafa Jamshid on 27.05.2021.
 */
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="waiter_table")
public class WaiterTable implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "CAFE_TABLE_ID", referencedColumnName = "id")
    private Table table;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}


    
}
