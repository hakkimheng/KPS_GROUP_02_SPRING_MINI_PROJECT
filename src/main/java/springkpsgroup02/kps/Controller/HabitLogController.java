package springkpsgroup02.kps.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springkpsgroup02.kps.Model.DTO.Request.HabitLogRequest;
import springkpsgroup02.kps.Model.DTO.Response.ApiResponse;
import springkpsgroup02.kps.Model.DTO.Response.BaseResponse;
import springkpsgroup02.kps.Model.Entity.HabitLog;
import springkpsgroup02.kps.Service.HabitLogService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/habit_logs")
@RequiredArgsConstructor
public class HabitLogController extends BaseResponse {
    private final HabitLogService habitLogService;

    // create habit log
    @PostMapping
    public ResponseEntity<ApiResponse<HabitLog>> createHabitLog(@RequestBody @Valid HabitLogRequest habitLogRequest) {
        return responseEntity(true,"created habit log successfully", HttpStatus.OK, habitLogService.createHabitLog(habitLogRequest));
    }

    // get habit log by habit id
    @GetMapping("{habit-id}")
    public ResponseEntity<ApiResponse<List<HabitLog>>> getHabitLogsByHabitId(@PathVariable("habit-id") UUID habitId, @RequestParam(defaultValue = "1") @Positive Integer offset, @RequestParam(defaultValue = "10") @Positive Integer limit) {
        return responseEntity(true,"Fetched all habit logs for the specified habit ID successfully!", HttpStatus.OK, habitLogService.getAllHabitLogsByHabitId(habitId, offset, limit));
    }
}
