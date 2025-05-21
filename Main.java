package Challenge;

import Challenge.Controller.ProdutoController;
import Challenge.Repository.ProdutoRepository;
import Challenge.Repository.ProdutoRepositoryImpl;
import Challenge.View.ProdutoView;

public class Main {
    public static void main(String[] args) {
        ProdutoRepository produtoRepository = new ProdutoRepositoryImpl();
        ProdutoController controller = new ProdutoController(produtoRepository);
        ProdutoView view = new ProdutoView(controller);

        view.sistema();
    }
}
