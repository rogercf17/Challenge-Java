package Challenge.Model;

import java.util.Date;

public class Descartavel extends Produto {
    private String material;
    private boolean esteril;

    public Descartavel(int id, String nome, int quantidade, String categoria, Date validade, String material, boolean esteril) {
        super(id,nome,quantidade,categoria,validade);
        this.material = material;
        this.esteril = esteril;
    }

    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean isEsteril() {
        return esteril;
    }
    public void setEsteril(boolean esteril) {
        this.esteril = esteril;
    }

    @Override
    public boolean estaVencido() {
        return false; // AINDA NÃO IMPLEMENTADO CORRETAMENTWE
    }
}
