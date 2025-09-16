package br.com.dasa.estoque.controller;

import br.com.dasa.estoque.model.Remedio;
import br.com.dasa.estoque.service.RemedioService;

import java.sql.Connection;
import java.util.List;

public class RemedioController {
    private RemedioService remedioService;

    public RemedioController(Connection connection) {
        this.remedioService = new RemedioService(connection);
    }

    public void adicionarRemedio(Remedio remedio) {
        try {
            remedioService.adicionar(remedio);
            System.out.println("Remédio cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar remédio: "+ e.getMessage());
        }
    }

    public void removerRemedio(Long id) {
        try {
            remedioService.remover(id);
            System.out.println("Remédio removido com sucesso!");
        }catch (Exception e) {
            System.out.println("Erro ao remover remédio: "+ e.getMessage());
        }
    }

    public void atualizarRemedio(Remedio remedio) {
        try {
            remedioService.atualizar(remedio);
            System.out.println("Remédio atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar remédio: " + e.getMessage());
        }
    }

    public void buscarRemedioPorId(Long id) {
        try {
            Remedio remedio = remedioService.buscarPorId(id);
            if (remedio != null) {
                System.out.println("Remédio encontrado:\n"+ remedio);
            }else {
                System.out.println("Nenhum remédio foi encontrado com o id: "+ id);
            }
        }catch (Exception e) {
            System.out.println("Erro ao buscar remédio por ID: "+ e.getMessage());
        }
    }

    public void buscarRemedioPorNome(String nome) {
        try {
            List<Remedio> remedioList = remedioService.buscarPorNome(nome);

            if (!remedioList.isEmpty()) {
                System.out.println("Remédio(s) encontrado(s):");
                remedioList.forEach(System.out::println);
            }else {
                System.out.println("Nenhum remédio foi encontrado com o nome: "+ nome);
            }
        }catch (Exception e) {
            System.out.println("Erro ao buscar remédio por nome: "+ e.getMessage());
        }
    }

    public void listarTodosRemedios() {
        try {
            List<Remedio> remedios = remedioService.listarTodos();

            if (!remedios.isEmpty()) {
                System.out.println("Remédios:");
                remedios.forEach(System.out::println);
            }else {
                System.out.println("Nenhum remédio foi encontrado");
            }
        }catch (Exception e) {
            System.out.println("Erro ao listar remédios: "+ e.getMessage());
        }
    }

    public void calcularTotalEstoque() {
        try {
            double total = remedioService.calcularTotalEstoque();
            System.out.println("Total do estoque de remédios: R$ "+ total);
        }catch (Exception e) {
            System.out.println("Erro ao calcular total do estoque: "+ e.getMessage());
        }
    }

    public void listarRemediosVencidos() {
        try {
            List<Remedio> remediosVencidos = remedioService.listarVencidos();

            if (!remediosVencidos.isEmpty()) {
                System.out.println("Atenção estes remédios estão vencidos:");
                remediosVencidos.forEach(System.out::println);
            }else {
                System.out.println("Nenhum remédio vencido encontrado");
            }
        }catch (Exception e) {
            System.out.println("Erro ao listar remédios vencidos: "+ e.getMessage());
        }
    }

    public void buscarRemedioPorPrincipioAtivo(String principioAtivo) {
        List<Remedio> remedios = remedioService.buscarPorPrincipioAtivo(principioAtivo);
        try {
            if (!remedios.isEmpty()) {
                System.out.println("Remédios com "+ principioAtivo +":");
                remedios.forEach(System.out::println);
            }else {
                System.out.println("Nenhum remédio encontrado com o princípio ativo: "+ principioAtivo);
            }
        }catch (Exception e) {
            System.out.println("Erro ao buscar remédio por princípio ativo: "+ e.getMessage());
        }
    }

    public void listarRemediosControlados() {
        List<Remedio> remedios = remedioService.listarControlados();

        try {
            if (!remedios.isEmpty()) {
                System.out.println("Remédios controlados:");
                remedios.forEach(System.out::println);
            }else {
                System.out.println("Nenhum remédio controlado foi encontrado");
            }
        }catch (Exception e) {
            System.out.println("Erro ao listar remédios controlados: "+ e.getMessage());
        }
    }
}
