package customerapp.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import customerapp.java.exception.CustomError;
import customerapp.java.model.Transaction;
import customerapp.java.service.TransactionServiceInterface;



@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
	@Autowired
	TransactionServiceInterface transactionServiceInterface;
	
	@PostMapping("/transferAmount")
	public ResponseEntity<?> transferAmount(@RequestBody Transaction transaction) {
		
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("Unable to create..."), HttpStatus.CONFLICT);

		Transaction savedTransaction = transactionServiceInterface.transferAmount(transaction);
		if (savedTransaction != null) {
			response = new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
		}
		return response;
	}
	@GetMapping("/getAllTransactions")
	public ResponseEntity<?> getAllTransactions() {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("No records found"), HttpStatus.NOT_FOUND);

		List<Transaction> foundTransactions = transactionServiceInterface.getAllTransactions();
		if (foundTransactions != null && !foundTransactions.isEmpty()) {
			response = new ResponseEntity<>(foundTransactions, HttpStatus.OK);
		}

		return response;
	}

	@GetMapping("/getCustomerById/{transactionId}")
	public ResponseEntity<?> getCustomerById(@PathVariable long transactionId) {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("For given id Customer not found"),HttpStatus.NOT_FOUND);
		Transaction foundTransaction = transactionServiceInterface.getTransactionById(transactionId);
		if (foundTransaction != null) {
			response = new ResponseEntity<>(foundTransaction, HttpStatus.OK);
		}

		return response;
	}

}
