package Controllers;

import Model.Book;
import Service.BookService.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ViewBooksController implements Initializable
{
    @FXML
    private Label lbl_emailLoggedIn;
    @FXML
    private TableColumn<Book, Integer> view_Id;
    @FXML
    private TableColumn<Book, String> view_Name;
    @FXML
    private TableColumn<Book, String> view_Author;
    @FXML
    private TableColumn<Book, Integer> view_ItemsAvailable;
    @FXML
    private TableView<Book> view_Table;

    private final BookService bookService = new BookService();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<Book> books = FXCollections.observableArrayList();

        books.addAll(bookService.getAllBooks());

        //for using Collections.sort
        Collections.sort(books, Comparator.comparing(book -> book.getName()));

        view_Id.setCellValueFactory(new PropertyValueFactory<Book,Integer>("id"));
        view_Name.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        view_Author.setCellValueFactory((new PropertyValueFactory<Book, String>("author")));
        view_ItemsAvailable.setCellValueFactory(new PropertyValueFactory<Book, Integer>("itemsAvailable"));
        view_Table.setItems(books);
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
