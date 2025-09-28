package customer.repositorys;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import customerapp.java.model.Customer;
import customerapp.java.repository.CustomerRepository;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class RepositoryTest {
	
	@Autowired
	CustomerRepository custRepo;
	
	 @Test
	    @Order(1)
	    @Rollback(value = false)
	    public void emptyRecordsTest() {
	        List<Customer> custs = custRepo.findAll();
	        assertFalse(custs.isEmpty());
	      }
	   @Test
	    @Order(2)
	    @Rollback(value = false)
	    public void saveCustomerAccountTest() {
	    	Customer  newCustomer= new Customer();
	    	newCustomer.setName("Gova");
			
			Customer savedCustomer = custRepo.save(newCustomer);
	    	assertThat(savedCustomer.getCustomerId()).isGreaterThan(0);
	    }
	   @Test
	    @Order(3)
	    @Rollback(value = false) 
	    public void getListOfCustTest() {
	    	List<Customer> customers = custRepo.findAll();
	    	assertThat(customers.size()).isGreaterThan(0);
	    }

	   @Test
	    @Order(4)
	    @Rollback(value = false) 
	    public void getCustomerTest() {
	    	Customer customer = custRepo.findById(1007L).get();
	    	assertThat(customer.getCustomerId()).isEqualTo(1007L);
	    }
	   @Test
	    @Order(5)
	    @Rollback(value = false) 
	    public void updateCustTest() {
	    	Customer cust = custRepo.findById((1007L)).get();
	    	cust.setName("Gova");
	    	Customer updcust = custRepo.save(cust);
	    	assertThat(updcust.getName()).isEqualTo("Gova");
	    }
	   @Test
	    @Order(6)
	    @Rollback(value = false) 
	    public void delCustByIdTest() {
	       Customer dcust = custRepo.findByName("Gova");
	    	custRepo.deleteById(dcust.getCustomerId());
	    	Customer dcust1 = custRepo.findByName("Gova");
	    	assertThat(dcust1).isNull();
	    }
}
