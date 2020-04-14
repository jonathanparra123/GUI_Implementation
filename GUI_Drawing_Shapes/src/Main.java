import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Main extends Application
{
    private BorderPane mainpane;
    private ToolPane toolPane;
    private Pane drawpane;
    private Scene scene;
    private Ellipse ellip;
    private Rectangle rec;
    private Path line;
    private double x;
    private double y;
    private double recx;
    private double recy;

    public void Main(Stage primaryStage)
    {
        //Main panes with layouts
        mainpane = new BorderPane();
        toolPane = new ToolPane();
        drawpane = new BorderPane();

        Rectangle clip = new Rectangle(500,500);

        drawpane.setClip(clip);
        drawpane.setPrefWidth(500);
        drawpane.setPrefHeight(500);

        //shape of panes
        clip.widthProperty().bind(drawpane.widthProperty());
        clip.heightProperty().bind(drawpane.heightProperty());

        //types of shape objects
        ellip = new Ellipse();
        rec = new Rectangle();
        line = new Path();

        //action on mouse pressed
        drawpane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(toolPane.ellBtnSelected()) {

                    x = event.getX();
                    y = event.getY();

                    ellip = new Ellipse(x, y, 0, 0);

                    ellip.setFill(toolPane.getFillPickerValue());
                    ellip.setStrokeWidth(toolPane.getStrokeSizeValue());
                    ellip.setStroke(toolPane.getStrokePickerValue());

                    setShapeHandler(ellip);

                    ellip.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            toolPane.setFillPickerValue((Color)((Ellipse)event.getSource()).getFill());
                            toolPane.setStrokePickerValue ((Color)((Ellipse)event.getSource()).getStroke());
                            toolPane.setStrokeSizeValue((int)((Ellipse)event.getSource()).getStrokeWidth());
                        }
                    });
                    drawpane.getChildren().add(ellip);
                }
                else if (toolPane.rectBtnSelected())
                {
                    rec = new Rectangle();

                    recx = event.getX();
                    recy = event.getY();

                    rec.setFill(toolPane.getFillPickerValue());
                    rec.setStrokeWidth(toolPane.getStrokeSizeValue());
                    rec.setStroke(toolPane.getStrokePickerValue());

                    setShapeHandler(rec);

                    drawpane.getChildren().add(rec);

                    rec.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            toolPane.setFillPickerValue((Color)((Rectangle)event.getSource()).getFill());
                            toolPane.setStrokePickerValue ((Color)((Rectangle)event.getSource()).getStroke());
                            toolPane.setStrokeSizeValue((int)((Rectangle)event.getSource()).getStrokeWidth());
                        }
                    });
                }
                else if(toolPane.editBtnSelected())
                {
                    if(event.getTarget() == ellip)
                    {
                        toolPane.setFillPickerAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                ellip.setFill(toolPane.getFillPickerValue());
                            }
                        });

                        toolPane.setStrokePickerAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                ellip.setStroke(toolPane.getStrokePickerValue());
                            }
                        });

                        toolPane.setStrokeSizeAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                ellip.setStrokeWidth(toolPane.getStrokeSizeValue());
                            }
                        });
                    }
                    else if(event.getTarget() == rec)
                    {
                        toolPane.setFillPickerAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                rec.setFill(toolPane.getFillPickerValue());
                            }
                        });

                        toolPane.setStrokePickerAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                rec.setStroke(toolPane.getStrokePickerValue());
                            }
                        });

                        toolPane.setStrokeSizeAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                rec.setStrokeWidth(toolPane.getStrokeSizeValue());
                            }
                        });
                    }
                    else if(event.getTarget() == line)
                    {
                        toolPane.setStrokePickerAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                line.setStroke(toolPane.getStrokePickerValue());
                            }
                        });

                        toolPane.setStrokeSizeAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                line.setStrokeWidth(toolPane.getStrokeSizeValue());
                            }
                        });
                    }
                }
                else if(toolPane.freeBtnSelected())
                {
                    line = new Path();

                    line.setStrokeWidth(toolPane.getStrokeSizeValue());
                    line.setStroke(toolPane.getStrokePickerValue());

                    line.getElements().add(new MoveTo(event.getX(),event.getY()));

                    setShapeHandler(line);
                    drawpane.getChildren().add(line);

                    line.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            toolPane.setFillPickerValue((Color)((Path)event.getSource()).getFill());
                            toolPane.setStrokePickerValue ((Color)((Path)event.getSource()).getStroke());
                            toolPane.setStrokeSizeValue((int)((Path)event.getSource()).getStrokeWidth());
                        }
                    });
                }
                else if(toolPane.eraseBtnSelected())
                {
                    drawpane.getChildren().remove(event.getTarget());
                }
            }
        });

        //action when mouse is dragged to draw objects
        drawpane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                 if (toolPane.ellBtnSelected()) {
                    ellip.setRadiusX(Math.abs(event.getX() - x)/ 2);
                    ellip.setRadiusY(Math.abs(event.getY() - y)/ 2);
                    ellip.setCenterX(Math.abs(event.getX() + x) / 2);
                    ellip.setCenterY(Math.abs(event.getY() + y) / 2);
                }
                else if(toolPane.rectBtnSelected()) {
                    rec.setX(Math.min(recx, event.getX()));
                    rec.setY(Math.min(recy, event.getY()));
                    rec.setWidth(Math.abs(recx - event.getX()));
                    rec.setHeight(Math.abs(recy - event.getY()));
                }
                else if (toolPane.freeBtnSelected())
                {
                    line.getElements().add(new LineTo(event.getX(), event.getY()));
                }
            }
        });

        toolPane.setAlignment(Pos.CENTER);
        mainpane.setTop(toolPane);
        mainpane.setCenter(drawpane);

        scene = new Scene(mainpane, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("somewhat decent drawing tablet");
        primaryStage.show();
    }

    //action handler of each event with a shape
    private void setShapeHandler(Shape shape) {
        shape.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                shape.setTranslateX(shape.getTranslateX() + event.getX() - x);
                shape.setTranslateY(shape.getTranslateY() + event.getY() - y);
            }
        });
    }

    public void start(Stage primaryStage)
    {
        Main(primaryStage);
    }
};
