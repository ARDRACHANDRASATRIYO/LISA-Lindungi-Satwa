package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WelcomePageController implements Initializable {

    @FXML
    private Button login;

    @FXML
    private Button signUp;

    @FXML
    private Button guest;

    @FXML
    private ImageView backgroundImageView;

    private List<Image> backgroundImageList;
    private int currentIndex = 0;
    private final int NUM_BACKGROUND_IMAGES = 5;
    private final Duration BACKGROUND_DURATION = Duration.seconds(5);

    private Timeline backgroundTimeline;


    @FXML
    void handleButtonSignUp(ActionEvent event) throws IOException {
        OpenScene open = new OpenScene((Stage)((Node)event.getSource()).getScene().getWindow());
        open.openScene("/FXML/Register.fxml");
    }

    @FXML
    void handleButtonLogin(ActionEvent event) throws IOException {
        OpenScene open = new OpenScene((Stage)((Node)event.getSource()).getScene().getWindow());
        open.openScene("/FXML/Login.fxml");
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backgroundImageList = new ArrayList<>();
        backgroundImageList.add(new Image("/Photo/15.jpeg"));
        backgroundImageList.add(new Image("/Photo/2.jpeg"));
        backgroundImageList.add(new Image("/Photo/12.jpg"));
        backgroundImageList.add(new Image("/Photo/9.jpeg"));
        backgroundImageList.add(new Image("/Photo/5.jpeg"));

        backgroundImageView.setImage(backgroundImageList.get(currentIndex));

        backgroundTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, this::changeBackgroundImage),
                new KeyFrame(BACKGROUND_DURATION)
        );
        backgroundTimeline.setCycleCount(Timeline.INDEFINITE);
        backgroundTimeline.play();
    }

    private void changeBackgroundImage(ActionEvent event) {
        currentIndex = (currentIndex + 1) % NUM_BACKGROUND_IMAGES;
        backgroundImageView.setImage(backgroundImageList.get(currentIndex));
    }
}


