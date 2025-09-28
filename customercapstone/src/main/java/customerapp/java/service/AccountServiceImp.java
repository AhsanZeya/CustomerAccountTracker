package customerapp.java.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import customerapp.java.exception.DataNotFoundException;
import customerapp.java.exception.InValidDetailsException;
import customerapp.java.exception.NoDataException;
import customerapp.java.exception.ValidateAccount;
import customerapp.java.model.Account;
import customerapp.java.model.Customer;
import customerapp.java.repository.AccountRepository;
import customerapp.java.repository.CustomerRepository;

@Service
public class AccountServiceImp implements AccountServiceInterface{

	
	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	CustomerServiceInterface customerServiceInterface;
	
	@Override
	public Account createAccount(long customerId,Account account) {
		Customer customer = customerServiceInterface.getCustomerById(customerId);
		Account saveacc = new Account();
		if(  customer == null) {
			throw new DataNotFoundException("Entered Customer Id Not Found");
			
		}
	 
		ValidateAccount va = new ValidateAccount();
		boolean validAccount = va.validateAccount(account,customer);
		if(validAccount) {
			account.setCustomer(customer);
			saveacc=accountRepo.save(account);
		}else {
			throw new InValidDetailsException("Invalid Details of Customer");
		}
		return saveacc;
		 
	}

	@Override
	public Account updateBalance(long accountNumber, double balance) {
		accountRepo.updateBalance(accountNumber,balance);
		Optional<Account>  account = accountRepo.findById(accountNumber);
		if(!account.isPresent()) {
			throw new DataNotFoundException("Entered Account Number Not Found");
		}
		return account.get();
	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account> accList = accountRepo.findAll();
		if(accList.isEmpty()) {
			throw new NoDataException();
		}
		return accList;
	}

	@Override
	public Account getAccountByNo(long accountNumber) {
		Optional<Account>  account = accountRepo.findById(accountNumber);
		if(!account.isPresent()) {
			throw new DataNotFoundException("Entered Account Number Not Found");
		}
		return account.get();
	}

	@Override
	public long deleteAccountByNo(long accountNumber) {
		Optional<Account>  account = accountRepo.findById(accountNumber);
		if(!account.isPresent()) {
			throw new DataNotFoundException("Entered Account Number Not Found");
		}
		accountRepo.deleteById(accountNumber);
		return accountNumber;
	}

}
