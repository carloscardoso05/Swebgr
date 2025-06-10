package com.example.application.reports.services;

import org.springframework.stereotype.Service;

import com.example.application.reports.domain.Report;
import com.example.application.reports.domain.Sector;

@Service
public class ReportsService {
    private final JasperReportService jasperReportService;



    public ReportsService(JasperReportService jasperReportService) {
        this.jasperReportService = jasperReportService;
    }

    public int getTotalReportsBySector(Sector sector) {
        return 1;
    }

    public byte[] generatePdf(Report report) {
        return jasperReportService.exportToPdf(report.getFile(), report.getArguments());
    }
}
