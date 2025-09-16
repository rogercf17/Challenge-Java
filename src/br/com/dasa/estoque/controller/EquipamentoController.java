package br.com.dasa.estoque.controller;

import br.com.dasa.estoque.model.Equipamento;
import br.com.dasa.estoque.service.EquipamentoService;
import java.sql.Connection;
import java.util.List;

public class EquipamentoController {
    private EquipamentoService equipamentoService;

    public EquipamentoController(Connection connection) {
        this.equipamentoService = new EquipamentoService(connection);
    }

    public void adicionarEquipamento(Equipamento equipamento) {
        try {
            equipamentoService.adicionar(equipamento);
            System.out.println("Equipamento adicionado com sucesso!");
        }catch (Exception e) {
            System.out.println("Erro ao adicionar equipamento: "+ e.getMessage());
        }
    }

    public void removerEquipamento(Long id) {
        try {
            equipamentoService.remover(id);
            System.out.println("Equipamento removido com sucesso!");
        }catch (Exception e) {
            System.out.println("Erro ao remover equipamento: "+ e.getMessage());
        }
    }

    public void atualizarEquipamento(Equipamento equipamento) {
        try {
            equipamentoService.atualizar(equipamento);
            System.out.println("Equipamento atualizado com sucesso!\n"+ equipamento);
        }catch (Exception e) {
            System.out.println("Erro ao atualizar equipamento: "+ e.getMessage());
        }
    }

    public void buscarEquipamentoPorId(Long id) {
        try {
            Equipamento equipamento = equipamentoService.buscarPorId(id);

            if (equipamento != null) {
                System.out.println("Equipamento encontrado:\n"+ equipamento);
            }else {
                System.out.println("Nenhum equipamento foi encontrado com ID: "+ id);
            }
        }catch (Exception e) {
            System.out.println("Erro ao buscar equipamento por ID: "+ e.getMessage());
        }
    }

    public void buscarEquipamentoPorNome(String nome) {
        try {
            List<Equipamento> equipamentoList = equipamentoService.buscarPorNome(nome);

            if (!equipamentoList.isEmpty()) {
                System.out.println("Equipamento encontrado:");
                equipamentoList.forEach(System.out::println);
            }else {
                System.out.println("Nenhum equipamento foi encontrado com nome: "+ nome);
            }
        }catch (Exception e) {
            System.out.println("Erro ao buscar equipamento por nome: "+ e.getMessage());
        }
    }

    public void listarTodosEquipamentos() {
        try {
            List<Equipamento> equipamentos = equipamentoService.listarTodos();

            if (!equipamentos.isEmpty()) {
                System.out.println("Equipamentos:");
                equipamentos.forEach(System.out::println);
            }else {
                System.out.println("Nenhum equipamento foi encontrado");
            }
        }catch (Exception e) {
            System.out.println("Erro ao listar equipamentos: "+ e.getMessage());
        }
    }

    public void calcularTotalEstoque() {
        try {
            double total = equipamentoService.calcularTotalEstoque();
            System.out.println("Total do estoque de equipamentos: R$ "+ String.format("%.2f", total));
        }catch (Exception e) {
            System.out.println("Erro ao calcular total do estoque: "+ e.getMessage());
        }
    }

    public void listarEquipamentosParaManutencao() {
        try {
            List<Equipamento> equipamentos = equipamentoService.listarEquipamentosParaManutencao();

            if (!equipamentos.isEmpty()) {
                System.out.println("Atenção Equipamentos para manutenção:");
                equipamentos.forEach(System.out::println);
            }else {
                System.out.println("Nenhum equipamento precisa de manutenção");
            }
        }catch (Exception e) {
            System.out.println("Erro ao listar equipamentos para a manutenção");
        }
    }

    public void listarEquipamentosPorSetor(String setor) {
        try {
            List<Equipamento> equipamentos = equipamentoService.listarPorSetor(setor);

            if (!equipamentos.isEmpty()) {
                System.out.println("Equipamentos do setor de "+ setor);
                equipamentos.forEach(System.out::println);
            }else {
                System.out.println("Nenhum equipamento foi encontrado do setor "+ setor);
            }
        }catch (Exception e) {
            System.out.println("Erro ao listar equipamentos por setor");
        }
    }

    public void listarEquipamentosForaVidaUtil() {
        try {
            List<Equipamento> equipamentos = equipamentoService.listarEquipamentosForaVidaUtil();

            if (!equipamentos.isEmpty()) {
                System.out.println("Atenção equipamentos fora da vida útil:");
                equipamentos.forEach(System.out::println);
            }else {
                System.out.println("Nenhum equipamento foi encontrado fora da vida útil");
            }
        }catch (Exception e) {
            System.out.println("Erro ao listar equipamentos fora da vida útil: "+ e.getMessage());
        }
    }
}