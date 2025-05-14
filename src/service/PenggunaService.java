package service;

import java.util.List;

import dao.PenggunaDAO;
import model.Pengguna;

// service berisikan logika bisnis dan berfungsi sebagai jembatan antara controller dan DAO
// service tidak berinteraksi langsung dengan database
// service berfungsi untuk memisahkan logika bisnis dari logika akses data

public class PenggunaService {
    private final PenggunaDAO penggunaDAO;

    public PenggunaService(PenggunaDAO penggunaDAO) {
        this.penggunaDAO = penggunaDAO;
    }

    public List<Pengguna> getAll() {
        return penggunaDAO.getAll();
    }

    public Pengguna getById(int id) {
        return penggunaDAO.getById(id);
    }

    public Pengguna getByUsername(String username) {
        return penggunaDAO.getByUsername(username);
    }

    public List<Pengguna> getByRole(String role) {
        return penggunaDAO.getByRole(role);
    }

    public void create(Pengguna pengguna) {
        penggunaDAO.create(pengguna);
    }

    public void update(Pengguna pengguna) {
        penggunaDAO.update(pengguna);
    }

    public void delete(int id) {
        penggunaDAO.delete(id);
    }
}
