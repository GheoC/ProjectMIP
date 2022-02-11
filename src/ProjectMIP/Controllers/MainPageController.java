package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {
    @FXML
    private Button lbl_AddBook;

    @FXML
    private Button lbl_LendBook;

    @FXML
    private Button lbl_ViewBooks;

    @FXML
    private Button lbl_ViewUsers;

    @FXML
    private Label lbl_emailLoggedIn;

    @FXML
    private Pane mainPane;


    @FXML
    void openAddBook(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/addBook.fxml"));
        Parent root = loader.load();

        AddBookController addBookController = loader.getController();
        addBookController.initUsername(lbl_emailLoggedIn.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void openLendBook(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/lendBook.fxml"));
        Parent root = loader.load();

        LendBookController lendBookController = loader.getController();
        lendBookController.initUsername(lbl_emailLoggedIn.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void openReceiveBook(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/receiveBook.fxml"));
        Parent root = loader.load();

        ReceiveBookController receiveBookController = loader.getController();
        receiveBookController.initUsername(lbl_emailLoggedIn.getText());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void openViewBooks(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/viewBooks.fxml"));
        Parent root = loader.load();

        ViewBooksController viewBooksController = loader.getController();
        viewBooksController.initUsername(lbl_emailLoggedIn.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void openViewUsers(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/viewUsers.fxml"));
        Parent root = loader.load();

        ViewUsersController viewUsersController = loader.getController();
        viewUsersController.initUsername(lbl_emailLoggedIn.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void initMainPage(String email)
    {
        lbl_emailLoggedIn.setText(email);
    }


    public void logout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
