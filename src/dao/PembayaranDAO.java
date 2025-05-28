package dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

import model.Pembayaran;
import config.Database;
import enums.PembayaranStatus;

public class PembayaranDAO {
    public List<Pembayaran> getAll() {
        List<Pembayaran> pembayaranList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM pembayaran";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idPesanan = resultSet.getInt("id_pesanan");
                double total = resultSet.getDouble("total");
                double bayar = resultSet.getDouble("bayar");
                Date tanggalPembayaran = resultSet.getDate("tanggal_pembayaran");
                PembayaranStatus status = PembayaranStatus.valueOf(resultSet.getString("status"));

                Pembayaran pembayaran = new Pembayaran(id, idPesanan, total, bayar, tanggalPembayaran, status);
                pembayaranList.add(pembayaran);
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

        return pembayaranList;
    }

    public Pembayaran getById(int id) {
        Pembayaran pembayaran = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM pembayaran WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int idPesanan = resultSet.getInt("id_pesanan");
                double total = resultSet.getDouble("total");
                double bayar = resultSet.getDouble("bayar");
                Date tanggalPembayaran = resultSet.getDate("tanggal_pembayaran");
                PembayaranStatus status = PembayaranStatus.valueOf(resultSet.getString("status"));

                pembayaran = new Pembayaran(id, idPesanan, total, bayar, tanggalPembayaran, status);
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

        return pembayaran;
    }

    public List<Pembayaran> getByStatus(PembayaranStatus status) {
        List<Pembayaran> pembayaranList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM pembayaran WHERE status = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status.toString());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idPesanan = resultSet.getInt("id_pesanan");
                double total = resultSet.getDouble("total");
                double bayar = resultSet.getDouble("bayar");
                Date tanggalPembayaran = resultSet.getDate("tanggal_pembayaran");

                Pembayaran pembayaran = new Pembayaran(id, idPesanan, total, bayar, tanggalPembayaran, status);
                pembayaranList.add(pembayaran);
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

        return pembayaranList;
    }

    public Pembayaran getByIdPesanan(int idPesanan) {
        Pembayaran pembayaran = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM pembayaran WHERE id_pesanan = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idPesanan);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                double total = resultSet.getDouble("total");
                double bayar = resultSet.getDouble("bayar");
                Date tanggalPembayaran = resultSet.getDate("tanggal_pembayaran");
                PembayaranStatus status = PembayaranStatus.valueOf(resultSet.getString("status"));

                pembayaran = new Pembayaran(id, idPesanan, total, bayar, tanggalPembayaran, status);
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

        return pembayaran;
    }

    public void create(Pembayaran pembayaran) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "INSERT INTO pembayaran (id_pesanan, total, bayar, tanggal_pembayaran, status) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pembayaran.getIdPesanan());
            preparedStatement.setDouble(2, pembayaran.getTotal());
            preparedStatement.setDouble(3, pembayaran.getBayar());
            preparedStatement.setDate(4, pembayaran.getTanggalPembayaran());
            preparedStatement.setString(5, pembayaran.getStatus().toString());
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

    public void update(Pembayaran pembayaran) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = Database.getInstance().getConnection();
            String sql = "UPDATE pembayaran SET id_pesanan = ?, total = ?, bayar = ?, tanggal_pembayaran = ?, status = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pembayaran.getIdPesanan());
            preparedStatement.setDouble(2, pembayaran.getTotal());
            preparedStatement.setDouble(3, pembayaran.getBayar());
            preparedStatement.setDate(4, pembayaran.getTanggalPembayaran());
            preparedStatement.setString(5, pembayaran.getStatus().toString());
            preparedStatement.setInt(6, pembayaran.getId());
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
            String sql = "DELETE FROM pembayaran WHERE id = ?";
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
