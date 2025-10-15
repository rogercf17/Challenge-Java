package com.example.estoque.dasa.model;

import com.example.estoque.dasa.util.BooleanToCharConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "EQUIPAMENTO")
public class Equipamento extends Produto {
    @NotBlank(message = "Número de Série não pode ser vazio") @Column(name = "NUMERO_SERIE", length = 100)
    private String numeroSerie;
    @NotBlank(message = "Setor não pode ser vazio") @Column(name = "SETOR", length = 100)
    private String setor;
    @NotNull(message = "Data de Aquisição não pode ser vazio") @Column(name = "DATA_AQUISICAO")
    private LocalDateTime dataAquisicao;
    @NotNull(message = "Última Manutenção não pode ser vazio") @Column(name = "ULTIMA_MANUTENCAO")
    private LocalDateTime ultimaManutencao;
    @PositiveOrZero(message = "Vida Útil tem que ser maior que zero") @Column(name = "VIDA_UTIL_MESES")
    private int vidaUtilMeses;
    @Column(name = "EM_USO") @Convert(converter = BooleanToCharConverter.class)
    private boolean emUso;

    public Equipamento() {}

    public Equipamento(Long id, String nome, String fabricante,
                       int quantidade, double precoUnitario,
                       String numeroSerie, String setor, LocalDateTime dataAquisicao,
                       LocalDateTime ultimaManutencao, int vidaUtilMeses, boolean emUso) {
        super(id, nome, fabricante, quantidade, precoUnitario, "Equipamento");
        this.numeroSerie = numeroSerie;
        this.setor = setor;
        this.dataAquisicao = dataAquisicao;
        this.ultimaManutencao = ultimaManutencao;
        this.vidaUtilMeses = vidaUtilMeses;
        this.emUso = emUso;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getSetor() {
        return setor;
    }
    public void setSetor(String setor) {
        this.setor = setor;
    }

    public LocalDateTime getDataAquisicao() {
        return dataAquisicao;
    }
    public void setDataAquisicao(LocalDateTime dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public LocalDateTime getUltimaManutencao() {
        return ultimaManutencao;
    }
    public void setUltimaManutencao(LocalDateTime ultimaManutencao) {
        this.ultimaManutencao = ultimaManutencao;
    }

    public int getVidaUtilMeses() {
        return vidaUtilMeses;
    }
    public void setVidaUtilMeses(int vidaUtilMeses) {
        this.vidaUtilMeses = vidaUtilMeses;
    }

    public boolean isEmUso() {
        return emUso;
    }
    public void setEmUso(boolean emUso) {
        this.emUso = emUso;
    }

    public boolean precisaManutencao() {
        return ultimaManutencao != null && ultimaManutencao.plusMonths(6).isBefore(LocalDateTime.now());
    }
}
