package customerapp.java.exception;

public class NoDataException extends RuntimeException{

	public NoDataException() {
		super("No Data found on Database");
	}
	public NoDataException(String message){
		super(message);
	}
	
}
