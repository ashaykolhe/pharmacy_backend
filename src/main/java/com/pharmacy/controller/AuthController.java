package com.pharmacy.controller;

import com.pharmacy.constants.Constants;
import com.pharmacy.exception.ErrorDetails;
import com.pharmacy.request.AuthRequestDTO;
import com.pharmacy.response.JwtResponseDTO;
import com.pharmacy.service.EmployeeService;
import com.pharmacy.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
@Log4j2
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequestDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                    .build(), HttpStatus.OK);

        }
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), Constants.GENERAL.BAD_CREDENTIALS, null), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/checkIfJwtValid")
    public ResponseEntity<?> checkIfJwtValid(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        return ResponseEntity.ok(new JwtResponseDTO(token));

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

    @GetMapping("/health")
    public ResponseEntity<Boolean> checkHealth() {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
