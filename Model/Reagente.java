package Challenge.Model;

import java.util.Date;

public class Reagente extends Produto {
    private double ph;
    private String temperaturaIdeal;

    public Reagente(int id, String nome, int quantidade, String categoria, Date validade, double ph, String temperaturaIdeal) {
        super(id,nome,quantidade,categoria,validade);
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
}
