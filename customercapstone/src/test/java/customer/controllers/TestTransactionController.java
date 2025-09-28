package customer.controllers;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import customerapp.java.controller.TransactionController;
import customerapp.java.model.Transaction;
import customerapp.java.service.TransactionServiceInterface;

@WebMvcTest(TransactionController.class)
public class TestTransactionController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	TransactionServiceInterface tservice;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void transferAmountTest() throws Exception {
		Transaction fund = getTransaction2();

		Mockito.when(tservice.transferAmount(fund)).thenReturn(fund);
		String json = mapper.writeValueAsString(fund);
		tservice.transferAmount(fund);
		Mockito.verify(tservice, Mockito.times(1)).transferAmount(fund);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/transaction/transferAmount")
				.accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

	@Test
	public void getAllTransactionsTest() throws Exception {
		List<Transaction> transactions = getAllTrans();

		Mockito.when(tservice.getAllTransactions()).thenReturn(transactions);
		String json = mapper.writeValueAsString(transactions);
		tservice.getAllTransactions();
		Mockito.verify(tservice, Mockito.times(1)).getAllTransactions();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/transaction/getAllTransactions")
				.accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void getTransactionByIdTest() throws Exception {
		Transaction transaction = getTransaction2();
		Mockito.when(tservice.getTransactionById(transaction.getTrasactionId())).thenReturn(transaction);
		String json = mapper.writeValueAsString(transaction);
		tservice.getTransactionById(transaction.getTrasactionId());
		Mockito.verify(tservice, Mockito.times(1)).getTransactionById(transaction.getTrasactionId());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/transaction/getCustomerById/7006")
				.accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	public Transaction getTransaction1() {
		Transaction transaction = new Transaction();
		transaction.setTrasactionId(7005);
		transaction.setFromAccountNo(1000008);
		transaction.setToAccountNo(1000007);
		transaction.setTransactionDateTime(LocalDateTime.now());
		transaction.setAmountTransfered(7000);
		return transaction;

	}

	public Transaction getTransaction2() {
		Transaction transaction = new Transaction();
		transaction.setTrasactionId(7006);
		transaction.setFromAccountNo(1000009);
		transaction.setToAccountNo(1000007);
		transaction.setTransactionDateTime(LocalDateTime.now());
		transaction.setAmountTransfered(8000);
		return transaction;

	}

	public List<Transaction> getAllTrans() {
		List<Transaction> transaction = new ArrayList<>();
		transaction.add(getTransaction1());
		transaction.add(getTransaction2());
		return transaction;

	}

}
