package Challenge.Testes;

import Challenge.Model.Reagente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {
    @Test
    public void testAdicionarQuantidade() {
        Reagente reagente = new Reagente("1", "Ácido", 10, "Reagente", 7.0, "15°C");
        reagente.adicionarQuantidade(5);
        assertEquals(15, reagente.getQuantidade());
    }
    @Test
    public void testRetirarQuantidade() {
        Reagente reagente = new Reagente("2", "Base", 10, "Reagente", 9.0, "20°C");
        reagente.retirarQuantidade(3);
        assertEquals(7, reagente.getQuantidade());
    }
}
