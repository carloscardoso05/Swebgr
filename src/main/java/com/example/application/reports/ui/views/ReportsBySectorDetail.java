package com.example.application.reports.ui.views;

import java.util.Arrays;

import com.example.application.reports.domain.Sector;
import com.example.application.reports.services.ReportsService;
import com.example.application.reports.ui.components.ReportButton;
import com.example.application.reports.ui.components.SectorCard;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route("reports-by-sector")
public class ReportsBySectorDetail extends VerticalLayout implements HasUrlParameter<String> {
    private String sectorName;
    private Sector sector;
    private final ReportsService reportsService;

    public ReportsBySectorDetail(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        sectorName = parameter;
        try {
            sector = Sector.valueOf(sectorName.toUpperCase());
        } catch (Exception e) {
            sector = null;
        } finally {
            render();
        }
    }

    void render() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        if (sector == null) {
            add(new Paragraph("Setor %s não encontrado".formatted(sectorName)));
        } else {
            add(new H2("RELATÓRIOS DE %s".formatted(sectorName.toUpperCase())));

            var reportsContainer = new HorizontalLayout();
            reportsContainer.setWrap(true);
            reportsContainer.setJustifyContentMode(JustifyContentMode.CENTER);
            reportsContainer.setMaxWidth(80, Unit.PERCENTAGE);
            var reports = reportsService.getReportsBySector(sector);
            reports.stream().map(ReportButton::new).forEach(reportsContainer::add);
            if (!reports.isEmpty())
                add(reportsContainer);
            if (reports.isEmpty())
                add(new Paragraph("Nenhum relatório encontrado para o setor %s".formatted(sectorName.toUpperCase())));
        }
        var button = new Button("Voltar para setores", event -> {
            getUI().ifPresent(ui -> ui.navigate(ReportsBySectorView.class));
        });
        button.getStyle().setCursor("pointer");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(button);
    }
}
