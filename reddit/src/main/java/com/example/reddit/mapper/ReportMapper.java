package com.example.reddit.mapper;

import com.example.reddit.dto.ReportDTO;
import com.example.reddit.model.Report;

import org.springframework.stereotype.Component;

@Component
public class ReportMapper {

    public ReportDTO modelToDto(Report report) {
        ReportDTO reportDTO = new ReportDTO();

        reportDTO.setId(report.getId());
        reportDTO.setAccepted(report.getAccepted());
        reportDTO.setReason(report.getReason());
        reportDTO.setTimestamp(report.getTimestamp());



        return reportDTO;
    }

    public Report dtoToModel(ReportDTO reportDTO) {
        Report report = new Report();

        report.setId(reportDTO.getId());
        report.setAccepted(reportDTO.getAccepted());
        report.setReason(reportDTO.getReason());
        report.setTimestamp(reportDTO.getTimestamp());



        return report;
    }
}
