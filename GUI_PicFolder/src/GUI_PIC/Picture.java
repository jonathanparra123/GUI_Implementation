package GUI_PIC;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.TextInputDialog;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Optional;

public class Picture extends Application
{
    private Button next, previous, del, add;
    private Pane pane;
    private Scene scene;
    private EventHandler<ActionEvent> handler;
    private TextInputDialog input;
    private ImageView view = new ImageView();
    private int initial = 0;
    private ArrayList<String> list = new ArrayList<String>();
    private ManageData picIn = new ManageData();
    private ArrayList<String> error = new ArrayList<String>();

    //controls main panel where buttons are accessed
    public void sceneControl(Stage primaryStage)
    {
        list = picIn.FileIn();
        Image picture = new Image(list.get(initial));
        error.add("http://www.jordans.com/~/media/jordans%20redesign/no-image-found.ashx?h=275&la=en&w=275&hash=F87BC23F17E37D57E2A0B1CC6E2E3EEE312AAD5B");

        //even handler that triggers when a button is pressed
        handler = new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                if(event.getSource() == next)
                {
                    initial += 1;
                    if(initial == list.size())
                    {
                        initial = 0;
                        view.setImage(new Image(list.get(initial)));
                    }
                    else{

                        view.setImage(new Image(list.get(initial)));
                    }
                }
                else if(event.getSource() == previous)
                {
                    initial -= 1;
                    if(initial < 0)
                    {
                        initial = list.size();
                        view.setImage(new Image(list.get(0)));
                    }
                    else{
                        view.setImage(new Image(list.get(initial)));
                    }
                }
                else if(event.getSource() == del)
                {
                    list.remove(initial);

                    if(list.isEmpty())
                    {
                        view.setImage(new Image(error.get(0)));
                        next.setDisable(true);
                        del.setDisable(true);
                        previous.setDisable(true);
                    }
                    else {
                        view.setImage((new Image(list.get(initial))));
                    }
                }
                else if (event.getSource() == add)
                {
                    addUrl();
                    next.setDisable(false);
                    del.setDisable(false);
                    previous.setDisable(false);

                    if(!list.get(initial).isEmpty()) {
                        view.setImage((new Image(list.get(initial))));
                    }
                }
            }
        };

        //set of button types
        next = new Button("next");
        next.setLayoutX(280);
        next.setLayoutY(320);
        next.setOnAction(handler);

        previous = new Button("previous");
        previous.setLayoutX(60);
        previous.setLayoutY(320);
        previous.setOnAction(handler);

        add = new Button("add");
        add.setLayoutX(150);
        add.setLayoutY(320);
        add.setOnAction(handler);

        del = new Button("delete");
        del.setLayoutX(200);
        del.setLayoutY(320);
        del.setOnAction(handler);

        view.setImage(picture);
        view.setFitWidth(150);
        view.setFitHeight(150);
        view.setLayoutX(120);
        view.setLayoutY(80);

        pane = new Pane();
        pane.getChildren().add(previous);
        pane.getChildren().add(next);
        pane.getChildren().add(add);
        pane.getChildren().add(del);
        pane.getChildren().add(view);
        scene = new Scene(pane, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Fun Pic Folder");
        primaryStage.show();

    }

//add URL to file and array
public void addUrl()
{
    input = new TextInputDialog();
    input.setTitle("Image");
    input.setHeaderText("Add URL of image");
    Optional<String> inputBox = input.showAndWait();

    if(inputBox.isPresent())
    {
        ManageData data = new ManageData();
        data.Fileout(inputBox.get());
        list.add(initial,inputBox.get());
    }

    if (list.get(initial).isEmpty()){

        list.remove(initial);
    }
}
    public void start(Stage primaryStage)
    {
        sceneControl(primaryStage);
    }
}
