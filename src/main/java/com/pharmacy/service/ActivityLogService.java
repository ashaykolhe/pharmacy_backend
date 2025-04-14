package com.pharmacy.service;

import com.pharmacy.model.ActivityLog;
import com.pharmacy.repository.ActivityLogRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class ActivityLogService implements IActivityLogService {
    private final ActivityLogRepository activityLogRepository;

    @Override
    public void save(ActivityLog activityLog) {
        log.debug("activity log " + activityLog.getEmployee().getUserName() + " " + activityLog.getPermission());
        activityLogRepository.save(activityLog);
    }
}
