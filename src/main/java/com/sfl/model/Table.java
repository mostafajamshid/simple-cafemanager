package com.sfl.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="cafe_table")
public class Table implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	private boolean closed;
	
	@OneToMany(mappedBy = "table")
	private Set<WaiterTable> waiterTable = new HashSet<WaiterTable>();
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}



	public Set<WaiterTable> getUserTable() {
		return waiterTable;
	}

	public void setUserTable(Set<WaiterTable> waiterTable) {
		this.waiterTable = waiterTable;
	}
	
}
