package com.example.application.reports.ui.views;

import java.util.Arrays;

import com.example.application.base.ui.components.BigCard;
import com.example.application.reports.domain.Sector;
import com.example.application.statistics.ui.view.StatisticsView;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("reports-by-sector")
public class ReportsBySectorView extends VerticalLayout {

    public ReportsBySectorView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        var sectorsContainer = new HorizontalLayout();
        sectorsContainer.setWrap(true);
        sectorsContainer.setJustifyContentMode(JustifyContentMode.CENTER);
        sectorsContainer.setMaxWidth(80, Unit.PERCENTAGE);
        var sectors = Sector.values();
        Arrays.stream(sectors).map(sector -> new BigCard(sector.label)).forEach(sectorsContainer::add);

        var button = new Button("Voltar para estatísticas", new Icon(VaadinIcon.BAR_CHART_H));
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.getStyle().setCursor("pointer");
        button.addClickListener(event -> {
            button.getUI().ifPresent(ui -> ui.navigate(StatisticsView.class));
        });

        add(new H2("Relatórios por setor"));

        add(sectorsContainer);
        add(button);
    }

}
