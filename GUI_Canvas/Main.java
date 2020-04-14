import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;

public class Main extends Application {

    private StackPane mainPane;
    private Pane draw_pane, button_pane;
    private Scene scene;
    private Popup pop = new Popup(); //pop up window variable
    private EventHandler<ActionEvent> handler, event_pop; //event handler action variable
    private Button draw, erase,shape, buttone,buttonr,buttonl; //buttons
    private boolean initial_draw = true;
    private boolean initial_erase = false;
    private Ellipse elli = new Ellipse();
    private Rectangle rect = new Rectangle();
    private Line lin = new Line();
    private int choose_shape = 1;
    private double cx, ex, lx;
    private double cy, ey, ly;

    public void drawPicture(Stage primaryStage) {

        initButton(primaryStage);

        //creating buttons and UI look and feel
        draw = new Button("draw");
        draw.setLayoutX(100);
        draw.setLayoutY(40);
        draw.setOnAction(handler);

        erase = new Button("erase");
        erase.setLayoutX(400);
        erase.setLayoutY(40);
        erase.setOnAction(handler);

        shape = new Button("shape");
        shape.setLayoutX(250);
        shape.setLayoutY(40);
        shape.setOnAction(handler);

        Rectangle clip = new Rectangle(0, 100, 700, 500);

        draw_pane = new Pane();
        draw_pane.setStyle("-fx-background-color: white");
        draw_pane.setClip(clip);

            //even handler when drawing figures on initial press
            draw_pane.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    if(pop.isShowing()) {
                        pop.hide();
                    }

                    if(initial_draw) {

                        if (choose_shape == 1) {

                            elli = new Ellipse();
                            cx = event.getX();
                            cy = event.getY();
                            elli.setFill(Color.RED);
                            elli.setStrokeWidth(5);
                            elli.setStroke(Color.BLACK);
                            draw_pane.getChildren().add(elli);

                        }else if(choose_shape == 2){

                            rect = new Rectangle();
                            ex = event.getX();
                            ey = event.getY();
                            rect.setFill(Color.RED);
                            rect.setStrokeWidth(5);
                            rect.setStroke(Color.BLACK);
                            draw_pane.getChildren().add(rect);

                        }else if(choose_shape == 3){

                            lin = new Line();
                            lx = event.getX();
                            ly = event.getY();
                            lin.setFill(Color.RED);
                            lin.setStrokeWidth(5);
                            lin.setStroke(Color.BLACK);
                            draw_pane.getChildren().add(lin);
                        }
                    }
                    else if(initial_erase)
                    {
                        draw_pane.getChildren().remove(event.getTarget());
                    }
                }
            });

        //event handler when mouse is dragged
        draw_pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(choose_shape == 1) {
                    elli.setRadiusX(Math.abs(event.getX() - cx)/2);
                    elli.setRadiusY(Math.abs(event.getY() - cy)/2);
                    elli.setCenterX(Math.abs((event.getX() + cx)/2);
                    elli.setCenterY(Math.abs((event.getY() + cx)/2);
                }else if(choose_shape == 2){
                    rect.setX(ex);
                    rect.setY(ey);
                    rect.setWidth(event.getX() - ex);
                    rect.setHeight(event.getY() - ey);
                }else if(choose_shape == 3){
                    lin.setStartX(lx);
                    lin.setStartY(ly);
                    lin.setEndX(event.getX());
                    lin.setEndY(event.getY());
                }
            }
        });

        button_pane = new Pane();
        button_pane.getChildren().add(draw);
        button_pane.getChildren().add(erase);
        button_pane.getChildren().add(shape);
        button_pane.setStyle("-fx-background-color: #d1d6dc");

        mainPane = new StackPane();
        mainPane.getChildren().add(button_pane);
        mainPane.getChildren().add(draw_pane);

        scene = new Scene(mainPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Somewhat Lame Drawing Tablet");
        primaryStage.show();
    }

//method to handle changing shapes pop window
public void ChoosePop(){

     event_pop = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if(event.getSource() == buttone){
                        choose_shape = 1;
                    } else if(event.getSource() == buttonr){
                        choose_shape = 2;
                    } else if(event.getSource() == buttonl){
                        choose_shape = 3;
                    }
                }
            };
}

//method to handle options of figures
public void initButton(Stage p) {

    ChoosePop();

    handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            if (event.getSource() == erase) {

                initial_draw = false;
                initial_erase = true;

                if(pop.isShowing()) {
                    pop.hide();
                }

            } else if (event.getSource() == draw) {

                initial_draw = true;
                initial_erase = false;
                if(pop.isShowing()) {
                    pop.hide();
                }

            }else if (event.getSource() == shape){

                initial_erase = false;
                initial_draw = false;

                buttonr = new Button("Rectangle");
                buttonr.setLayoutX(70);
                buttonr.setLayoutY(25);
                buttonr.setOnAction(event_pop);

                buttone = new Button("Ellipse");
                buttone.setLayoutX(76);
                buttone.setLayoutY(75);
                buttone.setOnAction(event_pop);

                buttonl = new Button("Line");
                buttonl.setLayoutX(80);
                buttonl.setLayoutY(125);
                buttonl.setOnAction(event_pop);
                Pane pop_pane = new Pane();

                pop_pane.getChildren().addAll(buttone,buttonl,buttonr);

                pop_pane.setStyle("-fx-background-color: #bbf2ff");
                pop_pane.setMinSize(200,170);

                pop.getContent().add(pop_pane);

                pop.show(p);

                initial_draw = true;
            }
        }
    };
}
    public void start(Stage primaryStage)
    {
        drawPicture(primaryStage);
    }
}
