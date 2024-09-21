package com.wipro.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LeaveRequestDto {
	private Integer empId;
	private Integer managerId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String leaveType;
	private String requestReason;
}
