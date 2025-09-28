package customerapp.java.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import customerapp.java.exception.CustomError;
import customerapp.java.model.Account;
import customerapp.java.service.AccountServiceInterface;



@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	@Autowired
	AccountServiceInterface accountServiceInterface;

	@PostMapping("/createAccount/{customerId}")
	public ResponseEntity<?> createAccount(@PathVariable long customerId,@RequestBody Account account){
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("Unable to create account..."), HttpStatus.CONFLICT);

		
		Account savedAccount = accountServiceInterface.createAccount(customerId,account);
		if (savedAccount != null) {
			response = new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
		}
		return response;
	}
	@PatchMapping("/updateBalance/{accId}")
	public ResponseEntity<?> updateBalance(@PathVariable long accId,@RequestBody double balance) {
		
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("Unable to update Balance ..."), HttpStatus.CONFLICT);

		Account savedAccount = accountServiceInterface.updateBalance(accId,balance);
		if (savedAccount != null) {
			response = new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
		}
		return response;
	}
	@GetMapping("/getAllAccounts")
	public ResponseEntity<?> getAllAccounts(){
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("No records found"), HttpStatus.NOT_FOUND);

		List<Account> foundAccounts = accountServiceInterface.getAllAccounts();
		if (foundAccounts != null && !foundAccounts.isEmpty()) {
			response = new ResponseEntity<>(foundAccounts, HttpStatus.OK);
		}

		return response;
	}
	@GetMapping("/getAccountByNo/{accountNo}")
	public ResponseEntity<?> getAccountByNo(@PathVariable long accountNo) {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("Unable to fetch by account number ..."), HttpStatus.CONFLICT);

		Account savedAccount = accountServiceInterface.getAccountByNo(accountNo);
		if (savedAccount != null) {
			response = new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
		}
		return response;
	}
	@DeleteMapping("/deleteAccountByNo/{accountNo}")
	public ResponseEntity<?> deleteAccountByNo(@PathVariable long accountNo) {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("For given id Customer not found"),HttpStatus.NOT_FOUND);

         accountServiceInterface.deleteAccountByNo(accountNo);
         return new ResponseEntity<>("account  :" + accountNo + " deleted successfully", HttpStatus.OK);
//		Map<String,Object> deleteStatus = new HashMap<>();
//		deleteStatus.put("timestamp",LocalDateTime.now());
//		deleteStatus.put("message","Account Deleted with ID: "+deletedAccountNo);
//		if (deletedAccountNo>1000) {
//			response = new ResponseEntity<>(deleteStatus, HttpStatus.OK);
//		}
//
//		return ResponseEntity.ok(response);
	}
}
