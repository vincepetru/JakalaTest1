package com.Jakala.Test.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Contract 
{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	private String contracttype;
	private Date startdate,enddate;
	
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
	public Date getStartDate() {
		return startdate;
	}
	public void setStartDate(Date startDate) {
		this.startdate = startDate;
	}
	public Date getEndDate() {
		return enddate;
	}
	public void setEndDate(Date endDate) {
		this.enddate = endDate;
	}
	
	public String getContractType() {
		return contracttype;
	}
	public void setContractType(String contractType) {
		this.contracttype = contractType;
	}
	public boolean isValid() 
    {
        return
        contracttype!=null              &&
        !contracttype.isBlank()         &&
        startdate!=null          		&&
        enddate!=null                   &&
        startdate.before(enddate)		;
    }
}
