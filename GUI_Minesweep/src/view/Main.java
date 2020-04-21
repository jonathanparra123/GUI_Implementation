package view;

import controller.Grid;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application
{
    // main panes that hold grid and buttons
    private Scene scene;
    private BorderPane mainpane = new BorderPane();
    private HBox toolBar = new HBox();

    //variables that control time and buttons
    private int counter = 0;
    private Text time = new Text("Time\n  "+ counter);
    private Button start = new Button("Start");
    private Timer timer = new Timer();
    private Grid grid = new Grid(timer);

    //starts timer
    public void initTime(){

        mainpane.setCenter(grid);

        timer.scheduleAtFixedRate(new TimerTask() {

                public void run() {
                    time.setText("Time\n  " + counter++);
                }
            }, 0, 1000);
    }

    public void Main( Stage primaryStage)
    {
        //setting up main pane
        toolBar.setPadding(new Insets(10,10,10,10));
        toolBar.setSpacing(40);
        toolBar.setAlignment(Pos.CENTER);
        toolBar.getChildren().addAll(start, time);

        grid.setMaxSize(420, 470);

        start.setOnMouseClicked(event -> initTime());

        mainpane.setTop(toolBar);

        scene = new Scene(mainpane, 420, 470);

        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void start(Stage primaryStage){Main(primaryStage);}
}
