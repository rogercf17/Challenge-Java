package com.example.estoque.dasa.repository;

import com.example.estoque.dasa.model.Descartavel;
import com.example.estoque.dasa.model.TipoDescartavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DescartavelRepository extends JpaRepository<Descartavel, Long> {
    List<Descartavel> findByTipoDescartavel(TipoDescartavel tipoDescartavel);
    List<Descartavel> findByEsterelizado(boolean esterelizado);
}
