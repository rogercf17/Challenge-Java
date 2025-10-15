package com.example.estoque.dasa.repository;

import com.example.estoque.dasa.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    List<Equipamento> findBySetor(String setor);
    Equipamento findByNumeroSerie(String numeroSerie);
}
