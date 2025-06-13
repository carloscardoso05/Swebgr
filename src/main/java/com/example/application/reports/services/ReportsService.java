package com.example.application.reports.services;

import java.util.AbstractMap;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.application.reports.domain.Report;
import com.example.application.reports.domain.Sector;
import com.example.application.reports.domain.reports.ProjetosDeCdi;

@Service
public class ReportsService {
    private final JasperReportService jasperReportService;

    private final AbstractMap<Sector, List<Report>> reportsBySector = new EnumMap<>(Sector.class);

    public ReportsService(JasperReportService jasperReportService) {
        this.jasperReportService = jasperReportService;
        List<Report> reports = List.of(new ProjetosDeCdi());
        reportsBySector.putAll(
                reports.stream()
                        .collect(Collectors.groupingBy(Report::getSector, Collectors.toList())));
    }

    public int getTotalReportsBySector(Sector sector) {
        return reportsBySector.getOrDefault(sector, List.of()).size();
    }

    public List<Report> getReportsBySector(Sector sector) {
        return reportsBySector.getOrDefault(sector, List.of());
    }

    public byte[] generatePdf(Report report) {
        return jasperReportService.exportToPdf(report.getFile(), report.getArguments());
    }

    public Report getReportById(int id) {
        return reportsBySector.values().stream()
                .flatMap(List::stream)
                .filter(report -> report.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
