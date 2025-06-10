package com.example.application.statistics.services;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.application.statistics.domain.Statistic;

@Service
public class StatisticService {
    private final JdbcTemplate jdbcTemplate;

    public StatisticService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Statistic> findAll() {
        return jdbcTemplate.queryForList("""
                SELECT descricao, COUNT(*) AS total
                FROM (
                    SELECT
                        CASE
                            WHEN sit.codigo IN (10, 13) THEN 'Projetos concluídos'
                            WHEN sit.codigo = 2 THEN 'Projetos em execução'
                            WHEN sit.codigo IN (7, 8) THEN 'Projetos cancelados'
                            WHEN sit.codigo = 3 THEN 'Propostas'
                            WHEN sit.codigo = 14 THEN 'Propostas aprovadas'
                            WHEN sit.codigo = 11 THEN 'Projetos em prorrogação'
                            ELSE 'Outro'
                        END AS descricao
                    FROM dbo.convenio conv
                    INNER JOIN dbo.situacaoProjeto sit ON conv.CodSituacaoProjeto = sit.codigo
                    WHERE sit.codigo in (2, 3, 7, 8, 10, 11, 13, 14)
                ) AS statisticas
                GROUP BY descricao;""").stream().map(
                map -> new Statistic(
                        (String) map.get("descricao"),
                        (int) map.get("total")))
                .toList();
    }
}
