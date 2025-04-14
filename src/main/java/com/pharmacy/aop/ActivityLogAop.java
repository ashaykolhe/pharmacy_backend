package com.pharmacy.aop;

import com.pharmacy.model.ActivityLog;
import com.pharmacy.model.Employee;
import com.pharmacy.repository.ActivityLogRepository;
import com.pharmacy.service.IActivityLogService;
import com.pharmacy.service.IEmployeeService;
import com.pharmacy.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Aspect
@AllArgsConstructor
@Log4j2
public class ActivityLogAop {

    private final JwtService jwtService;
    private final IEmployeeService iEmployeeService;
    private final IActivityLogService iActivityLogService;
    private final HttpServletRequest request;

    @AfterReturning(pointcut = "execution(* com.pharmacy.controller..*(..)) && !@annotation(com.pharmacy.aop.NoActivityLog)", returning = "returnValue")
    public void a(JoinPoint joinPoint, Object returnValue) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null) {
            String token = authorization.split(" ")[1];
            String username = jwtService.extractUsername(token);
            try {
                Employee employee = iEmployeeService.findByUserName(username);
                ActivityLog activityLog = new ActivityLog();
                activityLog.setEmployee(employee);
                activityLog.setPermission(((ResponseEntity<?>) returnValue).getBody().toString());
                iActivityLogService.save(activityLog);
            } catch (Exception exception) {
                log.error("Cannot insert activity log. " + exception.getMessage());
            }

        }


    }
}
