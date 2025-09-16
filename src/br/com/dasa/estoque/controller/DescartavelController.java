package br.com.dasa.estoque.controller;

import br.com.dasa.estoque.model.CategoriaRisco;
import br.com.dasa.estoque.model.Descartavel;
import br.com.dasa.estoque.service.DescartavelService;
import java.sql.Connection;
import java.util.List;

public class DescartavelController {
    private DescartavelService descartavelService;

    public DescartavelController(Connection connection) {
        this.descartavelService = new DescartavelService(connection);
    }

    public void adicionarDescartavel(Descartavel descartavel) {
        try {
            descartavelService.adicionar(descartavel);
            System.out.println("Descartável adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar descartável: "+ e.getMessage());
        }
    }

    public void removerDescartavel(Long id) {
        try {
            descartavelService.remover(id);
            System.out.println("Descartável removido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao remover descartável: "+ e.getMessage());
        }
    }

    public void atualizarDescartavel(Descartavel descartavel) {
        try {
            descartavelService.atualizar(descartavel);
            System.out.println("Descartável atualizado com sucesso!\n"+ descartavel);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar descartável: "+ e.getMessage());
        }
    }

    public void buscarDescartavelPorId(Long id) {
        try {
            Descartavel descartavel = descartavelService.buscarPorId(id);

            if (descartavel != null) {
                System.out.println("Descartável encontrado:\n"+ descartavel);
            }else {
                System.out.println("Nenhum descartável foi encontrado com ID: "+ id);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar descartável por ID: "+ e.getMessage());
        }
    }

    public void buscarDescartavelPorNome(String nome) {
        try {
            List<Descartavel> descartaveisList = descartavelService.buscarPorNome(nome);

            if (!descartaveisList.isEmpty()) {
                System.out.println("Descartável encontrado:");
                descartaveisList.forEach(System.out::println);
            }else {
                System.out.println("Nenhum descartável foi encontrado com o nome: "+ nome);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar descartável por nome: "+ e.getMessage());
        }
    }

    public void listarTodosDescartaveis() {
        try {
            List<Descartavel> descartaveis = descartavelService.listarTodos();

            if (!descartaveis.isEmpty()) {
                System.out.println("Descartáveis:");
                descartaveis.forEach(System.out::println);
            }else {
                System.out.println("Nenhum Descartável foi encontrado");
            }
        }catch (Exception e) {
            System.out.println("Erro ao listar descartáveis: "+ e.getMessage());
        }
    }

    public void calcularTotalEstoque() {
        try {
            double total = descartavelService.calcularTotalEstoque();
            System.out.println("Total do estoque de descartáveis: R$ "+ String.format("%.2f", total));
        }catch (Exception e) {
            System.out.println("Erro ao calcular total do estoque: "+ e.getMessage());
        }
    }

    public void listarDescartaveisVencidos() {
        try {
            List<Descartavel> descartaveisVencidos = descartavelService.listarVencidos();

            if (!descartaveisVencidos.isEmpty()) {
                System.out.println("Atenção descartáveis vencidos:");
                descartaveisVencidos.forEach(System.out::println);
            }else {
                System.out.println("Nenhum descartável vencido foi encontrado");
            }
        }catch (Exception e) {
            System.out.println("Erro ao listar descartáveis vencidos: "+ e.getMessage());
        }
    }

    public void listarDescartaveisPorCategoriaRisco(CategoriaRisco categoriaRisco) {
        try {
            List<Descartavel> descartaveis = descartavelService.listarPorCategoriaRisco(categoriaRisco);

            if (!descartaveis.isEmpty()) {
                System.out.println("Descartáveis encontrados - Categoria de risco "+categoriaRisco);
                descartaveis.forEach(System.out::println);
            }else {
                System.out.println("Nenhum descartável foi encontrado com a categoria de risco: "+ categoriaRisco);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar descrtáveis por categoria de risco: "+ e.getMessage());
        }
    }

    public void listarDescartaveisPorUsoPrevisto(String usoPrevisto) {
        try {
            List<Descartavel> descartaveis = descartavelService.listarPorUsoPrevisto(usoPrevisto);

            if (!descartaveis.isEmpty()) {
                System.out.println("Descartáveis encontrados para "+usoPrevisto);
                descartaveis.forEach(System.out::println);
            }else {
                System.out.println("Nenhum descartável foi encontrado para "+ usoPrevisto);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar descartáveis por uso previsto: "+ e.getMessage());
        }
    }
}
