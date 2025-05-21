package Challenge.Testes;

import Challenge.Controller.ProdutoController;
import Challenge.Model.Reagente;
import Challenge.Repository.ProdutoRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoControllerTest {
    private ProdutoController controller;

    @BeforeEach
    public void inicio() {
        controller = new ProdutoController(new ProdutoRepositoryImpl());
    }

    @Test
    public void testAddProdutoValido() {
        Reagente produto = new Reagente("5", "Água", 10, "reagente", 7.0, "25°C");
        controller.addProduto(produto);
        assertNotNull(controller.buscarProdutoPorId("5"));
    }
    @Test
    public void testRemoverProduto() {
        Reagente produto = new Reagente("6", "Vinagre", 10, "reagente", 3.0, "18°C");
        controller.addProduto(produto);
        controller.removerProduto(produto);
        assertNull(controller.buscarProdutoPorId("6"));
    }
}
