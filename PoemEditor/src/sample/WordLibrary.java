package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class WordLibrary implements Initializable {
    @FXML
    private WebView webView;
    @FXML
    private WebEngine engine;
    @FXML
    private AnchorPane wordLibraryPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine=webView.getEngine();
        searchEngine();
    }

    public void searchEngine(){
        engine.load("https://www.rhymezone.com");
    }
    public void reloadingEngine(ActionEvent event){
        engine.reload();
    }
    public void viewHistory(ActionEvent event){
        engine.getHistory();
    }

    public void closing(){
        wordLibraryPane.getScene().getWindow().hide();
    }
}
