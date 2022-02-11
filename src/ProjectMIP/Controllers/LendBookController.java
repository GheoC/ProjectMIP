package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LendBookController 
{
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_BookId;
    @FXML
    private TextField txt_Days;
    @FXML
    private Label lbl_emailLoggedIn;

    public void lendBook(ActionEvent actionEvent)
    {

    }

    public void returnMainPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MainPageBibliotecar.fxml"));
        Parent root = loader.load();

        MainPageController mainPageController = loader.getController();
        mainPageController.initMainPage(lbl_emailLoggedIn.getText());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void initUsername(String email)
    {
        lbl_emailLoggedIn.setText(email);
    }
}
