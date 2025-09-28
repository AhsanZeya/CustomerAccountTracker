package customerapp.java.exception;

public class DataNotFoundException extends RuntimeException{
	
	public DataNotFoundException() {
		super("Data Not Found");
	}
	
	public DataNotFoundException(String message) {
		super(message);
	}

}
