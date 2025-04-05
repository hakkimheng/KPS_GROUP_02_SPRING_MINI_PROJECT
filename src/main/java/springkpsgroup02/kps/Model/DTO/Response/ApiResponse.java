package springkpsgroup02.kps_group_02_spring_mini_project.Model.DTO.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String message;

    private HttpStatusCode status;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;

    @Builder.Default
    private LocalDateTime timestamps = LocalDateTime.now();
}
