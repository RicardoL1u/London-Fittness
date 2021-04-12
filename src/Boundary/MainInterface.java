package Boundary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class MainInterface {
    @FXML
    private Label video_button,live_button,personal_trainer_button,my_page_button;


    @FXML
    public void videosClick() throws Exception {
        Parent success = FXMLLoader.load(getClass().getResource("fxml/videos.fxml"));
        Stage stage = (Stage)video_button.getScene().getWindow();
        //Stage stage = new Stage();
        stage.setTitle("success window");
        stage.setResizable(false);
        stage.setScene(new Scene(success, 1200, 800));
        stage.show();
    }
    @FXML
    public void liveClick() throws Exception {
        Parent success = FXMLLoader.load(getClass().getResource("fxml/videos.fxml"));
        Stage stage = (Stage)live_button.getScene().getWindow();
        stage.setTitle("success window");
        stage.setResizable(false);
        stage.setScene(new Scene(success, 1200, 800));
        stage.show();
    }
    @FXML
    public void personalTrainerClick() throws Exception {
        Parent success = FXMLLoader.load(getClass().getResource("fxml/videos.fxml"));
        Stage stage = (Stage)personal_trainer_button.getScene().getWindow();
        stage.setTitle("success window");
        stage.setResizable(false);
        stage.setScene(new Scene(success, 1200, 800));
        stage.show();
    }
    @FXML
    public void myPageClick() throws Exception {
        Parent success = FXMLLoader.load(getClass().getResource("fxml/videos.fxml"));
        Stage stage = (Stage)my_page_button.getScene().getWindow();
        stage.setTitle("success window");
        stage.setResizable(false);
        stage.setScene(new Scene(success, 1200, 800));
        stage.show();
    }




}
