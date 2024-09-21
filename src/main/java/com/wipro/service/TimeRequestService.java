package com.wipro.service;

import java.util.List;

import com.wipro.dto.TimeRequestDto;
import com.wipro.entity.TimeOffRequest;

import jakarta.servlet.http.HttpServletRequest;

public interface TimeRequestService {
	void createNewRequest(TimeRequestDto request);
	void updateStatus(Integer id, String status);
	List<TimeOffRequest> getAllRequestByEmpId(HttpServletRequest request);
	List<TimeOffRequest> getAllRequestByManagerId(Integer managerId);
}