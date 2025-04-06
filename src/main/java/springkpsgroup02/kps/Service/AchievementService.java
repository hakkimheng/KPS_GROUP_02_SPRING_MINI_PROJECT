package springkpsgroup02.kps.Service;

import springkpsgroup02.kps.Model.Entity.Achievement;

import java.util.List;

public interface AchievementService {

    List<Achievement> getAllAchievement(Integer size , Integer page);
    List<Achievement> getAchievementById(Integer appUserId , Integer size , Integer page);
}
