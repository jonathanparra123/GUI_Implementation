package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

//labels for adding user
public class Input2 extends VBox {

    private Label name = new Label("UserName:");
    private Label email = new Label("Email:");
    private Label pass = new Label("Password:");
    private Label repass = new Label("Re-enter Password:");

    public Input2()
    {
        this.getChildren().addAll(name, email, pass, repass);
        this.setPadding(new Insets(10,10,10,10));
        this.setSpacing(18);
    }
}
