package springkpsgroup02.kps.Repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import springkpsgroup02.kps.Model.Entity.Achievement;

import java.util.List;

@Mapper
@Repository
public interface AchievementRepository {

    @Results(id = "achievementMapper" , value = {
            @Result(property = "achievementId" , column = "achievement_id"),
            @Result(property = "title" , column = "title"),
            @Result(property = "description" , column = "description"),
            @Result(property = "badge" , column = "badge"),
            @Result(property = "xpRequired" , column = "xp_required")
    })
    @Select("SELECT * FROM achievements LIMIT #{size} OFFSET #{size} * (#{page} - 1)")
    List<Achievement> retrievedAllAchievement(@Param("size") Integer size, @Param("page") Integer page);

    @ResultMap("achievementMapper")
    @Select("""
     
     SELECT  * FROM achievements ac INNER JOIN app_user_achievements au
     ON ac.achievement_id = au.achievement_id
     WHERE au.app_user_id = #{au.appUserId}
     LIMIT #{size} OFFSET #{size} * (#{page} - 1)
     
     """)
    List<Achievement> retrievedAchievementById(@Param("au") Integer appUserId,
                                               @Param("size") Integer size,
                                               @Param("page") Integer page );

}
