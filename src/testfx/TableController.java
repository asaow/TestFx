/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * FXML Controller class
 *
 * @author Grupp 2
 */
public class TableController implements Initializable {

    Question question;
    @FXML
    private Button addQuestionsMenuButton;
    @FXML
    private Button showTableMenuButton;
    @FXML
    private Button startTestMenuButton;
    @FXML
    private Button homeMenuButton;
    @FXML
    private Button deleteBtn;
    @FXML
    private Label label;
    @FXML
    private TableView<Question> tableView;
    @FXML
    private TableColumn<Question, Integer> questionIdColumn;
    @FXML
    private TableColumn<Question, String> questionColumn;
    @FXML
    private TableColumn<Question, String> typeColumn;

    public ObservableList<Question> questionList;


    // Deletes a question when selected from the tableView
    @FXML
    public void deleteQuestion(ActionEvent event) throws IOException {
        Question selected = (Question) tableView.getSelectionModel().getSelectedItem();
        questionList.remove(selected);

        Response r = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions")
                .path(String.valueOf(selected.getId()))
                .request(MediaType.APPLICATION_JSON)
                .delete();
        System.out.println("deleted " + r.getStatus());
    }



    @FXML
    public void startTestMenuButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartTestScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questionList = FXCollections.observableArrayList();

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
            questionList.add(q);
        }
        
        questionIdColumn.setCellValueFactory(new PropertyValueFactory<Question, Integer>("id"));
        questionColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("type"));
        
        questionIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        questionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
        tableView.setItems(questionList);

    }

}
