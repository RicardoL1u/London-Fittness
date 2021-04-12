package Boundary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class BoundaryRegister {
    @FXML
    private Button button_register;
    @FXML
    private TextField register_username,register_password,register_email,register_age;
    @FXML
    private ComboBox register_sex;
    @FXML
    private Text register_username_hint;
    @FXML
    private Text register_password_hint;
    @FXML
    private Text register_email_hint;
    @FXML
    private Text register_age_hint;
    @FXML
    private Text register_sex_hint;
    @FXML
    private Text username_invalid,password_invalid,email_invalid,age_invalid;
    @FXML
    private ImageView button_register_return;

    //method in register
    @FXML
    public void registerClick() throws IOException {
        String text_username = register_username.getText();
        String text_password = register_password.getText();
        String text_email = register_email.getText();
        String text_age = register_age.getText();
        String text_sex = (String)register_sex.getValue();

        if(text_username.equals("")||text_password.equals("")||text_email.equals("")||text_age.equals("")||text_sex==null){
            if(text_username.equals("")){
                register_username_hint.setVisible(true);
            }
            if(text_password.equals("")){
                register_password_hint.setVisible(true);
            }
            if(text_email.equals("")){
                register_email_hint.setVisible(true);
            }
            if(text_age.equals("")){
                register_age_hint.setVisible(true);
            }
            if(text_sex==null){
                register_sex_hint.setVisible(true);
            }
        }else{

            registerJudge(text_username, text_password,text_email,text_age,text_sex);
        }
    }
    @FXML
    public void registerJudge(String text_username, String text_password,String text_email,String text_age,String text_sex) throws IOException {
        String stringUrl = "http://127.0.0.1:8081/User?Method=Register&userName="+text_username+"&password="+text_password+"&email="+text_email+"&age="+text_age+"&sex="+text_sex;
        HttpClient hc = new HttpClient();
        String result = hc.doGet(stringUrl);
        if(result.equals(null)) {
            this.jumpAlertSystem();
        }else {
            if (result.contains("error_code:0")) {
                System.out.println("success");
                Stage stage_login = (Stage) button_register.getScene().getWindow();
                stage_login.close();
                try {
                    Parent success = FXMLLoader.load(getClass().getResource("fxml/main_interface.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("success window");
                    stage.setScene(new Scene(success, 1200, 800));
                    stage.setResizable(false);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(result);
                System.out.println("failed");
                this.jumpAlertRegister();
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
    @FXML
    public void jumpAlertRegister(){
        String info="Username exists";
        Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("yes", ButtonBar.ButtonData.YES));
        alert.setHeaderText(null);
        alert.setTitle("prompt");
        alert.show();
    }
    //detect methods in register
    @FXML
    public void detectRegUsername(){
        String text = register_username.getText();
        if(text.equals("")){
            register_username_hint.setVisible(true);
        }else{
            register_username_hint.setVisible(false);
        }
        if(!illegalWord(text) && !(register_username_hint.isVisible())) {
            username_invalid.setVisible(true);
        }else{
            username_invalid.setVisible(false);
        }
    }
    @FXML
    public void detectRegPassword(){
        String text = register_password.getText();
        if(text.equals("")){
            register_password_hint.setVisible(true);
        }else{
            register_password_hint.setVisible(false);
        }
    }
    @FXML
    public void detectRegEmail(){
        String text = register_email.getText();
        if(text.equals("")){
            register_email_hint.setVisible(true);
        }else{
            register_email_hint.setVisible(false);
        }
        if(!emailCorrect(text) && !(register_email_hint.isVisible())) {
            email_invalid.setVisible(true);
        }else{
            email_invalid.setVisible(false);
        }
    }
    @FXML
    public void detectRegAge(){
        String text = register_age.getText();
        if(text.equals("")){
            register_age_hint.setVisible(true);
        }else{
            register_age_hint.setVisible(false);
        }
        if(!ageCorrect(text) && !(register_age_hint.isVisible())) {
            age_invalid.setVisible(true);
        }else{
            age_invalid.setVisible(false);
        }
    }
    @FXML
    public boolean emailCorrect(String mail) {
        Boolean correct;
        if(mail.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"))
        {
            correct = true;
        }
        else
        {
            correct = false;
        }
        return correct;
    }
    @FXML
    public boolean ageCorrect(String age) {
        for (int i = 0; i < age.length(); i++){
            System.out.println(age.charAt(i));
            if (!Character.isDigit(age.charAt(i))){
                return false;
            }
        }
        return true;
    }
    @FXML
    public boolean illegalWord(String str) {
        Boolean correct;
        if (!str.matches("[\\da-zA-Z]+")) {
            correct = false;
        }
        else correct = true;
        return correct;
    }
    @FXML
    public void registerReturnClick() throws IOException {
        Parent success = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        Stage stage = (Stage)button_register_return.getScene().getWindow();
        stage.setTitle("success window");
        stage.setResizable(false);
        stage.setScene(new Scene(success, 1200, 800));
        stage.show();
    }
}
