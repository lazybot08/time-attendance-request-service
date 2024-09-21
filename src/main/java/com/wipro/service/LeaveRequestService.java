package com.wipro.service;

import java.util.List;

import com.wipro.dto.LeaveRequestDto;
import com.wipro.entity.LeaveRequest;

import jakarta.servlet.http.HttpServletRequest;

public interface LeaveRequestService {
	void createNewRequest(LeaveRequestDto request);
	void updateStatus(Integer id, String status);
	List<LeaveRequest> getAllRequestByEmpId(HttpServletRequest request);
	List<LeaveRequest> getAllRequestByManagerId(Integer managerId);
}
