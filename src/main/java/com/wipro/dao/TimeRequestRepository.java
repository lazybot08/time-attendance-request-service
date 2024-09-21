package com.wipro.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.entity.TimeOffRequest;

@Repository
public interface TimeRequestRepository extends JpaRepository<TimeOffRequest, Integer> {
	List<TimeOffRequest> findByEmpId(Integer empId);
	List<TimeOffRequest> findByManagerId(Integer managerId);
}
