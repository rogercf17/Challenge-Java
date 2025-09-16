package br.com.dasa.estoque;

import br.com.dasa.estoque.controller.RemedioController;
import br.com.dasa.estoque.model.Remedio;
import br.com.dasa.estoque.service.RemedioService;
import br.com.dasa.estoque.util.DateParsing;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    }

    public static void menuRemedio(RemedioController remedioController, Scanner scanner, RemedioService remedioService) {
        int opcao;

        do {
            System.out.println("\n===== Menu Remédios =====");
            System.out.println("1 - Adicionar Remédio");
            System.out.println("2 - Remover Remédio");
            System.out.println("3 - Atualizar Remédio");
            System.out.println("4 - Buscar por ID");
            System.out.println("5 - Buscar por Nome");
            System.out.println("6 - Listar Remédios");
            System.out.println("7 - Calcular Total Estoque");
            System.out.println("8 - Listar Remédios Vencidos");
            System.out.println("9 - Listar Remédios (Por Princípio Ativo)");
            System.out.println("10 - Listar Remédios Controlados");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção? ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do remédio: ");
                    String nome = scanner.nextLine();

                    System.out.println("Digite o fabricante do remédio: ");
                    String fabricante = scanner.nextLine();

                    System.out.println("Digite a quantidade total: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Digite o preço unitário (R$): ");
                    double precoUnitario = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Digite o lote do remédio: ");
                    String lote = scanner.nextLine();

                    System.out.println("Digite o princípio ativo: ");
                    String principioAtivo = scanner.nextLine();

                    System.out.println("Digite a forma farmacêutica: ");
                    String formaFarmaceutica = scanner.nextLine();

                    System.out.println("Digite a via de administração: ");
                    String viaAdministracao = scanner.nextLine();

                    System.out.println("Digite a data de fabricação (dd/MM/yyyy): ");
                    String dataFabStr = scanner.nextLine();
                    LocalDateTime dataFabricacao = DateParsing.parseDateTime(dataFabStr);

                    System.out.println("Digite a data de validade (dd/MM/yyyy): ");
                    String dataValStr = scanner.nextLine();
                    LocalDateTime dataValidade = DateParsing.parseDateTime(dataValStr);

                    System.out.println("O remédio é controlado? (1 - Sim, 0 - Não): ");
                    int controaladoInt = scanner.nextInt();
                    boolean controlado = (controaladoInt == 1);
                    scanner.nextLine();

                    Remedio remedio = new Remedio(
                            null,
                            nome,
                            fabricante,
                            quantidade,
                            precoUnitario,
                            lote,
                            principioAtivo,
                            formaFarmaceutica,
                            viaAdministracao,
                            dataFabricacao,
                            dataValidade,
                            controlado
                    );

                    remedioController.adicionarRemedio(remedio);
                case 2:
                    System.out.println("Digite o ID do remédio para ser removido: ");
                    Long idRemover = scanner.nextLong();
                    scanner.nextLine();
                    remedioController.removerRemedio(idRemover);
                    break;
                case 3:
                    System.out.println("Digite o ID do remédio que deseja atualizar: ");
                    Long idAtualizar = scanner.nextLong();
                    scanner.nextLine();

                    Remedio existente = remedioService.buscarPorId(idAtualizar);
                    if (existente == null) {
                        System.out.println("Nenhum remédio encontrado com o ID: " + idAtualizar);
                        break;
                    }

                    System.out.println("Digite o novo nome do remédio (atual: " + existente.getNome() + "): ");
                    String nome2 = scanner.nextLine();

                    System.out.println("Digite o novo fabricante (atual: " + existente.getFabricante() + "): ");
                    String fabricante2 = scanner.nextLine();

                    System.out.println("Digite a nova quantidade (atual: " + existente.getQuantidade() + "): ");
                    int quantidade2 = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Digite o novo preço unitário (R$) (atual: " + existente.getPrecoUnitario() + "): ");
                    double precoUnitario2 = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Digite o novo lote (atual: " + existente.getLote() + "): ");
                    String lote2 = scanner.nextLine();

                    System.out.println("Digite o novo princípio ativo (atual: " + existente.getPrincipioAtivo() + "): ");
                    String principioAtivo2 = scanner.nextLine();

                    System.out.println("Digite a nova forma farmacêutica (atual: " + existente.getFormaFarmaceutica() + "): ");
                    String formaFarmaceutica2 = scanner.nextLine();

                    System.out.println("Digite a nova via de administração (atual: " + existente.getViaAdministracao() + "): ");
                    String viaAdministracao2 = scanner.nextLine();

                    System.out.println("Digite a nova data de fabricação (dd/MM/yyyy) (atual: " + existente.getDataFabricacao().toLocalDate() + "): ");
                    String dataFabStr2 = scanner.nextLine();
                    LocalDateTime dataFabricacao2 = DateParsing.parseDateTime(dataFabStr2);

                    System.out.println("Digite a nova data de validade (dd/MM/yyyy) (atual: " + existente.getDataValidade().toLocalDate() + "): ");
                    String dataValStr2 = scanner.nextLine();
                    LocalDateTime dataValidade2 = DateParsing.parseDateTime(dataValStr2);

                    System.out.println("O remédio é controlado? (atual: " + (existente.isControlado() ? "Sim" : "Não") + ") (true/false): ");
                    boolean controlado2 = scanner.nextBoolean();
                    scanner.nextLine();


                    Remedio atualizado = new Remedio(
                            idAtualizar,
                            nome2,
                            fabricante2,
                            quantidade2,
                            precoUnitario2,
                            lote2,
                            principioAtivo2,
                            formaFarmaceutica2,
                            viaAdministracao2,
                            dataFabricacao2,
                            dataValidade2,
                            controlado2
                    );

                    remedioController.atualizarRemedio(atualizado);
                    break;

                case 4:
                    System.out.println("Digite o ID para busca: ");
                    Long idBuscar = scanner.nextLong();
                    scanner.nextLine();
                    remedioController.buscarRemedioPorId(idBuscar);
                    break;
                case 5:
                    System.out.println("Digite o nome para busca: ");
                    String nomeBuscar = scanner.nextLine();
                    remedioController.buscarRemedioPorNome(nomeBuscar);
                    break;
                case 6:
                    remedioController.listarTodosRemedios();
                case 7:
                    remedioController.calcularTotalEstoque();
                    break;
                case 8:
                    remedioController.listarRemediosVencidos();
                    break;
                case 9:
                    System.out.println("Digite o princípio ativo para busca: ");
                    String principioAtivoBuscar = scanner.nextLine();
                    remedioController.buscarRemedioPorPrincipioAtivo(principioAtivoBuscar);
                    break;
                case 10:
                    remedioController.listarRemediosControlados();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente denovo...");
            }
        }while (opcao != 0);
    }
}
