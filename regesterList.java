
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

public class HelloApplication extends Application {
    public void savefile (String id, String name , String grade){
        try {
            String data = id+"\t"+name+"\t"+grade+ "\t";
            File file = new File("C:/\\Users\\nati\\Desktop\\java\\student.txt\\");
            FileReader myReader = new FileReader(file);
            BufferedReader myBuffRead = new BufferedReader(myReader);

            if(myBuffRead.readLine() != null){
                FileWriter myWriter = new FileWriter(file,true);
                BufferedWriter mybuff = new BufferedWriter(myWriter);

                mybuff.write("\n"+data);
                mybuff.close();
            } else {
                FileWriter myWriter = new FileWriter(file,true);
                BufferedWriter mybuff = new BufferedWriter(myWriter);

                mybuff.write(data);
                mybuff.close();
            }


//            myWriter.write(data);
//            myWriter.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e){
            System.out.println(e);
        }
    }


    public  String[] readFile(String id) {
        String[] Arr = new String[0];
        try {
            File file = new File("C:/\\Users\\nati\\Desktop\\java\\student.txt\\");
            FileReader myReader = new FileReader(file);
            BufferedReader myBuffRead = new BufferedReader(myReader);

            String line = null;
            while ((line = myBuffRead.readLine()) != null) {
                if (line.startsWith(id)) {
                    System.out.println(line.startsWith(id));
                    Arr = line.split("\t");
                    System.out.println(Arr);
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        return Arr;
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
        HBox hbox1 = new HBox();
        Text text = new Text("Student List");
        hbox1.getChildren().add(text);
        hbox1.setAlignment(Pos.CENTER);

        HBox hBoxinput = new HBox();
        TextField textField = new TextField();
        Label ID = new Label("ID");
        ID.setLabelFor(textField);
        hBoxinput.getChildren().addAll(ID,textField);
        hBoxinput.setPadding(new Insets(20,20,20,20));
        hBoxinput.setAlignment((Pos.CENTER));

        HBox hBoxinput2 = new HBox();
        TextField textField2 = new TextField();
        Label Name = new Label("Name");
        ID.setLabelFor(textField2);
        hBoxinput2.getChildren().addAll(Name,textField2);
        hBoxinput2.setPadding(new Insets(20,20,20,20));
        hBoxinput2.setAlignment((Pos.CENTER));

        HBox hBoxinput3 = new HBox();
        TextField textField3 = new TextField();
        Label Grade = new Label("Grade");
        Grade.setLabelFor(textField3);
        hBoxinput3.getChildren().addAll(Grade,textField3);
        hBoxinput3.setPadding(new Insets(20,20,20,20));
        hBoxinput3.setAlignment((Pos.CENTER));

        HBox hBoxError = new HBox();
        Label textError = new Label();
        textError.setText("invalid Id, Id Must be a number with no characters");
        hBoxError.setAlignment(Pos.CENTER);
        hBoxError.getChildren().addAll(textError);
        textError.setTextFill(Color.RED);
        hBoxError.setVisible(false);

        HBox hBoxError1 = new HBox();
        Label textError1 = new Label();
        textError1.setText("invalid name");
        hBoxError1.setAlignment(Pos.CENTER);
        hBoxError1.getChildren().addAll(textError1);
        textError1.setTextFill(Color.RED);
        hBoxError1.setVisible(false);

        HBox hBoxError2 = new HBox();
        Label textError2 = new Label();
        textError2.setText("invalid Grade enter a value between 1.50-4.00");
        hBoxError2.setAlignment(Pos.CENTER);
        hBoxError2.getChildren().addAll(textError2);
        textError2.setTextFill(Color.RED);
        hBoxError2.setVisible(false);


        Button cancel = new Button("Cancel");
        cancel.setOnAction(e ->{
            textField.setText("");
            textField2.setText("");
            textField3.setText("");

        });

        Button Save = new Button("Save");

        Save.setOnAction(e -> {
            int validation = 0;
            String id = textField.getText();
            String name = textField2.getText();
            String grade = textField3.getText();

            System.out.println(validateId(id));
            System.out.println(validateName(name));
            System.out.println();

            if(validateId(id)){
                validation += 1;
            } else {
                hBoxError.setVisible(true);
            }
            if (validateName(name)){
                validation +=1;
            } else {
                hBoxError1.setVisible(true);
            }


            if (validation == 2){
                savefile(id,name,grade);
                hBoxError.setVisible(false);
                hBoxError1.setVisible(false);
                System.out.println(id + name +grade);
            }

        });


        Button View = new Button("View");
        View.setOnAction(e ->{
            String id =  textField.getText();
        String[] Arr  =   readFile(id);
            textField2.setText(Arr[1]);
            textField3.setText(Arr[2]);

        });


        HBox hBoxinput4 = new HBox();
        hBoxinput4.getChildren().addAll(cancel,Save,View);
        hBoxinput4.setPadding(new Insets(20,20,20,20));
        hBoxinput4.setAlignment((Pos.CENTER));


        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(hbox1,hBoxinput,hBoxError,hBoxinput2,hBoxError1,hBoxinput3,hBoxError2,hBoxinput4);



        Scene scene = new Scene(vBox,500,500);
        stage.setTitle("Student list");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
