package com.example.application.reports.domain;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.Assert;

import com.vaadin.flow.component.formlayout.FormLayout;

import jakarta.annotation.Nullable;

public abstract class Report {
    private static final ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    private final File file;
    private final Sector sector;
    private final String name;

    @Nullable
    public FormLayout getForm() {
        return null;
    }

    public Map<String, Object> getArguments() {
        return new HashMap<>();
    }

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

    public Report(String name, Sector sector, String fileName) {
        Assert.hasText(name, "Name must not be empty");
        Assert.isTrue(fileName.endsWith(".jrxml"), "File name must end with .jrxml");
        File file = null;
        try {
            List<File> found = Arrays.stream(resolver.getResources("classpath:**/" + fileName)).filter(Resource::isFile)
                    .map(resource -> {
                        try {
                            return resource.getFile();
                        } catch (IOException e) {
                            throw new IllegalArgumentException("Error accessing resource: " + resource, e);
                        }
                    }).toList();
            if (found.size() != 1) {
                throw new RuntimeException(
                        "Expected exactly one file for name '" + fileName + "', but found: " + found.size());
            }
            file = found.getFirst();
        } catch (IOException e) {
            throw new IllegalArgumentException("Error loading file: " + fileName, e);
        }

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
