package Controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import ModelData.Report;

public class ReportingController implements Initializable {
   
    @FXML
    private TextField namaTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField handphoneTextField;

    @FXML
    private TextField alamatTextField;

    @FXML
    private TextField lokasiTextField;

    @FXML
    private TextField waktuTextField;

    @FXML
    private TextField namaHewanTextField;

    @FXML
    private TextField jenisHewanTextField;

    @FXML
    private TextField kondisiHewanTextField;

    @FXML
    private TextField ciriHewanTextField;

    @FXML
    private Button reportButton;

    @FXML
    private Button kirimButton;

    @FXML
    private Button resetButton;

    @FXML
    private void handleReportButton(ActionEvent event) throws IOException {
        OpenScene open = new OpenScene((Stage)((Node)event.getSource()).getScene().getWindow());
        open.openScene("/FXML/MyReporting.fxml");
    }

    @FXML
    private void handleResetButton(ActionEvent event) throws IOException {
        namaTextField.clear();
        emailTextField.clear();
        handphoneTextField.clear();
        alamatTextField.clear();
        lokasiTextField.clear();
        waktuTextField.clear();
        namaHewanTextField.clear();
        jenisHewanTextField.clear();
        kondisiHewanTextField.clear();
        ciriHewanTextField.clear();
    }

    @FXML
private void handleKirimButton(ActionEvent event) throws IOException {
    // Membaca data dari file XML
    ArrayList<Report> listReport = readDataFromXML();

    // Membuat objek Report baru
    String status = "Di Proses";
    String nama = namaTextField.getText();
    String email = emailTextField.getText();
    String handphone = handphoneTextField.getText();
    String alamat = alamatTextField.getText();
    String lokasi = lokasiTextField.getText();
    String waktu = waktuTextField.getText();
    String namaHewan = namaHewanTextField.getText();
    String jenisHewan = jenisHewanTextField.getText();
    String kondisiHewan = kondisiHewanTextField.getText();
    String ciriHewan = ciriHewanTextField.getText();

    Report report = new Report(nama, email, handphone, alamat, lokasi, waktu, namaHewan, jenisHewan, kondisiHewan, ciriHewan, status);
    report.setNama(nama);
    report.setEmail(email);
    report.setHandphone(handphone);
    report.setAlamat(alamat);
    report.setLokasiPenemuan(lokasi);
    report.setWaktuPenemuan(waktu);
    report.setNamaHewan(namaHewan);
    report.setJenisHewan(jenisHewan);
    report.setKondisiHewan(kondisiHewan);
    report.setCiriCiriHewan(ciriHewan);
    report.setStatus(status);

    // Menambahkan data baru ke ArrayList
    listReport.add(report);

    // Menulis seluruh ArrayList ke file XML
    writeDataToXML(listReport);

    System.out.println("Data berhasil disimpan");
}

    
    private ArrayList<Report> readDataFromXML() {
        ArrayList<Report> listReport = new ArrayList<>();
        XStream xStream = new XStream(new StaxDriver());
        xStream.addPermission(AnyTypePermission.ANY);
        FileInputStream data = null;
        try {
            data = new FileInputStream("Report.xml");
    
            int isi;
            char c;
            String stringnya = "";
    
            while ((isi = data.read()) != -1) {
                c = (char) isi;
                stringnya += c;
            }
            ArrayList<Report> list = (ArrayList<Report>) xStream.fromXML(stringnya);
            listReport = list;
    
        } catch (Exception e) {
            System.err.println("test: " + e.getMessage());
        } finally {
            if (data != null) {
                try {
                    data.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listReport;
    }
    
    private void writeDataToXML(ArrayList<Report> listReport) {
        XStream xStream = new XStream(new StaxDriver());
        xStream.addPermission(AnyTypePermission.ANY);
        FileOutputStream dataReport = null;
    
        try {
            dataReport = new FileOutputStream("Report.xml");
            String xml = xStream.toXML(listReport);
            byte[] bytes = xml.getBytes("UTF-8");
            dataReport.write(bytes);
        } catch (Exception e) {
            System.out.println("Perhatian: " + e.getMessage());
        } finally {
            if (dataReport != null) {
                try {
                    dataReport.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
