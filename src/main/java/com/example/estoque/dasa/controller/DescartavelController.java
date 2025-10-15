package com.example.estoque.dasa.controller;

import com.example.estoque.dasa.model.Descartavel;
import com.example.estoque.dasa.model.TipoDescartavel;
import com.example.estoque.dasa.service.DescartavelService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/descartaveis")
public class DescartavelController {
    private final DescartavelService service;

    public DescartavelController(DescartavelService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Descartavel>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<Descartavel> adicionar(@Valid @RequestBody Descartavel descartavel) {
        return ResponseEntity.ok(service.adicionar(descartavel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Descartavel> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Descartavel> atualizar(@PathVariable Long id,
                                                 @Valid @RequestBody Descartavel descartavel) {
        return service.atualizar(id, descartavel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar-esterelizados/{esterelizado}")
    public ResponseEntity<List<Descartavel>> listarEsterelizados(@PathVariable boolean esterelizado) {
        return ResponseEntity.ok(service.listarEsterelizados(esterelizado));
    }

    @GetMapping("/listar-por-tipo-descartavel/{tipoDescartavel}")
    public ResponseEntity<List<Descartavel>> listarPorTipoDescartavel(@PathVariable TipoDescartavel tipoDescartavel) {
        return ResponseEntity.ok(service.listarPorTipoDescartavel(tipoDescartavel));
    }
}
