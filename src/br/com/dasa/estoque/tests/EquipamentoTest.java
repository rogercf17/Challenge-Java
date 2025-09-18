package br.com.dasa.estoque.tests;

import br.com.dasa.estoque.controller.EquipamentoController;
import br.com.dasa.estoque.dao.EquipamentoDAO;
import br.com.dasa.estoque.model.Equipamento;
import br.com.dasa.estoque.service.EquipamentoService;
import br.com.dasa.estoque.db.ConnectionFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class EquipamentoTest {
    private static Connection connection;
    private static EquipamentoDAO equipamentoDAO;
    private static EquipamentoService equipamentoService;
    private static EquipamentoController equipamentoController;

    @BeforeAll
    static void setupAll() throws SQLException {
        connection = ConnectionFactory.getConnecion();
        equipamentoDAO = new EquipamentoDAO(connection);
        equipamentoService = new EquipamentoService(connection);
        equipamentoController = new EquipamentoController(connection);
    }

    @AfterAll
    static void tearDownAll() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    void testModel() {
        Equipamento equipamento = new Equipamento(
                null,
                "Centrífuga",
                "Philips",
                2,
                5000.0,
                "SN123",
                "Laboratório",
                LocalDateTime.now().minusMonths(10),
                LocalDateTime.now().minusMonths(7),
                60,
                true
        );

        assertEquals("Centrífuga", equipamento.getNome());
        assertTrue(equipamento.precisaManutencao());
        assertTrue(equipamento.toString().contains("Centrífuga"));
    }

    @Test
    void testDAO() throws SQLException {
        Equipamento equipamento = new Equipamento(
                null,
                "Autoclave",
                "Phoenix",
                1,
                20000.0,
                "SN999",
                "Esterilização",
                LocalDateTime.now(),
                LocalDateTime.now(),
                120,
                true
        );

        equipamentoDAO.inserir(equipamento);

        List<Equipamento> lista = equipamentoDAO.buscarTodos();
        assertTrue(lista.stream().anyMatch(e -> e.getNome().equals("Autoclave")));

        Equipamento encontrado = lista.get(0);
        equipamentoDAO.remover(encontrado.getId());
    }

    @Test
    void testService() {
        Equipamento equipamento = new Equipamento(
                null,
                "Microscópio",
                "Zeiss",
                5,
                15000.0,
                "SN555",
                "Pesquisa",
                LocalDateTime.now(),
                LocalDateTime.now(),
                36,
                true
        );

        equipamentoService.adicionar(equipamento);

        List<Equipamento> lista = equipamentoService.listarTodos();
        assertTrue(lista.stream().anyMatch(e -> e.getNome().equals("Microscópio")));
    }

    @Test
    void testController() {
        Equipamento equipamento = new Equipamento(
                null,
                "Balança Analítica",
                "Toledo",
                3,
                3000.0,
                "SN777",
                "Pesagem",
                LocalDateTime.now(),
                LocalDateTime.now(),
                48,
                false
        );

        equipamentoController.adicionarEquipamento(equipamento);

        equipamentoController.buscarEquipamentoPorNome("Balança Analítica");
        equipamentoController.listarTodosEquipamentos();

        assertTrue(equipamentoService.listarTodos().stream().anyMatch(e -> e.getNome().equals("Balança Analítica")));
    }
}

