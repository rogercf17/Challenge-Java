package Challenge.Repository;

import Challenge.Model.Produto;
import java.util.*;

public class ProdutoRepositoryImpl implements ProdutoRepository {
    private final Map<String, Produto> storage = new HashMap<>();

    public void salvar(Produto produto) {
        storage.put(produto.getId(), produto);
    }

    public void remover(Produto produto) {
        storage.remove(produto.getId());
    }

    public Produto buscarPorId(String id) {
        return storage.get(id);
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<>(storage.values());
    }
}
