package com.project.entity;

import java.time.LocalDate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Elegibility_DTLS")
public class ReportEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sno;
	
	private String name;
	
	private String email;
	
	private Long mobile;
	
	private String gender;
	
	private Long ssn;
	
	private String planName;
	
	private String planStatus;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private String createdBy;
	
	private String activeSW;
	
	private String updatedBy;
	
	@CreationTimestamp
	private LocalDate createdDate;
	
	@UpdateTimestamp
	private LocalDate updatedDate;
	
	
}
