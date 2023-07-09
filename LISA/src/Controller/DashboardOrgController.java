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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DashboardOrgController implements Initializable {
    @FXML
    private Button reportingButton;

    @FXML
    private Button donationButton;

    @FXML
    private Button aboutButton;

    @FXML
    private Button logoutButton;

    @FXML
    private ImageView logoImageView;

    @FXML
    private ImageView backgroundImageView;

    private List<Image> backgroundImageList;

    private int currentIndex = 0;
    private final int NUM_BACKGROUND_IMAGES = 5;
    private final Duration BACKGROUND_DURATION = Duration.seconds(5);
    private Timeline backgroundTimeline;

    @FXML
    void handleButtonReporting(ActionEvent event) throws IOException {
        OpenScene open = new OpenScene((Stage)((Node)event.getSource()).getScene().getWindow());
        open.openScene("/FXML/ListReport.fxml");
    }

    @FXML
    void handleButtonLogout(ActionEvent event) throws IOException {
        OpenScene open = new OpenScene((Stage)((Node)event.getSource()).getScene().getWindow());
        open.openScene("/FXML/WelcomePage.fxml");
    }

    @FXML
    void handleButtonbarChart(ActionEvent event) throws IOException {
        OpenScene open = new OpenScene((Stage)((Node)event.getSource()).getScene().getWindow());
        open.openScene("/FXML/InputDataBarChart.fxml");
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
    }

    private void changeBackgroundImage(ActionEvent event) {
        currentIndex = (currentIndex + 1) % NUM_BACKGROUND_IMAGES;
        backgroundImageView.setImage(backgroundImageList.get(currentIndex));
    }
}
