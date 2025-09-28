package customerapp.java.service;

import java.util.List;

import org.springframework.stereotype.Service;

import customerapp.java.model.Customer;

@Service
public interface CustomerServiceInterface {
	Customer createCustomer(Customer customer);
	Customer updateCustomer(Customer customer);
	List<Customer> getAllCustomers();
	Customer getCustomerById(long customerId);
	long deleteCustomerById(long customerId);

}
