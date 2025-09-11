package br.com.dasa.estoque.model;

import br.com.dasa.estoque.util.DateParsing;
import java.time.LocalDateTime;

public class Equipamento extends Produto {
    private String numeroSerie;
    private String setor;
    private LocalDateTime dataAquisicao;
    private LocalDateTime ultimaManutencao;
    private int vidaUtilMeses;
    private boolean emUso;

    public Equipamento(Long id, String nome,
                       String fabricante, int quantidade,
                       double precoUnitario, String numeroSerie, String setor,
                       LocalDateTime dataAquisicao, LocalDateTime ultimaManutencao,
                       int vidaUtilMeses, boolean emUso) {
        super(id, nome, fabricante, quantidade, precoUnitario,"Equipamento");
        this.numeroSerie = numeroSerie;
        this.setor = setor;
        this.dataAquisicao = dataAquisicao;
        this.ultimaManutencao = ultimaManutencao;
        this.vidaUtilMeses = vidaUtilMeses;
        this.emUso = emUso;
    }

    public Equipamento(Long id, String nome,
                       String fabricante, int quantidade,
                       double precoUnitario,
                       String numeroSerie, String setor,
                       String dataAquisicao, String ultimaManutencao,
                       int vidaUtilMeses, boolean emUso) {
        this(id, nome, fabricante, quantidade, precoUnitario,
                numeroSerie, setor, DateParsing.parseDateTime(dataAquisicao),
                DateParsing.parseDateTime(ultimaManutencao), vidaUtilMeses,
                emUso);
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
        if (vidaUtilMeses <= 0) {
            throw new IllegalArgumentException("Vida útil do equipamento não pode ser igual ou menor que zero!");
        }
        this.vidaUtilMeses = vidaUtilMeses;
    }

    public boolean isEmUso() {
        return emUso;
    }
    public void setEmUso(boolean emUso) {
        this.emUso = emUso;
    }

    // Segunda regra de negócio para verifcar se o equipamento precisa ou não de manutenção
    public boolean precisaManutencao() {
        return ultimaManutencao.plusMonths(6).isBefore(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Equipamento: "+getNome()+" - ID: "+getId()+"" +
                "\nSetor: "+setor+"\nVida Útil: "+vidaUtilMeses+"\nEstá em uso? "+emUso+
                "\nPrecisa de manutenção? "+precisaManutencao();
    }
}
