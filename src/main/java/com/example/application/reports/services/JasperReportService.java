package com.example.application.reports.services;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class JasperReportService {
    private final DataSource dataSource;

    public JasperReportService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JasperReport load(File reportFile) {
        try {
            return JasperCompileManager.compileReport(reportFile.getAbsolutePath());
        } catch (JRException e) {
            throw new RuntimeException(
                    String.format("Erro ao compilar relatório em '%s'", reportFile.getAbsolutePath()), e);
        }
    }

    public byte[] exportToPdf(File reportFile, Map<String, Object> arguments) {
        byte[] pdf;
        JasperPrint jasperPrint;
        JasperReport jasperReport = load(reportFile);
        arguments.put("base_dir", reportFile.getParentFile().getAbsolutePath());

        try (Connection connection = dataSource.getConnection()) {
            Assert.notNull(connection, "Connection must not be null");
            try {
                jasperPrint = JasperFillManager.fillReport(jasperReport, arguments, connection);
            } catch (JRException e) {
                throw new RuntimeException(
                        "Erro ao preencher relatório em '%s'".formatted(reportFile.getAbsolutePath()),
                        e);
            }
            try {
                pdf = JasperExportManager.exportReportToPdf(jasperPrint);
            } catch (JRException e) {
                throw new RuntimeException(
                        "Erro ao exportar para PDF relatório em '%s'".formatted(reportFile.getAbsolutePath()),
                        e);
            }
            return pdf;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter conexão com o banco de dados", e);
        }
    }
}
