package customerapp.java.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import customerapp.java.model.Account;
import customerapp.java.model.Customer;
import customerapp.java.service.CustomerServiceInterface;

 

public class ValidateAccount {
	@Autowired
	CustomerServiceInterface customerServiceInterface;
	
	boolean validateAccount = false;
	
	public boolean validateAccount(Account account,Customer customer) {
		
		String accountType = account.getAccountType();
		String[] types = {"Savings", "Current", "Joint", "savings", "current", "joint"};
		List<String> accountTypes = Arrays.asList(types);
		if(!accountTypes.contains(account.getAccountType())) {
			throw new NoDataException("Invalid Account Type, Please enter among these three (savings, current, joint)");
		}
		
		List<Account> accountList = customer.getCustomerAccounts();
		List<String> accountTypeOnly = new ArrayList<>(); 
		if(accountList.size() !=0) {
			for(Account a:accountList ) {
				accountTypeOnly.add(a.getAccountType());
			}
		}
		if(accountTypeOnly.contains(accountType)) {
			throw new DataAlreadyExistsException("Account of type "+accountType+" is already Exists for customer with ID "+ customer.getCustomerId());
		}
		
		
		return true;
	}

}
