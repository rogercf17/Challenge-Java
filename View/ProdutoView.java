package Challenge.View;

import Challenge.Controller.ProdutoController;
import Challenge.Model.Descartavel;
import Challenge.Model.Equipamento;
import Challenge.Model.Produto;
import Challenge.Model.Reagente;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ProdutoView {
    private ProdutoController controller;
    private Scanner scanner = new Scanner(System.in);

    public ProdutoView(ProdutoController controller) {
        this.controller = controller;
    }

    public void sistema() {
        while (true) {
            System.out.println("=== Sistema de estoque de laboratório ===");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Buscar Produto");
            System.out.println("4 - Encerrar");
            System.out.print("Escolha sua opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    buscarProduto();
                    break;
                case 4:
                    System.out.println("\nEncerrando o sistema...");
                    return;
                default:
                    System.out.println("Opção inválida...");
                    break;
            }
        }
    }

    private void listarProdutos() {
        List<Produto> produtos = controller.listarProdutos();

        if (produtos.isEmpty()) {
            System.out.println("\nNenhum produto em estoque...");
        }else {
            System.out.println("\nProdutos em estoque:");
            for (Produto produto : produtos) {
                produto.exibirInfos();
            }
        }
    }

    private void cadastrarProduto() {
        System.out.println("ID: ");
        String id = scanner.nextLine();

        System.out.println("Nome: ");
        String nome = scanner.nextLine();

        System.out.println("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Categoria (Descartavel, Reagente, Equipamento): ");
        String categoria = scanner.nextLine().trim().toLowerCase(Locale.ROOT);

        Produto produto = null;

        switch (categoria) {
            case "descartavel":
                System.out.println("O descartável é feito de qual material? ");
                String material = scanner.nextLine();
                System.out.println("O descartável é esteril? (true ou false)");
                boolean esteril = scanner.nextBoolean();
                scanner.nextLine();
                produto = new Descartavel(id, nome, quantidade, categoria, material, esteril);
                break;
            case "reagente":
                System.out.println("Qual o Ph do ragente? ");
                double ph = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Qual a temperatura ideal do reagente? ");
                String temperaturaIdeal = scanner.nextLine();
                produto = new Reagente(id, nome, quantidade, categoria, ph, temperaturaIdeal);
                break;
            case "equipamento":
                System.out.println("Qual o número de série do equipamento? ");
                String numeroSerie = scanner.nextLine();
                System.out.println("Qual a data de manutenção do aparelho? (AAAA-MM-DD) ");
                String dataStr = scanner.nextLine();
                LocalDate dataManutencao = LocalDate.parse(dataStr);
                produto = new Equipamento(id, nome, quantidade, categoria, numeroSerie, dataManutencao);
                break;
            default:
                System.out.println("Categoria inválida.");
                return;
        }

        controller.addProduto(produto);
        System.out.println("Produto da categoria '" + categoria + "' cadastrado com sucesso, no estoque!");
    }

    private void buscarProduto() {
        System.out.println("Digite o ID do produto procurado: ");
        String id = scanner.nextLine();

        Produto produto = controller.buscarProdutoPorId(id);

        if (produto == null) {
            System.out.println("Produto com ID " + id + " não encontrado.");
        }else {
            System.out.println("Produto encontrado!\nDeseja realizar alguma operação com este produto?\n" +
                    "1 - Repor quantidade\n2 - Retirar quantidade\n3 - Remover Produto\n4 - Visualizar infos");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Quanto mais irá colocar no estoque? ");
                    int quantidadeAdicionada = scanner.nextInt();
                    scanner.nextLine();
                    produto.adicionarQuantidade(quantidadeAdicionada);
                    break;
                case 2:
                    System.out.println("Quanto mais irá retirar do estoque? ");
                    int quantidadeRetirada = scanner.nextInt();
                    scanner.nextLine();
                    produto.retirarQuantidade(quantidadeRetirada);
                    break;
                case 3:
                    controller.removerProduto(produto);
                    System.out.println("Produto removido");
                    break;
                case 4:
                    produto.exibirInfos();
                    break;
                default:
                    System.out.println("Opção inválida...");
                    return;
            }
        }
    }
}
