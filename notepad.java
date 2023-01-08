package com.example.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;

public class HelloApplication extends Application {
    String Email = "1";
    String Password = "1";
    public boolean login(String email, String password) {
        return Objects.equals(email, Email) && Objects.equals(password, Password);
    }

    public void savefile(String note,File path){
        try {

            FileWriter myWriter = new FileWriter(path);

            myWriter.write(note);
            myWriter.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e){
            System.out.println(e);
            System.out.println("wjau");
        }
    }
    public String readfile(File path){
        String note = " ";

        try{
            BufferedReader br
                    = new BufferedReader(new FileReader(path));
            String st;

            while ((st = br.readLine()) != null)

                // Print the string
                note = st;
            System.out.println(st);

        } catch (IOException e){
            System.out.println(e);
        }
        return note;
    }
 public void start(Stage stage) throws IOException{

        HBox hBoxtext = new HBox();
           Text text = new Text();
           text.setText("Login with email and password");
           hBoxtext.getChildren().addAll(text);
           hBoxtext.setPadding(new Insets(10,10,20,10));
           hBoxtext.setAlignment(Pos.CENTER);

        HBox hBoxinput = new HBox();
           TextField textField = new TextField();
           Label email = new Label("Email");
           email.setLabelFor(textField);
           email.setPadding(new Insets(0,32,0,0));
           hBoxinput.getChildren().addAll(email,textField);
           hBoxinput.setAlignment((Pos.CENTER));
           hBoxinput.setPadding(new Insets(0 , 0,20,0));

        HBox hBoxpasswordInput = new HBox();
           TextField textField1 = new TextField();
           Label password = new Label("Password");
           password.setLabelFor(textField1);
           password.setPadding(new Insets(0,12,0,0));
           hBoxpasswordInput.getChildren().addAll(password,textField1);
           hBoxpasswordInput.setAlignment(Pos.CENTER);

     Text error = new Text();
     error.setText("email or password error");
     error.setVisible(false);




        HBox hBoxbtns = new HBox();

        HBox hBoxbtn = new HBox();
           Button btn = new Button();
           btn.setText("login");
           btn.setTextFill(Color.BLUE);

           btn.setOnAction(e ->{
               String mail = textField.getText();
               String passcode = textField1.getText();
              boolean value = login(mail,passcode);
               if(value){

                   File file = new File("C:\\Users\\nati\\Desktop\\java");
                   FileChooser save = new FileChooser();

                   HBox text1 = new HBox();
                   TextArea textarea = new TextArea();
                   text1.getChildren().add(textarea);
                   textarea.setPrefSize(300,300);
                   textarea.setPadding(new Insets(20,20,20,20));
                    text1.setAlignment(Pos.CENTER);



//                   HBox navbar = new HBox();
//                   navbar.getChildren().addAll(file,read,reset);

                   MenuItem savebtn = new MenuItem("Save");
                   savebtn.setOnAction(event -> {
                       FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files(*.txt)","*.txt");
                       save.setInitialFileName("text");
                       save.setSelectedExtensionFilter(extensionFilter);
                       save.setInitialDirectory(file);
                       save.getExtensionFilters().add(extensionFilter);
                       String note = textarea.getText();
                       File path = save.showSaveDialog(stage);
                       savefile(note,path);

                   });

                   MenuItem saveAs = new MenuItem("SaveAs");
                   saveAs.setOnAction(event -> {
                       FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files(*.txt)","*.txt");
                       save.setInitialFileName("text");
                       save.setSelectedExtensionFilter(extensionFilter);
                       save.setInitialDirectory(file);
                       save.getExtensionFilters().add(extensionFilter);
                       String note = textarea.getText();
                       File path = save.showSaveDialog(stage);
                       savefile(note,path);

                   });


                   MenuItem open = new MenuItem("Open");
                   open.setOnAction(event -> {
                      File path = save.showOpenDialog(stage);
                      String note = readfile(path);
                      textarea.setText(note);
                   });

                   MenuItem exit = new MenuItem("Exit");
                   exit.setOnAction(event -> {
                       System.exit(1);
                   });


                   Menu fileMenu = new Menu("File");
                   fileMenu.getItems().addAll(savebtn,saveAs,open,exit);

                   MenuBar menuBar = new MenuBar();
                   menuBar.getMenus().add(fileMenu);


                   VBox vBox = new VBox();
                   vBox.getChildren().addAll(menuBar,textarea);



                   Scene scene = new Scene(vBox,500,500);
                   stage.setTitle("Notes");
                   stage.setScene(scene);
                   stage.show();
               }
               else {
                error.setVisible(true);
               }

           });
           hBoxbtn.getChildren().add(btn);
           hBoxbtn.setAlignment(Pos.CENTER);
           hBoxbtn.setPadding(new Insets(0,20,0,0));

           HBox hBoxbtn2 = new HBox();
           Button btn2 = new Button();
           btn2.setText("Cancel");
     btn2.setTextFill(Color.BLUE);
           hBoxbtn2.getChildren().add(btn2);
           hBoxbtn2.setAlignment(Pos.CENTER);
          btn2.setOnAction(e ->{
              textField.setText("");
              textField1.setText("");
              error.setVisible(false);
          });
            hBoxbtns.getChildren().addAll(hBoxbtn,hBoxbtn2);

           hBoxbtns.setAlignment(Pos.CENTER);
           hBoxbtns.setPadding(new Insets(20,0,0,0));


        VBox vboxForm = new VBox();
        vboxForm.getChildren().addAll(hBoxinput,hBoxpasswordInput);
        vboxForm.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(hBoxtext,vboxForm,error,hBoxbtns);
        vBox.setAlignment(Pos.CENTER);



        Scene scene = new Scene(vBox,500, 400);

        stage.setTitle("test");
        stage.setScene(scene);
        stage.show();
    }




    public static void main(String[] args) {
        launch();
    }
}