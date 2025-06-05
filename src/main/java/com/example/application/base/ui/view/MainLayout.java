package com.example.application.base.ui.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.lumo.LumoUtility.Background;

@Layout
public class MainLayout extends AppLayout {
    public MainLayout() {
        var streamResource = new StreamResource("fadesp_logo_horizontal.png", () -> getClass().getResourceAsStream("/assets/fadesp_logo_horizontal.png"));

        var image = new Image(streamResource, "Logo Fadesp");
        addToNavbar(image);
        addToNavbar(new H1("SISTEMA WEB DE GESTÃO DE RELATORIO - SWEBGR"));
        addClassNames(Background.PRIMARY_10);
    }
}
