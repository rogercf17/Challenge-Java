package com.example.estoque.dasa.controller;

import com.example.estoque.dasa.model.Equipamento;
import com.example.estoque.dasa.service.EquipamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {
    private final EquipamentoService service;

    public EquipamentoController(EquipamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Equipamento>> listarEquipamentos() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<Equipamento> adicionar(@Valid @RequestBody Equipamento equipamento) {
        return ResponseEntity.ok(service.adicionar(equipamento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizar(@PathVariable Long id,
                                                 @RequestBody Equipamento equipamento) {
        return service.atualizar(id, equipamento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar-por-setor/{setor}")
    public ResponseEntity<List<Equipamento>> listarPorNumeroSerie(@PathVariable String setor) {
        if (service.listarEquipamentosPorSetor(setor).isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(service.listarEquipamentosPorSetor(setor));
        }
    }

    @GetMapping("/buscar-por-num-serie/{numeroSerie}")
    public ResponseEntity<Equipamento> buscarPorNumeroSerie(@PathVariable String numeroSerie) {
        if (service.listarPorNumeroSerie(numeroSerie) == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(service.listarPorNumeroSerie(numeroSerie));
        }
    }
}
