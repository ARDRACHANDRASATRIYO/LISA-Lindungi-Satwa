package Controller;

import ModelData.PopulasiHewan;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InputBarChartController implements Initializable{
    @FXML
    private TextField tahunTextField;
    
    @FXML
    private TextField populasiTextField;

    @FXML
    private Button backButton;
    
    @FXML
    private Button saveButton;
    
    @FXML
    private void handleDonationButtonClick(ActionEvent event) throws IOException{
        OpenScene open = new OpenScene((Stage)((Node)event.getSource()).getScene().getWindow());
        open.openScene("/FXML/Dashboard.fxml");
    }
    
     @FXML
    private void handleSaveButtonClick(ActionEvent event) throws IOException{
        String tahunStr = tahunTextField.getText();
        String populasiStr = populasiTextField.getText();
        
        if (!tahunStr.isEmpty() && !populasiStr.isEmpty()) {
            String tahun = tahunTextField.getText();
            int populasi = Integer.parseInt(populasiStr);
            
            ArrayList<PopulasiHewan> list = new ArrayList<>();
            XStream xstream = new XStream(new StaxDriver());
            xstream.processAnnotations(PopulasiHewan.class);
            
            FileInputStream data = null;
            try {
                data = new FileInputStream("PopulasiHewan.xml");
                
                int isi;
                char c;
                String stringnya = "";
                
                while ((isi = data.read()) != -1) {
                    c = (char) isi;
                    stringnya += c;
                }
                
                list = (ArrayList<PopulasiHewan>) xstream.fromXML(stringnya);
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
            
            list.add(new PopulasiHewan(tahun, populasi));
            
            FileOutputStream dataUser = null;
            try {
                dataUser = new FileOutputStream("PopulasiHewan.xml");
                String xml = xstream.toXML(list);
                byte[] bytes = xml.getBytes("UTF-8");
                dataUser.write(bytes);
            } catch (Exception e) {
                System.out.println("Perhatian: " + e.getMessage());
            } finally {
                if (dataUser != null) {
                    try {
                        dataUser.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            
            System.out.println("Data berhasil disimpan");
            
            // ...
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
}
