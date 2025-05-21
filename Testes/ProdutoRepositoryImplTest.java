package Challenge.Testes;

import Challenge.Model.Produto;
import Challenge.Model.Reagente;
import Challenge.Repository.ProdutoRepository;
import Challenge.Repository.ProdutoRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class ProdutoRepositoryImplTest {
    private ProdutoRepositoryImpl repository;

    @BeforeEach
    public void inicio() {
        repository = new ProdutoRepositoryImpl();
    }

    @Test
    public void testSalvarEBuscarProduto() {
        Produto reagente = new Reagente("1", "Ácido", 10, "reagente", 7.0, "15°C");
        repository.salvar(reagente);
        Produto encontrado = repository.buscarPorId("1");
        assertNotNull(encontrado);
        assertEquals("Ácido", encontrado.getNome());
    }
    @Test
    public void testRemoverProduto() {
        Produto reagente = new Reagente("2", "Ácido", 10, "reagente", 7.0, "15°C");
        repository.salvar(reagente);
        repository.remover(reagente);
        assertNotNull(repository.buscarPorId("2"));
    }
    @Test
    public void testListarProdutos() {
        Produto r1 = new Reagente("3", "Cloro", 5, "reagente", 5.5, "20°C");
        Produto r2 = new Reagente("4", "Álcool", 8, "reagente", 6.0, "22°C");
        repository.salvar(r1);
        repository.salvar(r2);
        List<Produto> produtos = repository.listarProdutos();
        assertEquals(2, produtos.size());
    }
}
