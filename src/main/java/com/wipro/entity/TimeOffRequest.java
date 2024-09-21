package com.wipro.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
@Table(name = "time_request")
public class TimeOffRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_id")
	private Integer requestId;
	
	@Column(name = "emp_id")
	private Integer empId;
	@Column(name = "manager_id")
	private Integer managerId;
	@Column(name = "time_off_date")
	private LocalDate timeOffDate;
	@Column(name = "start_time")
	private LocalTime startTime;
	@Column(name = "end_time")
	private LocalTime endTime;
	@Column(name = "request_date_time")
	private LocalDateTime requestDateTime;
	@Column(name = "request_reason")
	private String requestReason;
	@Column(name = "status")
	@Enumerated(value = EnumType.STRING)
	private Status status;
}
