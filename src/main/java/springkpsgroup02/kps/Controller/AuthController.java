package springkpsgroup02.kps.Controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springkpsgroup02.kps.Jwt.JwtUtils;
import springkpsgroup02.kps.Model.DTO.Request.AuthRequest;
import springkpsgroup02.kps.Model.DTO.Response.BaseResponse;
import springkpsgroup02.kps.Model.DTO.Response.TokenResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController extends BaseResponse {
    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    @Operation( summary = "User Login")
    public TokenResponse login(@RequestBody AuthRequest authRequest){
        TokenResponse tokenResponse = new TokenResponse();
        String identifier = authRequest.getIdentifier();
        String password = authRequest.getPassword();
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(identifier, password)
            );
            tokenResponse.setToken(jwtUtils.generateToken(identifier));
            return tokenResponse;
        } catch (Exception e) {
            throw new RuntimeException("Not Found!");
        }
    }
}
