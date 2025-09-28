package customerapp.java.exception;

public class InValidDetailsException extends RuntimeException {
	
	
	public InValidDetailsException() {
		super("Invalid Details Entered");
	}
	
	public InValidDetailsException(String message) {
		super(message);
	}

}
