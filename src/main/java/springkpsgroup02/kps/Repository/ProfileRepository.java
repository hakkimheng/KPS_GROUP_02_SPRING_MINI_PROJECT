package springkpsgroup02.kps.Repository;

import org.apache.ibatis.annotations.*;
import org.springframework.security.core.userdetails.UserDetails;
import springkpsgroup02.kps.Model.DTO.Request.ProfileRequest;
import springkpsgroup02.kps.Model.DTO.Request.ProfileUpdateRequest;
import springkpsgroup02.kps.Model.DTO.Response.ProfileResponse;
import springkpsgroup02.kps.Model.Entity.Profile;

import java.util.UUID;

@Mapper
public interface ProfileRepository {

    @Select("""
                SELECT * FROM app_users
                WHERE app_user_id = #{userId}
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
    ProfileResponse getCurrentUser(UUID userId);


    @Select("""
                UPDATE app_users
                SET username = #{profileUpdateRequest.username}, profile_image = #{profileUpdateRequest.profileImage}
                WHERE app_user_id = '3fe9b4b6-012c-4a65-a9d9-5938c6fc8c5c'
                RETURNING *;
            """)
    @ResultMap("profileMapper")
    ProfileResponse updateProfile(@Param("profileUpdateRequest") ProfileUpdateRequest profileUpdateRequest);

    @Delete("""
            DELETE FROM app_users
            WHERE app_user_id = 'f7983eab-0775-4920-872b-6a90375d5ce4'
            """)
    void deleteProfile();

    @Select("SELECT * FROM app_users WHERE email = #{user} OR username = #{user}")
    @Result(property = "id", column = "app_user_id")
    @Result(property = "profileImage", column = "profile_image")
    @Result(property = "isVerified", column = "is_verified")
    @Result(property = "createdAt", column = "created_at")
    public Profile getUserByEmailOrUserName(String user);
}