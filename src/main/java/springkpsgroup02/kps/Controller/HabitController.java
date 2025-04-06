package springkpsgroup02.kps.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<List<Habit>>> getAllHabits(@RequestParam (defaultValue = "1") Integer offset, @RequestParam (defaultValue = "10") Integer limit) {
        return responseEntity("Fetched all habits successfully!",HttpStatus.OK,habitService.getAllHabits(offset,limit));
    }

    // get habit by id
    @GetMapping("{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> getHabit(@PathVariable("habit-id") UUID habitId) {
        return responseEntity("Habit fetched successfully!",HttpStatus.OK,habitService.getHabitById(habitId));

    }

    // create habit
    @PostMapping
    public ResponseEntity<ApiResponse<Habit>> createHabit(@RequestBody HabitRequest habitRequest) {
        return responseEntity("Create Habit successfully!",HttpStatus.CREATED,habitService.createHabit(habitRequest));
    }

    // update habit
    @PutMapping("{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> updateHabitById(@PathVariable("habit-id") UUID habitId, @RequestBody HabitRequest habitRequest) {
        return responseEntity("Update Habit successfully",HttpStatus.OK,habitService.updateHabitById(habitId,habitRequest));
    }

    // delete habit
    @DeleteMapping("{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> deleteHabitById(@PathVariable("habit-id") UUID habitId) {
        habitService.deleteHabitById(habitId);
        return responseEntity("Delete Habit successfully",HttpStatus.OK,null);

    }


}

