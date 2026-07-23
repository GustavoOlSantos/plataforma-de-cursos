package com.plataforma.cursos.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeedService {

    private final JdbcTemplate jdbcTemplate;
    private final ResourceLoader resourceLoader;

    @Transactional
    public void seedMysql() throws IOException {

        Resource resource = resourceLoader.getResource("classpath:db/seed.sql");

        String sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        for (String statement : sql.split(";")) {
            if (!statement.isBlank()) {
                jdbcTemplate.execute(statement);
            }
        }
    }
}