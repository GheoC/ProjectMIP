package Controllers;

import Service.UserService.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label lbl_ErrorMessage;
    @FXML
    private TextField txt_username;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button lbl_Login;
    @FXML
    private Button lbl_CreateUser;
    @FXML
    private AnchorPane content;

    private MainPageController mainPageController;
    private final UserService userService = new UserService();


    public void loginUser(ActionEvent actionEvent) throws IOException {
        if (userService.loginUser(txt_username.getText(), txt_password.getText())) {
            System.out.println("User " + txt_username.getText() + " logged in successfully");

            String roleOfUser = userService.getRoleOfUser(txt_username.getText());

            System.out.println(roleOfUser);
            FXMLLoader loader;
            if (roleOfUser.equals("bibliotecar"))
            {
                loader = new FXMLLoader(getClass().getResource("/FXML/MainPageBibliotecar.fxml"));
            }
            else
                {
                loader = new FXMLLoader(getClass().getResource("/FXML/MainPageCititor.fxml"));
            }

            Parent root = loader.load();
            MainPageController mainPageController = loader.getController();
            mainPageController.initMainPage(txt_username.getText());
            if (roleOfUser.equals("cititor"))
            {
                mainPageController.checkNotifications();
            }

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } else {

            lbl_ErrorMessage.setText("Something went wrong");
            lbl_ErrorMessage.setOpacity(1);
            lbl_ErrorMessage.setTextFill(Color.web("#da2525"));
            System.out.println("Something went wrong");
        }
    }

    //https://www.youtube.com/watch?v=wxhGKR3PQpo
    public void loadCreateUser(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CreateUser.fxml"));
        Parent root = loader.load();
        UserController userController = loader.getController();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
