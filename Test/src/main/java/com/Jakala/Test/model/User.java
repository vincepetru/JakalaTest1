package com.Jakala.Test.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name,surname,usertype;
	
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String userType) {
		this.usertype = userType;
	}
	public boolean isValid() 
    {
        return
        name!=null		              	&&
        !name.isBlank()         		&&
        surname!=null	          		&&
        !surname.isBlank()         		&&
        usertype!=null                  &&
        !usertype.isBlank()         	;
    }
}
