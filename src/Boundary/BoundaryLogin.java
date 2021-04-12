package Boundary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class BoundaryLogin {
    @FXML
    private Button button_login;
    @FXML
    private TextField login_username,login_password;
    @FXML
    private Text username_error_hint;
    @FXML
    private Text password_error_hint;
    @FXML
    private Hyperlink login_to_register;



    //methods in login
    @FXML
    public void loginClick() throws IOException {
        String text_username = login_username.getText();
        String text_password = login_password.getText();
        if(text_username.equals("")||text_password.equals("")){
            if(text_username.equals("")){
                username_error_hint.setVisible(true);
            }
            if(text_password.equals("")){
                password_error_hint.setVisible(true);
            }
        }else{
            loginJudge(text_username, text_password);
        }
    }
    @FXML
    public void loginJudge(String text_username, String text_password) throws IOException {
        String stringUrl = "http://127.0.0.1:8081/User?Method=Login&userName=" + text_username + "&password=" + text_password;
        HttpClient hc = new HttpClient();
        String result = hc.doGet(stringUrl);
        if(result==null) {
            this.jumpAlertSystem();
        }else{
            if(result.contains("error_code:0")){
                System.out.println("success");
                try {
                    Parent success = FXMLLoader.load(getClass().getResource("fxml/main_interface.fxml"));
                    Stage stage = (Stage)button_login.getScene().getWindow();
                    stage.setTitle("success window");
                    stage.setScene(new Scene(success, 1200, 800));
                    stage.setResizable(false);
                    stage.show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("failed");
                System.out.println(result);
                this.jumpAlertLogin();
            }
        }
    }
    @FXML
    public void jumpAlertLogin() throws IOException {
        String info="Invalid username or password!";
        Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("yes", ButtonBar.ButtonData.YES));
        alert.setHeaderText(null);
        alert.setTitle("prompt");
        alert.show();
    }
    @FXML
    public void jumpAlertSystem() throws IOException {
        String info="System Error!";
        Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("yes", ButtonBar.ButtonData.YES));
        alert.setHeaderText(null);
        alert.setTitle("prompt");
        alert.show();
    }
    //detect methods in login
    @FXML
    public void detectInputUsername(){
        String text_username = login_username.getText();
        if(text_username.equals("")){
            username_error_hint.setVisible(true);
        }else{
            username_error_hint.setVisible(false);
        }
    }
    @FXML
    public void detectInputPassword(){
        String text_password = login_password.getText();
        if(text_password.equals("")){
            password_error_hint.setVisible(true);
        }else{
            password_error_hint.setVisible(false);
        }
    }

    @FXML
    public void clicktoRegister() throws IOException {
        Parent success = FXMLLoader.load(getClass().getResource("fxml/register.fxml"));
        Stage stage = (Stage)login_to_register.getScene().getWindow();
        stage.setTitle("success window");
        stage.setResizable(false);
        stage.setScene(new Scene(success, 1200, 800));
        stage.show();
    }


}
