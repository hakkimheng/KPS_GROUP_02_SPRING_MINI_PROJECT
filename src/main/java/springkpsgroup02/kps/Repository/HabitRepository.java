package springkpsgroup02.kps.Repository;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import springkpsgroup02.kps.Config.UUIDTypeHandler;
import springkpsgroup02.kps.Model.DTO.Request.HabitRequest;
import springkpsgroup02.kps.Model.Entity.Habit;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitRepository {

    // get all habit
    @Select("""
            select * from habits
            limit #{limit}
            offset #{limit} * (#{offset} - 1)
            """)
    @Results(id = "habitMapper", value = {
            @Result(property = "habitId", column = "habit_id"),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "frequency", column = "frequency"),
            @Result(property = "appUserId", column = "app_user_Id"),
    })
    List<Habit> findAllHabits(Integer offset, Integer limit);

    // get habit by id
    @Select("""
    select * from habits where habit_id = #{habitId}
""")
    @ResultMap("habitMapper")
    Habit findHabitById(UUID habitId);


    // insert habit
    @Select("""
            insert into habits(title, description, frequency, app_user_id)
            values (#{habit.title},#{habit.description},#{habit.frequency},#{habit.appUserId})
            returning *;
            """)
    @ResultMap("habitMapper")
    Habit insertHabit(@Param("habit")HabitRequest habitRequest);


    // update habit by id
    @Select("""
                UPDATE habits
                set title= #{habit.title}, description = #{habit.description}, frequency = #{habit.frequency}
                where habit_id = #{habitId}
                returning *;
            """)
    @ResultMap("habitMapper")
    Habit updateHabitById(UUID habitId, @Param("habit") HabitRequest habitRequest);


    // delete habit by id
    @Delete("""
    DELETE from habits where habit_id = #{habitId}
    """)
    void deleteHabitById(UUID habitId);


}
