package com.plataforma.cursos.controller;

import java.io.IOException;
import java.util.Map;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.plataforma.cursos.service.SeedService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dev")
@RequiredArgsConstructor
@Profile("dev")
public class SeedController {

    private final SeedService seedService;

    @GetMapping("/seed")
    public ResponseEntity<String> seed() throws IOException {

        seedService.seedMysql();

        return ResponseEntity.ok("Base MySQL populada.");
    }
}
