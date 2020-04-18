package view;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

//labels for login
public class Input3 extends VBox {

    private Label name = new Label("UserName:");
    private Label pass = new Label("Password:");

    public Input3()
    {
        this.getChildren().addAll(name, pass);
        this.setPadding(new Insets(10,10,10,10));
        this.setSpacing(18);
    }
}
