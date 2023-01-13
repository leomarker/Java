package com.example.studentregisterationdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.*;

public class HelloApplication extends Application {

    public void saveToDb(String id , String name , String grade) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "Mysql@root90");
        Statement statement = connection.createStatement();
        PreparedStatement pstm = null;
        String sql = "insert into students values(?,?,?)";
        pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        pstm.setString(2,name);
        pstm.setString(3,grade);
        int i = pstm.executeUpdate();
        if (i == 1)
            System.out.print("Data inserted");
        else
            System.out.print("Data insertion failed");
    }

    public  void getFromDB(String id) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "Mysql@root90");
        Statement statement = connection.createStatement();
        String sql = "select * from students where ?=?";
        PreparedStatement prst = connection.prepareStatement(sql);
        prst.setString(1,"id");
        prst.setString(2,id);
        ResultSet resultSet =prst.executeQuery(sql);

        System.out.print(resultSet);

        while (resultSet.next()){
            System.out.print(resultSet.getString("id"));
            System.out.print(resultSet.getString("name"));
            System.out.print(resultSet.getString("grade"));
            System.out.println("");
        }
    }
    public  boolean validateId(String id) {
        boolean validation = false;
        if (id != null && !id.isEmpty()) {
            if (id.matches("^\\d+$")) {
                validation = true;
            }
        }
        return validation;
    }
    public  boolean validateName(String name){
        boolean validation = false;
        if (name != null && !name.isEmpty()){
            if (name.matches("^[\\p{L} .'-]+$")){
                validation = true;
            }
        }
        return validation;
    }
    public boolean validateGrade(String grade){
        boolean validation = false;
        if (grade != null && !grade.isEmpty()){
//            if(grade.matches(){
//
//            }
        }
        return validation;
    }
    @Override
    public void start(Stage stage) throws IOException {
//        HBox hbox1 = new HBox();
//        Text text = new Text("Student List");
//        hbox1.getChildren().add(text);
//        hbox1.setAlignment(Pos.CENTER);
//
//        HBox hBoxinput = new HBox();
//        TextField textField = new TextField();
//        Label ID = new Label("ID");
//        ID.setLabelFor(textField);
//        hBoxinput.getChildren().addAll(ID,textField);
//        hBoxinput.setPadding(new Insets(20,20,20,20));
//        hBoxinput.setAlignment((Pos.CENTER));
//
//        HBox hBoxinput2 = new HBox();
//        TextField textField2 = new TextField();
//        Label Name = new Label("Name");
//        ID.setLabelFor(textField2);
//        hBoxinput2.getChildren().addAll(Name,textField2);
//        hBoxinput2.setPadding(new Insets(20,20,20,20));
//        hBoxinput2.setAlignment((Pos.CENTER));
//
//        HBox hBoxinput3 = new HBox();
//        TextField textField3 = new TextField();
//        Label Grade = new Label("Grade");
//        Grade.setLabelFor(textField3);
//        hBoxinput3.getChildren().addAll(Grade,textField3);
//        hBoxinput3.setPadding(new Insets(20,20,20,20));
//        hBoxinput3.setAlignment((Pos.CENTER));
//
//        HBox hBoxError = new HBox();
//        Label textError = new Label();
//        textError.setText("invalid Id, Id Must be a number with no characters");
//        hBoxError.setAlignment(Pos.CENTER);
//        hBoxError.getChildren().addAll(textError);
//        textError.setTextFill(Color.RED);
//        hBoxError.setVisible(false);
//
//        HBox hBoxError1 = new HBox();
//        Label textError1 = new Label();
//        textError1.setText("invalid name");
//        hBoxError1.setAlignment(Pos.CENTER);
//        hBoxError1.getChildren().addAll(textError1);
//        textError1.setTextFill(Color.RED);
//        hBoxError1.setVisible(false);
//
//        HBox hBoxError2 = new HBox();
//        Label textError2 = new Label();
//        textError2.setText("invalid Grade enter a value between 1.50-4.00");
//        hBoxError2.setAlignment(Pos.CENTER);
//        hBoxError2.getChildren().addAll(textError2);
//        textError2.setTextFill(Color.RED);
//        hBoxError2.setVisible(false);
//
//
//        Button cancel = new Button("Cancel");
//        cancel.setOnAction(e ->{
//            textField.setText("");
//            textField2.setText("");
//            textField3.setText("");
//
//        });
//
//        Button Save = new Button("Save");
//
//        Save.setOnAction(e -> {
//            int validation = 0;
//            String id = textField.getText();
//            String name = textField2.getText();
//            String grade = textField3.getText();
//
//            System.out.println(validateId(id));
//            System.out.println(validateName(name));
//            System.out.println();
//
//            if(validateId(id)){
//                validation += 1;
//            } else {
//                hBoxError.setVisible(true);
//            }
//            if (validateName(name)){
//                validation +=1;
//            } else {
//                hBoxError1.setVisible(true);
//            }
//
//
//            if (validation == 2){
//                try {
//                    saveToDb(id,name,grade);
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//                hBoxError.setVisible(false);
//                hBoxError1.setVisible(false);
//                System.out.println(id + name +grade);
//            }
//
//        });
//
//
//        Button View = new Button("View");
//        View.setOnAction(e ->{
//            int validation = 0;
//            String id = textField.getText();
//            String name = textField2.getText();
//            String grade = textField3.getText();
//
//            System.out.println(validateId(id));
//            System.out.println(validateName(name));
//            System.out.println();
//
//            if(validateId(id)){
//                validation += 1;
//            } else {
//                hBoxError.setVisible(true);
//            }
//            if (validateName(name)){
//                validation +=1;
//            } else {
//                hBoxError1.setVisible(true);
//            }
//
//
//            if (validation == 2){
//                try {
//                    getFromDB(id);
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//                hBoxError.setVisible(false);
//                hBoxError1.setVisible(false);
//                System.out.println(id + name +grade);
//            }
//        });
//
//
//        HBox hBoxinput4 = new HBox();
//        hBoxinput4.getChildren().addAll(cancel,Save,View);
//        hBoxinput4.setPadding(new Insets(20,20,20,20));
//        hBoxinput4.setAlignment((Pos.CENTER));
//
//
//        VBox vBox = new VBox();
//        vBox.setAlignment(Pos.CENTER);
//        vBox.getChildren().addAll(hbox1,hBoxinput,hBoxError,hBoxinput2,hBoxError1,hBoxinput3,hBoxError2,hBoxinput4);

// new code
        VBox MainWrapper = new VBox();
        HBox TitleWrapper =  new HBox();
        Text title = new Text("Student regester");
        TitleWrapper.getChildren().add(title);
        TitleWrapper.setAlignment(Pos.CENTER);
// starting grid
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        Text NameText = new Text("Name");
        Text IDText = new Text("ID");
        Text GradeText = new Text("Grade");
        TextField NameInput = new TextField();
        TextField IDInput = new TextField();
        TextField GradeInput = new TextField();

        Button CancelBtn = new Button("Cancel");
        CancelBtn.setStyle("-fx-background-color:#3b5998; -fx-text-fill:#ffffff;");
        Button SaveBtn = new Button("Save");
        SaveBtn.setStyle("-fx-background-color:#3b5998; -fx-text-fill:#ffffff;");
        Button ViewBtn = new Button("View");
        ViewBtn.setStyle("-fx-background-color:#3b5998; -fx-text-fill:#ffffff;");

        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(NameText, 0, 0);
        gridPane.add(NameInput, 1, 0);
        gridPane.add(IDText, 0, 1);
        gridPane.add(IDInput, 1, 1);
        gridPane.add(GradeText, 0, 2);
        gridPane.add(GradeInput, 1, 2);
        gridPane.add(ViewBtn , 0, 3);
        gridPane.add(SaveBtn, 1, 3);
        gridPane.add(CancelBtn, 2, 3);

        MainWrapper.setAlignment(Pos.CENTER);
        MainWrapper.getChildren().addAll(TitleWrapper,gridPane);


        Scene scene = new Scene(MainWrapper,500,500);
        stage.setTitle("Student list");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}