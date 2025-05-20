package Challenge.View;

import Challenge.Controller.ProdutoController;
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
            System.out.println("3 - Remover Produto");
            System.out.println("4 - Encerrar");
            System.out.print("Escolha sua opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 4:
                    break;
            }
        }
    }
}
