package com.example.application.statistics.ui.view;

import com.example.application.statistics.services.StatisticService;
import com.example.application.statistics.ui.components.StatisticCard;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Grid;

@Route("")
public class StatisticsView extends VerticalLayout {
    public StatisticsView(StatisticService statisticService) {
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        var statisticsContainer = new Div();
        statisticsContainer.addClassNames(Display.GRID, Gap.MEDIUM, Grid.Column.COLUMNS_3);
        var statistics = statisticService.findAll();
        statistics.stream().map(StatisticCard::new).forEach(statisticsContainer::add);
        
        var button = new Button("Relatórios");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(new H2("Estatísticas"));
        add(statisticsContainer);
        add(button);
    }
}
