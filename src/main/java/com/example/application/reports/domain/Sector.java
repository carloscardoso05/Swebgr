package com.example.application.reports.domain;

public enum Sector {
    PROJETOS("Projetos"),
    CDI("CDI"),
    COMPRAS("Compras");

    public final String label;

    Sector(String label) {
        this.label = label;
    }
}
