package springkpsgroup02.kps.Service.ServiceImplement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springkpsgroup02.kps.Model.Entity.Achievement;
import springkpsgroup02.kps.Repository.AchievementRepository;
import springkpsgroup02.kps.Service.AchievementService;


import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AchievementServiceImp implements AchievementService {

    private final AchievementRepository achievementRepo;

    // get all achievement
    @Override
    public List<Achievement> getAllAchievement(Integer size , Integer page) {
        return achievementRepo.retrievedAllAchievement(size , page);
    }
    // get all achievement by user id
    @Override
    public List<Achievement> getAchievementById(UUID appUserId , Integer xp,  Integer size , Integer page){
        return achievementRepo.retrievedAchievementById(appUserId , xp ,  size , page);
    }



}
