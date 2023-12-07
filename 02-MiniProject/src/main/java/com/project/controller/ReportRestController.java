package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.binding.SearchRequest;
import com.project.binding.SearchResponse;
import com.project.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ReportRestController {

	@Autowired
	private ReportService service;
	
	@GetMapping("/plannames")
	public ResponseEntity<List<String>> getUniquePlans(){
		List<String> planNames = service.getPlanNames();
		return new ResponseEntity<>(planNames, HttpStatus.OK);
	}
	
	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> getUniquePlanStatus(){
		List<String> planStatus = service.getPlanStatus();
		return new ResponseEntity<List<String>>(planStatus, HttpStatus.OK);
	}
	
	@GetMapping("/Searchresponse")
	public ResponseEntity<List<SearchResponse>> getSearchResponse( SearchRequest request){
		List<SearchResponse> searchResponse = service.SearchResponse(request);
		return new ResponseEntity<List<SearchResponse>>(searchResponse,HttpStatus.OK);
	}
	
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=searchResponse.xls";
		response.setHeader(headerkey, headervalue);
		service.generateExcel(response);
	}
	
	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=searchResponse.pdf";
		response.setHeader(headerkey, headervalue);
		service.generatePdf(response);
	}
}
