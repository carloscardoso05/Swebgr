package com.example.application.statistics.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.application.statistics.domain.Statistic;

@Service
public class StatisticService {
    public List<Statistic> findAll() {
        return List.of(
            new Statistic("Projetos Concluídos", 120),
            new Statistic("Projetos em Execução", 75),
            new Statistic("Projetos Cancelados", 30),
            new Statistic("Projetos Propostas", 50),
            new Statistic("Propostas Aprovadas", 45),
            new Statistic("Projetos em Prorrogação", 20)
        );
    }
}
