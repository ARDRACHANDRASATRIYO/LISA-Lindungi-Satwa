package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import ModelData.PopulasiHewan;


public class DashboardController implements Initializable {

    private String kota[] = { "Kota Yogyakarta", "Kabupaten Sleman", "Kabupaten Bantul", "Kabupaten KulonProgo", "Kabupaten Gunung Kidul" };
    
    private String lastSelectedLocation;
    
    @FXML
    private Button reportingButton;

    @FXML
    private Button donationButton;

    @FXML
    private Button aboutButton;

    @FXML
    private Button logoutButton;

    @FXML
    private ChoiceBox<String> locationChoiceBox;

    @FXML
    private ImageView logoImageView;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Button button10;

    @FXML
    private Button saveButton;

    @FXML
    private ImageView backgroundImageView;
    
    @FXML
    private ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10;

    private List<Image> backgroundImageList;

    private int currentIndex = 0;
    private final int NUM_BACKGROUND_IMAGES = 5;
    private final Duration BACKGROUND_DURATION = Duration.seconds(5);
    private Timeline backgroundTimeline;

    public List<PopulasiHewan> readDataFromXML(String filePath) {
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

    private void setChartData(List<PopulasiHewan> dataPopulasi) {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (PopulasiHewan populasi : dataPopulasi) {
            series.getData().add(new XYChart.Data<>(populasi.getTahun(), populasi.getJumlahPopulasi()));
        }
        AnimalDataController animalDataController = getAnimalDataController();
        if (animalDataController != null) {
            animalDataController.setChartData(dataPopulasi);
        }
    }

    @FXML
    void handleButtonReporting(ActionEvent event) throws IOException {
        OpenScene open = new OpenScene((Stage)((Node)event.getSource()).getScene().getWindow());
        open.openScene("/FXML/MyReporting.fxml");
    }

    @FXML
    void handleButtonAbout(ActionEvent event) throws IOException {
        OpenScene open = new OpenScene((Stage)((Node)event.getSource()).getScene().getWindow());
        open.openScene("/FXML/About.fxml");
    }

    @FXML
    void handleButtonDonation(ActionEvent event) throws IOException {
        OpenScene open = new OpenScene((Stage)((Node)event.getSource()).getScene().getWindow());
        open.openScene("/FXML/Donation.fxml");
    }

    @FXML
    void handleButtonLogout(ActionEvent event) throws IOException {
        OpenScene open = new OpenScene((Stage)((Node)event.getSource()).getScene().getWindow());
        open.openScene("/FXML/WelcomePage.fxml");
    }

    @FXML
    void handleSaveButtonLocation(ActionEvent event) throws IOException {
        lastSelectedLocation  = locationChoiceBox.getValue();
        switch ((String) locationChoiceBox.getValue()) {
                case "Kota Yogyakarta":
                button1.setText("Lutung Jawa");
                button2.setText("Surili");
                button3.setText("Kijang Jawa");
                button4.setText("Banteng Jawa");
                button5.setText("Merak Jawa");
                button6.setText("Pelanduk Jawa");
                button7.setText("Berang-berang Jawa");
                button8.setText("Kucing Hutan Jawa");
                button9.setText("Kelelawar Seram");
                button10.setText("Kepodang Jawa");

                img1.setImage(new Image("/Photo/Lutung 1 1.png"));
                img2.setImage(new Image("/Photo/Surili 1.png"));
                img3.setImage(new Image("/Photo/Kijang Jawa 1.png"));
                img4.setImage(new Image("/Photo/Banteng Jawa 1.png"));
                img5.setImage(new Image("/Photo/Merak Jawa 1.png"));
                img6.setImage(new Image("/Photo/"));
                img7.setImage(new Image("/Photo/Berang Berang Jawa 1.png"));
                img8.setImage(new Image("/Photo/Kucing Hutan Jawa 1.png"));
                img9.setImage(new Image("/Photo/Kelelawar Seram 1.png"));
                img10.setImage(new Image("/Photo/Kepodang Jawa 1.png"));
                break;

                case "Kabupaten Sleman":
                button1.setText("Kucing Hutan Jawa");
                button2.setText("Kijang Jawa");
                button3.setText("Surili");
                button4.setText("Kapodang Jawa");
                button5.setText("Lutung Jawa");
                button6.setText("Kedidi Damar");
                button7.setText("Landak Jawa");
                button8.setText("Kadal Gunung Jawa");
                button9.setText("Kancil Jawa");
                button10.setText("Kadal Pasir Damar");

                // Mengatur gambar untuk setiap button
                img1.setImage(new Image("/Photo/Kucing Hutan Jawa 1.png"));
                img2.setImage(new Image("/Photo/Kijang Jawa 1.png"));
                img3.setImage(new Image("/Photo/Surili 1.png"));
                img4.setImage(new Image("/Photo/Kepodang Jawa 1.png"));
                img5.setImage(new Image("/Photo/Lutung 1 1.png"));
                img6.setImage(new Image("/Photo/Kedidi Damar 1.png"));
                img7.setImage(new Image("/Photo/Landak Jawa 1.png"));
                img8.setImage(new Image("/Photo/"));
                img9.setImage(new Image("/Photo/Kancil Jawa 1.png"));
                img10.setImage(new Image("/Photo/Kadal 1.png"));
                break;
                case "Kabupaten Bantul":
                button1.setText("Owa Jawa");
                button2.setText("Kucing Hutan Jawa");
                button3.setText("Surili");
                button4.setText("Kijang Jawa");
                button5.setText("Banteng Jawa");
                button6.setText("Merak Jawa");
                button7.setText("Jalak Putih");
                button8.setText("Kancil Jawa");
                button9.setText("Kadal Pasir Damar");
                button10.setText("Babi Rusa");
            
                img1.setImage(new Image("/Photo/Owa Jawa 1.png"));
                img2.setImage(new Image("/Photo/Kucing Hutan Jawa 1.png"));
                img3.setImage(new Image("/Photo/Surili 1.png"));
                img4.setImage(new Image("/Photo/Kijang Jawa 1.png"));
                img5.setImage(new Image("/Photo/Banteng Jawa 1.png"));
                img6.setImage(new Image("/Photo/Merak Jawa 1.png"));
                img7.setImage(new Image("/Photo/Jalak Putih 1.png"));
                img8.setImage(new Image("/Photo/Kancil Jawa 1.png"));
                img9.setImage(new Image("/Photo/Kadal 1.png"));
                img10.setImage(new Image("/Photo/Babi Rusa 1.png"));
                break;
            
                case "Kabupaten KulonProgo":
                button1.setText("Banteng Jawa");
                button2.setText("Kucing Hutan Jawa");
                button3.setText("Surili");
                button4.setText("Kijang Jawa");
                button5.setText("Merak Jawa");
                button6.setText("Owa Jawa");
                button7.setText("Landak Jawa");
                button8.setText("Jalak Putih");
                button9.setText("Kancil Jawa");
                button10.setText("Kadal Pasir Damar");
            
                img1.setImage(new Image("/Photo/Banteng Jawa 1.png"));
                img2.setImage(new Image("/Photo/Kucing Hutan Jawa 1.png"));
                img3.setImage(new Image("/Photo/Surili 1.png"));
                img4.setImage(new Image("/Photo/Kijang Jawa 1.png"));
                img5.setImage(new Image("/Photo/Merak Jawa 1.png"));
                img6.setImage(new Image("/Photo/Owa Jawa 1.png"));
                img7.setImage(new Image("/Photo/Landak Jawa 1.png"));
                img8.setImage(new Image("/Photo/Jalak Putih 1.png"));
                img9.setImage(new Image("/Photo/Kancil Jawa 1.png"));
                img10.setImage(new Image("/Photo/Kadal 1.png"));
                break;
            
                case "Kabupaten Gunung Kidul":
                button1.setText("Kucing Hutan Jawa");
                button2.setText("Banteng Jawa");
                button3.setText("Landak Jawa");
                button4.setText("Kijang Jawa");
                button5.setText("Merak Jawa");
                button6.setText("Jalak Putih");
                button7.setText("Surili");
                button8.setText("Owa Jawa");
                button9.setText("Babi Rusa");
                button10.setText("Kancil Jawa");
            
                img1.setImage(new Image("/Photo/Kucing Hutan Jawa 1.png"));
                img2.setImage(new Image("/Photo/Banteng Jawa 1.png"));
                img3.setImage(new Image("/Photo/Landak Jawa 1.png"));
                img4.setImage(new Image("/Photo/Kijang Jawa 1.png"));
                img5.setImage(new Image("/Photo/Merak Jawa 1.png"));
                img6.setImage(new Image("/Photo/Jalak Putih 1.png"));
                img7.setImage(new Image("/Photo/Surili 1.png"));
                img8.setImage(new Image("/Photo/Owa Jawa 1.png"));
                img9.setImage(new Image("/Photo/Babi Rusa 1.png"));
                img10.setImage(new Image("/Photo/Kancil Jawa 1.png"));
                break;
            
        }
    }

    @FXML
    private void handleButton1(ActionEvent event) throws IOException {
    String selectedLocation = locationChoiceBox.getValue();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AnimalData.fxml"));
    Parent root = loader.load();
    AnimalDataController controller = loader.getController();

    if (selectedLocation.equals("Kabupaten Sleman")) {
        controller.setAnimalData("Kucing Hutan Jawa", "Felis javanensis", "Asli", "Terancam", "Kucing Hutan Jawa adalah kucing endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Carnivora", "Felidae", "Felis", "javanensis", "Hutan-hutan di Jawa, termasuk Kabupaten Sleman.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Kucing Hutan Jawa 2.png"));
        controller.setAnimalImage(image);
        String filePath = "/path/to/your/xml/file.xml";
        List<PopulasiHewan> dataPopulasi = readDataFromXML(filePath);
        controller.setChartData(dataPopulasi);

    } else if (selectedLocation.equals("Kota Yogyakarta")) {
        controller.setAnimalData("Lutung Jawa", "Trachypithecus auratus", "Asli", "Terancam", "Lutung Jawa adalah primata endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Primates", "Cercopithecidae", "Trachypithecus", "auratus", "Hutan-hutan di Jawa, termasuk wilayah Kota Yogyakarta.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Lutung 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Bantul")) {
        controller.setAnimalData("Owa Jawa", "Hylobates moloch", "Asli", "Terancam", "Owa Jawa adalah owa endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Primates", "Hylobatidae", "Hylobates", "moloch", "Hutan-hutan di Jawa, termasuk Kabupaten Bantul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Owa Jawa 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten KulonProgo")) {
        controller.setAnimalData("Banteng Jawa", "Bos javanicus", "Asli", "Terancam", "Banteng Jawa adalah banteng endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Artiodactyla", "Bovidae", "Bos", "javanicus", "Hutan-hutan di Jawa, termasuk Kabupaten Kulonprogo.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Banteng Jawa 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Gunung Kidul")) {
        controller.setAnimalData("Kucing Hutan Jawa", "Felis javanensis", "Asli", "Terancam", "Kucing Hutan Jawa adalah kucing endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Carnivora", "Felidae", "Felis", "javanensis", "Hutan-hutan di Jawa, termasuk Kabupaten Gunung Kidul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Kucing Hutan Jawa 2.png"));
        controller.setAnimalImage(image);
    }

