package Boundary;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class Videos {
    @FXML
    MediaView video_player;
    @FXML
    AnchorPane right_pane;
    @FXML
    Slider sVol;
    @FXML
    ProgressBar sTime;
    @FXML
    ScrollPane videosScrollPane;
    @FXML
    ImageView vol_icon;
    @FXML
    Label imageView1,imageView2,imageView3;
    @FXML
    ImageView image_play;

    File url = new File("./Boundary/videos/listen.mp4");
    Media media = new Media(url.getAbsoluteFile().toURI().toString());
    MediaPlayer mplayer = new MediaPlayer(media);

    @FXML
    public void buttonPlayClick(){
        mplayer.play();
        image_play.setVisible(false);
    }

    @FXML
    public void callVideoPlayer() throws IOException {
        String video_id = "A001";
        String stringUrl = "http://127.0.0.1:8081/User?Method=PlayVideo&videoID=" + video_id;
        HttpClient hc = new HttpClient();
        String result = hc.doGet(stringUrl);
        if(result==null) {
            this.jumpAlertSystem();
        }else {
            System.out.println(result);
            if(result.contains("error_code:0")){
                System.out.println(result);
                image_play.setVisible(true);
                sTime.setVisible(true);
                vol_icon.setVisible(true);
                String video_path = result.substring(19);
                video_path = video_path.substring(0,video_path.length()-2);
                //video_path = "./Boundary/videos/listen.mp4";
                System.out.println(video_path);
                url = new File(video_path);
                media = new Media(url.getAbsoluteFile().toURI().toString());
                mplayer = new MediaPlayer(media);
                video_player.setMediaPlayer(mplayer);
                mplayer.volumeProperty().bind(sVol.valueProperty().divide(100));
                mplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                    @Override
                    public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                        updateTime();
                    }
                });
                videosScrollPane.setFitToHeight(true);
            }else{

            }
        }
    }

    @FXML
    public void jumpAlertSystem() throws IOException {
        String info="System Error!";
        Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("yes", ButtonBar.ButtonData.YES));
        alert.setHeaderText(null);
        alert.setTitle("prompt");
        alert.show();
    }
    public void updateTime() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                sTime.setProgress(mplayer.getCurrentTime().toSeconds()/mplayer.getTotalDuration().toSeconds());
            }
        });
    }

    @FXML
    public void videoPlayerClick(){
        mplayer.pause();
        image_play.setVisible(true);
    }

    @FXML
    public void volAppear(){
        sVol.setVisible(true);
    }

    @FXML
    public void volDisappear(){
        sVol.setVisible(false);
    }

}
