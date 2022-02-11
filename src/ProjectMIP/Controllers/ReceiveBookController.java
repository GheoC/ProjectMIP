package Controllers;

import Model.BookOrder;
import Model.User;
import Service.BookOrderService.BookOrderService;
import Service.BookService.BookService;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReceiveBookController
{
    public TableView<BookOrder> view_Table;
    @FXML
    private TextField txt_UserEmail;
    @FXML
    private Label txt_AlertMessage;
    @FXML
    private TableColumn<BookOrder, Integer> view_BookOrderId;
    @FXML
    private TableColumn<BookOrder, String> view_UserEmail;
    @FXML
    private TableColumn<BookOrder, String> view_BookName;
    @FXML
    private TableColumn<BookOrder, LocalDate > view_LendDate;
    @FXML
    private TableColumn<BookOrder, LocalDate> view_Deadline;
    @FXML
    private Label lbl_emailLoggedIn;
    @FXML
    private Label lbl_AlertMessage;

    private final BookOrderService bookOrderService = new BookOrderService();
    private final UserService userService = new UserService();
    private final BookService bookService = new BookService();

    public void receiveBooksBack(ActionEvent actionEvent)
    {
        User user = userService.findUserByEmail(txt_UserEmail.getText());
        System.out.println(user.getId());
        List<BookOrder> bookOrderList= bookOrderService.findBookOrder(user.getId());

        List<Integer> bookIds = new ArrayList<>();
        for (BookOrder bookOrder:bookOrderList)
        {
            bookIds.add(bookOrder.getBook().getId());
        }

        if (bookOrderList.size()>0)
        {
            bookOrderService.receiveBooksFromUser(bookOrderList);
            bookService.increaseItemsForBooks(bookIds);

        }
        txt_UserEmail.clear();
        view_Table.getItems().clear();

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


    public void findBookOrder(ActionEvent event)
    {
        User user = userService.findUserByEmail(txt_UserEmail.getText());
        System.out.println(user.getId());
        List<BookOrder> bookOrderList= bookOrderService.findBookOrder(user.getId());

        for (BookOrder bookOrder:bookOrderList)
        {
            if (bookOrder.getDeadline().isBefore(LocalDate.now()))
            {
                lbl_AlertMessage.setText("User extended lending period. Apply penalty");
                lbl_AlertMessage.setOpacity(1);
                lbl_AlertMessage.setTextFill(Color.web("#da2525"));
            }
        }

        if (bookOrderList.size()<1)
        {
            txt_AlertMessage.setText("No orders from that user");
            txt_AlertMessage.setOpacity(1);
            txt_AlertMessage.setTextFill(Color.web("#da2525"));
        }
        ObservableList<BookOrder> bookOrderObservableList = FXCollections.observableArrayList();
        bookOrderObservableList.addAll(bookOrderList);

        view_BookOrderId.setCellValueFactory(new PropertyValueFactory<BookOrder,Integer>("id"));
        view_UserEmail.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getUser().getEmail()));
        view_BookName.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getBook().getName()));
        view_LendDate.setCellValueFactory(new PropertyValueFactory<BookOrder, LocalDate>("lendingDate"));
        view_Deadline.setCellValueFactory((new PropertyValueFactory<BookOrder,LocalDate>("deadline")));
        view_Table.setItems(bookOrderObservableList);

    }
}
