package springkpsgroup02.kps.Controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import springkpsgroup02.kps.Model.DTO.Request.HabitRequest;
import springkpsgroup02.kps.Model.DTO.Response.ApiResponse;
import springkpsgroup02.kps.Model.DTO.Response.BaseResponse;
import springkpsgroup02.kps.Model.Entity.Habit;
import springkpsgroup02.kps.Service.HabitService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/habit")
//@RequiredArgsConstructor
public class HabitController extends BaseResponse {

    // inject habit service
    private final HabitService habitService;
    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }


    // get all habits
    @GetMapping
    public ResponseEntity<ApiResponse<List<Habit>>> getAllHabits(@RequestParam (defaultValue = "1") @Positive Integer page, @RequestParam (defaultValue = "10") @Positive Integer size) {
        return responseEntity(true,"Fetched all habits successfully!",HttpStatus.OK,habitService.getAllHabits(page,size));
    }

    // get habit by id
    @GetMapping("{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> getHabit(@PathVariable("habit-id") UUID habitId) {
        return responseEntity(true,"Habit fetched successfully!",HttpStatus.OK,habitService.getHabitById(habitId));

    }

    // create habit
    @PostMapping
    public ResponseEntity<ApiResponse<Habit>> createHabit(@RequestBody @Valid HabitRequest habitRequest) {
        return responseEntity(true,"Create Habit successfully!",HttpStatus.CREATED,habitService.createHabit(habitRequest));
    }

    // update habit
    @PutMapping("{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> updateHabitById(@PathVariable("habit-id") UUID habitId, @RequestBody @Valid HabitRequest habitRequest) {
        return responseEntity(true,"Update Habit successfully",HttpStatus.OK,habitService.updateHabitById(habitId,habitRequest));
    }

    // delete habit
    @DeleteMapping("{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> deleteHabitById(@PathVariable("habit-id") UUID habitId) {
        habitService.deleteHabitById(habitId);
        return responseEntity(true,"Delete Habit successfully",HttpStatus.OK,null);

    }


    // handle frequency
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<String> handleInvalidEnum(HttpMessageNotReadableException ex) {
//        Throwable cause = ex.getCause();
//        if (cause instanceof InvalidFormatException && cause.getMessage().contains("Frequency")) {
//            return ResponseEntity.badRequest().body("Invalid frequency. Allowed values: DAILY, WEEKLY, MONTHLY, YEARLY");
//        }
//        return ResponseEntity.badRequest().body("Invalid request format");
//    }

}

