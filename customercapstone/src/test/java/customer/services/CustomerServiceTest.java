package customer.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import customerapp.java.model.Account;
import customerapp.java.model.Customer;
import customerapp.java.repository.CustomerRepository;
import customerapp.java.service.CustomerServiceImp;
import customerapp.java.service.CustomerServiceInterface;

@SpringBootTest
class CustomerServiceTest {

	
	@InjectMocks
	CustomerServiceImp service;
	
	@Autowired
	CustomerServiceInterface customerServiceInterface;
	
	@Mock
	CustomerRepository custrepo;
	@Test
	void testCreateCustomer() {
		Customer newCustomer = new Customer();
		newCustomer.setCustomerId(1007);
		newCustomer.setName("Itachi");
		 
		List<Account> acc = new ArrayList<Account>();
		newCustomer.setCustomerAccounts(acc);
		Customer savedCustomer = customerServiceInterface.createCustomer(newCustomer);
		assertEquals(newCustomer.getCustomerAccounts(),savedCustomer.getCustomerAccounts());
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void testGetAllCustomers() {
		List<Customer> list = customerServiceInterface.getAllCustomers();
		equals(list.size() > 0);
		
	}

	@Test
	void testGetCustomerById() {
		assertEquals("Itachi",customerServiceInterface.getCustomerById(1001).getName());
	}

	@Test
	void testDeleteCustomerById() {
 
		//service.deleteCustomerById(1001);
		//verify(custrepo,times(1)).deleteById(1001L);
		assertEquals(1003,customerServiceInterface.deleteCustomerById(1003));
	}

}
