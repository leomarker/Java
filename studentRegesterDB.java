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
        String sql = "select * from students where ";
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
        Text NameError = new Text("Invalid name");
        NameError.setFill(Color.RED);
        NameError.setVisible(false);
        Text IDError = new Text("Invalid id , id must be a number with no characters");
        IDError.setFill(Color.RED);
        IDError.setVisible(false);
        Text GradeError = new Text("Invalid Grade enter a value between 1.50-4.00");
        GradeError.setFill(Color.RED);
        GradeError.setVisible(false);
        Text SuccessMsg = new Text("Successfully saved to DataBase");
        SuccessMsg.setFill(Color.GREEN);
        SuccessMsg.setVisible(false);


        TextField NameInput = new TextField();
        TextField IDInput = new TextField();
        TextField GradeInput = new TextField();

        Button CancelBtn = new Button("Cancel");
        CancelBtn.setStyle("-fx-background-color:#3b5998; -fx-text-fill:#ffffff;");
        Button SaveBtn = new Button("Save");
        SaveBtn.setStyle("-fx-background-color:#3b5998; -fx-text-fill:#ffffff;");
        Button ViewBtn = new Button("View");
        ViewBtn.setStyle("-fx-background-color:#3b5998; -fx-text-fill:#ffffff;");


        SaveBtn.setOnAction(e -> {
            int validation = 0;
            String id = IDInput.getText();
            String name = NameInput.getText();
            String grade = GradeInput.getText();


            if(validateId(id)){
                validation += 1;
            } else {
                IDError.setVisible(true);
            }
            if (validateName(name)){
                validation +=1;
            } else {
                NameError.setVisible(true);
            }


            if (validation == 2){
                try {
                    saveToDb(id,name,grade);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                IDError.setVisible(false);
                GradeError.setVisible(false);
                SuccessMsg.setVisible(true);
                System.out.println(id + name +grade);
            }

        });

        ViewBtn.setOnAction(e ->{
            int validation = 0;
            String id =  IDInput.getText();
            String name = NameInput.getText();
            String grade = GradeInput.getText();



            if(validateId(id)){
                validation += 1;
            } else {
                IDError.setVisible(true);
            }
            if (validateName(name)){
                validation +=1;
            } else {
                NameError.setVisible(true);
            }


            if (validation == 2){
                try {
                    getFromDB(id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                NameError.setVisible(false);
                IDError.setVisible(false);
                System.out.println(id + name +grade);
            }
        });

        CancelBtn.setOnAction(e ->{
            IDInput.setText("");
            NameInput.setText("");
            GradeInput.setText("");
        });

        gridPane.setMinSize(600, 600);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(NameText, 0, 0);
        gridPane.add(NameInput, 1, 0);
        gridPane.add(NameError, 2, 0);
        gridPane.add(IDText, 0, 1);
        gridPane.add(IDInput, 1, 1);
        gridPane.add(IDError, 2, 1);
        gridPane.add(GradeText, 0, 2);
        gridPane.add(GradeInput, 1, 2);
        gridPane.add(GradeError, 2, 2);
        gridPane.add(ViewBtn , 0, 3);
        gridPane.add(SaveBtn, 1, 3);
        gridPane.add(CancelBtn, 2, 3);
        gridPane.add(SuccessMsg,0,4);

        MainWrapper.setAlignment(Pos.CENTER);
        MainWrapper.getChildren().addAll(TitleWrapper,gridPane);


        Scene scene = new Scene(MainWrapper,700,700);
        stage.setTitle("Student list");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}