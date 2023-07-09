package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import ModelData.PopulasiHewan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AnimalDataController implements Initializable{
    
    @FXML
    private Label namaHewan;
    
    @FXML
    private Label namaIlmiah;
    
    @FXML
    private Label statusDaerah;
    
    @FXML
    private Label statusPopulasi;

    @FXML
    private Label kerajaan;

    @FXML
    private Label filum;

    @FXML
    private Label kelas;

    @FXML
    private Label ordo;

    @FXML
    private Label famili;

    @FXML
    private Label genus;

    @FXML
    private Label species;

    @FXML
    private Label jangkauan;
    
    @FXML
    private Text isiTentang;

    @FXML
    private BarChart barChartPopulasi;

    @FXML
    private ImageView imageHewan;
    
    @FXML
    private Button animalDataButton;

    public void setAnimalData(String namaHewan, String namaIlmiah, String statusDaerah, String statusPopulasi, String isiTentang, String kerajaan, String filum, String kelas, String ordo, String famili, String genus, String species, String jangkauan) {
        this.namaHewan.setText(namaHewan);
        this.namaIlmiah.setText(namaIlmiah);
        this.statusDaerah.setText(statusDaerah);
        this.statusPopulasi.setText(statusPopulasi);
        this.isiTentang.setText(isiTentang);
        this.kerajaan.setText(kerajaan);
        this.filum.setText(filum);
        this.kelas.setText(kelas);
        this.ordo.setText(ordo);
        this.famili.setText(famili);
        this.genus.setText(genus);
        this.species.setText(species);
        this.jangkauan.setText(jangkauan);
    }

    public void setAnimalImage(Image image) {
        imageHewan.setImage(image);
    }
    
    public void setChartData(List<PopulasiHewan> dataPopulasi) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (PopulasiHewan populasi : dataPopulasi) {
            series.getData().add(new XYChart.Data<>(String.valueOf(populasi.getTahun()), populasi.getJumlahPopulasi()));
        }
        barChartPopulasi.getData().add(series);
    }
    


    private String lastSelectedLocation;

    public void setLastSelectedLocation(String location) {
        lastSelectedLocation = location;
    }

    @FXML
    private void handleButtonAnimal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Dashboard.fxml"));
        Parent root = loader.load();
        DashboardController dashboardController = loader.getController();

        // Mengatur pilihan terakhir pada ChoiceBox di DashboardController
        dashboardController.setLastSelectedLocation(lastSelectedLocation);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public List<PopulasiHewan> getDataPopulasiFromXML(String filePath) {
    XStream xstream = new XStream(new StaxDriver());
    xstream.processAnnotations(PopulasiHewan.class);
    xstream.addPermission(AnyTypePermission.ANY);

    FileInputStream fileInputStream = null;
    try {
        fileInputStream = new FileInputStream(filePath);
        List<PopulasiHewan> data = (List<PopulasiHewan>) xstream.fromXML(fileInputStream);
        return data;
    } catch (FileNotFoundException e) {
        e.printStackTrace();
        return null;
    } finally {
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

@Override
public void initialize(URL location, ResourceBundle resources) {
    String filePath = "PopulasiHewan.xml"; // Ubah path sesuai dengan path file XML Anda
    List<PopulasiHewan> dataPopulasi = getDataPopulasiFromXML(filePath);
    setChartData(dataPopulasi);
}

}