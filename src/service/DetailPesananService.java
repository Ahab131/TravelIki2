package service;

import java.util.List;

import dao.DetailPesananDAO;
import model.DetailPesanan;

public class DetailPesananService {
    private final DetailPesananDAO detailPesananDAO;

    public DetailPesananService(DetailPesananDAO detailPesananDAO) {
        this.detailPesananDAO = detailPesananDAO;
    }

    public List<DetailPesanan> getAll() {
        // Validasi input
        if (detailPesananDAO == null) {
            throw new IllegalArgumentException("[ DetailPesananDAO tidak boleh null ]");
        }

        List<DetailPesanan> detailPesananList = detailPesananDAO.getAll();

        if (detailPesananList.isEmpty()) {
            throw new IllegalArgumentException("[ Tidak ada detail pesanan yang ditemukan ]");
        }

        return detailPesananList;
    }

    public DetailPesanan getById(int id) {
        DetailPesanan detailPesanan = detailPesananDAO.getById(id);
        if (detailPesanan == null) {
            throw new IllegalArgumentException("[ Detail Pesanan tidak ditemukan ]");
        }

        return detailPesanan;
    }

    public List<DetailPesanan> getByIdPesanan(int idPesanan) {
        // Validasi input
        if (idPesanan <= 0) {
            throw new IllegalArgumentException("[ ID Pesanan tidak boleh kurang dari atau sama dengan 0 ]");
        }

        List<DetailPesanan> detailPesananList = detailPesananDAO.getByIdPesanan(idPesanan);
        if (detailPesananList.isEmpty()) {
            throw new IllegalArgumentException("[ Tidak ada detail pesanan untuk ID Pesanan tersebut ]");
        }

        return detailPesananList;
    }

    public void create(DetailPesanan detailPesanan) {
        // Validasi input
        if (detailPesanan == null) {
            throw new IllegalArgumentException("[ DetailPesanan tidak boleh null ]");
        }

        detailPesananDAO.create(detailPesanan);
    }

    public void update(DetailPesanan detailPesanan) {
        // Validasi input
        if (detailPesanan == null || detailPesanan.getId() <= 0) {
            throw new IllegalArgumentException("[ DetailPesanan tidak boleh null dan ID harus lebih dari 0 ]");
        }

        detailPesananDAO.update(detailPesanan);
    }

    public void delete(int id) {
        // Validasi input
        if (id <= 0) {
            throw new IllegalArgumentException("[ ID tidak boleh kurang dari atau sama dengan 0 ]");
        }

        detailPesananDAO.delete(id);
    }

    public void validateDuplicate(DetailPesanan detailPesanan) {
        // Validasi input
        if (detailPesanan == null || detailPesanan.getIdPesanan() <= 0 || detailPesanan.getIdMenu() <= 0) {
            throw new IllegalArgumentException("[ DetailPesanan tidak boleh null dan ID Pesanan serta ID Menu harus lebih dari 0 ]");
        }

        if (detailPesananDAO.getByIdPesananAndIdMenu(detailPesanan.getIdPesanan(), detailPesanan.getIdMenu()) != null) {
            throw new IllegalArgumentException("[ Detail Pesanan dengan ID Pesanan dan ID Menu tersebut sudah ada ]");
        }
    }

}
