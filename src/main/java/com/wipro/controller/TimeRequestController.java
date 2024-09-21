package com.wipro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wipro.dto.TimeRequestDto;
import com.wipro.entity.TimeOffRequest;
import com.wipro.service.TimeRequestService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/requests/time-request")
public class TimeRequestController {

	@Autowired
	private TimeRequestService timeRequestService;
	
    @PostMapping("/employee")
    public ResponseEntity<Void> createNewRequest(@RequestBody TimeRequestDto request) {
        try {
            timeRequestService.createNewRequest(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/manager/{requestId}")
    public ResponseEntity<Void> updateStatus(@PathVariable Integer requestId, @RequestParam String status) {
        try {
            timeRequestService.updateStatus(requestId, status);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/employee")
    public ResponseEntity<List<TimeOffRequest>> getAllRequestByEmpId(HttpServletRequest request) {
        try {
            List<TimeOffRequest> requests = timeRequestService.getAllRequestByEmpId(request);
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<TimeOffRequest>> getAllRequestByManagerId(@RequestHeader("role") String role, @PathVariable Integer managerId) {
        try {
            List<TimeOffRequest> requests = timeRequestService.getAllRequestByManagerId(managerId);
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
