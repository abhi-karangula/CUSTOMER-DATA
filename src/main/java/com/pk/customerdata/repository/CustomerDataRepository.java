package com.pk.customerdata.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pk.customerdata.bean.Customer;

@Repository
public interface CustomerDataRepository extends JpaRepository<Customer, Long> {
		
	public Page<Customer> findByNameContaining(String name, Pageable pageable);
}
