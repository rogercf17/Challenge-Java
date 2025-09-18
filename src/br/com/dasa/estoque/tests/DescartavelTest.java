package br.com.dasa.estoque.tests;

import br.com.dasa.estoque.controller.DescartavelController;
import br.com.dasa.estoque.dao.DescartavelDAO;
import br.com.dasa.estoque.model.*;
import br.com.dasa.estoque.service.DescartavelService;
import br.com.dasa.estoque.db.ConnectionFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class DescartavelTest {
    private static Connection connection;
    private static DescartavelDAO descartavelDAO;
    private static DescartavelService descartavelService;
    private static DescartavelController descartavelController;

    @BeforeAll
    static void setupAll() throws SQLException {
        connection = ConnectionFactory.getConnecion();
        descartavelDAO = new DescartavelDAO(connection);
        descartavelService = new DescartavelService(connection);
        descartavelController = new DescartavelController(connection);
    }

    @AfterAll
    static void tearDownAll() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    void testModel() {
        Descartavel descartavel = new Descartavel(
                null,
                "Luvas Cirúrgicas",
                "Medix",
                200,
                1.0,
                "Látex",
                "Proteção",
                LocalDateTime.now().plusMonths(12),
                true,
                true,
                TipoDescartavel.EPI,
                CategoriaRisco.NAO_CONTAMINANTE
        );

        assertEquals("Luvas Cirúrgicas", descartavel.getNome());
        assertTrue(descartavel.estaDentroValidade());
        assertTrue(descartavel.toString().contains("Luvas"));
    }

    @Test
    void testDAO() throws SQLException {
        Descartavel descartavel = new Descartavel(
                null,
                "Seringa",
                "BD",
                500,
                0.5,
                "Plástico",
                "Aplicação de medicamentos",
                LocalDateTime.now().plusMonths(24),
                true,
                true,
                TipoDescartavel.MATERIAL_COLETA,
                CategoriaRisco.PERFURANTE
        );

        descartavelDAO.inserir(descartavel);

        List<Descartavel> lista = descartavelDAO.buscarTodos();
        assertTrue(lista.stream().anyMatch(desc -> desc.getNome().equals("Seringa")));

        Descartavel encontrado = lista.get(0);
        descartavelDAO.remover(encontrado.getId());
    }

    @Test
    void testServiceAdicionarListar() {
        Descartavel descartavel = new Descartavel(
                null,
                "Máscara N95",
                "3M",
                100,
                10.0,
                "Tecido",
                "Proteção respiratória",
                LocalDateTime.now().plusMonths(6),
                true,
                true,
                TipoDescartavel.EPI,
                CategoriaRisco.NAO_CONTAMINANTE
        );

        descartavelService.adicionar(descartavel);

        List<Descartavel> lista = descartavelService.listarTodos();
        assertTrue(lista.stream().anyMatch(desc -> desc.getNome().equals("Máscara N95")));
    }

    @Test
    void testControllerAdicionarBuscar() {
        Descartavel descartavel = new Descartavel(
                null,
                "Touca Descartável",
                "MedCare",
                300,
                0.2,
                "TNT",
                "Proteção capilar",
                LocalDateTime.now().plusMonths(9),
                true,
                true,
                TipoDescartavel.EPI,
                CategoriaRisco.NAO_CONTAMINANTE
        );

        descartavelController.adicionarDescartavel(descartavel);

        descartavelController.buscarDescartavelPorNome("Touca Descartável");
        descartavelController.listarTodosDescartaveis();

        assertTrue(descartavelService.listarTodos().stream().anyMatch(desc -> desc.getNome().equals("Touca Descartável")));
    }
}

