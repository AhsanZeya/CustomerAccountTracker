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
import customerapp.java.model.Customer;
import customerapp.java.service.AccountServiceInterface;
import customerapp.java.service.CustomerServiceInterface;
 

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	CustomerServiceInterface customerServiceInterface;
	@Autowired
	AccountServiceInterface accountServiceInterface;
	@PostMapping("/saveCustomer")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {

		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("Unable to create..."), HttpStatus.CONFLICT);

		Customer savedCustomer = customerServiceInterface.createCustomer(customer);
		if (savedCustomer != null) {
			response = new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
		}
		//return ResponseEntity.ok(response);
		return response;
	}

//	@PutMapping("/updateName/{customerId}/{accId}")
//	public ResponseEntity<?> updateCustomer(@PathVariable long customerId, @RequestBody String newName,@PathVariable long accId,@RequestBody double balance) {
////		Customer customer = customerServiceInterface.getCustomerById(customerId);
////		customer.setName(newName);
//		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("Unable to update  customer ..."), HttpStatus.CONFLICT);
//	
//		
//		Customer savedCustomer = customerServiceInterface.updateCustomer(customerId, newName);
//		Account savedAccount = accountServiceInterface.updateBalance(accId,balance);
//		if (savedCustomer != null && savedAccount != null) {
//			response = (new ResponseEntity<>(savedCustomer ,HttpStatus.CREATED), new ResponseEntity<>(savedAccount, HttpStatus.CREATED));
//		}
////		if (savedAccount != null) {
////			response = new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
////		}
//		return response;
//	
//	}
	@PutMapping("/updateCustomer")
	public ResponseEntity<?> updateCustomer( @RequestBody Customer customer) {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("Unable to update  customer ..."), HttpStatus.CONFLICT);
		Customer savedCustomer = customerServiceInterface.updateCustomer( customer);
		if (savedCustomer != null) {
			response = (new ResponseEntity<>(savedCustomer ,HttpStatus.CREATED));
		}
		 return response; 
	}
	@GetMapping("/getAllCustomers")
	public ResponseEntity<?> getAllCustomers() {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("No records found"), HttpStatus.NOT_FOUND);

		List<Customer> foundCustoemers = customerServiceInterface.getAllCustomers();
		if (foundCustoemers != null && !foundCustoemers.isEmpty()) {
			response = new ResponseEntity<>(foundCustoemers, HttpStatus.OK);
		}

		return response;
	}
	@GetMapping("/getCustomerById/{customerId}")
	public ResponseEntity<?> getCustomerById(@PathVariable long customerId) {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("For given id Customer not found"),HttpStatus.NOT_FOUND);
		Customer foundCustomer = customerServiceInterface.getCustomerById(customerId);
		if (foundCustomer != null) {
			response = new ResponseEntity<>(foundCustomer, HttpStatus.OK);
		}

		return response;
	}
	@DeleteMapping("/deleteCustomerById/{customerId}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable long customerId) {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("For given id Customer not found"),HttpStatus.NOT_FOUND);

		customerServiceInterface.deleteCustomerById(customerId);
	        return new ResponseEntity<>("Employee with ID :" + customerId + " deleted successfully", HttpStatus.OK);
//		Map<String,Object> deleteStatus = new HashMap<>();
//		deleteStatus.put("timestamp",LocalDateTime.now());
//		deleteStatus.put("message","Customer Deleted with ID: "+deletedCustId);
//		if (deletedCustId>1000) {
//			response = new ResponseEntity<>(deleteStatus, HttpStatus.OK);
//		}
//
//		return ResponseEntity.ok(response);
	}
}
