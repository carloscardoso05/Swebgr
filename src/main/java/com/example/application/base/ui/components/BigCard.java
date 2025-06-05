package com.example.application.base.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

public class BigCard extends VerticalLayout {
    public BigCard() {
        addClassNames(FontSize.XLARGE, TextAlignment.CENTER, TextColor.PRIMARY_CONTRAST, Background.PRIMARY, FontWeight.BOLD, BorderRadius.MEDIUM);
        setJustifyContentMode(JustifyContentMode.BETWEEN);
        setAlignItems(Alignment.CENTER);
    }

    public BigCard(Component... components) {
        this();
        add(components);
    }

    public BigCard(String text) {
        this();
        add(text);
    }
}