    // Menampilkan scene AnimalData.fxml
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.show();
}

    @FXML
    private void handleButton2(ActionEvent event) throws IOException {
    String selectedLocation = locationChoiceBox.getValue();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AnimalData.fxml"));
    Parent root = loader.load();
    AnimalDataController controller = loader.getController();

    if (selectedLocation.equals("Kota Yogyakarta")) {
        controller.setAnimalData("Surili", "Presbytis comata", "Asli", "Terancam", "Surili adalah monyet endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Primates", "Cercopithecidae", "Presbytis", "comata", "Hutan-hutan di Jawa, termasuk wilayah Kota Yogyakarta.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Surili 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Sleman")) {
        controller.setAnimalData("Kijang Jawa", "Muntiacus muntjak", "Asli", "Terancam", "Kijang Jawa adalah kijang endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Artiodactyla", "Cervidae", "Muntiacus", "muntjak", "Hutan-hutan di Jawa, termasuk Kabupaten Sleman.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Kijang Jawa 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Bantul")) {
        controller.setAnimalData("Kucing Hutan Jawa", "Felis javanensis", "Asli", "Terancam", "Kucing Hutan Jawa adalah kucing endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Carnivora", "Felidae", "Felis", "javanensis", "Hutan-hutan di Jawa, termasuk Kabupaten Bantul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Kucing Hutan Jawa 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten KulonProgo")) {
        controller.setAnimalData("Kucing Hutan Jawa", "Felis javanensis", "Asli", "Terancam", "Kucing Hutan Jawa adalah kucing endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Carnivora", "Felidae", "Felis", "javanensis", "Hutan-hutan di Jawa, termasuk Kabupaten Bantul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Kucing Hutan Jawa 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Gunung Kidul")) {
        controller.setAnimalData("Banteng Jawa", "Bos javanicus", "Asli", "Terancam", "Banteng Jawa adalah banteng endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Artiodactyla", "Bovidae", "Bos", "javanicus", "Hutan-hutan di Jawa, termasuk Kabupaten Gunung Kidul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Banteng Jawa 2.png"));
        controller.setAnimalImage(image);
    }

    // Menampilkan scene AnimalData.fxml
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.show();
}

    @FXML
    private void handleButton3(ActionEvent event) throws IOException {
    String selectedLocation = locationChoiceBox.getValue();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AnimalData.fxml"));
    Parent root = loader.load();
    AnimalDataController controller = loader.getController();

    if (selectedLocation.equals("Kota Yogyakarta")) {
        controller.setAnimalData("Kijang Jawa", "Muntiacus muntjak", "Asli", "Terancam", "Kijang Jawa adalah kijang endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Artiodactyla", "Cervidae", "Muntiacus", "muntjak", "Hutan-hutan di Jawa, termasuk wilayah Kota Yogyakarta.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Kijang Jawa 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Sleman")) {
        controller.setAnimalData("Surili", "Presbytis comata", "Asli", "Terancam", "Surili adalah monyet endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Primates", "Cercopithecidae", "Presbytis", "comata", "Hutan-hutan di Jawa, termasuk Kabupaten Sleman.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Surili 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Bantul")) {
        controller.setAnimalData("Surili", "Presbytis comata", "Asli", "Terancam", "Surili adalah monyet endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Primates", "Cercopithecidae", "Presbytis", "comata", "Hutan-hutan di Jawa, termasuk Kabupaten Bantul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Surili 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten KulonProgo")) {
        controller.setAnimalData("Surili", "Presbytis comata", "Asli", "Terancam", "Surili adalah monyet endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Primates", "Cercopithecidae", "Presbytis", "comata", "Hutan-hutan di Jawa, termasuk Kabupaten Kulonprogo.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Surili 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Gunung Kidul")) {
        controller.setAnimalData("Landak Jawa", "Hystrix javanica", "Asli", "Terancam", "Landak Jawa adalah landak endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Rodentia", "Hystricidae", "Hystrix", "javanica", "Hutan-hutan di Jawa, termasuk Kabupaten Gunung Kidul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Landak Jawa 2.png"));
        controller.setAnimalImage(image);
    }

    // Menampilkan scene AnimalData.fxml
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.show();
}

    @FXML
    private void handleButton4(ActionEvent event) throws IOException {
        String selectedLocation = locationChoiceBox.getValue();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AnimalData.fxml"));
        Parent root = loader.load();
        AnimalDataController controller = loader.getController();
    
        if (selectedLocation.equals("Kota Yogyakarta")) {
            controller.setAnimalData("Banteng Jawa", "Bos javanicus", "Asli", "Terancam", "Banteng Jawa adalah banteng endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Artiodactyla", "Bovidae", "Bos", "javanicus", "Hutan-hutan di Jawa, termasuk Kabupaten Kulonprogo.");
            Image image = new Image(getClass().getResourceAsStream("/Photo/Banteng Jawa 2.png"));
            controller.setAnimalImage(image);
        } else if (selectedLocation.equals("Kabupaten Sleman")) {
            controller.setAnimalData("Kepodang Jawa", "Dicrurus angelica", "Asli", "Terancam", "Kepodang Jawa adalah kepodang endemik Jawa...", "Animalia", "Chordata", "Aves", "Passeriformes", "Dicruridae", "Dicrurus", "angelica", "Hutan dan hutan sekunder di Jawa, termasuk wilayah Kota Yogyakarta.");
            Image image = new Image(getClass().getResourceAsStream("/Photo/"));
            controller.setAnimalImage(image);
        } else if (selectedLocation.equals("Kabupaten Bantul")) {
            controller.setAnimalData("Kijang Jawa", "Muntiacus muntjak", "Asli", "Terancam", "Kijang Jawa adalah kijang endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Artiodactyla", "Cervidae", "Muntiacus", "muntjak", "Hutan-hutan di Jawa, termasuk Kabupaten Kulonprogo.");
            Image image = new Image(getClass().getResourceAsStream("/Photo/Kijang Jawa 2.png"));
            controller.setAnimalImage(image);
        } else if (selectedLocation.equals("Kabupaten KulonProgo")) {
            controller.setAnimalData("Kijang Jawa", "Muntiacus muntjak", "Asli", "Terancam", "Kijang Jawa adalah kijang endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Artiodactyla", "Cervidae", "Muntiacus", "muntjak", "Hutan-hutan di Jawa, termasuk Kabupaten Kulonprogo.");
            Image image = new Image(getClass().getResourceAsStream("/Photo/Kijang Jawa 2.png"));
            controller.setAnimalImage(image);
        } else if (selectedLocation.equals("Kabupaten Gunung Kidul")) {
            controller.setAnimalData("Kijang Jawa", "Muntiacus muntjak", "Asli", "Terancam", "Kijang Jawa adalah kijang endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Artiodactyla", "Cervidae", "Muntiacus", "muntjak", "Hutan-hutan di Jawa, termasuk Kabupaten Gunung Kidul.");
            Image image = new Image(getClass().getResourceAsStream("/Photo/Kijang Jawa 2.png"));
            controller.setAnimalImage(image);
        }
    
        // Menampilkan scene AnimalData.fxml
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML
    private void handleButton5(ActionEvent event) throws IOException {
    String selectedLocation = locationChoiceBox.getValue();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AnimalData.fxml"));
    Parent root = loader.load();
    AnimalDataController controller = loader.getController();

    if (selectedLocation.equals("Kota Yogyakarta")) {
        controller.setAnimalData("Merak Jawa", "Pavo muticus", "Asli", "Terancam", "Merak Jawa adalah merak endemik Jawa...", "Animalia", "Chordata", "Aves", "Galliformes", "Phasianidae", "Pavo", "muticus", "Hutan-hutan di Jawa, termasuk wilayah Kota Yogyakarta.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Merak Jawa 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Sleman")) {
        controller.setAnimalData("Lutung Jawa", "Trachypithecus auratus", "Asli", "Terancam", "Lutung Jawa adalah lutung endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Primates", "Cercopithecidae", "Trachypithecus", "auratus", "Hutan-hutan di Jawa, termasuk Kabupaten Sleman.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Lutung 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Bantul")) {
        controller.setAnimalData("Banteng Jawa", "Bos javanicus", "Asli", "Terancam", "Banteng Jawa adalah banteng endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Artiodactyla", "Bovidae", "Bos", "javanicus", "Hutan-hutan di Jawa, termasuk Kabupaten Bantul.");    
        Image image = new Image(getClass().getResourceAsStream("/Photo/Banteng Jawa 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten KulonProgo")) {
        controller.setAnimalData("Merak Jawa", "Pavo muticus", "Asli", "Terancam", "Merak Jawa adalah merak endemik Jawa...", "Animalia", "Chordata", "Aves", "Galliformes", "Phasianidae", "Pavo", "muticus", "Hutan-hutan di Jawa, termasuk Kabupaten Kulonprogo.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Merak Jawa 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Gunung Kidul")) {
        controller.setAnimalData("Merak Jawa", "Pavo muticus", "Asli", "Terancam", "Merak Jawa adalah merak endemik Jawa...", "Animalia", "Chordata", "Aves", "Galliformes", "Phasianidae", "Pavo", "muticus", "Hutan-hutan di Jawa, termasuk Kabupaten Gunung Kidul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Merak Jawa 2.png"));
        controller.setAnimalImage(image);
    }

    // Menampilkan scene AnimalData.fxml
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.show();
}

    @FXML
    private void handleButton6(ActionEvent event) throws IOException {
    String selectedLocation = locationChoiceBox.getValue();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AnimalData.fxml"));
    Parent root = loader.load();
    AnimalDataController controller = loader.getController();

    if (selectedLocation.equals("Kota Yogyakarta")) {
        controller.setAnimalData("Pelanduk Jawa", "Tragulus javanicus", "Asli", "Terancam", "Pelanduk Jawa adalah pelanduk endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Artiodactyla", "Tragulidae", "Tragulus", "javanicus", "Hutan-hutan di Jawa, termasuk wilayah Kota Yogyakarta.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Sleman")) {
        controller.setAnimalData("Kedidi Damar", "Zosterops flavus", "Asli", "Terancam", "Kedidi Damar adalah burung endemik Jawa...", "Animalia", "Chordata", "Aves", "Passeriformes", "Zosteropidae", "Zosterops", "flavus", "Hutan pegunungan di Jawa, termasuk Kabupaten Sleman.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Kedidi Damar.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Bantul")) {
        controller.setAnimalData("Merak Jawa", "Pavo muticus", "Asli", "Terancam", "Merak Jawa adalah merak endemik Jawa...", "Animalia", "Chordata", "Aves", "Galliformes", "Phasianidae", "Pavo", "muticus", "Hutan-hutan di Jawa, termasuk Kabupaten Bantul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Merak Jawa 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten KulonProgo")) {
        controller.setAnimalData("Owa Jawa", "Hylobates moloch", "Asli", "Terancam", "Owa Jawa adalah owa endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Primates", "Hylobatidae", "Hylobates", "moloch", "Hutan-hutan di Jawa, termasuk Kabupaten Kulonprogo.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Owa Jawa 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Gunung Kidul")) {
        controller.setAnimalData("Jalak Putih", "Leucopsar rothschildi", "Asli", "Terancam", "Jalak Putih adalah jalak endemik Jawa...", "Animalia", "Chordata", "Aves", "Passeriformes", "Sturnidae", "Leucopsar", "rothschildi", "Hutan-hutan di Jawa, termasuk Kabupaten Gunung Kidul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Jalak Putih 2.png"));
        controller.setAnimalImage(image);
    }

    // Menampilkan scene AnimalData.fxml
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.show();
}

    @FXML
    private void handleButton7(ActionEvent event) throws IOException {
    String selectedLocation = locationChoiceBox.getValue();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AnimalData.fxml"));
    Parent root = loader.load();
    AnimalDataController controller = loader.getController();

    if (selectedLocation.equals("Kota Yogyakarta")) {
        controller.setAnimalData("Berang-berang Jawa", "Potamogale velox", "Asli", "Terancam", "Berang-berang Jawa adalah berang-berang endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Carnivora", "Potamogalidae", "Potamogale", "velox", "Sungai-sungai di Jawa, termasuk wilayah Kota Yogyakarta.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Berang Berang Jawa 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Sleman")) {
        controller.setAnimalData("Landak Jawa", "Hystrix javanica", "Asli", "Terancam", "Landak Jawa adalah landak endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Rodentia", "Hystricidae", "Hystrix", "javanica", "Beberapa kawasan hutan di Jawa, termasuk Kabupaten Sleman.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Landak Jawa 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Bantul")) {
        controller.setAnimalData("Jalak Putih", "Leucopsar rothschildi", "Asli", "Terancam", "Jalak Putih adalah jalak endemik Jawa...", "Animalia", "Chordata", "Aves", "Passeriformes", "Sturnidae", "Leucopsar", "rothschildi", "Hutan-hutan di Jawa, termasuk Kabupaten Bantul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Jalak Putih 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten KulonProgo")) {
        controller.setAnimalData("Landak Jawa", "Hystrix javanica", "Asli", "Terancam", "Landak Jawa adalah landak endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Rodentia", "Hystricidae", "Hystrix", "javanica", "Hutan-hutan di Jawa, termasuk Kabupaten Kulonprogo.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Landak Jawa 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Gunung Kidul")) {
        controller.setAnimalData("Surili", "Presbytis comata", "Asli", "Terancam", "Surili adalah monyet endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Primates", "Cercopithecidae", "Presbytis", "comata", "Hutan-hutan di Jawa, termasuk Kabupaten Gunung Kidul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Surili 2.png"));
        controller.setAnimalImage(image);
    }

    // Menampilkan scene AnimalData.fxml
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.show();
}

    @FXML
    private void handleButton8(ActionEvent event) throws IOException {
    String selectedLocation = locationChoiceBox.getValue();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AnimalData.fxml"));
    Parent root = loader.load();
    AnimalDataController controller = loader.getController();

    if (selectedLocation.equals("Kota Yogyakarta")) {
        controller.setAnimalData("Kucing Hutan Jawa", "Felis javanensis", "Asli", "Terancam", "Kucing Hutan Jawa adalah kucing endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Carnivora", "Felidae", "Felis", "javanensis", "Hutan-hutan di Jawa, termasuk wilayah Kota Yogyakarta.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Kucing Hutan Jawa 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Sleman")) {
        controller.setAnimalData("Kadal Gunung Jawa", "Sphenomorphus javanicus", "Asli", "Terancam", "Kadal Gunung Jawa adalah kadal endemik Jawa...", "Animalia", "Chordata", "Reptilia", "Squamata", "Scincidae", "Sphenomorphus", "javanicus", "Gunung-gunung di Jawa, termasuk Kabupaten Sleman.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Bantul")) {
        controller.setAnimalData("Kancil Jawa", "Tragulus javanicus", "Asli", "Terancam", "Kancil Jawa adalah kancil endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Artiodactyla", "Tragulidae", "Tragulus", "javanicus", "Hutan-hutan di Jawa, termasuk Kabupaten Bantul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Kancil 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten KulonProgo")) {
        controller.setAnimalData("Jalak Putih", "Leucopsar rothschildi", "Asli", "Terancam", "Jalak Putih adalah jalak endemik Jawa...", "Animalia", "Chordata", "Aves", "Passeriformes", "Sturnidae", "Leucopsar", "rothschildi", "Hutan-hutan di Jawa, termasuk Kabupaten Bantul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Jalak Putih 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Gunung Kidul")) {
        controller.setAnimalData("Owa Jawa", "Hylobates moloch", "Asli", "Terancam", "Owa Jawa adalah owa endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Primates", "Hylobatidae", "Hylobates", "moloch", "Hutan-hutan di Jawa, termasuk Kabupaten Gunung Kidul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Owa Jawa 2.png"));
        controller.setAnimalImage(image);
    }

    // Menampilkan scene AnimalData.fxml
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.show();
}

    @FXML
    private void handleButton9(ActionEvent event) throws IOException {
    String selectedLocation = locationChoiceBox.getValue();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AnimalData.fxml"));
    Parent root = loader.load();
    AnimalDataController controller = loader.getController();

    if (selectedLocation.equals("Kota Yogyakarta")) {
        controller.setAnimalData("Kelelawar Seram", "Eonycteris spelaea", "Asli", "Terancam", "Kelelawar Seram adalah kelelawar endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Chiroptera", "Pteropodidae", "Eonycteris", "spelaea", "Gua-gua di Jawa, termasuk wilayah Kota Yogyakarta.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Kelelawar Seram 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Sleman")) {
        controller.setAnimalData("Kancil Jawa", "Tragulus javanicus", "Asli", "Terancam", "Kancil Jawa adalah kancil endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Artiodactyla", "Tragulidae", "Tragulus", "javanicus", "Hutan-hutan di Jawa, termasuk Kabupaten Sleman.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Kancil Jawa 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Bantul")) {
        controller.setAnimalData("Kadal Pasir Damar", "Calotes emma", "Asli", "Terancam", "Kadal Pasir Damar adalah kadal endemik Jawa...", "Animalia", "Chordata", "Reptilia", "Squamata", "Agamidae", "Calotes", "emma", "Kawasan hutan dan semak di Jawa, termasuk Kabupaten Bantul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Kadal 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten KulonProgo")) {
        controller.setAnimalData("Kancil Jawa", "Tragulus javanicus", "Asli", "Terancam", "Kancil Jawa adalah kancil endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Artiodactyla", "Tragulidae", "Tragulus", "javanicus", "Hutan-hutan di Jawa, termasuk Kabupaten Kulonprogo.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Kancil Jawa 2.png"));
        controller.setAnimalImage(image);
    } else if (selectedLocation.equals("Kabupaten Gunung Kidul")) {
        controller.setAnimalData("Babi Rusa", "Sus scrofa", "Diperkenalkan", "Terancam", "Babi Rusa diperkenalkan ke Jawa oleh manusia...", "Animalia", "Chordata", "Mammalia", "Artiodactyla", "Suidae", "Sus", "scrofa", "Hutan-hutan di Jawa, termasuk Kabupaten Bantul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Babi Rusa 2.png"));
        controller.setAnimalImage(image);
    }

    // Menampilkan scene AnimalData.fxml
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.show();
}

    @FXML
    private void handleButton10(ActionEvent event) throws IOException {
        String selectedLocation = locationChoiceBox.getValue();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AnimalData.fxml"));
        Parent root = loader.load();
        AnimalDataController controller = loader.getController();
    
        if (selectedLocation.equals("Kota Yogyakarta")) {
        controller.setAnimalData("Kepodang Jawa", "Dicrurus angelica", "Asli", "Terancam", "Kepodang Jawa adalah kepodang endemik Jawa...", "Animalia", "Chordata", "Aves", "Passeriformes", "Dicruridae", "Dicrurus", "angelica", "Hutan dan hutan sekunder di Jawa, termasuk wilayah Kota Yogyakarta.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Kepodang Jawa 2.png"));
        controller.setAnimalImage(image);
        } else if (selectedLocation.equals("Kabupaten Sleman")) {
        controller.setAnimalData("Kadal Pasir Damar", "Calotes emma", "Asli", "Terancam", "Kadal Pasir Damar adalah kadal endemik Jawa...", "Animalia", "Chordata", "Reptilia", "Squamata", "Agamidae", "Calotes", "emma", "Kawasan hutan dan semak di Jawa, termasuk Kabupaten Sleman.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Kadal 2.png"));
        controller.setAnimalImage(image);
        } else if (selectedLocation.equals("Kabupaten Bantul")) {
        controller.setAnimalData("Babi Rusa", "Sus scrofa", "Diperkenalkan", "Terancam", "Babi Rusa diperkenalkan ke Jawa oleh manusia...", "Animalia", "Chordata", "Mammalia", "Artiodactyla", "Suidae", "Sus", "scrofa", "Hutan-hutan di Jawa, termasuk Kabupaten Bantul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Babi Rusa 2.png"));
        controller.setAnimalImage(image);
        } else if (selectedLocation.equals("Kabupaten KulonProgo")) {
        controller.setAnimalData("Kadal Pasir Damar", "Calotes emma", "Asli", "Terancam", "Kadal Pasir Damar adalah kadal endemik Jawa...", "Animalia", "Chordata", "Reptilia", "Squamata", "Agamidae", "Calotes", "emma", "Kawasan hutan dan semak di Jawa, termasuk Kabupaten Kulonprogo.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Kadal 2.png"));
        controller.setAnimalImage(image);
        } else if (selectedLocation.equals("Kabupaten Gunung Kidul")) {
        controller.setAnimalData("Kancil Jawa", "Tragulus javanicus", "Asli", "Terancam", "Kancil Jawa adalah kancil endemik Jawa...", "Animalia", "Chordata", "Mammalia", "Artiodactyla", "Tragulidae", "Tragulus", "javanicus", "Hutan-hutan di Jawa, termasuk Kabupaten Gunung Kidul.");
        Image image = new Image(getClass().getResourceAsStream("/Photo/Kancil Jawa 2.png"));
        controller.setAnimalImage(image);
    }
    
        // Menampilkan scene AnimalData.fxml
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backgroundImageList = new ArrayList<>();
        backgroundImageList.add(new Image("/Photo/8.jpeg"));
        backgroundImageList.add(new Image("/Photo/10.jpeg"));
        backgroundImageList.add(new Image("/Photo/13.jpg"));
        backgroundImageList.add(new Image("/Photo/4.jpeg"));
        backgroundImageList.add(new Image("/Photo/14.jpg"));

        backgroundImageView.setImage(backgroundImageList.get(currentIndex));

        backgroundTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, this::changeBackgroundImage),
                new KeyFrame(BACKGROUND_DURATION)
        );
        backgroundTimeline.setCycleCount(Timeline.INDEFINITE);
        backgroundTimeline.play();

        ObservableList<String> locationOptions = FXCollections.observableArrayList(kota);
        locationChoiceBox.setItems(locationOptions);

        if (lastSelectedLocation != null && locationOptions.contains(lastSelectedLocation)) {
            locationChoiceBox.setValue(lastSelectedLocation);
        } else {
        locationChoiceBox.setValue(locationOptions.get(0));
     lastSelectedLocation = locationOptions.get(0);
    }
    }
    private void changeBackgroundImage(ActionEvent event) {
        currentIndex = (currentIndex + 1) % NUM_BACKGROUND_IMAGES;
        backgroundImageView.setImage(backgroundImageList.get(currentIndex));
    }

    public void setLastSelectedLocation(String location) {
        lastSelectedLocation = location;
    }

    private AnimalDataController getAnimalDataController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AnimalData.fxml"));
        try {
            Parent parent = loader.load();
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
