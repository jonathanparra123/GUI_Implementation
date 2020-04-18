package view;


import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

//User input class for new user
public class Textfield2 extends VBox {

    private TextField textfieldName = new TextField();
    private TextField textfieldEmail = new TextField();
    private TextField textfieldPass = new TextField();
    private TextField textfieldRePass = new TextField();

    public Textfield2()
    {
        this.getChildren().addAll(textfieldName, textfieldEmail, textfieldPass, textfieldRePass);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setSpacing(10);
    }

    public void clear()
    {
        textfieldName.setText("");
        textfieldEmail.setText("");
        textfieldPass.setText("");
        textfieldRePass.setText("");
    }

    public String getName2(){return textfieldName.getText();}

    public String getEmail2() {return textfieldEmail.getText();}

    public String getPass2() { return textfieldPass.getText();}

    public String getRePass2() { return textfieldRePass.getText();}
}