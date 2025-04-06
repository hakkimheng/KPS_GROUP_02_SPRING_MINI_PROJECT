package springkpsgroup02.kps.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springkpsgroup02.kps.Model.DTO.Response.ApiResponse;
import springkpsgroup02.kps.Model.Entity.Achievement;
import springkpsgroup02.kps.Service.AchievementService;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/achievements")
public class AchievementController {

    private final AchievementService achievementService;


    // get all achievement

    @GetMapping
    public ResponseEntity<ApiResponse<List<Achievement>>> getAllAchievement(
            @RequestParam(defaultValue = "5") Integer size ,
            @RequestParam(defaultValue = "1") Integer page)
    {
        List<Achievement> achievementList = achievementService.getAllAchievement(size , page);


        ApiResponse<List<Achievement>> response = ApiResponse.<List<Achievement>>builder()
                .success(true)
                .message("Achievements retrieved successfully!")
                .status(HttpStatus.OK)
                .payload(achievementList)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/app-users")
    public ResponseEntity<ApiResponse<List<Achievement>>> getAllAchievement(
            Integer appUserId,
            @RequestParam(defaultValue = "5") Integer size ,
            @RequestParam(defaultValue = "1") Integer page)
    {
        List<Achievement> achievementList = achievementService.getAchievementById(appUserId , size , page);

        ApiResponse<List<Achievement>> response = ApiResponse.<List<Achievement>>builder()
                .success(true)
                .message("Achievements retrieved successfully!")
                .status(HttpStatus.OK)
                .payload(achievementList)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
