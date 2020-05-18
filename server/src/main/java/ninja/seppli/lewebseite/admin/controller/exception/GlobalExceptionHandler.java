package ninja.seppli.lewebseite.admin.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	/**
	 * the logger
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(value = {NotFoundException.class})
	protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest req) {
		String body = ex.getMessage();
		return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.NOT_FOUND, req);
	}

}
