package Challenge.Model;

public abstract class Produto {
    private String id;
    private String nome;
    private int quantidade;
    private String categoria;

    public Produto(String id, String nome, int quantidade, String categoria) {
        this.id= id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
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

    public abstract boolean estaVencido();

    public void adicionarQuantidade(int quantidadeAMais) {quantidade += quantidadeAMais;}
    public void retirarQuantidade(int quantidadeAMenos){quantidade -= quantidadeAMenos;}

    public void exibirInfos() {
        System.out.println(id+" - "+nome+"\nQuantidade em estoque: "+quantidade+"\nCategoria: "+categoria);
    }
}
