package com.pharmacy.aop;

import com.pharmacy.model.ActivityLog;
import com.pharmacy.model.Employee;
import com.pharmacy.request.AuthRequestDTO;
import com.pharmacy.service.IActivityLogService;
import com.pharmacy.service.IEmployeeService;
import com.pharmacy.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Aspect
@AllArgsConstructor
@Log4j2
public class ActivityLogAop {

    private final JwtService jwtService;
    private final IEmployeeService iEmployeeService;
    private final IActivityLogService iActivityLogService;
    private final HttpServletRequest request;
    private static Map<String, Integer> countOfFailedLogin = new ConcurrentHashMap<>();

    @AfterReturning(pointcut = "execution(* com.pharmacy.controller..*(..)) && !@annotation(com.pharmacy.aop.NoActivityLog)", returning = "returnValue")
    public void activityLog(JoinPoint joinPoint, Object returnValue) {
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

    @AfterThrowing(pointcut = "execution(* com.pharmacy.controller.AuthController.login(..))")
    public void lockEmployee(JoinPoint joinPoint) {
        String username = ((AuthRequestDTO)joinPoint.getArgs()[0]).getUsername();
        Integer integer = countOfFailedLogin.get(username);
        Employee employee = iEmployeeService.findByUserName(username);
        if (integer == null) {
            countOfFailedLogin.put(username, 1);
        } else {
            countOfFailedLogin.put(username, integer + 1);
        }
        if(integer != null && integer >= 4) {
            iEmployeeService.lockEmployee(employee);
            countOfFailedLogin.remove(username);
        }
    }
}
