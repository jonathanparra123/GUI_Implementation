package controller;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Timer;

public class Tile extends StackPane {

    //how each cell is going to look
    private Label tile = new Label();
    private Label question = new Label();
    private Rectangle border = new Rectangle(20,20);

    //holds files of pictures
    private ImageView flag = new ImageView("data/flag.png");
    private ImageView bombs = new ImageView("data/bomb.png");

    private boolean clickflag = true;
    private boolean clickbomb = false;
    private boolean clicknormal = false;
    private boolean disableleft = false;
    private boolean disableRight = false;
    private boolean isBomb;
    private int bombCount;

    public Tile(boolean bomb, Timer timer)
    {
            isBomb = bomb;

            //constructing each cell
            tile.setText(null);
            question.setText("?");
            question.setStyle("-fx-font-style: italic");
            question.setStyle("-fx-font-size: 15");

            border.setStrokeWidth(1);
            border.setStroke(Color.GRAY);
            border.setFill(Color.LIGHTGRAY);

            flag.setFitHeight(20);
            flag.setFitWidth(20);
            bombs.setFitWidth(20);
            bombs.setFitHeight(20);

            this.getChildren().addAll(tile, border);

            //event handler of each cell when mouse is clicked
            this.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton() == MouseButton.PRIMARY) {

                        if (!disableleft) {

                            disableRight = true;

                            if(!isBomb) {

                                tile.setText(""+getBomb());

                                border.setFill(null);
                            }
                            else {

                                setBombImage();

                                AlertBoxLose();

                                stop_timer(timer);

                                border.setFill(null);
                            }
                        }
                    } else if (event.getButton() == MouseButton.SECONDARY) {

                        if (!disableRight) {

                            disableleft = true;

                            setImg();
                        }
                    }
                }
            });
    }

    //alert box when bomb is found
    public void AlertBoxLose()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("result");

            alert.setHeaderText("You Lose!!");
            alert.show();
    }

    //returns if tile is a bomb
    public int getBomb() {return bombCount;}
    public void setBomb(int bombs) {bombCount = bombs;}

    public boolean getIsBomb(){return isBomb;}

    public void setBombImage(){this.getChildren().add(bombs);}

    //stops timer when bomb is found
    public void stop_timer(Timer timer){

        timer.cancel();
    }

        //sets image on cell depending of right click.
        public void setImg()
        {
            if(clickflag)
            {
                clickflag = false;
                clickbomb = true;
                clicknormal = false;

                this.getChildren().add(flag);
            }
            else if(clickbomb)
            {
                clickflag = false;
                clickbomb = false;
                clicknormal = true;

                this.getChildren().add(question);
                this.getChildren().remove(flag);
            }
            else if(clicknormal)
            {
                clickflag = true;
                clickbomb = false;
                clicknormal = false;
                disableleft = false;

                this.getChildren().remove(question);
            }
        }
}

