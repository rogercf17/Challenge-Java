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
        return "Descatável "+getNome()+" Tipo "+tipoDescartavel+" - ID: "+getId()+
                "\nUso Previsto: "+usoPrevisto+"\nCategoria de risco: "+categoriaRisco+
                "\nDescartado Após uso? "+descartadoAposUso+
                "\nEstá dentro da validade? "+estaDentroValidade();
    }
}
