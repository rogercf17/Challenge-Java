package br.com.dasa.estoque.dao;

import br.com.dasa.estoque.model.Remedio;
import java.sql.*;
import java.util.*;

public class RemedioDAO implements ProdutoDAO<Remedio> {
    private Connection connection;

    public RemedioDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(Remedio remedio) throws SQLException {
        String sql = "INSERT INTO remedio (id, nome, fabricante, quantidade, preco_unitario, lote, principio_ativo, forma_farmaceutica, via_administracao, data_fabricacao, data_validade, controlado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, remedio.getId());
            statement.setString(2, remedio.getNome());
            statement.setString(3, remedio.getFabricante());
            statement.setInt(4, remedio.getQuantidade());
            statement.setDouble(5, remedio.getPrecoUnitario());
            statement.setString(6, remedio.getLote());
            statement.setString(7, remedio.getPrincipioAtivo());
            statement.setString(8, remedio.getFormaFarmaceutica());
            statement.setString(9, remedio.getViaAdministracao());
            statement.setTimestamp(10, Timestamp.valueOf(remedio.getDataFabricacao()));
            statement.setTimestamp(11, Timestamp.valueOf(remedio.getDataValidade()));
            statement.setBoolean(12, remedio.isControlado());
            statement.executeUpdate();
        }
    }

    @Override
    public void atualizar(Remedio remedio) throws SQLException {
        String sql = "UPDATE remedio SET nome=?, fabricante=?, quantidade=?, preco_unitario=?, lote=?, principio_ativo=?, forma_farmaceutica=?, via_administracao=?, data_fabricacao=?, data_validade=?, controlado=? WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, remedio.getNome());
            statement.setString(2, remedio.getFabricante());
            statement.setInt(3, remedio.getQuantidade());
            statement.setDouble(4, remedio.getPrecoUnitario());
            statement.setString(5, remedio.getLote());
            statement.setString(6, remedio.getPrincipioAtivo());
            statement.setString(7, remedio.getFormaFarmaceutica());
            statement.setString(8, remedio.getViaAdministracao());
            statement.setTimestamp(9, Timestamp.valueOf(remedio.getDataFabricacao()));
            statement.setTimestamp(10, Timestamp.valueOf(remedio.getDataValidade()));
            statement.setBoolean(11, remedio.isControlado());
            statement.setLong(12, remedio.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void remover(Long id) throws SQLException {
        String sql = "DELETE FROM remedio WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public Remedio buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM remedio WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearRemedio(resultSet);
                }
            }
        }

        return null;
    }

    @Override
    public List<Remedio> buscarTodos() throws SQLException {
        List<Remedio> lista = new ArrayList<>();

        String sql = "SELECT * FROM remedio";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                lista.add(mapearRemedio(resultSet));
            }
        }

        return lista;
    }

    private Remedio mapearRemedio(ResultSet resultSet) throws SQLException {
        return new Remedio(
                resultSet.getLong("id"),
                resultSet.getString("nome"),
                resultSet.getString("fabricante"),
                resultSet.getInt("quantidade"),
                resultSet.getDouble("preco_unitario"),
                resultSet.getString("lote"),
                resultSet.getString("principio_ativo"),
                resultSet.getString("forma_farmaceutica"),
                resultSet.getString("via_administracao"),
                resultSet.getTimestamp("data_fabricacao").toLocalDateTime(),
                resultSet.getTimestamp("data_validade").toLocalDateTime(),
                resultSet.getBoolean("controlado")
        );
    }
}
