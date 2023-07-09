package Controller;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ModelData.Anggota;

import org.mindrot.jbcrypt.BCrypt;

public class LoginController implements Initializable{

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordTetxField;

    @FXML
    private Label labelPeringatan;

    @FXML
    private Button btnKembali;

    @FXML
    private Button loginButton;

    @FXML
    private Button buttonDaftar;

    @FXML
    private void kembaliAction(ActionEvent event) throws IOException {
        OpenScene open = new OpenScene((Stage)((Node)event.getSource()).getScene().getWindow());
        open.openScene("/FXML/WelcomePage.fxml");
    }

    @FXML
    private void handleButtonDaftar(ActionEvent event) throws IOException {
        OpenScene open = new OpenScene((Stage)((Node)event.getSource()).getScene().getWindow());
        open.openScene("/FXML/Register.fxml");
    }

    
    @FXML
    private void handleButtonLogin() {
    if (!emailTextField.getText().isEmpty() && !passwordTetxField.getText().isEmpty()) {
        String email = emailTextField.getText();
        String password = passwordTetxField.getText();

        XStream xstream = new XStream(new StaxDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        FileInputStream data = null;
        try {
            data = new FileInputStream("Anggota.xml");

            int isi;
            char c;
            String stringnya = "";

            while ((isi = data.read()) != -1) {
                c = (char) isi;
                stringnya += c;
            }
            ArrayList<Anggota> list = (ArrayList<Anggota>) xstream.fromXML(stringnya);
            boolean emailMatch = false;
            boolean passwordMatch = false;

           for (int i = 0; i < list.size(); i++) {
        if (email.equals(list.get(i).getEmail()) && BCrypt.checkpw(password, list.get(i).getPassword())) {
             emailMatch = true;
            passwordMatch = true;
        break;
    } else if (email.equals(list.get(i).getEmail())) {
        emailMatch = true;
        break;
    }
}


            if (emailMatch && passwordMatch) {
                // Login success
                System.out.println("Login Successfully");
                loginButton.getScene().getWindow().hide();
                Stage dashboardStage = new Stage();
                Parent root;
                try {
                    FXMLLoader loader = new FXMLLoader();
                    if (email.endsWith(".com")) {
                        loader.setLocation(getClass().getResource("/FXML/Dashboard.fxml"));
                    } else if (email.endsWith(".org")) {
                        loader.setLocation(getClass().getResource("/FXML/DashboardOrg.fxml"));
                    }
                    root = loader.load();
                    Scene scene = new Scene(root);
                    dashboardStage.setScene(scene);
                    dashboardStage.show();
                    dashboardStage.setResizable(true);
                    dashboardStage.setTitle("LISA");

                    Image image = new Image("/Photo/LOGO LISA.png");
                    dashboardStage.getIcons().add(image);
                    dashboardStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (emailMatch) {
                // Password salah
                labelPeringatan.setText("Password Anda salah");
            } else {
                // Akun tidak terdeteksi
                labelPeringatan.setText("Akun tidak terdeteksi, mohon masukan lagi");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } finally {
            if (data != null) {
                try {
                    data.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    } else {
        // Email dan Password kosong
        labelPeringatan.setText("Email dan Password harus diisi");
    }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}

