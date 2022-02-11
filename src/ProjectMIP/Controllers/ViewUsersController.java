package Controllers;

import Model.User;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewUsersController implements Initializable
{
    @FXML
    private Label lbl_emailLoggedIn;
    @FXML
    private TableView<User> view_Table;
    @FXML
    private TableColumn<User, Integer> view_id;
    @FXML
    private TableColumn<User, String> view_FirstName;
    @FXML
    private TableColumn<User, String> view_LastName;
    @FXML
    private TableColumn<User, String> view_Email;
    @FXML
    private TableColumn<User, String> view_Role;



    private final UserService userService = new UserService();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<User> users = FXCollections.observableArrayList();

        users.addAll(userService.getAllUsers());
        System.out.println(users);

        view_id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        view_FirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        view_LastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        view_Email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        view_Role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));

        view_Table.setItems(users);
    }


    public void returnMainPage(ActionEvent event) throws IOException {

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
