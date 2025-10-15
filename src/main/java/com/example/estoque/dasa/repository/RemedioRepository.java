package com.example.estoque.dasa.repository;

import com.example.estoque.dasa.model.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RemedioRepository extends JpaRepository<Remedio, Long> {
    List<Remedio> findByControlado(boolean controlado);
    List<Remedio> findByPrincipioAtivo(String principioAtivo);
}
