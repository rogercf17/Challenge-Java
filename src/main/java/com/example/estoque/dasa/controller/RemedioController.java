package com.example.estoque.dasa.controller;

import com.example.estoque.dasa.model.Remedio;
import com.example.estoque.dasa.service.RemedioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/remedios")
public class RemedioController {
    private final RemedioService service;

    public RemedioController(RemedioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Remedio>> listarRemedios() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<Remedio> adicionar(@Valid @RequestBody Remedio remedio) {
        return ResponseEntity.ok(service.adicionar(remedio));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Remedio> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Remedio> atualizar(@PathVariable Long id,
                                             @RequestBody Remedio remedio) {
        return service.atualizar(id, remedio)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar-remedios-controlados/{controlado}")
    public ResponseEntity<List<Remedio>> listarRemediosControlados(@PathVariable boolean controlado) {
        if (service.listarRemediosControlados(controlado).isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(service.listarRemediosControlados(controlado));
        }
    }

    @GetMapping("/listar-por-principio-ativo/{principioAtivo}")
    public ResponseEntity<List<Remedio>> listarPorPrincipioAtivo(@PathVariable String principioAtivo) {
        if (service.listarPorPrincipioAtivo(principioAtivo).isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(service.listarPorPrincipioAtivo(principioAtivo));
        }
    }
}
