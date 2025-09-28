package customerapp.java.exception;

public class CustomError {
	
	String errorMessage;
	
	public CustomError(String message) {
		this.errorMessage=message;		
	}
	
	public String getMessage() {
		return errorMessage;
	}

}
