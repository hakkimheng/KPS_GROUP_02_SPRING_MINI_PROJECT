package springkpsgroup02.kps_group_02_spring_mini_project.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail notFoundException(NotFoundException e) {
        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        detail.setProperty("Timestamp", LocalDateTime.now());
        detail.setProperty("error", e.getMessage());
        return detail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String , String > error = new HashMap<>();
        for(FieldError fieldError : e.getBindingResult().getFieldErrors()){
            error.put(fieldError.getField() , fieldError.getDefaultMessage());
        }

        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
            detail.setProperty("timestamp", LocalDateTime.now());
            detail.setProperty("error " , error);

        return detail;
    }
    

    @ExceptionHandler(WrongInputException.class)
    public ProblemDetail wrongInputException(WrongInputException e){
        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setProperty("TimeStamp",LocalDateTime.now());
        detail.setProperty("Error", e.getMessage());
        return detail;
    }
}
