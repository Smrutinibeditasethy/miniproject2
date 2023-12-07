package com.project.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import com.project.entity.ReportEntity;
import com.project.repository.ReportRepository;

@Configuration
public class AppRunner implements ApplicationRunner{

	@Autowired
	private ReportRepository rerepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		ReportEntity entity1 = new ReportEntity();
		entity1.setSno(1);
		entity1.setName("Priya");
		entity1.setEmail("Priya@gmail.com");
		entity1.setGender("Female");
		entity1.setSsn(25678123456L);
		entity1.setMobile(987654321L);
		entity1.setPlanName("Health");
		entity1.setPlanStatus("Accepted");
		rerepo.save(entity1);
		
		ReportEntity entity2 = new ReportEntity();
		entity2.setSno(2);
		entity2.setName("Seema");
		entity2.setEmail("Seema@gmail.com");
		entity2.setGender("Female");
		entity2.setSsn(2585656L);
		entity2.setMobile(984895621L);
		entity2.setPlanName("Food");
		entity2.setPlanStatus("Accepted");
		rerepo.save(entity2);
		
		ReportEntity entity3 = new ReportEntity();
		entity3.setSno(3);
		entity3.setName("Raj");
		entity3.setEmail("Raj@gmail.com");
		entity3.setGender("Male");
		entity3.setSsn(56878123456L);
		entity3.setMobile(988854321L);
		entity3.setPlanName("Unemployed");
		entity3.setPlanStatus("Accepted");
		rerepo.save(entity3);
		
		ReportEntity entity4 = new ReportEntity();
		entity4.setSno(4);
		entity4.setName("Rohan");
		entity4.setEmail("Rohan@gmail.com");
		entity4.setGender("Male");
		entity4.setSsn(252365456L);
		entity4.setMobile(988884321L);
		entity4.setPlanName("School");
		entity4.setPlanStatus("Deined");
		rerepo.save(entity4);
		
		ReportEntity entity5 = new ReportEntity();
		entity5.setSno(5);
		entity5.setName("Simran");
		entity5.setEmail("Simran@gmail.com");
		entity5.setGender("Female");
		entity5.setSsn(2567777456L);
		entity5.setMobile(9823321L);
		entity5.setPlanName("Hospital");
		entity5.setPlanStatus("Accepted");
		rerepo.save(entity5);
		
		ReportEntity entity6 = new ReportEntity();
		entity6.setSno(1);
		entity6.setName("Mihir");
		entity6.setEmail("Mihir@gmail.com");
		entity6.setGender("Male");
		entity6.setSsn(25623456L);
		entity6.setMobile(9812354321L);
		entity6.setPlanName("Tourist");
		entity6.setPlanStatus("Deined");
		rerepo.save(entity1);
	}

}
