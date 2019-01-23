package com.pk.customerdata.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pk.customerdata.bean.Customer;
import com.pk.customerdata.bean.CustomerCriteria;
import com.pk.customerdata.bean.CustomerDataResponse;
import com.pk.customerdata.repository.CustomerDataRepository;

@RestController()
@RequestMapping("rest/cust")
public class CustomerDataController {

	@Autowired
	CustomerDataRepository customerDataRepository;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/findAllCustomers", method = RequestMethod.POST)
	public CustomerDataResponse findAllCustomers(@RequestBody CustomerCriteria criteria) {
		PageRequest pageRequest = PageRequest.of(criteria.getCurrentPage()-1, criteria.getPageSize(),
				Direction.valueOf(criteria.getSortType()), criteria.getSortBy());
		Page<Customer> page = customerDataRepository.findAll(pageRequest);
		CustomerDataResponse response = new CustomerDataResponse();
		criteria.setTotalRows(page.getTotalElements());
		criteria.setTotalPages(page.getTotalPages());
		response.setCustomerCriteria(criteria);
		response.setCustomers(page.getContent());
		return response;
	}

	@RequestMapping(value = "/findCustomerByName", method = RequestMethod.POST)
	public CustomerDataResponse findCustomerByName(@RequestBody CustomerCriteria criteria) {
		PageRequest pageRequest = PageRequest.of(criteria.getCurrentPage()-1, criteria.getPageSize(),
				Direction.valueOf(criteria.getSortType()), criteria.getSortBy());
		Page<Customer> page = customerDataRepository.findByNameContaining(criteria.getSearchValue(), pageRequest);
		CustomerDataResponse response = new CustomerDataResponse();
		criteria.setTotalRows(page.getTotalElements());
		criteria.setTotalPages(page.getTotalPages());
		response.setCustomerCriteria(criteria);
		response.setCustomers(page.getContent());
		return response;
	}

	@RequestMapping(value = "/UpdateCustomer", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, String>> UpdateCustomer(@RequestBody Customer customer) {
		customerDataRepository.save(customer);
		return buildResponse("Updated updated", HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, String>> deleteCustomer(@RequestParam("id") long id) throws Exception {
		customerDataRepository.deleteById(id);
		return buildResponse("Customer deleted", HttpStatus.OK);
	}

	public ResponseEntity<Map<String, String>> buildResponse(String message, HttpStatus status) {
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("message", message);
		ResponseEntity<Map<String, String>> response = new ResponseEntity<>(responseMap, status);
		return response;
	}
}
