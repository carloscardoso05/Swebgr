package com.example.application.reports.ui.components;

import com.example.application.base.ui.components.BigCard;
import com.example.application.reports.domain.Sector;
import com.vaadin.flow.component.html.Paragraph;

public class SectorCard extends BigCard {
    public SectorCard(Sector sector, int totalReports) {
        add(new Paragraph(sector.label));
        add(new Paragraph(String.valueOf(totalReports)));
        addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate("reports-by-sector/" + sector.name()));
        });
    }
}
