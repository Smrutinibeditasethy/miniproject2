package com.project.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.entity.ReportEntity;

public interface ReportRepository extends JpaRepository<ReportEntity, Serializable> {

	@Query("SELECT DISTINCT(planName) FROM ReportEntity")
	public List<String> findByPlanNames();
	
	@Query("SELECT DISTINCT(planStatus) FROM ReportEntity")
	public List<String> findByPlanStatus();
	 
		}
