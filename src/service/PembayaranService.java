package service;

import java.util.List;
import java.sql.Date;

import dao.PembayaranDAO;
import model.Pembayaran;
import enums.PembayaranStatus;

public class PembayaranService {
    private final PembayaranDAO pembayaranDAO;

    public PembayaranService(PembayaranDAO pembayaranDAO) {
        this.pembayaranDAO = pembayaranDAO;
    }

    public List<Pembayaran> getAll() {
        // Validasi input
        if (pembayaranDAO == null) {
            throw new IllegalArgumentException("[ PembayaranDAO tidak boleh null ]");
        }

        List<Pembayaran> pembayaranList = pembayaranDAO.getAll();

        if (pembayaranList.isEmpty()) {
            throw new IllegalArgumentException("[ Tidak ada pembayaran yang ditemukan ]");
        }

        return pembayaranList;
    }

    public Pembayaran getById(int id) {
        Pembayaran pembayaran = pembayaranDAO.getById(id);
        if (pembayaran == null) {
            throw new IllegalArgumentException("[ Pembayaran tidak ditemukan ]");
        }

        return pembayaran;
    }

    public Pembayaran getByIdPesanan(int idPesanan) {
        // Validasi input
        if (idPesanan <= 0) {
            throw new IllegalArgumentException("[ ID Pesanan tidak valid ]");
        }

        Pembayaran pembayaran = pembayaranDAO.getByIdPesanan(idPesanan);
        if (pembayaran == null) {
            throw new IllegalArgumentException("[ Pembayaran tidak ditemukan ]");
        }

        return pembayaran;
    }

    public List<Pembayaran> getByStatus(PembayaranStatus status) {
        // Validasi input
        if (status == null) {
            throw new IllegalArgumentException("[ Status tidak boleh kosong ]");
        }

        List<Pembayaran> pembayaranList = pembayaranDAO.getByStatus(status);
        if (pembayaranList.isEmpty()) {
            throw new IllegalArgumentException("[ Tidak ada pembayaran yang ditemukan untuk status ini ]");
        }

        return pembayaranList;
    }

    public void create(int idPesanan, double total, double bayar, Date tanggalPembayaran, PembayaranStatus status) {
        // Validasi input
        validateInput(idPesanan, total, bayar, tanggalPembayaran, status);

        Pembayaran pembayaran = new Pembayaran(0, idPesanan, total, bayar, tanggalPembayaran, status);
        pembayaranDAO.create(pembayaran);
    }
    
    public void update(int id, int idPesanan, double total, double bayar, Date tanggalPembayaran, PembayaranStatus status) {
        // Validasi input
        validateInput(idPesanan, total, bayar, tanggalPembayaran, status);

        Pembayaran pembayaran = new Pembayaran(id, idPesanan, total, bayar, tanggalPembayaran, status);
        pembayaranDAO.update(pembayaran);
    }

    public void delete(int id) {
        // Validasi input
        if (id <= 0) {
            throw new IllegalArgumentException("[ ID tidak valid ]");
        }

        Pembayaran pembayaran = pembayaranDAO.getById(id);
        if (pembayaran == null) {
            throw new IllegalArgumentException("[ Pembayaran tidak ditemukan ]");
        }

        pembayaranDAO.delete(id);
    }

    public void validateInput(int idPesanan, double total, double bayar, Date tanggalPembayaran, PembayaranStatus status) {
        if (idPesanan <= 0) {
            throw new IllegalArgumentException("[ ID Pesanan tidak valid ]");
        }
        if (total <= 0) {
            throw new IllegalArgumentException("[ Total tidak valid ]");
        }
        if (bayar <= 0) {
            throw new IllegalArgumentException("[ Jumlah bayar tidak valid ]");
        }
        if (tanggalPembayaran == null) {
            throw new IllegalArgumentException("[ Tanggal pembayaran tidak boleh kosong ]");
        }
        if (status == null) {
            throw new IllegalArgumentException("[ Status tidak boleh kosong ]");
        }
    }

}
