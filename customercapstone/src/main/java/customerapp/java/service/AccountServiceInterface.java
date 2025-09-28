package customerapp.java.service;

import java.util.List;

import org.springframework.stereotype.Service;

import customerapp.java.model.Account;



@Service
public interface AccountServiceInterface {

	Account createAccount(long customerId,Account account);
	Account updateBalance(long accountNumber, double balance);
	List<Account> getAllAccounts();
	Account getAccountByNo(long accountNumber);
	long deleteAccountByNo(long accountNumber);
}
