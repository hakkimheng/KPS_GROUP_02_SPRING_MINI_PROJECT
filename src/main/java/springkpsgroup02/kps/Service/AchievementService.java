package springkpsgroup02.kps.Service;

import springkpsgroup02.kps.Model.Entity.Achievement;

import java.util.List;
import java.util.UUID;

public interface AchievementService {

    List<Achievement> getAllAchievement(Integer size , Integer page);
    List<Achievement> getAchievementById(UUID appUserId  , Integer xp , Integer size, Integer page);
}
