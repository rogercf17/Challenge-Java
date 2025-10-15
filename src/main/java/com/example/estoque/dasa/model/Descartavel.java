package com.example.estoque.dasa.model;

import com.example.estoque.dasa.util.BooleanToCharConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "DESCARTAVEL")
public class Descartavel extends Produto {
    @NotBlank(message = "Material não pode ser vazio") @Column(name = "MATERIAL", length = 255)
    private String material;
    @NotBlank(message = "Uso previsto não pode ser vazio") @Column(name = "USO_PREVISTO", length = 255)
    private String usoPrevisto;
    @NotNull(message = "Data de Validade não pode ser nula") @Column(name = "DATA_VALIDADE")
    private LocalDateTime dataValidade;
    @Column(name = "ESTERELIZADO") @Convert(converter = BooleanToCharConverter.class)
    private boolean esterelizado;
    @Column(name = "DESCARTADO_APOS_USO") @Convert(converter = BooleanToCharConverter.class)
    private boolean descartadoAposUso;
    @Enumerated(EnumType.STRING) @Column(name = "TIPO_DESCARTAVEL", length = 100)
    private TipoDescartavel tipoDescartavel;
    @Enumerated(EnumType.STRING) @Column(name = "CATEGORIA_RISCO", length = 100)
    private CategoriaRisco categoriaRisco;

    public Descartavel() {}

    public Descartavel(Long id, String nome, String fabricante,
                       int quantidade, double precoUnitario,
                       String material, String usoPrevisto, LocalDateTime dataValidade,
                       boolean esterelizado, boolean descartadoAposUso, TipoDescartavel tipoDescartavel,
                       CategoriaRisco categoriaRisco) {
        super(id, nome, fabricante, quantidade, precoUnitario, "Descartável");
        this.material = material;
        this.usoPrevisto = usoPrevisto;
        this.dataValidade = dataValidade;
        this.esterelizado = esterelizado;
        this.descartadoAposUso = descartadoAposUso;
        this.tipoDescartavel = tipoDescartavel;
        this.categoriaRisco = categoriaRisco;
    }

    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }

    public String getUsoPrevisto() {
        return usoPrevisto;
    }
    public void setUsoPrevisto(String usoPrevisto) {
        this.usoPrevisto = usoPrevisto;
    }

    public LocalDateTime getDataValidade() {
        return dataValidade;
    }
    public void setDataValidade(LocalDateTime dataValidade) {
        this.dataValidade = dataValidade;
    }

    public boolean isEsterelizado() {
        return esterelizado;
    }
    public void setEsterelizado(boolean esterelizado) {
        this.esterelizado = esterelizado;
    }

    public boolean isDescartadoAposUso() {
        return descartadoAposUso;
    }
    public void setDescartadoAposUso(boolean descartadoAposUso) {
        this.descartadoAposUso = descartadoAposUso;
    }

    public TipoDescartavel getTipoDescartavel() {
        return tipoDescartavel;
    }
    public void setTipoDescartavel(TipoDescartavel tipoDescartavel) {
        this.tipoDescartavel = tipoDescartavel;
    }

    public CategoriaRisco getCategoriaRisco() {
        return categoriaRisco;
    }
    public void setCategoriaRisco(CategoriaRisco categoriaRisco) {
        this.categoriaRisco = categoriaRisco;
    }

    public boolean estaDentroValidade() {
        return dataValidade != null && dataValidade.isAfter(LocalDateTime.now());
    }
}
