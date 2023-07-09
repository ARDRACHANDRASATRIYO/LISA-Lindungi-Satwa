package ModelData;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("PopulasiHewan")
public class PopulasiHewan {
    private String tahun;
    private int jumlahPopulasi;

    public PopulasiHewan(String tahun, int jumlahPopulasi) {
        this.tahun = tahun;
        this.jumlahPopulasi = jumlahPopulasi;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public int getJumlahPopulasi() {
        return jumlahPopulasi;
    }

    public void setJumlahPopulasi(int jumlahPopulasi) {
        this.jumlahPopulasi = jumlahPopulasi;
    }
}
