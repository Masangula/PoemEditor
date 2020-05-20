package sample;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ReadingPage implements Initializable {
    @FXML
    private TextArea ReadingArea;
    @FXML
    private Button closeButton;
    @FXML
    private AnchorPane readingPane;

    private Scanner read;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    StringBuilder words;
    private String poemName;
    private String writer;
    public void printText(String poemName){
        this.poemName=poemName;
        try {
            read=new Scanner(new File("C:\\Users\\Joshua Masangula\\IdeaProjects\\PoemEditor\\poems\\"+poemName));
            words=new StringBuilder("");
            while(read.hasNext()){
                words.append(read.nextLine()+",\n");
            }
            ReadingArea.setText(words.toString());
            read.close();
        }catch (FileNotFoundException fileNotFoundException){
            JOptionPane.showMessageDialog(null,poemName+" Is not available!");
        }


    }

    public void closing() throws IOException {
        ReadingArea.setText("");
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Activities.fxml"));
        primaryStage.setTitle("ActivitiesScreen");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void voice(ActionEvent event){
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();// Allocating Voice
            try {
                voice.setRate(120);// Setting the rate of the voice
                voice.setPitch(120);// Setting the Pitch of the voice
                voice.setVolume(1);// Setting the volume of the voice
                voice.speak(ReadingArea.getText());// Calling speak() method

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            throw new IllegalStateException("Cannot find voice: kevin16");
        }
    }

    public void editingPoem(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("EditingPoem.fxml"));
        Parent root=(Parent)loader.load();
        Stage primaryStage=new Stage();
        EditingPoem editingPoem=loader.getController();
        editingPoem.openingEditingPane(poemName,writer);
        primaryStage.setTitle("ReadingPageScreen");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
       removeRecord(poemName);
       readingPane.getScene().getWindow().hide();

    }

    public void removeRecord(String poemName){
        File newFile=new File("C:\\Users\\Joshua Masangula\\IdeaProjects\\PoemEditor\\poems\\temp");
        File oldFile=new File("C:\\Users\\Joshua Masangula\\IdeaProjects\\PoemEditor\\poems\\table");
        Poem poem=new Poem();

        try{
            FileWriter fileWriter=new FileWriter(newFile,true);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            PrintWriter printWriter=new PrintWriter(bufferedWriter);
            read=new Scanner(oldFile);

            while (read.hasNext()){
                poem.setPoemName(read.next());
                poem.setWriter(read.next());
                poem.setDate(read.nextLine());
                if(!poem.getPoemName().equals(poemName))
                    printWriter.println(poem.getPoemName()+" "+poem.getWriter()+" "+poem.getDate());
                else
                    writer=poem.getWriter();

            }
            read.close();
            printWriter.flush();
            printWriter.close();
            oldFile.delete();
            File poemFile=new File("\\Users\\Joshua Masangula\\IdeaProjects\\PoemEditor\\poems\\"+poemName);
            poemFile.delete();
            File dump=new File("C:\\Users\\Joshua Masangula\\IdeaProjects\\PoemEditor\\poems\\table");
            newFile.renameTo(dump);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error in deleting a file!");
        }
    }

}
