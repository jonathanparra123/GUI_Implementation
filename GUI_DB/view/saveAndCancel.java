package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

//Button class for save
public class saveAndCancel extends HBox{

    private Button save = new Button("save");
    private Button cancel = new Button("cancel");

    public saveAndCancel()
    {
        this.getChildren().addAll(cancel, save);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setSpacing(20);
    }

    public void CancelButton(EventHandler<ActionEvent> event) {cancel.setOnAction(event);}

    public void SaveButton(EventHandler<ActionEvent> event) {save.setOnAction(event);}
}
