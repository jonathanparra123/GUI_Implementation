package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

//buttons class for login
public class loginAndCancel extends HBox{

    private Button log = new Button("login");
    private Button can = new Button("cancel");

    public loginAndCancel()
    {
        this.getChildren().addAll(can, log);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setSpacing(20);
    }

    public void LogButton(EventHandler<ActionEvent> event) {log.setOnAction(event);}

    public void CanButton(EventHandler<ActionEvent> event) {can.setOnAction(event);}
}

