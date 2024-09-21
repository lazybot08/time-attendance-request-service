package com.wipro.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.dao.TimeRequestRepository;
import com.wipro.dto.TimeRequestDto;
import com.wipro.entity.TimeOffRequest;
import com.wipro.enums.Status;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TimeRequestServiceImpl implements TimeRequestService {

    @Autowired
    private TimeRequestRepository timeRequestRepository;

    @Override
    public void createNewRequest(TimeRequestDto request) {
        validateRequest(request);
        
        TimeOffRequest newRequest = new TimeOffRequest();
        newRequest.setEmpId(request.getEmpId());
        newRequest.setManagerId(request.getManagerId());
        newRequest.setTimeOffDate(request.getTimeOffDate());
        newRequest.setStartTime(LocalTime.parse(request.getStartTime()));
        newRequest.setEndTime(LocalTime.parse(request.getEndTime()));
        newRequest.setRequestReason(request.getRequestReason());
        newRequest.setRequestDateTime(LocalDateTime.now());
        newRequest.setStatus(Status.PENDING);

        timeRequestRepository.save(newRequest);
    }

    private void validateRequest(TimeRequestDto request) {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        if (request.getTimeOffDate().isEqual(today)) {
            LocalTime startTime = LocalTime.parse(request.getStartTime());
            if (startTime.isBefore(now)) {
                throw new IllegalArgumentException("Start time must be after the current time for today's requests.");
            }
        }

        LocalTime startTime = LocalTime.parse(request.getStartTime());
        LocalTime endTime = LocalTime.parse(request.getEndTime());

        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time must be after start time.");
        }
    }

    @Override
    public void updateStatus(Integer requestId, String status) {
        Optional<TimeOffRequest> request = timeRequestRepository.findById(requestId);
        if (request.isEmpty()) {
            throw new RuntimeException("Invalid request Id");
        }

        if (status.equalsIgnoreCase("accepted")) {
            request.get().setStatus(Status.CONFIRMED);
        } else if (status.equalsIgnoreCase("rejected")) {
            request.get().setStatus(Status.CANCELLED);
        } else {
            throw new IllegalArgumentException("Invalid status value: " + status);
        }

        timeRequestRepository.save(request.get());
    }

    @Override
    public List<TimeOffRequest> getAllRequestByEmpId(HttpServletRequest request) {
    	Integer empId = Integer.parseInt(request.getHeader("empId"));
        return timeRequestRepository.findByEmpId(empId);
    }

    @Override
    public List<TimeOffRequest> getAllRequestByManagerId(Integer managerId) {
        return timeRequestRepository.findByManagerId(managerId);
    }
}
