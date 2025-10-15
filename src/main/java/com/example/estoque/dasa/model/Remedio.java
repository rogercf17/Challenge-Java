package com.example.estoque.dasa.model;

import com.example.estoque.dasa.util.BooleanToCharConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "REMEDIO")
public class Remedio extends Produto {
    @NotBlank(message = "Lote não pode ser vazio") @Column(name = "LOTE", length = 100)
    private String lote;
    @NotBlank(message = "Princípio Ativo não pode ser vazio") @Column(name = "PRINCIPIO_ATIVO", length = 255)
    private String principioAtivo;
    @NotBlank(message = "Forma Farmaceûtica não pode ser vazio") @Column(name = "FORMA_FARMACEUTICA", length = 100)
    private String formaFarmaceutica;
    @NotBlank(message = "Via Administração não pode ser vazio") @Column(name = "VIA_ADMINISTRACAO", length = 100)
    private String viaAdministracao;
    @NotNull(message = "Data de Fabricação não pode ser vazio") @Column(name = "DATA_FABRICACAO")
    private LocalDateTime dataFabricacao;
    @NotNull(message = "Data de Validade não pode ser vazio") @Column(name = "DATA_VALIDADE")
    private LocalDateTime dataValidade;
    @Column(name = "CONTROLADO") @Convert(converter = BooleanToCharConverter.class)
    private boolean controlado;

    public Remedio() {}

    public Remedio(Long id, String nome, String fabricante,
                   int quantidade, double precoUnitario,
                   String lote, String principioAtivo, String formaFarmaceutica,
                   String viaAdministracao, LocalDateTime dataFabricacao, LocalDateTime dataValidade,
                   boolean controlado) {
        super(id, nome, fabricante, quantidade, precoUnitario, "Remédio");
        this.lote = lote;
        this.principioAtivo = principioAtivo;
        this.formaFarmaceutica = formaFarmaceutica;
        this.viaAdministracao = viaAdministracao;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.controlado = controlado;
    }

    public String getLote() {
        return lote;
    }
    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }
    public void setPrincipioAtivo(String principioAtivo) {
        this.principioAtivo = principioAtivo;
    }

    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }
    public void setFormaFarmaceutica(String formaFarmaceutica) {
        this.formaFarmaceutica = formaFarmaceutica;
    }

    public String getViaAdministracao() {
        return viaAdministracao;
    }
    public void setViaAdministracao(String viaAdministracao) {
        this.viaAdministracao = viaAdministracao;
    }

    public LocalDateTime getDataFabricacao() {
        return dataFabricacao;
    }
    public void setDataFabricacao(LocalDateTime dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public LocalDateTime getDataValidade() {
        return dataValidade;
    }
    public void setDataValidade(LocalDateTime dataValidade) {
        this.dataValidade = dataValidade;
    }

    public boolean isControlado() {
        return controlado;
    }
    public void setControlado(boolean controlado) {
        this.controlado = controlado;
    }

    public boolean isVencido() {
        return dataValidade != null && dataValidade.isBefore(LocalDateTime.now());
    }
}
