package springkpsgroup02.kps.Model.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor@Builder
public class HabitLogRequest {
    @NotBlank(message = "Status is required")
    private String status;
    private UUID habitId;
}
