package auth_service.controllers;

import auth_service.dto.*;
import auth_service.entity.User;
import auth_service.service.AuthService;
import auth_service.service.UserService;
import auth_service.util.JwtUtil;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, AuthService authService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        try {
            if (!authService.validateUser(username, password)) {
                logger.warn("Login failed for username: {}", username);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Invalid credentials"));
            }

            String accessToken = jwtUtil.generateToken(username);
            String refreshToken = jwtUtil.generateRefreshToken(username);

            logger.info("User logged in successfully: {}", username);
            return ResponseEntity.ok(Map.of("accessToken", accessToken, "refreshToken", refreshToken));
        } catch (Exception e) {
            logger.error("Login error for user {}: {}", username, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred during login"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody @Valid RegisterRequest userData) {
        logger.info("Attempting to register user: {}", userData.getUsername());

        try {
            userService.createUser(userData);
            logger.info("User registered successfully: {}", userData.getUsername());
            return ResponseEntity.ok(Map.of("message", "User registered successfully"));
        } catch (IllegalArgumentException e) {
            logger.warn("Registration failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            logger.error("Registration error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred during registration"));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody @Valid TokenRefreshRequest request) {
        try {
            String username = jwtUtil.extractUsername(request.getRefreshToken());

            if (!jwtUtil.isTokenValid(request.getRefreshToken(), username)) {
                logger.warn("Invalid refresh token for user: {}", username);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Invalid or expired refresh token"));
            }

            String newAccessToken = jwtUtil.generateToken(username);
            logger.info("New access token generated for user: {}", username);
            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));

        } catch (Exception e) {
            logger.error("Token refresh error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while refreshing token"));
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, String>> verifyToken(@RequestBody TokenVerifyRequest request) {
        try {
            String username = jwtUtil.extractUsername(request.getAccessToken());

            if (!jwtUtil.isTokenValid(request.getAccessToken(), username)) {
                logger.warn("Invalid token for user: {}", username);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Invalid or expired token"));
            }

            logger.info("Token verified for user: {}", username);
            return ResponseEntity.ok(Map.of("username", username, "message", "Token is valid"));

        } catch (Exception e) {
            logger.error("Token verification error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while verifying token"));
        }
    }
}
