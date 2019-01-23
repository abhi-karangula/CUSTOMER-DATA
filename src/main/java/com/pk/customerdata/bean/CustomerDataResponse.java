package com.pk.customerdata.bean;

import java.io.Serializable;
import java.util.List;

public class CustomerDataResponse implements Serializable {

	private static final long serialVersionUID = 2L;

	private CustomerCriteria customerCriteria;
	private List<Customer> customers;

	public CustomerCriteria getCustomerCriteria() {
		return customerCriteria;
	}

	public void setCustomerCriteria(CustomerCriteria customerCriteria) {
		this.customerCriteria = customerCriteria;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

}
