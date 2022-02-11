package Controllers;

import Service.BookService.BookService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class AddBookController
{
    @FXML
    private Label lbl_Message;
    @FXML
    private Label lbl_emailLoggedIn;
    @FXML
    private AnchorPane anch_AddBook;
    @FXML
    private Button btn_Cancel;
    @FXML
    private TextField txt_Author;
    @FXML
    private TextField txt_Name;
    @FXML
    private TextField txt_items;

    private final BookService bookService = new BookService();


    public void addBook(javafx.event.ActionEvent actionEvent)
    {
        bookService.addBook(txt_Name.getText(),txt_Author.getText(), Integer.valueOf(txt_items.getText()));

        lbl_Message.setText("Book "+txt_Name.getText()+ " by "+ txt_Author.getText()+ " was added in library; "+txt_items.getText() +" items");
        lbl_Message.setOpacity(1);
        lbl_Message.setTextFill(Color.web("#1a832c"));
        txt_Name.clear();
        txt_Author.clear();
        txt_items.clear();


    }

    public void returnMainMenu(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MainPageBibliotecar.fxml"));
        Parent root = loader.load();

        MainPageController mainPageController = loader.getController();
        mainPageController.initMainPage(lbl_emailLoggedIn.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void initUsername(String email)
    {
        lbl_emailLoggedIn.setText(email);
    }

}
