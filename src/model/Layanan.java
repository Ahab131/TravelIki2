package model;

import enums.LayananStatus;

public class Layanan {
    private int id;
    private String nama;
    private String deskripsi;
    private double harga;
    private int idKategori;
    private LayananStatus status;

    public Layanan(int id, String nama, String deskripsi, double harga, int idKategori, LayananStatus status) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.idKategori = idKategori;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(int idKategori) {
        this.idKategori = idKategori;
    }

    public LayananStatus getStatus() {
        return status;
    }

    public void setStatus(LayananStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Layanan{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", deskripsi='" + deskripsi + '\'' +
                ", harga=" + harga +
                ", idKategori=" + idKategori +
                ", status=" + status +
                '}';
    }
}