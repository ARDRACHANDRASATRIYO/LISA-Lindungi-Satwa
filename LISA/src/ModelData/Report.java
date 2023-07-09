package ModelData;

public class Report {
    private String nama;
    private String handphone;
    private String waktuPenemuan;
    private String lokasiPenemuan;
    private String email;
    private String alamat;
    private String namaHewan;
    private String jenisHewan;
    private String kondisiHewan;
    private String ciriCiriHewan;
    private String Status;

    public Report(String nama, String email, String handphone, String alamat, String lokasi, String waktu,
            String namaHewan, String jenisHewan, String kondisiHewan, String ciriHewan, String Status) {
                this.nama = nama;
                this.email = email;
                this.handphone = handphone;
                this.alamat = alamat;
                this.lokasiPenemuan = lokasi;
                this.waktuPenemuan = waktu;
                this.namaHewan = namaHewan;
                this.jenisHewan = jenisHewan;
                this.kondisiHewan = kondisiHewan;
                this.ciriCiriHewan = ciriHewan;
                this.Status = Status;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHandphone() {
        return handphone;
    }

    public void setHandphone(String handphone) {
        this.handphone = handphone;
    }

    public String getWaktuPenemuan() {
        return waktuPenemuan;
    }

    public void setWaktuPenemuan(String waktuPenemuan) {
        this.waktuPenemuan = waktuPenemuan;
    }

    public String getLokasiPenemuan() {
        return lokasiPenemuan;
    }

    public void setLokasiPenemuan(String lokasiPenemuan) {
        this.lokasiPenemuan = lokasiPenemuan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisHewan() {
        return jenisHewan;
    }
    public void setJenisHewan(String jenisHewan) {
        this.jenisHewan = jenisHewan;
    }

    public String getNamaHewan() {
    return namaHewan;
    }

    public void setNamaHewan(String namaHewan) {
        this.namaHewan = namaHewan;
    }

    public String getKondisiHewan() {
        return kondisiHewan;
    }

    public void setKondisiHewan(String kondisiHewan) {
        this.kondisiHewan = kondisiHewan;
    }

    public String getCiriCiriHewan() {
        return ciriCiriHewan;
    }

    public void setCiriCiriHewan(String ciriCiriHewan) {
        this.ciriCiriHewan = ciriCiriHewan;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

}

