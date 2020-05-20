package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreen implements Initializable {

    @FXML
    private StackPane splashPane;

    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new SplashScreenSleeper().start();
    }

    class SplashScreenSleeper extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Stage primaryStage=new Stage();
                        Parent root=null;
                        try {
                            FXMLLoader loader=new FXMLLoader(getClass().getResource("Activities.fxml"));
                            root=(Parent)loader.load();
                            Activities activities=loader.getController();
                            activities.viewDetails(username);
                            activities.viewUploadedPoems(username);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        primaryStage.setTitle("ActivitiesScreen");
                        primaryStage.initStyle(StageStyle.UNDECORATED);
                        primaryStage.setScene(new Scene(root));
                        primaryStage.show();

                        splashPane.getScene().getWindow().hide();
                    }
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
