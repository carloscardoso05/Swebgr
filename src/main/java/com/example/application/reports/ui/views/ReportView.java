package com.example.application.reports.ui.views;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.example.application.reports.domain.Report;
import com.example.application.reports.services.ReportsService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@Route("reports")
public class ReportView extends VerticalLayout implements HasUrlParameter<Integer> {
    private final ReportsService reportsService;
    private Report report;

    public ReportView(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @Override
    public void setParameter(BeforeEvent event, Integer parameter) {
        if (parameter == null || parameter < 0) {
            report = null;
        } else {
            report = reportsService.getReportById(parameter);
        }
        render();
    }

    void render() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        if (report == null) {
            add(new Paragraph("Relatório não encontrado"));
        } else {
            add(new H2("RELATÓRIO: %s".formatted(report.getName().toUpperCase())));
            var form = report.getForm();
            if (form != null) {
                add(form);
            }
            StreamResource pdfResource = new StreamResource("report.pdf",
                    () -> new ByteArrayInputStream(reportsService.generatePdf(report)));
            pdfResource.setCacheTime(0);
            pdfResource.setContentType("application/pdf");
            var anchor = new Anchor(pdfResource, "Gerar relatório");
            anchor.setTarget("_blank");
            add(anchor);
        }
        var button = new Button("Voltar");
        button.addClickListener(event -> {
            button.getUI().ifPresent(ui -> {
                if (report == null) {
                    ui.navigate(ReportsBySectorView.class);
                    return;
                }
                ui.navigate(ReportsBySectorDetail.class, report.getSector().name());
            });
        });
        button.getStyle().set("cursor", "pointer");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(button);
    }

}
