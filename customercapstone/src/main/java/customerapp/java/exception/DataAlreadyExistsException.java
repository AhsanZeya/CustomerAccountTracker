package customerapp.java.exception;

@SuppressWarnings("serial")
public class DataAlreadyExistsException extends RuntimeException {
	
	public DataAlreadyExistsException() {
		super("Data Already Exists");
	}
	
	public DataAlreadyExistsException(String message) {
		super(message);
	}

}
