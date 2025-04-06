package springkpsgroup02.kps.Service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import springkpsgroup02.kps.Model.DTO.Response.ProfileResponse;

public interface AuthService extends UserDetailsService{
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public ProfileResponse register();
}