package com.example.estoque.dasa.service;

import com.example.estoque.dasa.model.Remedio;
import com.example.estoque.dasa.repository.RemedioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RemedioService {
    private final RemedioRepository repository;

    public RemedioService(RemedioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Remedio> listar() {
        return repository.findAll();
    }

    @Transactional
    public Remedio adicionar(Remedio remedio) {
        return repository.save(remedio);
    }

    @Transactional
    public Optional<Remedio> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Optional<Remedio> atualizar(Long id, Remedio rAtualizado) {
        return repository.findById(id)
                .map(remedio -> {
                    remedio.setNome(rAtualizado.getNome());
                    remedio.setFabricante(rAtualizado.getFabricante());
                    remedio.setQuantidade(rAtualizado.getQuantidade());
                    remedio.setPrecoUnitario(rAtualizado.getPrecoUnitario());
                    remedio.setCategoria(rAtualizado.getCategoria());
                    remedio.setLote(rAtualizado.getLote());
                    remedio.setPrincipioAtivo(rAtualizado.getPrincipioAtivo());
                    remedio.setFormaFarmaceutica(rAtualizado.getFormaFarmaceutica());
                    remedio.setViaAdministracao(rAtualizado.getViaAdministracao());
                    remedio.setDataFabricacao(rAtualizado.getDataFabricacao());
                    remedio.setDataValidade(rAtualizado.getDataValidade());
                    remedio.setControlado(remedio.isControlado());
                    return repository.save(remedio);
                });
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<Remedio> listarRemediosControlados(boolean controlado) {
        return repository.findByControlado(controlado);
    }
    public List<Remedio> listarPorPrincipioAtivo(String principioAtivo) {
        return repository.findByPrincipioAtivo(principioAtivo);
    }
}
