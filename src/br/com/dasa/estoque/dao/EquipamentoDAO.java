package br.com.dasa.estoque.dao;

import br.com.dasa.estoque.model.Equipamento;
import java.sql.*;
import java.util.*;

public class EquipamentoDAO implements ProdutoDAO<Equipamento> {
    private Connection connection;

    public EquipamentoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(Equipamento equipamento) throws SQLException {
        String sql = "INSERT INTO equipamento (id, nome, fabricante, quantidade, preco_unitario, numero_serie, setor, data_aquisicao, ultima_manutencao, vida_util_meses, em_uso) VALUES (PRODUTO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, equipamento.getNome());
            statement.setString(2, equipamento.getFabricante());
            statement.setInt(3, equipamento.getQuantidade());
            statement.setDouble(4, equipamento.getPrecoUnitario());
            statement.setString(5, equipamento.getNumeroSerie());
            statement.setString(6, equipamento.getSetor());
            statement.setTimestamp(7, Timestamp.valueOf(equipamento.getDataAquisicao()));
            statement.setTimestamp(8, Timestamp.valueOf(equipamento.getUltimaManutencao()));
            statement.setInt(9, equipamento.getVidaUtilMeses());
            statement.setString(10, equipamento.isEmUso() ? "Y" : "N");
            statement.executeUpdate();
        }
    }

    @Override
    public void atualizar(Equipamento equipamento) throws SQLException {
        String sql = "UPDATE equipamento SET nome=?, fabricante=?, quantidade=?, preco_unitario=?, numero_serie=?, setor=?, data_aquisicao=?, ultima_manutencao=?, vida_util_meses=?, em_uso=? WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, equipamento.getNome());
            statement.setString(2, equipamento.getFabricante());
            statement.setInt(3, equipamento.getQuantidade());
            statement.setDouble(4, equipamento.getPrecoUnitario());
            statement.setString(5, equipamento.getNumeroSerie());
            statement.setString(6, equipamento.getSetor());
            statement.setTimestamp(7, Timestamp.valueOf(equipamento.getDataAquisicao()));
            statement.setTimestamp(8, Timestamp.valueOf(equipamento.getUltimaManutencao()));
            statement.setInt(9, equipamento.getVidaUtilMeses());
            statement.setString(10, equipamento.isEmUso() ? "Y" : "N");
            statement.setLong(11, equipamento.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void remover(Long id) throws SQLException {
        String sql = "DELETE FROM equipamento WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public Equipamento buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM equipamento WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearEquipamento(resultSet);
                }
            }
        }

        return null;
    }

    @Override
    public List<Equipamento> buscarTodos() throws SQLException {
        List<Equipamento> lista = new ArrayList<>();

        String sql = "SELECT * FROM equipamento";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                lista.add(mapearEquipamento(resultSet));
            }
        }

        return lista;
    }

    private Equipamento mapearEquipamento(ResultSet resultSet) throws SQLException {
        String emUsoStr = resultSet.getString("em_uso");
        boolean emUso = "S".equalsIgnoreCase(emUsoStr);

        return new Equipamento(
                resultSet.getLong("id"),
                resultSet.getString("nome"),
                resultSet.getString("fabricante"),
                resultSet.getInt("quantidade"),
                resultSet.getDouble("preco_unitario"),
                resultSet.getString("numero_serie"),
                resultSet.getString("setor"),
                resultSet.getTimestamp("data_aquisicao").toLocalDateTime(),
                resultSet.getTimestamp("ultima_manutencao").toLocalDateTime(),
                resultSet.getInt("vida_util_meses"),
                emUso
        );
    }
}
