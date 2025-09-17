package br.com.dasa.estoque.model;

import br.com.dasa.estoque.util.DateParsing;

import java.time.LocalDateTime;

public class Remedio extends Produto {
    private String lote;
    private String principioAtivo;
    private String formaFarmaceutica;
    private String viaAdministracao;
    private LocalDateTime dataFabricacao;
    private LocalDateTime dataValidade;
    private boolean controlado;

    public Remedio(Long id, String nome,
                   String fabricante, int quantidade,
                   double precoUnitario, String lote, String principioAtivo,
                   String formaFarmaceutica, String viaAdministracao,
                   LocalDateTime dataFabricacao, LocalDateTime dataValidade,
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

    public Remedio(Long id, String nome,
                   String fabricante, int quantidade,
                   double precoUnitario,
                   String lote, String principioAtivo,
                   String formaFarmaceutica, String viaAdministracao,
                   String dataFabricacao, String dataValidade,
                   boolean controlado) {
        this(id, nome, fabricante, quantidade,
                precoUnitario, lote,
                principioAtivo, formaFarmaceutica, viaAdministracao,
                DateParsing.parseDateTime(dataFabricacao), DateParsing.parseDateTime(dataValidade),
                controlado);
    }

    public String getLote() {
        return lote;
    }
    public void setLote(String lote) {
        if (lote.isBlank() || lote == null) {
            throw new IllegalArgumentException("Lote não pode ser vazio ou nulo!");
        }
        this.lote = lote;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }
    public void setPrincipioAtivo(String principioAtivo) {
        if (principioAtivo.isBlank() || principioAtivo == null) {
            throw new IllegalArgumentException("Princípio ativo não pode ser vazio ou nulo!");
        }
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

    public boolean isControlado() {
        return controlado;
    }
    public void setControlado(boolean controlado) {
        this.controlado = controlado;
    }

    public LocalDateTime getDataValidade() {
        return dataValidade;
    }
    public void setDataValidade(LocalDateTime dataValidade) {
        this.dataValidade = dataValidade;
    }

    // Primeira regra de negócio para verificar se o remédio está vencido
    public boolean isVencido() {
        return dataValidade.isBefore(LocalDateTime.now());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(getId()).append("\n")
                .append("Nome: ").append(getNome()).append("\n")
                .append("Fabricante: ").append(getFabricante()).append("\n")
                .append("Lote: ").append(lote).append("\n")
                .append("Princípio Ativo: ").append(principioAtivo).append("\n")
                .append("Forma Farmacêutica: ").append(formaFarmaceutica).append("\n")
                .append("Via Administração: ").append(viaAdministracao).append("\n")
                .append("Data Fabricação: ").append(DateParsing.formatDateTime(dataFabricacao)).append("\n")
                .append("Validade: ").append(DateParsing.formatDateTime(dataValidade)).append("\n")
                .append("Controlado: ").append(controlado ? "Sim" : "Não").append("\n")
                .append("Vencido: ").append(isVencido() ? "Sim" : "Não").append("\n");
        return sb.toString();
    }

}
