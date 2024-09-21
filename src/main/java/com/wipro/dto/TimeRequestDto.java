package com.wipro.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TimeRequestDto {

    @NotNull(message = "Employee ID cannot be null")
    private Integer empId;

    @NotNull(message = "Manager ID cannot be null")
    private Integer managerId;

    @NotNull(message = "Time off date cannot be null")
    @FutureOrPresent(message = "Time off date must be today or in the future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate timeOffDate;

    @NotNull(message = "Start time cannot be null")
    @JsonFormat(pattern = "HH:mm")
    private String startTime;

    @NotNull(message = "End time cannot be null")
    @JsonFormat(pattern = "HH:mm")
    private String endTime;

    private String requestReason;
}
