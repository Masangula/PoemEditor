package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

public class EditingPoem {
    @FXML
    private TextArea editingArea;
    @FXML
    private TextField editedPoemNameField;
    @FXML
    private TextField WriterEditedPoemField;
    @FXML
    private AnchorPane EditingPane;

    private Formatter save;
    private File file;

    public void editing(ActionEvent event) {
        Poem poem=new Poem();

        if (editedPoemNameField.getText() != null && WriterEditedPoemField.getText() != null && editingArea.getText()!=null) {
            poem.setPoemName(editedPoemNameField.getText());
            poem.setWriter(WriterEditedPoemField.getText());
            Date date=new Date();
            try {
                save = new Formatter("C:\\Users\\Joshua Masangula\\IdeaProjects\\PoemEditor\\poems\\" + poem.getPoemName());
                file=new File("C:\\Users\\Joshua Masangula\\IdeaProjects\\PoemEditor\\poems\\table");
                if (poem.getPoemName() != null) {
                    save.format("%s \n", editingArea.getText());
                    FileWriter fileWriter=new FileWriter(file,true);
                    fileWriter.write(poem.getPoemName()+" "+poem.getWriter()+" "+date.toLocaleString()+"\n");
                    fileWriter.close();
                    save.close();
                    JOptionPane.showMessageDialog(null, "!POEM EDITED!");
                    editingArea.setText("");
                    editedPoemNameField.setText("");
                    WriterEditedPoemField.setText("");
                    EditingPane.getScene().getWindow().hide();

                    Stage primaryStage=new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("Activities.fxml"));
                    primaryStage.setTitle("ActivitiesScreen");
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    primaryStage.setScene(new Scene(root));
                    primaryStage.show();
                } else {
                    JOptionPane.showMessageDialog(null, "!POEM NOT SAVED!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "poem name and writers field must be filled or content field must have at least a word");
            }

        }else{
            JOptionPane.showMessageDialog(null,"poem name and writers field must be filled or content field must have at least a word");
        }
    }

    public void openingEditingPane(String poemName,String writer){

        StringBuilder words=new StringBuilder("");
        try {
            Scanner reader=new Scanner(new File("C:\\Users\\Joshua Masangula\\IdeaProjects\\PoemEditor\\poems\\" + poemName));
            while (reader.hasNext()){
                words.append(reader.nextLine()+"\n");
            }
            reader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null,"!File cannot be Edited!");
        }
        editingArea.setText(words.toString());
        editedPoemNameField.setText(poemName);
        WriterEditedPoemField.setText(writer);
    }
}
