package com.example.estoque.dasa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Objects;

@MappedSuperclass
public abstract class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome é obrigatório") @Column(name = "NOME", length = 255)
    private String nome;
    @NotBlank(message = "Fabricante é obrigatório") @Column(name = "FABRICANTE", length = 255)
    private String fabricante;
    @PositiveOrZero(message = "Quantidade tem que ser maior que zero") @Column(name = "QUANTIDADE")
    private int quantidade;
    @PositiveOrZero(message = "Preço Unitário tem que ser maior que zero") @Column(name = "PRECO_UNITARIO")
    private double precoUnitario;
    @NotBlank(message = "Categoria é obrigatório") @Column(name = "CATEGORIA")
    private String categoria;

    public Produto() {}

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

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;
        return Objects.equals(id, produto.id);
    }
}
