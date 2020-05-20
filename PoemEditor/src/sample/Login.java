package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;


public class Login {

    @FXML
    private Label close;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label messageLabel;
    @FXML
    private Label registrationLinkLabel;
    @FXML
    private Button loginButton;

    public void closing(){
        Stage stage=(Stage)close.getScene().getWindow();
        stage.close();
    }

    public void loggingIn(ActionEvent event)throws IOException {
        try{
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/poemeditor", "root","JoshMasaqc11!");
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT username, password from users where username=? and password=?");
            preparedStatement.setString(1,usernameField.getText());
            preparedStatement.setString(2,passwordField.getText());
            ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next()){
            //JOptionPane.showMessageDialog(null,"Login successful");
            Stage stage=(Stage)loginButton.getScene().getWindow();
            stage.close();

            Stage primaryStage=new Stage();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("SplashScreen.fxml"));
            Parent root=(Parent)loader.load();
            SplashScreen splashScreen=loader.getController();
            splashScreen.setUsername(usernameField.getText());
            primaryStage.setTitle("SplashScreen");
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }else{
            messageLabel.setText("Password or Username are/is not correct");
        }
        }catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null,"Oops! you are not connected to the internet");
            System.out.println(sqlException.getMessage());
        }
    }

    public void registration()throws IOException{
        Stage stage=(Stage)registrationLinkLabel.getScene().getWindow();
        stage.close();

        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        primaryStage.setTitle("RegistrationFormScreen");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
