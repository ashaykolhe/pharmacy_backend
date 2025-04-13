package com.pharmacy.aop;

import com.pharmacy.model.ActivityLog;
import com.pharmacy.model.Employee;
import com.pharmacy.repository.ActivityLogRepository;
import com.pharmacy.service.IEmployeeService;
import com.pharmacy.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Aspect
@AllArgsConstructor
public class ActivityLogAop {

    private final JwtService jwtService;
    private final IEmployeeService iEmployeeService;
    private final ActivityLogRepository activityLogRepository;
    private final HttpServletRequest request;

    @AfterReturning(pointcut = "execution(* com.pharmacy.controller..*(..))", returning = "returnValue")
    public void a(JoinPoint joinPoint, Object returnValue) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null) {
            String token = authorization.split(" ")[1];
            String username = jwtService.extractUsername(token);
            ActivityLog activityLog = new ActivityLog();
            activityLog.setEmployee(username);
            activityLog.setPermission(((ResponseEntity<?>) returnValue).getBody().toString());
            activityLogRepository.save(activityLog);
        }


    }
}
