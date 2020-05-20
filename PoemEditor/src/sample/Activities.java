package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;


import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Activities implements Initializable {
    @FXML
    private javafx.scene.control.Label close;
    @FXML
    private Button newPoemButton;
    @FXML
    private Button readButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button uploadButton;
    @FXML
    private Label titleLabel;
    @FXML
    private Pane titlePane;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane5;
    @FXML
    private TextArea contentField;
    @FXML
    private TextField poemNameField;
    @FXML
    private TextField writerField;
    @FXML
    private AnchorPane generalPane;
    @FXML
    private TextField poemToBeDeletedField;
    @FXML
    private TextField viewUserNameField;
    @FXML
    private TextField viewEmailField;
    @FXML
    private TextField viewPasswordField;
    @FXML
    private Button editDetailsButton;
    @FXML
    private TextField fileToBeUploadedField;
    @FXML
    private Label dateLabel;




    @FXML private TableView<Poem> table1;
    @FXML private TableColumn<Poem,Integer> number;
    @FXML private TableColumn<Poem,String> poemName;
    @FXML private TableColumn<Poem,String> writer;
    @FXML private TableColumn<Poem,String> date;

    @FXML private TableView<Poem> table2;
    @FXML private TableColumn<Poem,Integer> number1;
    @FXML private TableColumn<Poem,String> poemName1;
    @FXML private TableColumn<Poem,String> writer1;
    @FXML private TableColumn<Poem,String> date1;

    @FXML private TableView<Poem> table3;
    @FXML private TableColumn<Poem,Integer> number2;
    @FXML private TableColumn<Poem,String> poemName2;
    @FXML private TableColumn<Poem,String> writer2;
    @FXML private TableColumn<Poem,String> date2;
    ObservableList<Poem>list=FXCollections.observableArrayList();

    @FXML
    private TextField readTextField;

    private Formatter save;
    private File file;
    private  String username;

    public void settingDate(){
        Date date=new Date();
        dateLabel.setText(date.toLocaleString());
    }

    public void savePoem(ActionEvent event) {
        Poem poem=new Poem();

        if (poemNameField.getText() != null && writerField.getText() != null && contentField.getText()!=null) {
            poem.setPoemName(poemNameField.getText());
            poem.setWriter(writerField.getText());
            Date date=new Date();
            try {
                save = new Formatter("C:\\Users\\Joshua Masangula\\IdeaProjects\\PoemEditor\\poems\\" + poem.getPoemName());
                file=new File("C:\\Users\\Joshua Masangula\\IdeaProjects\\PoemEditor\\poems\\table");
                if (poem.getPoemName() != null) {
                    save.format("%s \n", contentField.getText());
                    FileWriter fileWriter=new FileWriter(file,true);
                    fileWriter.write(poem.getPoemName()+" "+poem.getWriter()+" "+date.toLocaleString()+"\n");
                    fileWriter.close();
                    save.close();
                    JOptionPane.showMessageDialog(null, "!POEM SAVED!");
                    contentField.setText("");
                    poemNameField.setText("");
                    writerField.setText("");
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


    public void closing() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
                updateTables();
                settingDate();
    }

    public  void updateTables(){
        try{
            Scanner reader=new Scanner(new File("C:\\Users\\Joshua Masangula\\IdeaProjects\\PoemEditor\\poems\\table"));
            int id=1;
            Poem poem=new Poem();
            while (reader.hasNext()){
                poem.setNumber(id);
                poem.setPoemName(reader.next());
                poem.setWriter(reader.next());
                poem.setDate(reader.nextLine());
                list.add(new Poem(poem.getNumber(),poem.getPoemName(),poem.getWriter(),poem.getDate()));
                id++;
            }
        }catch (FileNotFoundException fileNotFoundException){
            JOptionPane.showMessageDialog(null,"There is problem with the poem list");
        }
        number.setCellValueFactory(new PropertyValueFactory<Poem,Integer>("number"));
        poemName.setCellValueFactory(new PropertyValueFactory<Poem,String>("poemName"));
        writer.setCellValueFactory(new PropertyValueFactory<Poem,String>("writer"));
        date.setCellValueFactory(new PropertyValueFactory<Poem, String>("date"));
        table1.setItems(list);

        number1.setCellValueFactory(new PropertyValueFactory<Poem,Integer>("number"));
        poemName1.setCellValueFactory(new PropertyValueFactory<Poem,String>("poemName"));
        writer1.setCellValueFactory(new PropertyValueFactory<Poem,String>("writer"));
        date1.setCellValueFactory(new PropertyValueFactory<Poem, String>("date"));
        table2.setItems(list);
    }

    @FXML
    public void handleClicks(ActionEvent event) {

        if (event.getSource() == newPoemButton) {
            titleLabel.setText("New Poem");
            titlePane.setBackground(new Background(new BackgroundFill(Color.rgb(63,43,99), CornerRadii.EMPTY, Insets.EMPTY)));
            pane1.toFront();
        } else if (event.getSource() == readButton) {
            titleLabel.setText("Read Poems");
            titlePane.setBackground(new Background(new BackgroundFill(Color.rgb(43,63,99), CornerRadii.EMPTY, Insets.EMPTY)));
            pane2.toFront();
        } else if (event.getSource() == deleteButton) {
            titleLabel.setText("Delete Poem");
            titlePane.setBackground(new Background(new BackgroundFill(Color.rgb(43,99,63), CornerRadii.EMPTY, Insets.EMPTY)));
            pane3.toFront();
        } else if (event.getSource() == uploadButton) {
            titleLabel.setText("Upload Poems");
            titlePane.setBackground(new Background(new BackgroundFill(Color.rgb(99,63,43), CornerRadii.EMPTY, Insets.EMPTY)));
            pane4.toFront();
        } else if (event.getSource() == logoutButton) {
            titleLabel.setText("Details");
            titlePane.setBackground(new Background(new BackgroundFill(Color.rgb(99,43,63), CornerRadii.EMPTY, Insets.EMPTY)));
            pane5.toFront();
        }
    }


    public void readingProcess(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ReadingPage.fxml"));
        Parent root=(Parent)loader.load();
        Stage primaryStage=new Stage();
        ReadingPage readingPage=loader.getController();
        readingPage.printText(readTextField.getText());
        primaryStage.setTitle("ReadingPageScreen");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        generalPane.getScene().getWindow().hide();
    }

    public void deletingPoem(ActionEvent event) throws IOException {
        ReadingPage readingPage=new ReadingPage();
        readingPage.removeRecord(poemToBeDeletedField.getText());
        JOptionPane.showMessageDialog(null,poemToBeDeletedField.getText()+" Is deleted!");
        generalPane.getScene().getWindow().hide();

        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Activities.fxml"));
        primaryStage.setTitle("ActivitiesScreen");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void viewDetails(String username){
        this.username=username;
        try{
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/poemeditor", "root","JoshMasaqc11!");
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(String.format("select * from users where username='%s'", username));
            resultSet.next();
            viewUserNameField.setText(resultSet.getString("username"));
            viewEmailField.setText(resultSet.getString("email"));
            viewPasswordField.setText(resultSet.getString("password"));
        }catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null,"You are not connected to internet");
            System.out.println(sqlException.getMessage());
        }
    }

    public void editingDetails(ActionEvent event){
        if(editDetailsButton.getText().equals("Edit Details")){
            editDetailsButton.setText("Save Changes");
            viewUserNameField.setEditable(true);
            viewEmailField.setEditable(true);
            viewPasswordField.setEditable(true);
        }else{
            try{
                Connection connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/poemeditor","root","JoshMasaqc11!");
                PreparedStatement preparedStatement=(PreparedStatement)connection.prepareStatement("Update users set username=?, email=?, password=? where username=?");
                preparedStatement.setString(1,viewUserNameField.getText());
                preparedStatement.setString(2,viewEmailField.getText());
                preparedStatement.setString(3,viewPasswordField.getText());
                preparedStatement.setString(4,username);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null,"!Details have been updated successfully!");
                editDetailsButton.setText("Edit Details");
                viewUserNameField.setEditable(false);
                viewEmailField.setEditable(false);
                viewPasswordField.setEditable(false);
            }catch (SQLException sqlException){
                JOptionPane.showMessageDialog(null,"Oops! You are not connected to the internet");
                sqlException.printStackTrace();
            }
        }
    }

    public void loggingOut(ActionEvent event) throws IOException {
        generalPane.getScene().getWindow().hide();

        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("LoginScreen");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void uploadingPoem(ActionEvent event){
        Poem poem=new Poem();
        try{
            Connection connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/poemeditor","root","JoshMasaqc11!");
            PreparedStatement preparedStatement=(PreparedStatement)connection.prepareStatement("insert into poemsdetails (uploadedtime, writer, poemname, file, users_username) values (?,?,?,?,?)");
            Scanner read=new Scanner(new File("C:\\Users\\Joshua Masangula\\IdeaProjects\\PoemEditor\\poems\\table"));
            while (read.hasNext()){
                String name=read.next();
                String writer=read.next();
                String date=read.nextLine();
                if(fileToBeUploadedField.getText().equals(name)){
                    poem.setPoemName(name);
                    poem.setWriter(writer);
                }
            }
            Date date=new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
            String currentDateTime = format.format(date);
            file=new File("C:\\Users\\Joshua Masangula\\IdeaProjects\\PoemEditor\\poems\\"+fileToBeUploadedField.getText());
            FileInputStream fileInputStream=new FileInputStream(file);

            preparedStatement.setString(1,currentDateTime);
            preparedStatement.setString(2,poem.getWriter());
            preparedStatement.setString(3,poem.getPoemName());
            preparedStatement.setBlob(4,fileInputStream);
            preparedStatement.setString(5,username);
            preparedStatement.execute();

            JOptionPane.showMessageDialog(null,fileToBeUploadedField.getText()+" is uploaded to the database");
            read.close();
            fileInputStream.close();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null,"Poem is uploaded to the database");
        }catch (FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
            JOptionPane.showMessageDialog(null,"Record not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void viewWordLibrary(ActionEvent event) throws IOException {
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("WordLibrary.fxml"));
        primaryStage.setTitle("WordLibraryScreen");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void viewUploadedPoems(String username){
        ObservableList<Poem>list2=FXCollections.observableArrayList();
        try{
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/poemeditor","root","JoshMasaqc11!");
            ResultSet resultSet=connection.createStatement().executeQuery("select * from poemsdetails where users_username = '"+username+"'");
            int number=1;
            while(resultSet.next()){
                list2.add(new Poem(number,resultSet.getString("poemname"),resultSet.getString("writer"),resultSet.getString("uploadedtime")));
                number++;
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null,"Oops! You are not connected to the internet");
        }

        number2.setCellValueFactory(new PropertyValueFactory<Poem,Integer>("number"));
        poemName2.setCellValueFactory(new PropertyValueFactory<Poem,String>("poemName"));
        writer2.setCellValueFactory(new PropertyValueFactory<Poem,String>("writer"));
        date2.setCellValueFactory(new PropertyValueFactory<Poem, String>("date"));
        table3.setItems(list2);
    }

}
