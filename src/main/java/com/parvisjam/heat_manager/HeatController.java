package com.parvisjam.heat_manager;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.parvisjam.heat_manager.service.dao.UserDao;

@RestController
public class HeatController {
    private final UserDao userDao;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public HeatController(UserDao userDao, UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/user/{userName}")
    public CustomUser getUser(@PathVariable String userName) {
        return userDao.getUserByName(userName);
    }
    
    @GetMapping("/")
    public String getBasic() {
        return "basic";
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "username", authentication.getName()));
    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.findByName(registerRequest.username()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "Username already exists"));
        }

        String encodedPassword = passwordEncoder.encode(registerRequest.password());
        CustomUser savedUser = userRepository.save(new CustomUser(
                registerRequest.username(),
                registerRequest.email(),
                registerRequest.age(),
                encodedPassword));

        return ResponseEntity.ok(Map.of(
                "status", "registered",
                "username", savedUser.getUsername()));
    }
}
