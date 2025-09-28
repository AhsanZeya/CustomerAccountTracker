package customerapp.java.exception;


import customerapp.java.model.Customer;

public class ValidateCustomer {
	public boolean validateCustomer(Customer customer) {
		String name = customer.getName();
		if(name.length()<3) {
			throw new InValidDetailsException("Name should contain atleast 3 letters");
		}
		if(name.length()>50) {
			throw new InValidDetailsException("Name should not contain more than 50 letters");
		}
		String nameRegex = "^[a-zA-Z\\s]*$";
		if(!name.matches(nameRegex)) {
			throw new InValidDetailsException("Name should not contain numbers or special characters");
		}
          return true;	
	}
	

}
