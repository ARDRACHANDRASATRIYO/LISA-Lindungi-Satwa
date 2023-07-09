package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import ModelData.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MyReportingController implements Initializable {
    private ObservableList<Report> reports = FXCollections.observableArrayList();

    @FXML
    private TableView<Report> tableView;

    @FXML
    private TableColumn<Report, String> dateColumn;

    @FXML
    private TableColumn<Report, String> nameColumn;

    @FXML
    private TableColumn<Report, String> handphoneColumn;

    @FXML
    private TableColumn<Report, String> emailColumn;

    @FXML
    private TableColumn<Report, String> namaHewanColumn;

    @FXML
    private TableColumn<Report, String> lokasiColumn;
    
    @FXML
    private TableColumn<Report, String> kondisiColumn;

    @FXML
    private TableColumn<Report, String> ciriColumn;

    @FXML
    private TableColumn<Report, String> statusColumn;

    @FXML
    private Button reportingButton;

    @FXML
    private Button reportButton;

    @FXML
    private Button batalButton;

    @FXML
    private void handleButtonReporting(ActionEvent event) throws IOException {
        OpenScene open = new OpenScene((Stage) ((Node) event.getSource()).getScene().getWindow());
        open.openScene("/FXML/Dashboard.fxml");
    }

    @FXML
    private void handleButtonReport(ActionEvent event) throws IOException {
        OpenScene open = new OpenScene((Stage) ((Node) event.getSource()).getScene().getWindow());
        open.openScene("/FXML/Reporting.fxml");
    }

    @FXML
    private void handleBatalButton(ActionEvent event) throws IOException {
        removeSelectedReportAndUpdateXML();
    }

    public List<Report> readReportsFromXML(String filePath) {
        XStream xstream = new XStream(new StaxDriver());
        xstream.alias("ModelData.Report", Report.class);

        // Konfigurasi izin untuk kelas ModelData.Report
        XStream.setupDefaultSecurity(xstream);
        xstream.addPermission(AnyTypePermission.ANY);

        try {
            FileInputStream data = new FileInputStream(filePath);
            return (List<Report>) xstream.fromXML(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void removeSelectedReportAndUpdateXML() {
        Report selectedReport = tableView.getSelectionModel().getSelectedItem();
        if (selectedReport != null) {
            tableView.getItems().remove(selectedReport);
            ArrayList<Report> listReport = new ArrayList<>(tableView.getItems());
            writeReportsToXML(listReport);
        }
    }

    private void writeReportsToXML(ArrayList<Report> listReport) {
        XStream xstream = new XStream(new StaxDriver());
        xstream.alias("ModelData.Report", Report.class);

        // Konfigurasi izin untuk kelas ModelData.Report
        XStream.setupDefaultSecurity(xstream);
        xstream.addPermission(AnyTypePermission.ANY);

        try {
            xstream.toXML(listReport, new FileOutputStream("Report.xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("WaktuPenemuan"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nama"));
        handphoneColumn.setCellValueFactory(new PropertyValueFactory<>("handphone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        namaHewanColumn.setCellValueFactory(new PropertyValueFactory<>("NamaHewan"));
        kondisiColumn.setCellValueFactory(new PropertyValueFactory<>("kondisiHewan"));
        ciriColumn.setCellValueFactory(new PropertyValueFactory<>("ciriCiriHewan"));
        lokasiColumn.setCellValueFactory(new PropertyValueFactory<>("lokasiPenemuan"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        reports = FXCollections.observableArrayList(readReportsFromXML("Report.xml"));
        tableView.setItems(reports);
    }
}
