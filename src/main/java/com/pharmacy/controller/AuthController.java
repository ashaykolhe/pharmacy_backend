package com.pharmacy.controller;

import com.pharmacy.model.Employee;
import com.pharmacy.request.AuthRequestDTO;
import com.pharmacy.response.JwtResponseDTO;
import com.pharmacy.service.EmployeeService;
import com.pharmacy.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class AuthController {

    private final JwtService jwtService;
    private final EmployeeService employeeService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            Employee employee = employeeService.findByUserName(authRequestDTO.getUsername());

            if (Objects.nonNull(employee)) {
                return new ResponseEntity<>(JwtResponseDTO.builder()
                        .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                        .build(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Exception in User Service", HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @PostMapping("auth/v1/signup")
//    public ResponseEntity SignUp(@RequestBody UserInfoDto userInfoDto) {
//        try {
//            String userId = userDetailsService.signupUser(userInfoDto);
//            if (Objects.isNull(userId)) {
//                return new ResponseEntity<>("Already Exist", HttpStatus.BAD_REQUEST);
//            }
//            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInfoDto.getUsername());
//            String jwtToken = jwtService.GenerateToken(userInfoDto.getUsername());
//            return new ResponseEntity<>(JwtResponseDTO.builder().accessToken(jwtToken).
//                    token(refreshToken.getToken()).userId(userId).build(), HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>("Exception in User Service", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/auth/v1/ping")
    public ResponseEntity<String> ping() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Employee employee = employeeService.findByUserName(authentication.getName());
            if (Objects.nonNull(employee)) {
                return ResponseEntity.ok(employee.getUserName());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @GetMapping("/health")
    public ResponseEntity<Boolean> checkHealth() {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
