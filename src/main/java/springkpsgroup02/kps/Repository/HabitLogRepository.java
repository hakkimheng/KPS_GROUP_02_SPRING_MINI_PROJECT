package springkpsgroup02.kps.Repository;

import org.apache.ibatis.annotations.*;
import springkpsgroup02.kps.Model.DTO.Request.HabitLogRequest;
import springkpsgroup02.kps.Model.Entity.HabitLog;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitLogRepository {
    // insert habit log
    @Select("""
            INSERT INTO habit_logs(log_date, status, habit_id)
            values (current_date,#{habitLog.status},#{habitLog.habitId})
            returning *
            """)
    @Results(id = "habitLogMapper", value = {
            @Result(property = "habitLogId", column = "habit_log_id"),
            @Result(property = "logDate", column = "log_date"),
            @Result(property = "status", column = "status"),
            @Result(property = "xpEarned", column = "xp_earned"),
            @Result(property = "habit", column = "habit_id",
                    one = @One(select = "springkpsgroup02.kps_02_spring_mini_project.Repository.HabitRepository.findHabitById")
            )
    })
    HabitLog createHabitLog(@Param("habitLog") HabitLogRequest habitLogRequest);

    // get all habit logs
    @Select("""
            select * from habit_logs where habit_id = #{habitId}
            limit #{limit}
            offset #{limit} * (#{offset} - 1)
            """)
    @ResultMap("habitLogMapper")
    List<HabitLog> findAllHabitLogsByHabitId(UUID habitId, Integer offset, Integer limit);
}
