package br.com.dasa.estoque.service;

import br.com.dasa.estoque.model.Produto;
import java.util.List;

public interface ProdutoService <T extends Produto> {
    void adicionar(T produto);
    void remover(Long id);
    void atualizar(T produto);
    T buscarPorId(Long id);
    List<T> buscarPorNome(String nome);
    List<T> listarTodos();
    double calcularTotalEstoque();
}
