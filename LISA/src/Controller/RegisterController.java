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

import ModelData.Anggota;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.mindrot.jbcrypt.BCrypt;

public class RegisterController implements Initializable{
    @FXML
    private TextField namaTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField handphoneTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button signUpButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button kembaliAction;

    @FXML
    private void handleButtonLogin(ActionEvent event) throws IOException {
        OpenScene open = new OpenScene((Stage)((Node)event.getSource()).getScene().getWindow());
        open.openScene("/FXML/Login.fxml");
    }

    @FXML
    private void handleButtonKembali(ActionEvent event) throws IOException {
        OpenScene open = new OpenScene((Stage)((Node)event.getSource()).getScene().getWindow());
        open.openScene("/FXML/WelcomePage.fxml");
    }

    @FXML
    private void handleButtonRegisterUser(ActionEvent event) throws IOException {
        if (!emailTextField.getText().equals("") && !passwordTextField.getText().equals("")) {
            ArrayList<Anggota> listUser = new ArrayList<>();
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
                listUser = list;

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
            FileOutputStream dataUser = null;
            String email = emailTextField.getText();
            String passsword = passwordTextField.getText();
            String hashedPassword = BCrypt.hashpw(passsword, BCrypt.gensalt());
            String Nama = namaTextField.getText();
            String handphone = handphoneTextField.getText();
            listUser.add(new Anggota(Nama,email,hashedPassword,handphone));
            String xml = xstream.toXML(listUser);
            try {
                dataUser = new FileOutputStream("Anggota.xml");
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
            PauseTransition pt = new PauseTransition();
            pt.setDuration(Duration.seconds(1));
            pt.setOnFinished(ev -> {
                System.out.println("SignUp User Succesfully");
                signUpButton.getScene().getWindow().hide();
                Stage login = new Stage();
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
                    Scene scene = new Scene(root);
                    login.setScene(scene);
                    login.show();
                    login.setTitle("LISA");

                    Image image = new Image("/Photo/LOGO LISA.png");
                    login.getIcons().add(image);
                    login.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            pt.play();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
