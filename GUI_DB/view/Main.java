package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.EventHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//holds main pain, buttons, and text fields for application
public class Main extends Application
{
    private Scene scene;

    private ToolBar toolBar = new ToolBar();

    private Input2 input2 = new Input2();
    private Input3 input3 = new Input3();

    private saveAndCancel saveAndCancel = new saveAndCancel();
    private loginAndCancel loginAndCancel = new loginAndCancel();

    private Textfield2 text2 = new Textfield2();
    private Textfield3 text3 = new Textfield3();

    private BorderPane mainpane = new BorderPane();
    private Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);

    public Firebase database = new Firebase();
    public Map<String, Userinfo> user_in = new HashMap<>();

    public void Main(Stage primaryStage)
    {
        //All event handlers for each button
        toolBar.NewButton(new EventHandler<ActionEvent>() {
            @Override
                public void handle(ActionEvent event) {

                    input2.setAlignment(Pos.CENTER_LEFT);
                    text2.setAlignment(Pos.CENTER_LEFT);
                    saveAndCancel.setAlignment(Pos.BOTTOM_RIGHT);

                    mainpane.setLeft(input2);
                    mainpane.setCenter(text2);
                    mainpane.setBottom(saveAndCancel);
                }
            });

        toolBar.LogButton(new EventHandler<ActionEvent>() {
            @Override
                public void handle(ActionEvent event) {

                    input3.setAlignment(Pos.CENTER_LEFT);
                    text3.setAlignment(Pos.CENTER_LEFT);
                    loginAndCancel.setAlignment(Pos.BOTTOM_RIGHT);

                    mainpane.setLeft(input3);
                    mainpane.setCenter(text3);
                    mainpane.setBottom(loginAndCancel);
                }
            });

        saveAndCancel.CancelButton(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                text2.clear();
                text3.clear();
                mainpane.getChildren().removeAll(input2, text2, saveAndCancel);
                toolBar.setAlignment(Pos.CENTER);
                mainpane.setCenter(toolBar);
            }
        });

        loginAndCancel.CanButton(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                text2.clear();
                text3.clear();
                mainpane.getChildren().removeAll(input3, text3, loginAndCancel);
                toolBar.setAlignment(Pos.CENTER);
                mainpane.setCenter(toolBar);
            }
        });

        saveAndCancel.SaveButton(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(text2.getPass2().equals(text2.getRePass2())) {

                    user_in.put(text2.getName2(), new Userinfo(text2.getEmail2(),text2.getPass2()));

                    database.Add(user_in);

                    confirmation.setTitle("Confirmation");
                    confirmation.setHeaderText("New User Added!");

                    Optional<ButtonType> result = confirmation.showAndWait();

                    if (result.get() == ButtonType.OK) {
                        text2.clear();
                        text3.clear();
                    }
                }else{
                    confirmation.setTitle("Error");
                    confirmation.setHeaderText("passwords must match");

                    Optional<ButtonType> result = confirmation.showAndWait();
                }
            }
        });

        loginAndCancel.LogButton(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                    if(database.Check(text3.getName3(), text3.getPass3())){

                        confirmation.setTitle("Confirmation");
                        confirmation.setHeaderText("Congrats You Are in the Database!");

                        Optional<ButtonType> result = confirmation.showAndWait();

                    }else{

                        confirmation.setTitle("invalid");
                        confirmation.setHeaderText("invalid username or password");

                        Optional<ButtonType> result = confirmation.showAndWait();
                    }

                    text2.clear();
                    text3.clear();
            }
        });

            toolBar.setAlignment(Pos.CENTER);
            mainpane.setCenter(toolBar);

            scene = new Scene(mainpane, 400, 400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Log into database!");
            primaryStage.show();
    }

    public void start(Stage primaryStage)
    {
        Main(primaryStage);
    }
}

