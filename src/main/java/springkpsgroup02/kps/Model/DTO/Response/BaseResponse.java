package springkpsgroup02.kps.Model.DTO.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Map;

public abstract class BaseResponse {

    // Don't Edit It
    public <T> ResponseEntity<ApiResponse<T>> responseEntity(Boolean success, String message, HttpStatus httpStatus, T payload) {
        ApiResponse<T> apiResponse = ApiResponse.<T>builder()
                .success(success)
                .message(message)
                .status(httpStatus)
                .payload(payload)
                .build();
        return new ResponseEntity<>(apiResponse,
                apiResponse.getStatus()
        );
    }

    // Don't Edit It
    public ResponseEntity<ProblemDetail> problemDetailResponseEntity(Map<?,?> errors) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("error",errors);
        return new ResponseEntity<>(problemDetail,HttpStatus.BAD_REQUEST);
    }
}