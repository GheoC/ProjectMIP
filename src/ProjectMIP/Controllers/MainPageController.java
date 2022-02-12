package Controllers;

import Model.BookOrder;
import Service.BookOrderService.BookOrderService;
import Service.UserService.UserService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class MainPageController {


    @FXML
    private TableColumn<BookOrder, LocalDate> view_Deadline;
    @FXML
    private TableView<BookOrder> view_Table;
    @FXML
    private TableColumn<BookOrder, LocalDate> view_LendDate;
    @FXML
    private TableColumn<BookOrder,String> view_BookName;
    @FXML
    private TableColumn<BookOrder, Integer> view_BookOrderiD;
    @FXML
    private Label lbl_NotificariAlert;
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

    public void checkNotifications()
    {
        UserService userService = new UserService();


        if (userService.getRoleOfUser(lbl_emailLoggedIn.getText()).equals("cititor"))
        {
            BookOrderService bookOrderService = new BookOrderService();
            List<BookOrder> bookOrderList = bookOrderService
                    .findBookOrderedByUsed(userService.findUserByEmail(lbl_emailLoggedIn.getText()).getId());

            ObservableList<BookOrder> bookOrderObservableList = FXCollections.observableArrayList();

            Boolean checkDeadlineOver=false;
            for(BookOrder bookOrder:bookOrderList)
            {
             if (bookOrder.getDeadline().isBefore(LocalDate.now()))
             {
                 checkDeadlineOver=true;
                 bookOrderObservableList.add(bookOrder);
             }
            }
            if (checkDeadlineOver)
            {
                lbl_NotificariAlert.setText("You kept some books over the deadline. Please return them asap");
                lbl_NotificariAlert.setOpacity(1);
                lbl_NotificariAlert.setTextFill(Color.web("#da2525"));
                view_BookOrderiD.setCellValueFactory(new PropertyValueFactory<BookOrder, Integer>("id"));
                view_BookName.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getBook().getName()));
                view_LendDate.setCellValueFactory(new PropertyValueFactory<BookOrder,LocalDate>("lendingDate"));
                view_Deadline.setCellValueFactory(new PropertyValueFactory<BookOrder,LocalDate>("deadline"));
                view_Table.setItems(bookOrderObservableList);
                view_Table.setVisible(true);
            }
        }

    }
}
