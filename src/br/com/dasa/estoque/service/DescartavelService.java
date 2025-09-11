package br.com.dasa.estoque.service;

import br.com.dasa.estoque.model.Descartavel;
import br.com.dasa.estoque.dao.DescartavelDAO;
import br.com.dasa.estoque.model.CategoriaRisco;
import java.sql.*;
import java.util.*;

public class DescartavelService implements ProdutoService<Descartavel> {
    private DescartavelDAO descartavelDAO;

    public DescartavelService(Connection connection) {
        this.descartavelDAO = new DescartavelDAO(connection);
    }

    @Override
    public void adicionar(Descartavel descartavel) {
        try {
            descartavelDAO.inserir(descartavel);
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar Descartável: " + e.getMessage());
        }
    }

    @Override
    public void remover(Long id) {
        try {
            descartavelDAO.remover(id);
        } catch (SQLException e) {
            System.out.println("Erro ao remover Descartável: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Descartavel descartavel) {
        try {
            descartavelDAO.atualizar(descartavel);
        } catch (SQLException e) {
            System.out.println("Erro ao atulizar Descartável: " + e.getMessage());
        }
    }

    @Override
    public Descartavel buscarPorId(Long id) {
        try {
            return descartavelDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar descartável por id: " + e.getMessage());
        }

        return null;
    }
    @Override
    public List<Descartavel> buscarPorNome(String nome) {
        List<Descartavel> descartaveisEncontrados = new ArrayList<>();

        try {
            for (Descartavel descartavel : descartavelDAO.buscarTodos()) {
                if (descartavel.getNome().equalsIgnoreCase(nome)) {
                    descartaveisEncontrados.add(descartavel);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar descartável por nome: " + e.getMessage());
        }

        return descartaveisEncontrados;
    }

    @Override
    public List<Descartavel> listarTodos() {
        try {
            return descartavelDAO.buscarTodos();
        } catch (SQLException e) {
            System.out.println("Erro ao listar descartáveis: " + e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public double calcularTotalEstoque() {
        double totalEstoque = 0;

        try {
            for (Descartavel descartavel : descartavelDAO.buscarTodos()) {
                totalEstoque += descartavel.getQuantidade() * descartavel.getPrecoUnitario();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao calcular total do estoque: " + e.getMessage());
        }

        return totalEstoque;
    }

    public List<Descartavel> listarVencidos() {
        List<Descartavel> descartaveisVencidos = new ArrayList<>();

        try {
            for (Descartavel descartavel : descartavelDAO.buscarTodos()) {
                if (!descartavel.estaDentroValidade()) {
                    descartaveisVencidos.add(descartavel);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar descartáveis vencidos: " + e.getMessage());
        }

        return descartaveisVencidos;
    }

    public List<Descartavel> listarPorCategoriaRisco(CategoriaRisco categoriaRisco) {
        List<Descartavel> descartaveisEncontrados = new ArrayList<>();

        try {
            for (Descartavel descartavel : descartavelDAO.buscarTodos()) {
                if (descartavel.getCategoriaRisco().equals(categoriaRisco)) {
                    descartaveisEncontrados.add(descartavel);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar descartáveis por categoria de risco: "+ e.getMessage());
        }

        return descartaveisEncontrados;
    }

    public List<Descartavel> listarPorUsoPrevisto(String usoPrevisto) {
        List<Descartavel> descartaveisEncontrados = new ArrayList<>();

        try {
            for (Descartavel descartavel : descartavelDAO.buscarTodos()) {
                if (descartavel.getUsoPrevisto().equalsIgnoreCase(usoPrevisto)) {
                    descartaveisEncontrados.add(descartavel);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar descartáveis por uso previsto: "+ e.getMessage());
        }

        return descartaveisEncontrados;
    }
}
