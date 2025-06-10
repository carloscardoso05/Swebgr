package com.example.application.statistics.ui.view;

import com.example.application.reports.ui.views.ReportsBySectorView;
import com.example.application.statistics.services.StatisticService;
import com.example.application.statistics.ui.components.StatisticCard;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class StatisticsView extends VerticalLayout {
    public StatisticsView(StatisticService statisticService) {
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        var statisticsContainer = new HorizontalLayout();
        statisticsContainer.setWrap(true);
        statisticsContainer.setJustifyContentMode(JustifyContentMode.CENTER);
        statisticsContainer.setMaxWidth(80, Unit.PERCENTAGE);
        var statistics = statisticService.findAll();
        statistics.stream().map(StatisticCard::new).forEach(statisticsContainer::add);

        var button = new Button("Relatórios", new Icon(VaadinIcon.FILE_TEXT));
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.getStyle().setCursor("pointer");
        button.addClickListener(event -> {
            button.getUI().ifPresent(ui -> ui.navigate(ReportsBySectorView.class));
        });

        add(new H2("Estatísticas"));

        add(statisticsContainer);
        add(button);
    }
}
