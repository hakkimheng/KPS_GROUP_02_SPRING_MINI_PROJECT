package springkpsgroup02.kps.Exception;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import springkpsgroup02.kps.Model.DTO.Response.BaseResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException extends BaseResponse {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException e) {
        return responseEntity(false,e.getMessage(),HttpStatus.NOT_FOUND,null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> methodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String , String > error = new HashMap<>();
        for(FieldError fieldError : e.getBindingResult().getFieldErrors()){
            error.put(fieldError.getField() , fieldError.getDefaultMessage());
        }
        return problemDetailResponseEntity(error);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ProblemDetail> handlerMethodValidationException (HandlerMethodValidationException e){
        Map<String,String> errors = new HashMap<>();
        for(MessageSourceResolvable pathError : e.getAllErrors()){
            for(String err : pathError.getCodes()){
                System.out.println(err);
                if(err.contains("Positive")){
                    errors.put("PositiveId",pathError.getDefaultMessage());
                }
                if(err.contains("Min")){
                    errors.put("MinId",pathError.getDefaultMessage());
                }
            }
        }
        return problemDetailResponseEntity(errors);
    }

    @ExceptionHandler(WrongInputException.class)
    public ResponseEntity<?> wrongInputException(WrongInputException e){
        return responseEntity(false,e.getMessage(),HttpStatus.NOT_FOUND,null);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidEnum(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException && cause.getMessage().contains("Frequency")) {
            return ResponseEntity.badRequest().body("Invalid frequency. Allowed values: DAILY, WEEKLY, MONTHLY, YEARLY");
        }
        return ResponseEntity.badRequest().body("Invalid request format");
    }
}