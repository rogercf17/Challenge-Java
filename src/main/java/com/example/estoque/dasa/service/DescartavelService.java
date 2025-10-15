package com.example.estoque.dasa.service;

import com.example.estoque.dasa.model.Descartavel;
import com.example.estoque.dasa.model.TipoDescartavel;
import com.example.estoque.dasa.repository.DescartavelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DescartavelService {
    private final DescartavelRepository repository;

    public DescartavelService(DescartavelRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Descartavel> listar() {
        return repository.findAll();
    }

    @Transactional
    public Descartavel adicionar(Descartavel descartavel) {
        return repository.save(descartavel);
    }

    @Transactional
    public Optional<Descartavel> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Optional<Descartavel> atualizar(Long id, Descartavel dAtualizado) {
        return repository.findById(id)
                .map(descartavel -> {
                    descartavel.setNome(dAtualizado.getNome());
                    descartavel.setFabricante(dAtualizado.getFabricante());
                    descartavel.setQuantidade(dAtualizado.getQuantidade());
                    descartavel.setPrecoUnitario(dAtualizado.getPrecoUnitario());
                    descartavel.setCategoria(dAtualizado.getCategoria());
                    descartavel.setMaterial(dAtualizado.getMaterial());
                    descartavel.setUsoPrevisto(dAtualizado.getUsoPrevisto());
                    descartavel.setDataValidade(dAtualizado.getDataValidade());
                    descartavel.setEsterelizado(dAtualizado.isEsterelizado());
                    descartavel.setDescartadoAposUso(dAtualizado.isDescartadoAposUso());
                    descartavel.setTipoDescartavel(dAtualizado.getTipoDescartavel());
                    descartavel.setCategoriaRisco(dAtualizado.getCategoriaRisco());
                    return repository.save(descartavel);
                });
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<Descartavel> listarPorTipoDescartavel(TipoDescartavel tipoDescartavel) {
        return repository.findByTipoDescartavel(tipoDescartavel);
    }
    public List<Descartavel> listarEsterelizados(boolean esterelizado) {
        return repository.findByEsterelizado(esterelizado);
    }
}
