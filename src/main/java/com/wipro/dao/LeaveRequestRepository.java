package com.wipro.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.entity.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {
	List<LeaveRequest> findByEmpId(Integer empId);
	List<LeaveRequest> findByManagerId(Integer managerId);
}
