package springkpsgroup02.kps.Service.ServiceImplement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springkpsgroup02.kps.Jwt.UserContext;
import springkpsgroup02.kps.Model.DTO.Request.ProfileUpdateRequest;
import springkpsgroup02.kps.Model.DTO.Response.ProfileResponse;
import springkpsgroup02.kps.Model.Entity.Profile;
import springkpsgroup02.kps.Repository.ProfileRepository;
import springkpsgroup02.kps.Service.ProfileService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImp implements ProfileService {
    private final ProfileRepository profileRepository;

    @Override
    public ProfileResponse getCurrentUsr() {
        UUID uuid = UUID.fromString(UserContext.getUserId());
        return profileRepository.getCurrentUser(uuid);
    }

    @Override
    public ProfileResponse updateProfile(ProfileUpdateRequest profileUpdateRequest) {
        return profileRepository.updateProfile(profileUpdateRequest);
    }

    @Override
    public void deleteProfile() {
        profileRepository.deleteProfile();
    }
}
