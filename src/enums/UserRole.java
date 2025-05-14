package enums;

public enum UserRole {
    ADMIN("Admin"),
    CUSTOMER("Customer"),
    EMPLOYEE("Employee");

    private final String deskripsi;

    UserRole(String deskripsi) {
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