package com.example.application.base.ui.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.lumo.LumoUtility.Background;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;

@Layout
@CssImport(value = "./themes/default/styles.css", themeFor = "vaadin-app-layout")
public class MainLayout extends AppLayout {
    public MainLayout() {
        var streamResource = new StreamResource("fadesp_logo_horizontal.png",
                () -> getClass().getResourceAsStream("/assets/fadesp_logo_horizontal.png"));

        var image = new Image(streamResource, "Logo Fadesp");
        // image.addClassNames(Width.XLARGE, "fadesp-logo");
        // addToNavbar(image);
        var h1 = new H1("SISTEMA WEB DE GESTÃO DE RELATORIO - SWEBGR");
        h1.addClassNames(TextColor.PRIMARY_CONTRAST, "swebgr-title");
        addToNavbar(h1);
        addClassNames(Background.PRIMARY_10);
    }
}
