package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "A_TEST3")
public class Test3Entity {

	public String id;

	@Id
	@Column(name="ID",length=100)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
