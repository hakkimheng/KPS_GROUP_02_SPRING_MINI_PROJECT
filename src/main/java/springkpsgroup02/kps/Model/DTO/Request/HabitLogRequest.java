package springkpsgroup02.kps.Model.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor@Builder
public class HabitLogRequest {
    private String status;
    private UUID habitId;
}
