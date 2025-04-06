package springkpsgroup02.kps.Controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springkpsgroup02.kps.Model.DTO.Response.ApiResponse;
import springkpsgroup02.kps.Model.DTO.Response.BaseResponse;
import springkpsgroup02.kps.Model.Entity.Achievement;
import springkpsgroup02.kps.Service.AchievementService;


import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/achievements")
public class AchievementController extends BaseResponse {

    private final AchievementService achievementService;


    // get all achievement

    @GetMapping
    public ResponseEntity<ApiResponse<List<Achievement>>> getAllAchievement(
            @RequestParam(defaultValue = "5") @Positive Integer size ,
            @RequestParam(defaultValue = "1") @Positive Integer page)
    {
        List<Achievement> achievementList = achievementService.getAllAchievement(size , page);

        return responseEntity(true , "Achievements retrieved successfully!", HttpStatus.OK , achievementList);
    }
    @GetMapping("/app-users")
    public ResponseEntity<ApiResponse<List<Achievement>>> getAllAchievementByAppUser(
            @RequestParam(defaultValue = "5") @Positive Integer size ,
            @RequestParam(defaultValue = "1") @Positive Integer page)
    {
        UUID appUserId = UUID.fromString("3fe9b4b6-012c-4a65-a9d9-5938c6fc8c5c");
        List<Achievement> achievementListByUser = achievementService.getAchievementById( appUserId , 100 , size , page);
        return responseEntity(true , "Achievements for the specified App User retrieved successfully!" , HttpStatus.OK, achievementListByUser);
    }
}

