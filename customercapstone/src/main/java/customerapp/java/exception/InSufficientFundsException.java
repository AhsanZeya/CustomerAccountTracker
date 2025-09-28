package customerapp.java.exception;

public class InSufficientFundsException extends RuntimeException {
	
	public InSufficientFundsException() {
		super("InSufficinet Funds in your Account");
	}
	
	public InSufficientFundsException(String message) {
		super(message);
	}

}
