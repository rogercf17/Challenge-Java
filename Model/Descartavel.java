package Challenge.Model;

import java.time.LocalDate;

public class Descartavel extends Produto {
    private String material;
    private boolean esteril;

    public Descartavel(String id, String nome, int quantidade, String categoria, String material, boolean esteril) {
        super(id, nome, quantidade, categoria);
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
        return false;
    }

    @Override
    public void exibirInfos() {
        super.exibirInfos();
        String eEsteril = esteril ? "sim" : "não";
        System.out.println("Material: "+material+"\nÉ esteril: "+eEsteril);
    }
}
