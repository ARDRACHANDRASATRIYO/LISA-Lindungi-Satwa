package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AboutController implements Initializable{
    
    @FXML
    private Button aboutButton;

    @FXML
    private void handleAboutButtonClick(ActionEvent event) throws IOException{
        OpenScene open = new OpenScene((Stage)((Node)event.getSource()).getScene().getWindow());
        open.openScene("/FXML/Dashboard.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
}
