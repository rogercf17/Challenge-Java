package Challenge.Model;

import java.util.Date;

public abstract class Produto {
    private int id;
    private String nome;
    private int quantidade;
    private String categoria;
    private Date validade;

    public Produto(int id, String nome, int quantidade, String categoria, Date validade) {
        this.id= id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.validade = validade;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getValidade() {
        return validade;
    }
    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public abstract boolean estaVencido();

    public void adicionarQuantidade(int quantidadeAMais) {
        this.quantidade += quantidadeAMais;
    }
    public void adicionarQuantidade(int quantidadeAMais, String lote) {
        this.quantidade += quantidadeAMais;
        System.out.println("Adicionando "+quantidadeAMais+" unidades do lote "+lote);
    }
}
