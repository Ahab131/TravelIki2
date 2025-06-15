package model;

public class DetailPesanan {
    private int id;
    private int idPesanan;
    private int idLayanan;
    private int kuantitas;
    private double subtotal;

    public DetailPesanan(int id, int idPesanan, int idLayanan, int kuantitas, double subtotal) {
        this.id = id;
        this.idPesanan = idPesanan;
        this.idLayanan = idLayanan;
        this.kuantitas = kuantitas;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(int idPesanan) {
        this.idPesanan = idPesanan;
    }

    public int getIdLayanan() {
        return idLayanan;
    }

    public void setIdLayanan(int idLayanan) {
        this.idLayanan = idLayanan;
    }

    public int getKuantitas() {
        return kuantitas;
    }

    public void setKuantitas(int kuantitas) {
        this.kuantitas = kuantitas;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "DetailPesanan{" +
                "id=" + id +
                ", idPesanan=" + idPesanan +
                ", idLayanan=" + idLayanan +
                ", kuantitas=" + kuantitas +
                ", subtotal=" + subtotal +
                '}';
    }
}
