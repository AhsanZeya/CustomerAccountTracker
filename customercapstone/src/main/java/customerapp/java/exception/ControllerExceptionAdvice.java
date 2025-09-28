package customerapp.java.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionAdvice extends ResponseEntityExceptionHandler {

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.IM_USED)
	public Object handle(Exception ex) {
		Map<String, Object> exceptionDetails = new HashMap<>();
		exceptionDetails.put("timestamp", LocalDateTime.now());
		if(ex.getMessage().equals(null)) {
			exceptionDetails.put("message", "Encountered Null Pointer Exception");
		}else {
			exceptionDetails.put("message", ex.getMessage());
		}
		return exceptionDetails;
	}

	@ResponseBody
	@ExceptionHandler(NoDataException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	Object handleNoDataException(NoDataException ex) {
		Map<String, Object> exceptionDetails = new HashMap<>();
		exceptionDetails.put("timestamp", LocalDateTime.now());
		exceptionDetails.put("message", ex.getMessage());
		return exceptionDetails;
	}

	@ResponseBody
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	Object handleDataNotFoundException(DataNotFoundException ex) {
		Map<String, Object> exceptionDetails = new HashMap<>();
		exceptionDetails.put("timestamp", LocalDateTime.now());
		exceptionDetails.put("message", ex.getMessage());
		return exceptionDetails;
	}

	@ResponseBody
	@ExceptionHandler(InSufficientFundsException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	Object handleInSufficientFundsException(InSufficientFundsException ex) {
		Map<String, Object> exceptionDetails = new HashMap<>();
		exceptionDetails.put("timestamp", LocalDateTime.now());
		exceptionDetails.put("message", ex.getMessage());
		return exceptionDetails;
	}

	@ResponseBody
	@ExceptionHandler(InValidDetailsException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	Object handleInValidDetailsException(InValidDetailsException ex) {
		Map<String, Object> exceptionDetails = new HashMap<>();
		exceptionDetails.put("timestamp", LocalDateTime.now());
		exceptionDetails.put("message", ex.getMessage());
		return exceptionDetails;
	}

	@ResponseBody
	@ExceptionHandler(DataAlreadyExistsException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	Object handleDataAlreadyExistsException(DataAlreadyExistsException ex) {
		Map<String, Object> exceptionDetails = new HashMap<>();
		exceptionDetails.put("timestamp", LocalDateTime.now());
		exceptionDetails.put("message", ex.getMessage());
		return exceptionDetails;
	}

}
