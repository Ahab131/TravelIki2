package repository;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

import model.DetailPesanan;
import config.Database;

public class DetailPesananRepository {
    public List<DetailPesanan> getAll() {
        List<DetailPesanan> detailPesananList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM detail_pesanan";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idDetailPesanan = resultSet.getInt("id_detail_pesanan");
                int idPesanan = resultSet.getInt("id_pesanan");
                int idLayanan = resultSet.getInt("id_layanan");
                int kuantitas = resultSet.getInt("kuantitas");
                double subtotal = resultSet.getDouble("subtotal");

                DetailPesanan detailPesanan = new DetailPesanan(idDetailPesanan, idPesanan, idLayanan, kuantitas,
                        subtotal);
                detailPesananList.add(detailPesanan);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return detailPesananList;
    }

    public DetailPesanan getById(int id) {
        DetailPesanan detailPesanan = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM detail_pesanan WHERE id_detail_pesanan = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int idPesanan = resultSet.getInt("id_pesanan");
                int idLayanan = resultSet.getInt("id_layanan");
                int kuantitas = resultSet.getInt("kuantitas");
                double subtotal = resultSet.getDouble("subtotal");

                detailPesanan = new DetailPesanan(id, idPesanan, idLayanan, kuantitas, subtotal);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return detailPesanan;
    }

    public List<DetailPesanan> getByIdPesanan(int idPesanan) {
        List<DetailPesanan> detailPesananList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM detail_pesanan WHERE id_pesanan = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idPesanan);
            resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {
                int idDetailPesanan = resultSet.getInt("id_detail_pesanan");
                int idLayanan = resultSet.getInt("id_layanan");
                int kuantitas = resultSet.getInt("kuantitas");
                double subtotal = resultSet.getDouble("subtotal");

                DetailPesanan detailPesanan = new DetailPesanan(idDetailPesanan, idPesanan, idLayanan, kuantitas,
                        subtotal);
                detailPesananList.add(detailPesanan);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return detailPesananList;
    }

    public void create(DetailPesanan detailPesanan) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "INSERT INTO detail_pesanan (id_pesanan, id_layanan, kuantitas, subtotal) VALUES (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, detailPesanan.getIdPesanan());
            preparedStatement.setInt(2, detailPesanan.getIdLayanan());
            preparedStatement.setInt(3, detailPesanan.getKuantitas());
            preparedStatement.setDouble(4, detailPesanan.getSubtotal());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(DetailPesanan detailPesanan) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "UPDATE detail_pesanan SET id_pesanan = ?, id_layanan = ?, kuantitas = ?, subtotal = ? WHERE id_detail_pesanan = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, detailPesanan.getIdPesanan());
            preparedStatement.setInt(2, detailPesanan.getIdLayanan());
            preparedStatement.setInt(3, detailPesanan.getKuantitas());
            preparedStatement.setDouble(4, detailPesanan.getSubtotal());
            preparedStatement.setInt(5, detailPesanan.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "DELETE FROM detail_pesanan WHERE id_detail_pesanan = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
