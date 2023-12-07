
package com.project.service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.project.binding.SearchRequest;
import com.project.binding.SearchResponse;
import com.project.entity.ReportEntity;
import com.project.repository.ReportRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse; 

@Service
public class ReportServiceImplementation implements ReportService {

	@Autowired
	private ReportRepository reportrepo;
	
	
	
	@Override
	public List<SearchResponse> SearchResponse(SearchRequest request) {
		ReportEntity queryBuilder = new ReportEntity();
		String planName = request.getPlanName();
		if (planName != null && !planName.equals("")) {
			queryBuilder.setPlanName(planName);
		}
		String planStatus = request.getPlanStatus();
		if (planStatus !=null && !planStatus.equals("")) {
			queryBuilder.setPlanStatus(planStatus);
		}
		
		LocalDate startDate = request.getStartDate();
		if (startDate!= null) {
			queryBuilder.setStartDate(startDate);
		}
		
		LocalDate endDate = request.getEndDate();
		if (endDate != null) {
			queryBuilder.setEndDate(endDate);
		}
		
		Example<ReportEntity> example = Example.of(queryBuilder);
		
		ArrayList<SearchResponse> response = new ArrayList<>();
		   List<ReportEntity> findAll = reportrepo.findAll(example);
		   
		   for(ReportEntity entity : findAll) {
			   SearchResponse searchResponse = new SearchResponse();
			   BeanUtils.copyProperties(entity, searchResponse);
			   response.add(searchResponse);
		   }
		   return response;
	        
	}
	

	@Override
	public List<String> getPlanNames() {
		List<String> findByPlanNames = reportrepo.findByPlanNames();
		 return findByPlanNames;
	}

	@Override
	public List<String> getPlanStatus() {
List<String> findByPlanStatus = reportrepo.findByPlanStatus();
return findByPlanStatus;
	}


	@Override
	public void generatePdf(HttpServletResponse response)throws Exception {
		 
		List<ReportEntity> findAll = reportrepo.findAll();
	    Document document = new Document(PageSize.A4);
	    PdfWriter.getInstance(document, response.getOutputStream());
	    document.open();
	    Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    fontTiltle.setSize(20);
	    Paragraph paragraph1 = new Paragraph("Search Report", fontTiltle);
	    paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
	    document.add(paragraph1);
	    PdfPTable table = new PdfPTable(8);
	    table.setWidthPercentage(100);
	    table.setWidths(new int[] {3,3,3,3,3,3,3,3});
	    table.setSpacingBefore(5);
	    PdfPCell cell = new PdfPCell();
	    cell.setBackgroundColor(CMYKColor.BLUE);
	    cell.setPadding(3);
	    Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    font.setColor(CMYKColor.BLACK);
	    cell.setPhrase(new Phrase("S.No", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Name", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Email", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Mobile", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Gender", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("SSN", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Plan Name", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Plan Status", font));
	    table.addCell(cell);
	   
	    for (ReportEntity entity: findAll) {
	      table.addCell(String.valueOf(entity.getSno()));
	      table.addCell(entity.getName());
	      table.addCell(entity.getEmail());
	      table.addCell(entity.getPlanName());
	      table.addCell(entity.getGender());
	      table.addCell(entity.getPlanStatus());
	      table.addCell(String.valueOf(entity.getSsn()));
	      table.addCell(String.valueOf(entity.getMobile()));
	      
	    }
	   
	    document.add(table);
	   
	    document.close();		
	}


	@Override
	public void generateExcel(HttpServletResponse response)throws Exception {
		
		List<ReportEntity> findAll = reportrepo.findAll();
		
	HSSFWorkbook workbook= new HSSFWorkbook();
	HSSFSheet createSheet = workbook.createSheet();
	HSSFRow headerRow = createSheet.createRow(0);
	headerRow.createCell(0).setCellValue("S.No");
	headerRow.createCell(1).setCellValue("Name");
	headerRow.createCell(2).setCellValue("Email");
	headerRow.createCell(3).setCellValue("Gender");
	headerRow.createCell(4).setCellValue("Mobile");
	headerRow.createCell(5).setCellValue("SSN");
	headerRow.createCell(6).setCellValue("Plan Name");
	
	int i = 1;
	for(ReportEntity entity: findAll) {
		HSSFRow dataRow = createSheet.createRow(i);
		dataRow.createCell(0).setCellValue(entity.getSno());
		dataRow.createCell(1).setCellValue(entity.getName());
		dataRow.createCell(2).setCellValue(entity.getEmail());
		dataRow.createCell(3).setCellValue(entity.getGender());
		dataRow.createCell(4).setCellValue(entity.getMobile());
		dataRow.createCell(5).setCellValue(entity.getSsn());
		dataRow.createCell(6).setCellValue(entity.getPlanName());
		i++;
	}
	ServletOutputStream outputStream = response.getOutputStream();
	workbook.write(outputStream);
	outputStream.close();
	}

}
