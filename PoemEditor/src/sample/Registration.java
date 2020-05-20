package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Registration implements Initializable {

    @FXML
    private Label backLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField retypePasswordField;
    @FXML
    private Button registrationButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void backToLoginForm() throws IOException {
        Stage stage=(Stage)backLabel.getScene().getWindow();
        stage.close();

        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("LoginScreen");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void submitRegistration(ActionEvent event){
        try{
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/poemeditor", "root","JoshMasaqc11!");
            Statement statement=connection.createStatement();

            if(passwordField.getText().equals(retypePasswordField.getText()) && passwordField.getText()!=null){
                statement.executeUpdate(String.format("insert into users values ('%s','%s','%s')", userNameField.getText(), emailField.getText(), passwordField.getText()));
                JOptionPane.showMessageDialog(null,"W E L C O M E");
                Stage stage=(Stage)registrationButton.getScene().getWindow();
                stage.close();
            Stage primaryStage=new Stage();
                FXMLLoader loader=new FXMLLoader(getClass().getResource("SplashScreen.fxml"));
                Parent root=(Parent)loader.load();
                SplashScreen splashScreen=loader.getController();
                splashScreen.setUsername(userNameField.getText());
                primaryStage.setTitle("SplashScreen");
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            }
            else {
                messageLabel.setText("Passwords don't match, please! write again correctly");
                passwordField.setText("");
                retypePasswordField.setText("");
            }
        }catch (SQLException | IOException sqlException){
            JOptionPane.showMessageDialog(null,"Oops! you are not connected to the internet");
            System.out.println(sqlException.getMessage());
        }
    }
}
