package springkpsgroup02.kps.Service.ServiceImplement;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springkpsgroup02.kps.Exception.NotFoundException;
import springkpsgroup02.kps.Model.DTO.Response.ProfileResponse;
import springkpsgroup02.kps.Model.DTO.Response.TokenResponse;
import springkpsgroup02.kps.Repository.ProfileRepository;
import springkpsgroup02.kps.Service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    private  final ProfileRepository profileRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = profileRepository.getUserByEmailOrUserName(username);
        if (userDetails == null) throw new UsernameNotFoundException("User Not Found with username: " + username);
        return userDetails;
    }

    @Override
    public ProfileResponse register() {
        return null;
    }
}
