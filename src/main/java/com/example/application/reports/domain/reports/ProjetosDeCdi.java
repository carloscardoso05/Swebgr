package com.example.application.reports.domain.reports;

import com.example.application.reports.domain.Report;
import com.example.application.reports.domain.Sector;

public class ProjetosDeCdi extends Report {

    public ProjetosDeCdi() {
        super("Projetos de CDI", Sector.CDI, "projetos_de_cdi.jrxml");
    }

}
