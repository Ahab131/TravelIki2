package enums;

public enum PembayaranStatus {
    PENDING("Pending"), // menunggu pembayaran
    PAID("Paid"),
    FAILED("Failed");

    private final String deskripsi;

    PembayaranStatus(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    @Override
    public String toString() {
        return deskripsi;
    }
}
