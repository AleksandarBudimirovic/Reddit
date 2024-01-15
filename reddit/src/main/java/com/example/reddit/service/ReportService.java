package com.example.reddit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.reddit.model.Report;
import com.example.reddit.repository.ReportRepository;

@Service
public class ReportService {

	@Autowired
	ReportRepository reportRepository;
	
	public Report findOne(Long id) {
		return reportRepository.findById(id).orElse(null);
	}
	
	
	public List<Report> findAll() {

		return reportRepository.findAll();
	}
	

	public Report save(Report report) {
		return reportRepository.save(report);
		
	}
	
	
	public void remove(Long id){
		reportRepository.deleteById(id);
	}
	
	public Page<Report> findAll(Pageable page) {
		return reportRepository.findAll(page);
	}
	
}
