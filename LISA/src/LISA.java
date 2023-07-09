import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LISA extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/WelcomePage.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
        stage.setTitle("LISA");

        Image image = new Image("/Photo/LOGO LISA.png");
        stage.getIcons().add(image);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
