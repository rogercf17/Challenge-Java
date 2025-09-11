package br.com.dasa.estoque.model;

import java.util.Objects;

public abstract class Produto {
    private Long id;
    private String nome;
    private String fabricante;
    private int quantidade;
    private double precoUnitario;
    private String categoria;

    protected Produto() {}

    public Produto(Long id, String nome, String fabricante,
                   int quantidade, double precoUnitario,
                   String categoria) {
        this.id = id;
        this.nome = nome;
        this.fabricante = fabricante;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        if (nome.isBlank() || nome == null) {
            throw new IllegalArgumentException("Nome não pode ser vazio ou nulo!");
        }
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        if (fabricante.isBlank() || fabricante == null) {
            throw new IllegalArgumentException("Fabricante não pode ser vazio ou nulo");
        }
        this.fabricante = fabricante;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade não pode ser igual ou menor que zero!");
        }
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }
    public void setPrecoUnitario(double precoUnitario) {
        if (precoUnitario <= 0) {
            throw new IllegalArgumentException("Quantidade não pode ser igual ou menor que zero!");
        }
        this.precoUnitario = precoUnitario;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return quantidade == produto.quantidade && Double.compare(precoUnitario, produto.precoUnitario) == 0 && Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome) && Objects.equals(fabricante, produto.fabricante) && Objects.equals(categoria, produto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
