package com.example.application.reports.domain;

import java.io.File;
import java.util.Map;

import org.springframework.util.Assert;

import com.vaadin.flow.component.formlayout.FormLayout;

public abstract class Report {
    private final File file;
    private final Sector sector;
    private final String name;

    public abstract FormLayout getForm();

    public abstract Map<String, Object> getArguments();

    public Report(String name, Sector sector, File file) {
        Assert.hasText(name, "Name must not be empty");
        Assert.notNull(sector, "Sector must not be null");
        Assert.notNull(file, "File must not be null");
        Assert.isTrue(file.exists(), "File must exist");
        Assert.isTrue(file.isFile(), "File must be a regular file");
        this.file = file;
        this.sector = sector;
        this.name = name;
    }

    public int getId() {
        return file.hashCode();
    }

    public File getFile() {
        return file;
    }

    public Sector getSector() {
        return sector;
    }

    public String getName() {
        return name;
    }
}
