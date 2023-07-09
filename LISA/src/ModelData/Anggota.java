package ModelData;

public class Anggota {
    private String Nama;
    private String email;
    private String password;
    private String handphone;

    public Anggota(String Nama, String email, String password, String handphone) {
        this.Nama = Nama;
        this.email = email;
        this.password = password;
        this.handphone = handphone;
    }

    public String getNama() {
        return Nama;
    }

    public void setUsername(String Nama) {
        this.Nama = Nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHandphone() {
        return handphone;
    }

    public void setHandphone (String handphone) {
        this.handphone = handphone;
    }
}