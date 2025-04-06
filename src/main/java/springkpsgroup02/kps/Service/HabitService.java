package springkpsgroup02.kps.Service;


import springkpsgroup02.kps.Model.DTO.Request.HabitRequest;
import springkpsgroup02.kps.Model.Entity.Habit;

import java.util.List;
import java.util.UUID;

public interface HabitService {
    List<Habit> getAllHabits (Integer offset, Integer limit);
    Habit getHabitById ( UUID habitId );
    Habit createHabit (HabitRequest habitRequest);
    Habit updateHabitById(UUID habitId, HabitRequest habitRequest);
    void deleteHabitById(UUID habitId);

}
