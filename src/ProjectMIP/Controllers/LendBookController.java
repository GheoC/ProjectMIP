package Controllers;

import Model.Book;
import Model.User;
import Service.BookOrderService.BookOrderService;
import Service.BookService.BookService;
import Service.UserService.UserService;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class LendBookController implements Initializable
{
    @FXML
    private TableColumn view_Id;
    @FXML
    private TableColumn view_Name;
    @FXML
    private TableColumn view_Author;
    @FXML
    private TableColumn view_Select;
    @FXML
    private TableView view_Table;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_BookId;
    @FXML
    private TextField txt_Days;
    @FXML
    private Label lbl_emailLoggedIn;

    private final BookService bookService = new BookService();
    private final UserService userService = new UserService();
    private final BookOrderService bookOrderService = new BookOrderService();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<Book> books = FXCollections.observableArrayList();

        books.addAll(bookService.getAllBooks());

        view_Id.setCellValueFactory(new PropertyValueFactory<Book,Integer>("id"));
        view_Name.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        view_Author.setCellValueFactory((new PropertyValueFactory<Book, String>("author")));
        view_Select.setCellValueFactory(new PropertyValueFactory<Book, String>("selected"));
        view_Table.setItems(books);

    }

    public void lendBook(ActionEvent actionEvent)
    {
       ObservableList<Book> books = view_Table.getItems();
       Set<Book> selectedBooks = new HashSet<>();
       for (Book book:books)
       {
           if (book.getSelected().isSelected())
           {
               selectedBooks.add(book);
           }
       }
        System.out.println(selectedBooks);

       User user = userService.findUserByEmail(txt_email.getText());

       bookOrderService.lendBooksToUser(user, selectedBooks, Integer.valueOf(txt_Days.getText()));
       bookService.decreaseItemsForBooks(selectedBooks);
        ObservableList<Book> refresedBooks = FXCollections.observableArrayList();
        refresedBooks.addAll(bookService.getAllBooks());
        view_Table.setItems(refresedBooks);
        txt_email.clear();
        txt_Days.clear();
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
