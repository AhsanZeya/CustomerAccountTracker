package customerapp.java.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import customerapp.java.exception.DataNotFoundException;
import customerapp.java.exception.InValidDetailsException;
import customerapp.java.exception.NoDataException;
import customerapp.java.exception.ValidateCustomer;
import customerapp.java.model.Customer;
import customerapp.java.repository.CustomerRepository;


@Service
public class CustomerServiceImp implements CustomerServiceInterface{

	@Autowired
	CustomerRepository custRepo;
	@Override
	public Customer createCustomer(Customer customer) {
		int accSize = customer.getCustomerAccounts().size();
		Customer savecus = new Customer();
		for(int i=0;i<accSize;i++) {
			
			customer.getCustomerAccounts().get(i).setCustomer(customer);
		}
		boolean validCustomer = new ValidateCustomer().validateCustomer(customer);
		if(validCustomer) {
			savecus=custRepo.save(customer);
		}else {
			throw new InValidDetailsException("Invalid Details of Customer");
		}
		return savecus;
	}

	public Customer updateCustomer(Customer customer) {
		int accSize = customer.getCustomerAccounts().size();
		Customer savecus = new Customer();
		for(int i=0;i<accSize;i++) {
			
			customer.getCustomerAccounts().get(i).setCustomer(customer);
		}
		boolean validCustomer = new ValidateCustomer().validateCustomer(customer);
		if(validCustomer) {
			savecus=custRepo.save(customer);
		}else {
			throw new InValidDetailsException("Invalid Details of Customer");
		}
		return savecus;
		//return custRepo.save(customer);
//		Optional<Customer>  existCustomerOpt = custRepo.findById(customerId);
//		if(!existCustomerOpt.isPresent()) {
//			throw new DataNotFoundException("Entered customer Id Not Found");
//		}
//		Customer existCustomer = existCustomerOpt.get();
//		existCustomer.setName(customer.getName());
//		existCustomer.setCustomerAccounts(customer.getCustomerAccounts());
//        for(int i=0;i<existCustomer.getCustomerAccounts().size();i++) {
//			
//			existCustomer.getCustomerAccounts().get(i).setCustomer(existCustomer);
//		}
//		Customer updatedCustomer = new Customer();
//		boolean validCustomer = new ValidateCustomer().validateCustomer(customer);
//		if(validCustomer) {
//			updatedCustomer=custRepo.save(existCustomer);
//		}else {
//			throw new InValidDetailsException("Invalid Details of Customer");
//		}
//		return updatedCustomer;
//		 
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> custList = custRepo.findAll();
		if(custList.isEmpty()) {
			throw new NoDataException();
		}
		return custList;
	}

	@Override
	public Customer getCustomerById(long customerId) {
		Optional<Customer>  customer = custRepo.findById(customerId);
		if(!customer.isPresent()) {
			throw new DataNotFoundException("Entered customer Id Not Found");
		}
		return customer.get();
	}

	@Override
	public long deleteCustomerById(long customerId) {
		Optional<Customer>  customer = custRepo.findById(customerId);
		if(!customer.isPresent()) {
			throw new DataNotFoundException("Entered customer Id Not Found");
		}
		long id = customer.get().getCustomerId();
		custRepo.deleteById(id);
		return id;
	}
	

}
