package service;

import java.util.List;

import dao.LayananDAO;
import model.Layanan;

public class LayananService {
    private LayananDAO layananDAO;

    public LayananService(LayananDAO layananDAO) {
        this.layananDAO = layananDAO;
    }

    public List<Layanan> getAll() {
        return layananDAO.getAll();
    }

    public Layanan getById(int id) {
        return layananDAO.getById(id);
    }

    public List<Layanan> getByKategori(int idKategori) {
        return layananDAO.getByKategori(idKategori);
    }

    public List<Layanan> getByStatus(String status) {
        return layananDAO.getByStatus(status);
    }

    public void create(Layanan layanan) {
        layananDAO.create(layanan);
    }

    public void update(Layanan layanan) {
        layananDAO.update(layanan);
    }

    public void delete(int id) {
        layananDAO.delete(id);
    }
}
