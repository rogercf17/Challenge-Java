package Challenge.Model;

import java.time.LocalDate;

public class Reagente extends Produto {
    private double ph;
    private String temperaturaIdeal;

    public Reagente(String id, String nome, int quantidade, String categoria, double ph, String temperaturaIdeal) {
        super(id, nome, quantidade, categoria);
        this.ph = ph;
        this.temperaturaIdeal = temperaturaIdeal;
    }

    public double getPh() {
        return ph;
    }
    public void setPh(double ph) {
        this.ph = ph;
    }

    public String getTemperaturaIdeal() {
        return temperaturaIdeal;
    }
    public void setTemperaturaIdeal(String temperaturaIdeal) {
        this.temperaturaIdeal = temperaturaIdeal;
    }

    @Override
    public boolean estaVencido() {
        return false; // AINDA NÃO IMPLEMENTADO CORRETAMENTWE
    }

    @Override
    public void exibirInfos() {
        super.exibirInfos();
        System.out.println("Ph do reagente: "+ph+"\nTemperatura ideal do reagente: "+temperaturaIdeal);
    }
}
