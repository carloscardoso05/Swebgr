package com.example.application.base.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility.Background;
import com.vaadin.flow.theme.lumo.LumoUtility.BorderRadius;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.TextAlignment;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

public class BigCard extends VerticalLayout {
    public BigCard() {
        addClassNames(FontSize.XLARGE, TextAlignment.CENTER, TextColor.PRIMARY_CONTRAST,
                Background.PRIMARY, FontWeight.BOLD, BorderRadius.MEDIUM);
        setJustifyContentMode(JustifyContentMode.BETWEEN);
        setAlignItems(Alignment.CENTER);
        var baseWidth = 20;
        setWidth(baseWidth, Unit.REM);
        setHeight(baseWidth / 2, Unit.REM);
        getStyle()
                .setCursor("pointer")
                .set("user-select", "none");
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
