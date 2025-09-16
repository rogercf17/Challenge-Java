package br.com.dasa.estoque;

import br.com.dasa.estoque.controller.DescartavelController;
import br.com.dasa.estoque.controller.EquipamentoController;
import br.com.dasa.estoque.controller.RemedioController;
import br.com.dasa.estoque.db.ConnectionFactory;
import br.com.dasa.estoque.model.Descartavel;
import br.com.dasa.estoque.model.CategoriaRisco;
import br.com.dasa.estoque.model.TipoDescartavel;
import br.com.dasa.estoque.model.Equipamento;
import br.com.dasa.estoque.model.Remedio;
import br.com.dasa.estoque.service.DescartavelService;
import br.com.dasa.estoque.service.EquipamentoService;
import br.com.dasa.estoque.service.RemedioService;
import br.com.dasa.estoque.util.DateParsing;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Connection connection = ConnectionFactory.getConnecion();
            System.out.println("Conexão com o banco estabelecida com sucesso!");

            RemedioService remedioService = new RemedioService(connection);
            EquipamentoService equipamentoService = new EquipamentoService(connection);
            DescartavelService descartavelService = new DescartavelService(connection);

            RemedioController remedioController = new RemedioController(connection);
            EquipamentoController equipamentoController = new EquipamentoController(connection);
            DescartavelController descartavelController = new DescartavelController(connection);

            menuPrincipal(
                    remedioController,
                    equipamentoController,
                    descartavelController,
                    remedioService,
                    equipamentoService,
                    descartavelService,
                    scanner
            );

            connection.close();
            System.out.println("Conexão fechada");
        }catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }finally {
            scanner.close();
        }
    }

    private static void menuPrincipal(
            RemedioController remedioController,
            EquipamentoController equipamentoController,
            DescartavelController descartavelController,
            RemedioService remedioService,
            EquipamentoService equipamentoService,
            DescartavelService descartavelService,
            Scanner scanner
    ) {
        int opcao;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Gerenciar Remédios");
            System.out.println("2 - Gerenciar Equipamentos");
            System.out.println("3 - Gerenciar Descartáveis");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuRemedio(remedioController, scanner, remedioService);
                    break;
                case 2:
                    menuEquipamento(equipamentoController, scanner, equipamentoService);
                    break;
                case 3:
                    menuDescartavel(descartavelController, scanner, descartavelService);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente!");
            }
        }while (opcao != 0);
    }

    private static void menuRemedio(RemedioController remedioController, Scanner scanner, RemedioService remedioService) {
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
                    break;
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

    private static void menuEquipamento(EquipamentoController equipamentoController, Scanner scanner, EquipamentoService equipamentoService) {
        int opcao;

        do {
            System.out.println("\n===== Menu Equipamentos =====");
            System.out.println("1 - Adicionar Equipamento");
            System.out.println("2 - Remover Equipamento");
            System.out.println("3 - Atualizar Equipamento");
            System.out.println("4 - Buscar por ID");
            System.out.println("5 - Buscar por Nome");
            System.out.println("6 - Listar Equipamentos");
            System.out.println("7 - Calcular Total Estoque");
            System.out.println("8 - Listar Equipamentos para Manutenção");
            System.out.println("9 - Listar Equipamentos por Setor");
            System.out.println("10 - Listar Equipamentos Fora da Vida Útil");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do equipamento: ");
                    String nome = scanner.nextLine();

                    System.out.println("Digite o fabricante: ");
                    String fabricante = scanner.nextLine();

                    System.out.println("Digite a quantidade total: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Digite o preço unitário (R$): ");
                    double precoUnitario = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Digite o número de série: ");
                    String numeroSerie = scanner.nextLine();

                    System.out.println("Digite o setor: ");
                    String setor = scanner.nextLine();

                    System.out.println("Digite a data de aquisição (dd/MM/yyyy): ");
                    String dataAqStr = scanner.nextLine();
                    LocalDateTime dataAquisicao = DateParsing.parseDateTime(dataAqStr);

                    System.out.println("Digite a data da última manutenção (dd/MM/yyyy): ");
                    String dataManutStr = scanner.nextLine();
                    LocalDateTime ultimaManutencao = DateParsing.parseDateTime(dataManutStr);

                    System.out.println("Digite a vida útil em meses: ");
                    int vidaUtilMeses = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("O equipamento está em uso? (1 - Sim, 0 - Não): ");
                    int emUsoInt = scanner.nextInt();
                    boolean emUso = (emUsoInt == 1);
                    scanner.nextLine();

                    Equipamento equipamento = new Equipamento(
                            null,
                            nome,
                            fabricante,
                            quantidade,
                            precoUnitario,
                            numeroSerie,
                            setor,
                            dataAquisicao,
                            ultimaManutencao,
                            vidaUtilMeses,
                            emUso
                    );

                    equipamentoController.adicionarEquipamento(equipamento);
                    break;
                case 2:
                    System.out.println("Digite o ID do equipamento para ser removido: ");
                    Long idRemover = scanner.nextLong();
                    scanner.nextLine();
                    equipamentoController.removerEquipamento(idRemover);
                    break;
                case 3:
                    System.out.println("Digite o ID do equipamento que deseja atualizar: ");
                    Long idAtualizar = scanner.nextLong();
                    scanner.nextLine();

                    Equipamento existente = equipamentoService.buscarPorId(idAtualizar);
                    if (existente == null) {
                        System.out.println("Nenhum equipamento encontrado com o ID: " + idAtualizar);
                        break;
                    }

                    System.out.println("Digite o novo nome (atual: " + existente.getNome() + "): ");
                    String nome2 = scanner.nextLine();

                    System.out.println("Digite o novo fabricante (atual: " + existente.getFabricante() + "): ");
                    String fabricante2 = scanner.nextLine();

                    System.out.println("Digite a nova quantidade (atual: " + existente.getQuantidade() + "): ");
                    int quantidade2 = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Digite o novo preço unitário (R$) (atual: " + existente.getPrecoUnitario() + "): ");
                    double precoUnitario2 = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Digite o novo número de série (atual: " + existente.getNumeroSerie() + "): ");
                    String numeroSerie2 = scanner.nextLine();

                    System.out.println("Digite o novo setor (atual: " + existente.getSetor() + "): ");
                    String setor2 = scanner.nextLine();

                    System.out.println("Digite a nova data de aquisição (dd/MM/yyyy) (atual: " + existente.getDataAquisicao().toLocalDate() + "): ");
                    String dataAqStr2 = scanner.nextLine();
                    LocalDateTime dataAquisicao2 = DateParsing.parseDateTime(dataAqStr2);

                    System.out.println("Digite a nova data da última manutenção (dd/MM/yyyy) (atual: " + existente.getUltimaManutencao().toLocalDate() + "): ");
                    String dataManutStr2 = scanner.nextLine();
                    LocalDateTime ultimaManutencao2 = DateParsing.parseDateTime(dataManutStr2);

                    System.out.println("Digite a nova vida útil em meses (atual: " + existente.getVidaUtilMeses() + "): ");
                    int vidaUtilMeses2 = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("O equipamento está em uso? (atual: " + (existente.isEmUso() ? "Sim" : "Não") + ") (1 - Sim, 0 - Não): ");
                    int emUsoInt2 = scanner.nextInt();
                    boolean emUso2 = (emUsoInt2 == 1);
                    scanner.nextLine();

                    Equipamento atualizado = new Equipamento(
                            idAtualizar,
                            nome2,
                            fabricante2,
                            quantidade2,
                            precoUnitario2,
                            numeroSerie2,
                            setor2,
                            dataAquisicao2,
                            ultimaManutencao2,
                            vidaUtilMeses2,
                            emUso2
                    );

                    equipamentoController.atualizarEquipamento(atualizado);
                    break;
                case 4:
                    System.out.println("Digite o ID para busca: ");
                    Long idBuscar = scanner.nextLong();
                    scanner.nextLine();
                    equipamentoController.buscarEquipamentoPorId(idBuscar);
                    break;
                case 5:
                    System.out.println("Digite o nome para busca: ");
                    String nomeBuscar = scanner.nextLine();
                    equipamentoController.buscarEquipamentoPorNome(nomeBuscar);
                    break;
                case 6:
                    equipamentoController.listarTodosEquipamentos();
                    break;
                case 7:
                    equipamentoController.calcularTotalEstoque();
                    break;
                case 8:
                    equipamentoController.listarEquipamentosParaManutencao();
                    break;
                case 9:
                    System.out.println("Digite o setor para listar equipamentos: ");
                    String setorBuscar = scanner.nextLine();
                    equipamentoController.listarEquipamentosPorSetor(setorBuscar);
                    break;
                case 10:
                    equipamentoController.listarEquipamentosForaVidaUtil();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente...");
            }
        }while (opcao != 0);
    }

    private static void menuDescartavel(DescartavelController descartavelController, Scanner scanner, DescartavelService descartavelService) {
        int opcao;

        do {
            System.out.println("\n===== Menu Descartáveis =====");
            System.out.println("1 - Adicionar Descartável");
            System.out.println("2 - Remover Descartável");
            System.out.println("3 - Atualizar Descartável");
            System.out.println("4 - Buscar por ID");
            System.out.println("5 - Buscar por Nome");
            System.out.println("6 - Listar Todos");
            System.out.println("7 - Calcular Total Estoque");
            System.out.println("8 - Listar Descartáveis Vencidos");
            System.out.println("9 - Listar por Categoria de Risco");
            System.out.println("10 - Listar por Uso Previsto");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o fabricante: ");
                    String fabricante = scanner.nextLine();
                    System.out.print("Digite a quantidade: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o preço unitário: ");
                    double preco = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Digite o material: ");
                    String material = scanner.nextLine();
                    System.out.print("Digite o uso previsto: ");
                    String usoPrevisto = scanner.nextLine();
                    System.out.print("Digite a data de validade (yyyy-MM-ddTHH:mm): ");
                    LocalDateTime dataValidade = LocalDateTime.parse(scanner.nextLine());
                    System.out.print("Está esterilizado? (true/false): ");
                    boolean esterelizado = scanner.nextBoolean();
                    System.out.print("Descartado após uso? (true/false): ");
                    boolean descartadoAposUso = scanner.nextBoolean();
                    scanner.nextLine();
                    System.out.print("Digite o tipo de descartável (ex: LUVAS, SERINGA...): ");
                    TipoDescartavel tipo = TipoDescartavel.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("Digite a categoria de risco (ex: BAIXO, MEDIO, ALTO): ");
                    CategoriaRisco categoriaRisco = CategoriaRisco.valueOf(scanner.nextLine().toUpperCase());

                    Descartavel descartavel = new Descartavel(
                            null, nome, fabricante, quantidade, preco,
                            material, usoPrevisto, dataValidade, esterelizado,
                            descartadoAposUso, tipo, categoriaRisco
                    );

                    descartavelController.adicionarDescartavel(descartavel);
                    break;

                case 2:
                    System.out.print("Digite o ID do descartável a remover: ");
                    Long idRemover = scanner.nextLong();
                    descartavelController.removerDescartavel(idRemover);
                    break;

                case 3:
                    System.out.print("Digite o ID do descartável a atualizar: ");
                    Long idAtualizar = scanner.nextLong();
                    scanner.nextLine();
                    Descartavel descartavelExistente = descartavelService.buscarPorId(idAtualizar);

                    if (descartavelExistente != null) {
                        System.out.print("Novo nome (" + descartavelExistente.getNome() + "): ");
                        String novoNome = scanner.nextLine();
                        if (!novoNome.isEmpty()) descartavelExistente.setNome(novoNome);

                        System.out.print("Novo fabricante (" + descartavelExistente.getFabricante() + "): ");
                        String novoFabricante = scanner.nextLine();
                        if (!novoFabricante.isEmpty()) descartavelExistente.setFabricante(novoFabricante);

                        System.out.print("Nova quantidade (" + descartavelExistente.getQuantidade() + "): ");
                        String novaQtdStr = scanner.nextLine();
                        if (!novaQtdStr.isEmpty()) descartavelExistente.setQuantidade(Integer.parseInt(novaQtdStr));

                        System.out.print("Novo preço (" + descartavelExistente.getPrecoUnitario() + "): ");
                        String novoPrecoStr = scanner.nextLine();
                        if (!novoPrecoStr.isEmpty()) descartavelExistente.setPrecoUnitario(Double.parseDouble(novoPrecoStr));

                        descartavelController.atualizarDescartavel(descartavelExistente);
                    } else {
                        System.out.println("Descartável com ID " + idAtualizar + " não encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o ID: ");
                    Long idBusca = scanner.nextLong();
                    descartavelController.buscarDescartavelPorId(idBusca);
                    break;

                case 5:
                    System.out.print("Digite o nome: ");
                    String nomeBusca = scanner.nextLine();
                    descartavelController.buscarDescartavelPorNome(nomeBusca);
                    break;

                case 6:
                    descartavelController.listarTodosDescartaveis();
                    break;

                case 7:
                    descartavelController.calcularTotalEstoque();
                    break;

                case 8:
                    descartavelController.listarDescartaveisVencidos();
                    break;

                case 9:
                    System.out.print("Digite a categoria de risco (ex: BAIXO, MEDIO, ALTO): ");
                    CategoriaRisco categoriaBusca = CategoriaRisco.valueOf(scanner.nextLine().toUpperCase());
                    descartavelController.listarDescartaveisPorCategoriaRisco(categoriaBusca);
                    break;

                case 10:
                    System.out.print("Digite o uso previsto: ");
                    String usoBusca = scanner.nextLine();
                    descartavelController.listarDescartaveisPorUsoPrevisto(usoBusca);
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente.");
            }

        } while (opcao != 0);
    }
}
