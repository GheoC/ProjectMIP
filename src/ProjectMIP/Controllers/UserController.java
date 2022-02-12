package Controllers;

import Service.UserService.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class UserController {

    @FXML
    private TextField txt_FirstName;
    @FXML
    private TextField txt_LastName;
    @FXML
    private TextField txt_Email;
    @FXML
    private PasswordField txt_Password;
    @FXML
    private Label lbl_UserCreated;

    private final UserService userService = new UserService();


    public void clickButton(ActionEvent actionEvent) {

        String message = userService.addUser(txt_FirstName.getText(), txt_LastName.getText(), txt_Email.getText(), txt_Password.getText());

        lbl_UserCreated.opacityProperty().setValue(1);
        lbl_UserCreated.setText(message);
        lbl_UserCreated.setFont(Font.font(15));

        if
        (message.startsWith("User Created Successfully:")) {
            lbl_UserCreated.setTextFill(Color.web("#1a832c"));
            txt_FirstName.clear();
            txt_LastName.clear();
            txt_Email.clear();
            txt_Password.clear();
        } else {
            lbl_UserCreated.setTextFill(Color.web("#da2525"));
        }

    }
}
