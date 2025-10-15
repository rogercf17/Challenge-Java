package com.example.estoque.dasa.service;

import com.example.estoque.dasa.model.Equipamento;
import com.example.estoque.dasa.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {
    private final EquipamentoRepository repository;

    public EquipamentoService(EquipamentoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Equipamento> listar() {
        return repository.findAll();
    }

    @Transactional
    public Equipamento adicionar(Equipamento equipamento) {
        return repository.save(equipamento);
    }

    @Transactional
    public Optional<Equipamento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Optional<Equipamento> atualizar(Long id, Equipamento eAtualizado) {
        return repository.findById(id)
                .map(equipamento -> {
                    equipamento.setNome(eAtualizado.getNome());
                    equipamento.setFabricante(eAtualizado.getFabricante());
                    equipamento.setQuantidade(eAtualizado.getQuantidade());
                    equipamento.setPrecoUnitario(eAtualizado.getPrecoUnitario());
                    equipamento.setCategoria(eAtualizado.getCategoria());
                    equipamento.setNumeroSerie(eAtualizado.getNumeroSerie());
                    equipamento.setSetor(eAtualizado.getSetor());
                    equipamento.setDataAquisicao(eAtualizado.getDataAquisicao());
                    equipamento.setUltimaManutencao(eAtualizado.getUltimaManutencao());
                    equipamento.setVidaUtilMeses(eAtualizado.getVidaUtilMeses());
                    equipamento.setEmUso(eAtualizado.isEmUso());
                    return repository.save(equipamento);
                });
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<Equipamento> listarEquipamentosPorSetor(String setor) {
        return repository.findBySetor(setor);
    }
    public Equipamento listarPorNumeroSerie(String numeroSerie) {
        return repository.findByNumeroSerie(numeroSerie);
    }
}
