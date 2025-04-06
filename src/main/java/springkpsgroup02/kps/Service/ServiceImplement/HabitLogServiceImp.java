package springkpsgroup02.kps.Service.ServiceImplement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springkpsgroup02.kps.Model.DTO.Request.HabitLogRequest;
import springkpsgroup02.kps.Model.Entity.HabitLog;
import springkpsgroup02.kps.Repository.HabitLogRepository;
import springkpsgroup02.kps.Service.HabitLogService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitLogServiceImp implements HabitLogService {
    private final HabitLogRepository habitLogRepository;

    @Override
    public HabitLog createHabitLog(HabitLogRequest habitLogRequest) {
       habitLogRepository.updateUserXp();
        return habitLogRepository.createHabitLog(habitLogRequest);
    }

    @Override
    public List<HabitLog> getAllHabitLogsByHabitId(UUID habitId, Integer offset, Integer limit) {
        return habitLogRepository.findAllHabitLogsByHabitId(habitId, offset, limit);
    }
}
