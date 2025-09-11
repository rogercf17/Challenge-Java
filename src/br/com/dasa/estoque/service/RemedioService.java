package br.com.dasa.estoque.service;

import br.com.dasa.estoque.dao.RemedioDAO;
import br.com.dasa.estoque.model.Remedio;
import java.sql.*;
import java.util.*;

public class RemedioService implements ProdutoService<Remedio> {
    private RemedioDAO remedioDAO;

    public RemedioService(Connection connection) {
        this.remedioDAO = new RemedioDAO(connection);
    }

    @Override
    public void adicionar(Remedio remedio) {
        try {
            remedioDAO.inserir(remedio);
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar Remédio: "+e.getMessage());
        }
    }

    @Override
    public void remover(Long id) {
        try {
            remedioDAO.remover(id);
        } catch (SQLException e) {
            System.out.println("Erro ao remover remédio: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Remedio remedio) {
        try {
            remedioDAO.atualizar(remedio);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar remédio: " + e.getMessage());
        }
    }

    @Override
    public Remedio buscarPorId(Long id) {
        try {
            return remedioDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar remédio por id: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Remedio> buscarPorNome(String nome) {
        List<Remedio> remediosEncontrados =new ArrayList<>();

        try {
            for (Remedio remedio : remedioDAO.buscarTodos()) {
                if (remedio.getNome().equalsIgnoreCase(nome)) {
                    remediosEncontrados.add(remedio);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar remédios por nome: " + e.getMessage());
        }

        return remediosEncontrados;
    }

    @Override
    public List<Remedio> listarTodos() {
        try {
            return remedioDAO.buscarTodos();
        } catch (SQLException e) {
            System.out.println("Erro ao listar remédios: " + e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public double calcularTotalEstoque() {
        double totalEstoque = 0;

        try {
            for (Remedio remedio : remedioDAO.buscarTodos()) {
                totalEstoque += remedio.getQuantidade() * remedio.getPrecoUnitario();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao calcular total do estoque: " + e.getMessage());
        }

        return totalEstoque;
    }

    public List<Remedio> listarVencidos() {
        List<Remedio> vencidos = new ArrayList<>();

        try {
            for (Remedio remedio : remedioDAO.buscarTodos()) {
                if (remedio.isVencido()) {
                    vencidos.add(remedio);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar remédios vencidos: " + e.getMessage());
        }

        return vencidos;
    }

    public List<Remedio> buscarPorPrincipioAtivo(String principioAtivo) {
        List<Remedio> remediosEncontrados = new ArrayList<>();

        try {
            for (Remedio remedio : remedioDAO.buscarTodos()) {
                if (remedio.getPrincipioAtivo().equalsIgnoreCase(principioAtivo)) {
                    remediosEncontrados.add(remedio);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar remédios por princípio ativo: " + e.getMessage());
        }

        return remediosEncontrados;
    }

    public List<Remedio> listarControlados() {
        List<Remedio> controlados = new ArrayList<>();

        try {
            for (Remedio remedio : remedioDAO.buscarTodos()) {
                if (remedio.isControlado()) {
                    controlados.add(remedio);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar remédios controlados: " + e.getMessage());
        }

        return controlados;
    }
}
