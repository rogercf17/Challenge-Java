package br.com.dasa.estoque.tests;

import br.com.dasa.estoque.controller.RemedioController;
import br.com.dasa.estoque.dao.RemedioDAO;
import br.com.dasa.estoque.db.ConnectionFactory;
import br.com.dasa.estoque.model.Remedio;
import br.com.dasa.estoque.service.RemedioService;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class RemedioTest {
    private static Connection connection;
    private static RemedioService remedioService;
    private static RemedioController remedioController;
    private static RemedioDAO remedioDAO;

    @BeforeAll
    static void setupAll() throws SQLException {
        connection = ConnectionFactory.getConnecion();
        remedioDAO = new RemedioDAO(connection);
        remedioService = new RemedioService(connection);
        remedioController = new RemedioController(connection);
    }

    @AfterAll
    static void tearDownAll() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    void testModel() {
        Remedio remedio = new Remedio(
                null,
                "Dipirona",
                "EMS",
                100,
                2.5,
                "L123",
                "Metamizol",
                "Comprimido",
                "Oral",
                LocalDateTime.now().minusMonths(1),
                LocalDateTime.now().plusMonths(12),
                false
        );

        assertEquals("Dipirona", remedio.getNome());
        assertFalse(remedio.isVencido());
        assertTrue(remedio.toString().contains("Dipirona"));
    }

    @Test
    void testDAO() throws SQLException {
        Remedio remedio = new Remedio(
                null,
                "Amoxicilina",
                "Medley",
                50,
                10.0,
                "L456",
                "Amoxicilina",
                "Cápsula",
                "Oral",
                LocalDateTime.now(),
                LocalDateTime.now().plusMonths(6),
                true
        );

        remedioDAO.inserir(remedio);

        List<Remedio> lista = remedioDAO.buscarTodos();
        assertTrue(lista.stream().anyMatch(r -> r.getNome().equals("Amoxicilina")));

        Remedio encontrado = remedioDAO.buscarTodos().get(0);
        assertNotNull(encontrado);

        remedioDAO.remover(encontrado.getId());
    }

    @Test
    void testService() {
        Remedio remedio = new Remedio(
                null,
                "Ibuprofeno",
                "Bayer",
                80,
                5.0,
                "L789",
                "Ibuprofeno",
                "Comprimido",
                "Oral",
                LocalDateTime.now(),
                LocalDateTime.now().plusMonths(18),
                false
        );

        remedioService.adicionar(remedio);

        List<Remedio> lista = remedioService.listarTodos();
        assertTrue(lista.stream().anyMatch(r -> r.getNome().equals("Ibuprofeno")));
    }

    @Test
    void testController() {
        Remedio remedio = new Remedio(
                null,
                "Paracetamol",
                "Neo Química",
                200,
                1.5,
                "L321",
                "Paracetamol",
                "Comprimido",
                "Oral",
                LocalDateTime.now(),
                LocalDateTime.now().plusMonths(24),
                false
        );

        remedioController.adicionarRemedio(remedio);

        remedioController.buscarRemedioPorNome("Paracetamol");
        remedioController.listarTodosRemedios();

        assertTrue(remedioService.listarTodos().stream().anyMatch(r -> r.getNome().equals("Paracetamol")));
    }
}
