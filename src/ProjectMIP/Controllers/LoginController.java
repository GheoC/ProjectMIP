package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginController {
    public TextField txt_username;
    public PasswordField txt_password;
    public Button lbl_Login;
    public Button lbl_CreateUser;
    public AnchorPane content;


    public void loginUser(ActionEvent actionEvent)
    {
        System.out.println(txt_username.getText() + " " + txt_password.getText());
    }

    public void loadCreateUser(ActionEvent actionEvent) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/CreateUser.fxml"));

        content.getChildren().setAll(anchorPane);


    }
}
