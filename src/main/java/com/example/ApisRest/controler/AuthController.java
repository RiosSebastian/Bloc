package com.example.ApisRest.controler;

import com.example.ApisRest.dto.AuthResponse;
import com.example.ApisRest.dto.LoginRequest;
import com.example.ApisRest.dto.RefreshTokenRequest;
import com.example.ApisRest.entity.RefreshToken;
import com.example.ApisRest.entity.User;
import com.example.ApisRest.repository.RefreshTokenRepository;
import com.example.ApisRest.servis.RefreshTokenService;
import com.example.ApisRest.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private  RefreshTokenService refreshTokenService;
    private RefreshTokenRepository refreshTokenRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails user = (UserDetails) auth.getPrincipal();

        String role = user.getAuthorities().iterator().next().getAuthority();

        String accessToken =
                jwtUtil.generateAccessToken(user.getUsername(), role);

        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user.getUsername());

        return ResponseEntity.ok(
                new AuthResponse(accessToken, refreshToken.getToken())
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(
            @RequestBody RefreshTokenRequest request) {

        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(request.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .orElseThrow(() -> new RuntimeException("Refresh token inválido"));

        User user = refreshToken.getUser();

        String accessToken =
                jwtUtil.generateAccessToken(
                        user.getUsername(),
                        user.getRole()
                );

        return ResponseEntity.ok(
                new AuthResponse(accessToken, refreshToken.getToken())
        );
    }


}

