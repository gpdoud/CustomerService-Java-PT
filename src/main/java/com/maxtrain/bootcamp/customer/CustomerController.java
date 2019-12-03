package com.maxtrain.bootcamp.customer;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerRepository custRepo;
	
	@GetMapping()
	public Iterable<Customer> GetAllCustomers() {
		logger.info("Entering GetAllCustomers()");
		return custRepo.findAll();
	}
	@GetMapping("/{id}")
	public Customer GetCustomer(@PathVariable Integer id) {
		logger.info("Entering GetCustomers({})", id);
		if(id == null) return null;
		try {
			Optional<Customer> custOpt =  custRepo.findById(id);
			return custOpt.isPresent() ? custOpt.get() : null;
		} catch (IllegalArgumentException ex) {
			logger.error("EXCEPTION: "+ex.getMessage()+" in CustomerController.GetCustomer()");
			return null;
		}
	}
	@PostMapping()
	public Customer InsertCustomer(@RequestBody Customer cust) {
		logger.info("Entering InsertCustomer()");
		if(cust == null) return null;
		try {
			return custRepo.save(cust);
		} catch (IllegalArgumentException ex) {
			logger.error("EXCEPTION: "+ex.getMessage()+" in CustomerController.InsertCustomer()");
			return null;
		}
	}
	@PutMapping()
	public Customer UpdateCustomer(@RequestBody Customer cust) {
		logger.info("Entering UpdateCustomer()");
		if(cust == null) return null;
		try {
			return custRepo.save(cust);
		} catch (IllegalArgumentException ex) {
			logger.error("EXCEPTION: "+ex.getMessage()+" in CustomerController.UpdateCustomer()");
			return null;
		}	}
	@DeleteMapping()
	public void DeleteCustomer(@RequestBody Customer cust) {
		logger.info("Entering DeleteCustomer()");
		if(cust == null) return;
		try {
			custRepo.deleteById(cust.getId());
		} catch (IllegalArgumentException ex) {
			logger.error("EXCEPTION: "+ex.getMessage()+" in CustomerController.GetCustomer()");
		}	
	}
}
