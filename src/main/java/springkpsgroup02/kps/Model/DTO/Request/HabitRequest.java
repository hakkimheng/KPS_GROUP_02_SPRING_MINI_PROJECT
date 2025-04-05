package springkpsgroup02.kps.Model.DTO.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class HabitRequest {
    private String title;
    private String description;
    private String frequency;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID appUserId;

}
