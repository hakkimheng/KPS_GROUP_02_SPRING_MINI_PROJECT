package springkpsgroup02.kps.Service.ServiceImplement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springkpsgroup02.kps.Model.Entity.Achievement;
import springkpsgroup02.kps.Repository.AchievementRepository;
import springkpsgroup02.kps.Service.AchievementService;


import java.util.List;

@RequiredArgsConstructor
@Service
public class AchievementServiceImp implements AchievementService {

    private final AchievementRepository achievementRepo;

    @Override
    public List<Achievement> getAllAchievement(Integer size , Integer page) {
        System.out.println("imp" + achievementRepo.retrievedAllAchievement(size , page));
        return achievementRepo.retrievedAllAchievement(size , page);
    }
    @Override
    public List<Achievement> getAchievementById(Integer appUserId , Integer size , Integer page){
        return achievementRepo.retrievedAchievementById(appUserId , size , page);
    }
}
