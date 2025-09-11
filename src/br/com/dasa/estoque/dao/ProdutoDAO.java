package br.com.dasa.estoque.dao;

import java.sql.SQLException;
import java.util.List;

public interface ProdutoDAO<T> {
    void inserir(T produto) throws SQLException;
    void atualizar(T produto) throws SQLException;
    void remover(Long id) throws SQLException;
    T buscarPorId(Long id) throws SQLException;
    List<T> buscarTodos() throws SQLException;
}
