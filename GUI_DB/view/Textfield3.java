package view;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

//user input class for login
public class Textfield3 extends VBox {

    private TextField textfieldName = new TextField();
    private TextField textfieldPass = new TextField();

    public Textfield3()
    {
        this.getChildren().addAll(textfieldName, textfieldPass);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setSpacing(10);
    }

    public void clear()
    {
        textfieldName.setText("");
        textfieldPass.setText("");
    }

    public String getName3(){return textfieldName.getText();}

    public String getPass3() { return textfieldPass.getText();}
}
