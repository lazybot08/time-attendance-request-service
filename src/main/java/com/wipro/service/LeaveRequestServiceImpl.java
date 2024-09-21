package com.wipro.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.dao.LeaveRequestRepository;
import com.wipro.dto.LeaveRequestDto;
import com.wipro.entity.LeaveRequest;
import com.wipro.enums.LeaveType;
import com.wipro.enums.Status;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Override
    public void createNewRequest(LeaveRequestDto request) {
        validateRequest(request);
        
        LeaveRequest newRequest = new LeaveRequest();
        newRequest.setEmpId(request.getEmpId());
        newRequest.setManagerId(request.getManagerId());
        newRequest.setStartDate(request.getStartDate());
        newRequest.setEndDate(request.getEndDate());
        newRequest.setRequestDateTime(LocalDateTime.now());
        if(request.getLeaveType().equalsIgnoreCase("casual")) {
        	newRequest.setLeaveType(LeaveType.CASUAL_LEAVE);
        }else if(request.getLeaveType().equalsIgnoreCase("vacation")) {
        	newRequest.setLeaveType(LeaveType.VACATION_LEAVE);
        }else if(request.getLeaveType().equalsIgnoreCase("sick")) {
        	newRequest.setLeaveType(LeaveType.SICK_LEAVE);
        }
        newRequest.setRequestReason(request.getRequestReason());
        newRequest.setStatus(Status.PENDING);

        leaveRequestRepository.save(newRequest);
    }

    private void validateRequest(LeaveRequestDto request) {
        LocalDate today = LocalDate.now();

        if (request.getStartDate() == null || request.getEndDate() == null) {
            throw new IllegalArgumentException("Start date and end date must not be null.");
        }

        if (request.getStartDate().isBefore(today) || request.getEndDate().isBefore(today)) {
            throw new IllegalArgumentException("Start date and end date must be in the future.");
        }

        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new IllegalArgumentException("End date must be after start date.");
        }
    }

    @Override
    public void updateStatus(Integer id, String status) {
        Optional<LeaveRequest> request = leaveRequestRepository.findById(id);
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

        leaveRequestRepository.save(request.get());
    }

    @Override
    public List<LeaveRequest> getAllRequestByEmpId(HttpServletRequest request) {
    	Integer empId = Integer.parseInt(request.getHeader("empId"));
        return leaveRequestRepository.findByEmpId(empId);
    }

    @Override
    public List<LeaveRequest> getAllRequestByManagerId(Integer managerId) {
        return leaveRequestRepository.findByManagerId(managerId);
    }
}
