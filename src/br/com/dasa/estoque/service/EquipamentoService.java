package br.com.dasa.estoque.service;

import br.com.dasa.estoque.dao.EquipamentoDAO;
import br.com.dasa.estoque.model.Equipamento;
import java.time.LocalDateTime;
import java.sql.*;
import java.util.*;

public class EquipamentoService implements ProdutoService<Equipamento> {
    private EquipamentoDAO equipamentoDAO;

    public EquipamentoService(Connection connection) {
        this.equipamentoDAO = new EquipamentoDAO(connection);
    }

    @Override
    public void adicionar(Equipamento equipamento) {
        try {
            equipamentoDAO.inserir(equipamento);
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar Equipamento: "+e.getMessage());
        }
    }

    @Override
    public void remover(Long id) {
        try {
            equipamentoDAO.remover(id);
        } catch (SQLException e) {
            System.out.println("Erro ao remover Equipamento: "+e.getMessage());
        }
    }

    @Override
    public void atualizar(Equipamento equipamento) {
        try {
            equipamentoDAO.atualizar(equipamento);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Equipamento: " + e.getMessage());
        }
    }

    @Override
    public Equipamento buscarPorId(Long id) {
        try {
            return equipamentoDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar equipamento por id: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Equipamento> buscarPorNome(String nome) {
        List<Equipamento> equipamentosEncontrados = new ArrayList<>();

        try {
            for (Equipamento equipamento : equipamentoDAO.buscarTodos()) {
                if (equipamento.getNome().equalsIgnoreCase(nome)) {
                    equipamentosEncontrados.add(equipamento);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar equipamentos por nome: " + e.getMessage());
        }

        return equipamentosEncontrados;
    }

    @Override
    public List<Equipamento> listarTodos() {
        try {
            return equipamentoDAO.buscarTodos();
        } catch (SQLException e) {
            System.out.println("Erro ao listar equipamentos: " + e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public double calcularTotalEstoque() {
        double totalEstoque = 0;

        try {
            for (Equipamento equipamento : equipamentoDAO.buscarTodos()) {
                totalEstoque += equipamento.getQuantidade() * equipamento.getPrecoUnitario();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao calcular total do estoque: " + e.getMessage());
        }

        return totalEstoque;
    }

    public List<Equipamento> listarEquipamentosParaManutencao() {
        List<Equipamento> equipamentosParaManutencao = new ArrayList<>();

        try {
            for (Equipamento equipamento : equipamentoDAO.buscarTodos()) {
                if (equipamento.precisaManutencao()) {
                    equipamentosParaManutencao.add(equipamento);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erros ao listar equipamentos para manutenção: " + e.getMessage());
        }

        return equipamentosParaManutencao;
    }

    public List<Equipamento> listarPorSetor(String setor) {
        List<Equipamento> equipamentosEncontrados = new ArrayList<>();

        try {
            for (Equipamento equipamento : equipamentoDAO.buscarTodos()) {
                if (equipamento.getSetor().equalsIgnoreCase(setor)) {
                    equipamentosEncontrados.add(equipamento);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar equipamentos por setor: " + e.getMessage());
        }

        return equipamentosEncontrados;
    }

    public List<Equipamento> listarEquipamentosForaVidaUtil() {
        List<Equipamento> equipamentosEncontrados = new ArrayList<>();

        try {
            for (Equipamento equipamento : equipamentoDAO.buscarTodos()) {
                LocalDateTime fimVidaUtil = equipamento.getDataAquisicao().plusMonths(equipamento.getVidaUtilMeses());

                if (fimVidaUtil.isBefore(LocalDateTime.now())) {
                    equipamentosEncontrados.add(equipamento);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar equipamentos fora da vida útil: " + e.getMessage());
        }

        return equipamentosEncontrados;
    }
}
