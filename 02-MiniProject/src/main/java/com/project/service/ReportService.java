package com.project.service;

import java.util.List;

import com.project.binding.SearchRequest;
import com.project.binding.SearchResponse;

import jakarta.servlet.http.HttpServletResponse;


public interface ReportService {

	public List<SearchResponse> SearchResponse(SearchRequest request);
	public List<String> getPlanNames();
	public List<String> getPlanStatus();
	public void generatePdf(HttpServletResponse response)throws Exception;
	public void generateExcel(HttpServletResponse response) throws Exception;
	
}
