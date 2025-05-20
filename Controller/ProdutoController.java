package Challenge.Controller;

import Challenge.Model.Produto;
import Challenge.Repository.ProdutoRepository;

import java.util.List;

public class ProdutoController {
    private ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    public void addProduto(Produto produto) {
        if (!produto.estaVencido()) {
            repository.salvar(produto);
        }else {
            System.out.println("Não é possível adicionar!!");
        }
    }

    public List<Produto> listarProdutos() {
        return repository.listarProdutos();
    }
}
