package com.wipro.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.wipro.enums.LeaveType;
import com.wipro.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "leave_request")
public class LeaveRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_id")
	private Integer requestId;
	
	@Column(name = "emp_id")
	private Integer empId;
	@Column(name = "manager_id")
	private Integer managerId;
	@Column(name = "start_date")
	private LocalDate startDate;
	@Column(name = "end_date")
	private LocalDate endDate;
	@Column(name = "request_date_time")
	private LocalDateTime requestDateTime;
	@Column(name = "leave_type")
	private LeaveType leaveType;
	@Column(name = "request_reason")
	private String requestReason;
	@Column(name = "status")
	@Enumerated(value = EnumType.STRING)
	private Status status;
}