package Challenge.Repository;

import Challenge.Model.Produto;
import java.util.List;

public interface ProdutoRepository {
    void salvar(Produto produto);
    void remover(Produto produto);
    Produto buscarPorId(int id);
    List<Produto> listarProdutos();
}
