/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;


/**
 * FXML Controller class
 *
 * @author Loki
 */
public class ListSceneController implements Initializable {

    @FXML
    private Button addQuestionsMenuButton;
    @FXML
    private Button showTableMenuButton;
    @FXML
    private Button homeMenuButton;
    @FXML
    private Button toLeftButton;
    @FXML
    private Button toRightButton;
    @FXML
    private Button createButton;
    
    @FXML
    private TableView<Question> table1;

    @FXML
    private TableView<Question> table2;
    @FXML
    private TableColumn<Question, String> col1;
    @FXML
    private TableColumn<Question, String> col2;

    ObservableList<Question> listLeft;
    ObservableList<Question> listRight;
    List<Question> newList;
    Question question;

    @FXML
    public void toLeftAction(ActionEvent event) {
        Question selectedItem = (Question) table2.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            listRight.remove(selectedItem);
            listLeft.add(selectedItem);
        }

    }

    @FXML
    public void toRightAction(ActionEvent event) {
        Question selectedItem = (Question) table1.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            listLeft.remove(selectedItem);
            listRight.add(selectedItem);
        }
    }
    
    @FXML
    public void createTestAction(ActionEvent event) {
            listLeft.addAll(listRight);
            listRight.clear();
 }

    @FXML
    public void addQuestionsMenuButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RadioButtonScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void homeMenuButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void showTableMenuButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Table.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newList = new ArrayList();
        listLeft = FXCollections.observableArrayList();
        listRight = FXCollections.observableArrayList();
        
        GenericType<List<Question>> gt = new GenericType<List<Question>>() {
        };
        List<Question> c;
        c = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions")
                .request(MediaType.APPLICATION_JSON)
                .get(gt);
        System.out.println(c);

        for (Question q : c) {
            q.getQuestion();
            System.out.println(q.getQuestion());
            listLeft.add(q);
        }

        col1.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
        col2.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
        
        col1.setCellFactory(TextFieldTableCell.forTableColumn());
        col2.setCellFactory(TextFieldTableCell.forTableColumn());

        col1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, String>>() {
            public void handle(TableColumn.CellEditEvent<Question, String> t) {
                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setQuestion(t.getNewValue());
            }
        });

        col2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, String>>() {
            public void handle(TableColumn.CellEditEvent<Question, String> t) {
                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setQuestion(t.getNewValue());
            }
        });
        
        table1.setItems(listLeft);
        table2.setItems(listRight);
    }
}
