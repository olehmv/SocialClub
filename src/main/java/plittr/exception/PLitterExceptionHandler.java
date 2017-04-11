//package plittr.exception;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import plittr.exception.error.ErrorInfo;
//
//@ControllerAdvice
//public class PLitterExceptionHandler extends ResponseEntityExceptionHandler{
//	
//	
////	    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
////	    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
////	        String bodyOfResponse = "This should be application specific";
////	        return handleExceptionInternal(ex, bodyOfResponse, 
////	          new HttpHeaders(), HttpStatus.CONFLICT, request);
////	    }
////	
//	
//	
//
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	@ExceptionHandler(PlitterNotFoundException.class)
//	ErrorInfo
//	handleBadRequest(HttpServletRequest req, Exception ex) {
//	    return new ErrorInfo(req.getRequestURL(), ex);
//	} 
//}
