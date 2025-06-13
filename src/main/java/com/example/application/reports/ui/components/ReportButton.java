package com.example.application.reports.ui.components;

import com.example.application.reports.domain.Report;
import com.example.application.reports.ui.views.ReportView;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility.Background;
import com.vaadin.flow.theme.lumo.LumoUtility.BorderRadius;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextAlignment;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

public class ReportButton extends VerticalLayout {
    public ReportButton(Report report) {
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        addClassNames(TextAlignment.CENTER, Background.PRIMARY, TextColor.PRIMARY_CONTRAST,
                FontWeight.BOLD, BorderRadius.SMALL, Padding.SMALL, FontSize.LARGE);
        getStyle().set("cursor", "pointer");
        setWidth(9, Unit.REM);
        setHeight(9, Unit.REM);
        addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate(ReportView.class, report.getId()));
        });
        add(new Paragraph(report.getName()));
    }
}
