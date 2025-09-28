package customer.controllers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import customerapp.java.controller.CustomerController;
import customerapp.java.model.Account;
import customerapp.java.model.Customer;
import customerapp.java.service.AccountServiceInterface;
import customerapp.java.service.CustomerServiceInterface;

@AutoConfigureMockMvc
@WebAppConfiguration
@WebMvcTest(CustomerController.class)
public class ControllersTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CustomerServiceInterface service;

	@MockBean
	AccountServiceInterface s;
	
	@Autowired
	private ObjectMapper mapper;

//	@Test
//	public void findAllCustomersTest() throws Exception {
//	 List<Customer> customers = getCustomers();
//	 when(service.getAllCustomers()).thenReturn(customers);
//
//	 Mockito.when(service.getAllCustomers()).thenReturn(customers);
//    String json = objectMapper.writeValueAsString(customers);
//   mockMvc.perform(get("/api/customers/getAllCustomers").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
//           .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//            .andDo(print());
//			       
//		  }

	@Test
	public void testPost() throws Exception {
		Account acc = new Account();
		acc.setAccountNumber(1000009);
		acc.setAccountType("savings");
		acc.setBalance(7654321);
		Customer customer = new Customer();
		customer.setCustomerId(1009);
		customer.setName("Naruto");
		List<Account> accs = List.of(acc);
		customer.setCustomerAccounts(accs);
		acc.setCustomer(customer);
		Mockito.when(service.createCustomer(customer)).thenReturn(customer);
		String json = mapper.writeValueAsString(customer);
		mockMvc.perform(post("/api/customers/saveCustomer").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andDo(print());
	}

	@Test
	public void testGet() throws Exception {
		Customer customer = getCustomer1();
		Mockito.when(service.getCustomerById(customer.getCustomerId())).thenReturn(customer);
		String json = mapper.writeValueAsString(customer);
		mockMvc.perform(get("/api/customers/getCustomerById/1007").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void testGetAll() throws Exception {
		List<Customer> customers = getCustomers();
		Mockito.when(service.getAllCustomers()).thenReturn(customers);
		String json = mapper.writeValueAsString(customers);
		mockMvc.perform(get("/api/customers/getAllCustomers").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print());
	}

	//@Test
//	public void testPut() throws Exception {
//		Customer customer = getCustomer2();
//		String json = mapper.writeValueAsString(customer);
//		Mockito.when(service.updateCustomer(customer.getCustomerId(), customer.getName())).thenReturn(customer);
//		service.updateCustomer(customer.getCustomerId(), customer.getName());
//		Mockito.verify(service, Mockito.times(1)).updateCustomer(customer.getCustomerId(), customer.getName());
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/customers/updateName/1008")
//				.accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON);
//
//		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
//		MockHttpServletResponse response = mvcResult.getResponse();
//		assertEquals(HttpStatus.OK.value(), response.getStatus());

//		Mockito.when(service.updateCustomer(customer.getCustId(),customer.getName())).thenReturn(customer);
//		String json = mapper.writeValueAsString(customer);
//		mockMvc.perform(put("/api/customers/updateName/1008").contentType(MediaType.APPLICATION_JSON)
//				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//				.andDo(print());
	//}

	@Test
	public void testDelete() throws Exception {
		  mockMvc.perform( MockMvcRequestBuilders.delete("/api/customers/deleteCustomerById/{customerId}",1002) )     
		  .andExpect(status().isOk());
		  
		//Customer customer = getCustomer2();
//		Mockito.when(service.deleteCustomerById(1002)).thenReturn(1002L);
//		String json = mapper.writeValueAsString(customer);
//		mockMvc.perform(get("/api/customers/deleteCustomerById/1002").contentType(MediaType.APPLICATION_JSON)
//				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//				.andDo(print());
//		Customer customer = getCustomer3();
//		Mockito.when(service.getCustomerById(customer.getCustId())).thenReturn(customer);
//		String json = mapper.writeValueAsString(customer);
//		//Mockito.doNothing().when(service).deleteCustomerById(Mockito.any(Long.class));
//		service.deleteCustomerById(customer.getCustId());
//		Mockito.verify(service, Mockito.times(1)).deleteCustomerById(customer.getCustId());
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/customers/deleteCustomerById/1008")
//				.accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON);
//
//		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
//		MockHttpServletResponse response = mvcResult.getResponse();
//		assertEquals(HttpStatus.OK.value(), response.getStatus());
//		Customer customer = getCustomer3();
//		 Mockito.when(service.getCustomerById(customer.getCustId())).thenReturn(Optional.Customer.class);
//		String json = mapper.writeValueAsString(customer);
//		mockMvc.perform(delete("/api/customers/deleteCustomerById/1008").contentType(MediaType.APPLICATION_JSON)
//				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//				.andDo(print());
//		Customer customer = getCustomer3();
//		    doNothing().when(service.deleteCustomerById(customer.getCustId()));
//		    mockMvc.perform(delete("/api/customers/deleteCustomerById/1009")
//		         .andExpect(status().isNoContent())
//		         .andDo(print());

	}

	public Customer getCustomer1() {

		Account acc = new Account();
		Customer cus = new Customer();

		acc.setAccountNumber(1000007);
		acc.setAccountType("savings");
		acc.setBalance(3564823);

		cus.setCustomerId(1007);
		cus.setName("peter");

		List<Account> accs = List.of(acc);
		cus.setCustomerAccounts(accs);
		return cus;
	}

	public Customer getCustomer2() {

		Account acc = new Account();
		Customer cus = new Customer();

		acc.setAccountNumber(1000008);
		acc.setAccountType("current");
		acc.setBalance(1234567);

		cus.setCustomerId(1008);
		cus.setName("dilip");

		List<Account> accs = List.of(acc);
		cus.setCustomerAccounts(accs);
		return cus;
	}

	public Customer getCustomer3() {

		Account acc = new Account();
		Customer cus = new Customer();

		acc.setAccountNumber(1000009);
		acc.setAccountType("joint");
		acc.setBalance(6547654.0);

		cus.setCustomerId(1009);
		cus.setName("ram");

		List<Account> accs = List.of(acc);
		cus.setCustomerAccounts(accs);
		return cus;
	}

	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<>();
		customers.add(getCustomer1());
		customers.add(getCustomer2());
		customers.add(getCustomer3());
		return customers;
	}

}
