package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class OpenScene {
    private Stage stage;

    public OpenScene(Stage stage){
        this.stage = stage;
    }
    
    public void openScene(String fileName){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("LISA");

            Image image = new Image("/Photo/LOGO LISA.png");
            stage.getIcons().add(image);
            stage.show();
            
        }catch (Exception e){
       
            System.out.println("Tidak ditemukan halaman tersebut");
            e.printStackTrace();
        }
    }
}