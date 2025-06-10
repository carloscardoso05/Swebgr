package com.example.application.statistics.ui.components;

import com.example.application.base.ui.components.BigCard;
import com.example.application.statistics.domain.Statistic;
import com.vaadin.flow.component.html.Paragraph;

public class StatisticCard extends BigCard {
    public StatisticCard(Statistic statistic) {
        add(new Paragraph(statistic.name()));
        add(new Paragraph(String.valueOf(statistic.value())));
    }
    
}
