package springkpsgroup02.kps.Service.ServiceImplement;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springkpsgroup02.kps.Exception.InvalidException;
import springkpsgroup02.kps.Exception.NotFoundException;
import springkpsgroup02.kps.Model.DTO.Request.HabitRequest;
import springkpsgroup02.kps.Model.Entity.Habit;
import springkpsgroup02.kps.Model.Enums.Frequency;
import springkpsgroup02.kps.Repository.HabitRepository;
import springkpsgroup02.kps.Service.HabitService;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitServiceImp  implements HabitService {

    // inject Habit repository
    private final HabitRepository habitRepo;


    @Override
    public List<Habit> getAllHabits(Integer offset, Integer limit) {
        return habitRepo.findAllHabits(offset,limit);
    }

    @Override
    public Habit getHabitById(UUID habitId) {
        return habitRepo.findHabitById(habitId);
    }

    @Override
    public Habit createHabit(HabitRequest habitRequest) {

        boolean isFrequencyFound = false;
        for (Frequency frequency : Frequency.values()) {
            if (frequency.name().equalsIgnoreCase(habitRequest.getFrequency())) {
                isFrequencyFound = true;
                break;
            }
        }

        if(!isFrequencyFound) {
            throw new InvalidException("JSON parse error: Cannot deserialize value of type `kh.com.kshrd.miniprojectgamifiedhabittracker.enums.HabitFrequency` from String \\\"a\\\": not one of the values accepted for Enum class: [WEEKLY, MONTHLY, DAILY]");
        }

        return habitRepo.insertHabit(habitRequest);
    }

    @Override
    public Habit updateHabitById(UUID habitId, HabitRequest habitRequest) {
        return habitRepo.updateHabitById(habitId,habitRequest);
    }

    @Override
    public void deleteHabitById(UUID habitId) {
        habitRepo.deleteHabitById(habitId);
    }


}
