package com.pharmacy.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pharmacy.constants.Constants;
import com.pharmacy.exception.ErrorDetails;
import com.pharmacy.request.AuthRequestDTO;
import com.pharmacy.response.JwtResponseDTO;
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

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
@Log4j2
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
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
    public ResponseEntity<ObjectNode> checkIfJwtValid(HttpServletRequest request) {
        JsonNodeFactory instance = JsonNodeFactory.instance;
        ObjectNode obj = instance.objectNode();
        obj.put("status", "success");
        return ResponseEntity.ok(obj);
    }

    @PostMapping("/renewJwt")
    public ResponseEntity<?> renewJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String username = jwtService.extractUsername(authHeader.substring(7));
            return new ResponseEntity<>(JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(username))
                    .build(), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/getJwtExpiryTime")
    public ResponseEntity<?> getJwtExpiryTime(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        Date tokenExpiration = jwtService.getTokenExpiration(authHeader.substring(7));
        JsonNodeFactory instance = JsonNodeFactory.instance;
        ObjectNode obj = instance.objectNode();
        LocalDateTime tokenEx = tokenExpiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime now = LocalDateTime.now();
        long expirySeconds = Duration.between(now, tokenEx).getSeconds();
        obj.put("expirySeconds", expirySeconds);
        return ResponseEntity.ok(obj);
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
