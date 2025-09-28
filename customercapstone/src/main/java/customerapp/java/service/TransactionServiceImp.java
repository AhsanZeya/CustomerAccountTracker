package customerapp.java.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import customerapp.java.exception.DataNotFoundException;
import customerapp.java.exception.InSufficientFundsException;
import customerapp.java.exception.NoDataException;
import customerapp.java.model.Account;
import customerapp.java.model.Transaction;
import customerapp.java.repository.AccountRepository;
import customerapp.java.repository.TransactionRepository;

@Service
public class TransactionServiceImp implements TransactionServiceInterface{

	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	AccountServiceInterface accountServiceInterface;
	
	@Autowired
	TransactionRepository transactionRepo;
	@Override
	public Transaction transferAmount(Transaction transaction) {
		Optional<Account> fromAccountOptional = accountRepo.findById(transaction.getFromAccountNo());
		Optional<Account> toAccountOptional = accountRepo.findById(transaction.getToAccountNo());
		
		if(!fromAccountOptional.isPresent()) {
			throw new DataNotFoundException("From Account ID does not exists!!!");
		}
		if(!toAccountOptional.isPresent()) {
			throw new DataNotFoundException("To Account ID does not exists!!!");
		}
		Account fromAccount = fromAccountOptional.get();
		Account toAccount = toAccountOptional.get();
		
		double amountToBeTransfered = transaction.getAmountTransfered();
		if(fromAccount.getBalance() < amountToBeTransfered) {
			throw new InSufficientFundsException();
		}
		
		//fromAccount.setAccountBalance(fromAccount.getAccountBalance()-amountToBeTransfered);
		//toAccount.setAccountBalance(toAccount.getAccountBalance()+amountToBeTransfered);
		fromAccount = accountServiceInterface.updateBalance(fromAccount.getAccountNumber(),fromAccount.getBalance()-amountToBeTransfered);
		toAccount = accountServiceInterface.updateBalance(toAccount.getAccountNumber(),toAccount.getBalance()+amountToBeTransfered);
		transactionRepo.save(transaction);
		return transaction;
	}

	@Override
	public List<Transaction> getAllTransactions() {
		List<Transaction> transactionList = transactionRepo.findAll();
		if(transactionList.isEmpty()) {
			throw new NoDataException();
		}
		return transactionList;
	}

	@Override
	public Transaction getTransactionById(long transactionId) {
		Optional<Transaction>  transaction = transactionRepo.findById(transactionId);
		if(!transaction.isPresent()) {
			throw new DataNotFoundException("Entered transaction Id Not Found");
		}
		return transaction.get();
	}

}
