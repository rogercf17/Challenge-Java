package br.com.dasa.estoque.dao;

import br.com.dasa.estoque.model.Descartavel;
import br.com.dasa.estoque.model.CategoriaRisco;
import br.com.dasa.estoque.model.TipoDescartavel;
import java.sql.*;
import java.util.*;

public class DescartavelDAO implements ProdutoDAO<Descartavel> {
    private Connection connection;

    public DescartavelDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(Descartavel descartavel) throws SQLException {
        String sql = "INSERT INTO descartavel (id, nome, fabricante, quantidade, preco_unitario, material, uso_previsto, data_validade, esterelizado, descartado_apos_uso, tipo_descartavel, categoria_risco) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, descartavel.getId());
            statement.setString(2, descartavel.getNome());
            statement.setString(3, descartavel.getFabricante());
            statement.setInt(4, descartavel.getQuantidade());
            statement.setDouble(5, descartavel.getPrecoUnitario());
            statement.setString(6, descartavel.getMaterial());
            statement.setString(7, descartavel.getUsoPrevisto());
            statement.setTimestamp(8, Timestamp.valueOf(descartavel.getDataValidade()));
            statement.setBoolean(9, descartavel.isEsterelizado());
            statement.setBoolean(10, descartavel.isDescartadoAposUso());
            statement.setString(11, descartavel.getTipoDescartavel().name());
            statement.setString(12, descartavel.getCategoriaRisco().name());
            statement.executeUpdate();
        }
    }

    @Override
    public void atualizar(Descartavel descartavel) throws SQLException {
        String sql = "UPDATE descartavel SET nome=?, fabricante=?, quantidade=?, preco_unitario=?, material=?, uso_previsto=?, data_validade=?, esterelizado=?, descartado_apos_uso=?, tipo_descartavel=?, categoria_risco=? WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, descartavel.getNome());
            statement.setString(2, descartavel.getFabricante());
            statement.setInt(3, descartavel.getQuantidade());
            statement.setDouble(4, descartavel.getPrecoUnitario());
            statement.setString(5, descartavel.getMaterial());
            statement.setString(6, descartavel.getUsoPrevisto());
            statement.setTimestamp(7, Timestamp.valueOf(descartavel.getDataValidade()));
            statement.setBoolean(8, descartavel.isEsterelizado());
            statement.setBoolean(9, descartavel.isDescartadoAposUso());
            statement.setString(10, descartavel.getTipoDescartavel().name());
            statement.setString(11, descartavel.getCategoriaRisco().name());
            statement.setLong(12, descartavel.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void remover(Long id) throws SQLException {
        String sql = "DELETE FROM descartavel WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public Descartavel buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM descartavel WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearDescartavel(resultSet);
                }
            }
        }

        return null;
    }

    @Override
    public List<Descartavel> buscarTodos() throws SQLException {
        List<Descartavel> lista = new ArrayList<>();

        String sql = "SELECT * FROM descartavel";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                lista.add(mapearDescartavel(resultSet));
            }
        }

        return lista;
    }

    private Descartavel mapearDescartavel(ResultSet resultSet) throws SQLException {
        return new Descartavel(
                resultSet.getLong("id"),
                resultSet.getString("nome"),
                resultSet.getString("fabricante"),
                resultSet.getInt("quantidade"),
                resultSet.getDouble("preco_unitario"),
                resultSet.getString("material"),
                resultSet.getString("uso_previsto"),
                resultSet.getTimestamp("data_validade").toLocalDateTime(),
                resultSet.getBoolean("esterelizado"),
                resultSet.getBoolean("descartado_apos_uso"),
                TipoDescartavel.valueOf(resultSet.getString("tipo_descartavel")),
                CategoriaRisco.valueOf(resultSet.getString("categoria_risco"))
        );
    }
}
