package customer.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import customerapp.java.model.Transaction;
import customerapp.java.service.TransactionServiceInterface;



@SpringBootTest
class TransactionServiceTest {

	@Autowired
	TransactionServiceInterface transactionServiceInterface;
	
	@Test
	void testTransferAmount() {
		Transaction transaction = new Transaction();
		transaction.setTrasactionId(7005);
		transaction.setAmountTransfered(7007);
		transaction.setFromAccountNo(1000003);
		transaction.setToAccountNo(1000001);
		Transaction newTransaction = transactionServiceInterface.transferAmount(transaction);
		assertEquals(7007,newTransaction.getAmountTransfered());
	}

	@Test
	void testGetAllTransactions() {
		List<Transaction> list = transactionServiceInterface.getAllTransactions();
		equals(list.size()>0);
	}

	@Test
	void testGetTransactionById() {
		Transaction transaction = transactionServiceInterface.getTransactionById(7001);
		assertEquals(7000,transaction.getAmountTransfered());
	}

}
