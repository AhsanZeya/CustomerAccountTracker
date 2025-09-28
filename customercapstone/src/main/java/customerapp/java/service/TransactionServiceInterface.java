package customerapp.java.service;

import java.util.List;

import org.springframework.stereotype.Service;

import customerapp.java.model.Transaction;


@Service
public interface TransactionServiceInterface {
	
	Transaction transferAmount(Transaction transaction);
	List<Transaction> getAllTransactions();
	Transaction getTransactionById(long transactionId);

}
