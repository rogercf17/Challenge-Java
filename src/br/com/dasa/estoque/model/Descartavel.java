package br.com.dasa.estoque.model;

import br.com.dasa.estoque.util.DateParsing;
import java.time.LocalDateTime;

public class Descartavel extends Produto {
    private String material;
    private String usoPrevisto;
    private LocalDateTime dataValidade;
    private boolean esterelizado;
    private boolean descartadoAposUso;
    private TipoDescartavel tipoDescartavel;
    private CategoriaRisco categoriaRisco;


    public Descartavel(Long id, String nome,
                       String fabricante, int quantidade,
                       double precoUnitario, String material,
                       String usoPrevisto, LocalDateTime dataValidade,
                       boolean esterelizado,
                       boolean descartadoAposUso, TipoDescartavel tipoDescartavel,
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

    public Descartavel(Long id, String nome,
                       String fabricante, int quantidade,
                       double precoUnitario, String material,
                       String usoPrevisto, String dataValidade,
                       boolean esterelizado, boolean descartadoAposUso,
                       TipoDescartavel tipoDescartavel,
                       CategoriaRisco categoriaRisco) {
        this(id, nome, fabricante, quantidade,
                precoUnitario, material,
                usoPrevisto, DateParsing.parseDateTime(dataValidade),
                esterelizado, descartadoAposUso,
                tipoDescartavel, categoriaRisco);
    }

    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        if (material.isBlank() || material == null) {
            throw new IllegalArgumentException("Material não pode ser vazio ou nulo!");
        }
        this.material = material;
    }

    public String getUsoPrevisto() {
        return usoPrevisto;
    }
    public void setUsoPrevisto(String usoPrevisto) {
        if (usoPrevisto.isBlank() || usoPrevisto == null) {
            throw new IllegalArgumentException("Uso Previsto não pode ser vazio ou nulo!");
        }
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
        return dataValidade.isAfter(LocalDateTime.now());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(getId()).append("\n")
                .append("Nome: ").append(getNome()).append("\n")
                .append("Fabricante: ").append(getFabricante()).append("\n")
                .append("Preço Unitário: R$ ").append(getPrecoUnitario()).append("\n")
                .append("Material: ").append(material).append("\n")
                .append("Uso Previsto: ").append(usoPrevisto).append("\n")
                .append("Validade: ").append(DateParsing.formatDateTime(dataValidade)).append("\n")
                .append("Esterilizado: ").append(esterelizado ? "Sim" : "Não").append("\n")
                .append("Descartado Após Uso: ").append(descartadoAposUso ? "Sim" : "Não").append("\n")
                .append("Tipo: ").append(tipoDescartavel).append("\n")
                .append("Categoria de Risco: ").append(categoriaRisco).append("\n")
                .append("Dentro da Validade: ").append(estaDentroValidade() ? "Sim" : "Não").append("\n");
        return sb.toString();
    }

}
