package springkpsgroup02.kps.Repository;

import org.apache.ibatis.annotations.*;
import springkpsgroup02.kps.Model.DTO.Request.ProfileUpdateRequest;
import springkpsgroup02.kps.Model.DTO.Response.ProfileResponse;
import springkpsgroup02.kps.Model.Entity.Profile;

@Mapper
public interface ProfileRepository {

    @Select("""
    SELECT * FROM app_users
    WHERE app_user_id = '3fe9b4b6-012c-4a65-a9d9-5938c6fc8c5c'
""")
    ProfileResponse getCurrentUser();


    @Select("""
    UPDATE app_users
    SET username = #{profileUpdateRequest.username}, profile_image = #{profileUpdateRequest.profileImage}
    WHERE app_user_id = '3fe9b4b6-012c-4a65-a9d9-5938c6fc8c5c'
    RETURNING *;
""")
    @Results(id = "profileMapper", value = {
            @Result(property = "profileId", column = "app_user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "level", column = "level"),
            @Result(property = "xp", column = "xp"),
            @Result(property = "profileImage", column = "profile_image"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at"),
    })
    ProfileResponse updateProfile(@Param("profileUpdateRequest") ProfileUpdateRequest profileUpdateRequest);

    @Delete("""
    DELETE FROM app_users
    WHERE app_user_id = 'f7983eab-0775-4920-872b-6a90375d5ce4'
    """)
    void deleteProfile();
}