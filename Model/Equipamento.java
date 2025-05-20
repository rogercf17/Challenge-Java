package Challenge.Model;

import java.time.LocalDate;
import java.util.Date;

public class Equipamento extends Produto {
    private String numeroSerie;
    private Date dataManutencao;

    public Equipamento(int id, String nome, int quantidade, String categoria, String numeroSerie, Date dataManutencao) {
        super(id, nome, quantidade, categoria);
        this.numeroSerie = numeroSerie;
        this.dataManutencao = dataManutencao;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Date getDataManutencao() {
        return dataManutencao;
    }
    public void setDataManutencao(Date dataManutencao) {
        this.dataManutencao = dataManutencao;
    }

    @Override
    public boolean estaVencido() {
        return false; // AINDA NÃO IMPLEMENTADO CORRETAMENTE
    }

    @Override
    public void exibirInfos() {
        super.exibirInfos();
        System.out.println("Número de série: "+numeroSerie+"\nData de manutenção: "+dataManutencao);
    }
}
