package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;

//Button class for main pane
public class ToolBar extends HBox {
    private Button newU = new Button("New User");
    private Button oldU = new Button("Log In");

    public ToolBar()
    {
        this.getChildren().addAll(oldU, newU);

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setSpacing(50);
    }

    public void NewButton(EventHandler<ActionEvent> event)
    {
        newU.setOnAction(event);
    }

    public void LogButton(EventHandler<ActionEvent> event)
    {
        oldU.setOnAction(event);
    }
}



