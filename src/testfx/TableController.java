/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
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
import javafx.scene.control.TextField;
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
 * @author Loki
 */
public class TableController implements Initializable {

    Question question;
    @FXML
    private Button addQuestionsMenuButton;
    @FXML
    private Button showQuestionsMenuButton;
    @FXML
    private Button showTableMenuButton;
    @FXML
    private Button showTestMenuButton;
    @FXML
    private Button homeMenuButton;
    @FXML
    private Button createTestMenuButton;
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
    private void deleteQuestion(ActionEvent event) throws IOException {
        Question selected = (Question) tableView.getSelectionModel().getSelectedItem();
        questionList.remove(selected);

        Response r = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions")
                .path(String.valueOf(selected.getId()))
                .request(MediaType.APPLICATION_JSON)
                .delete();
        System.out.println("deleted " + r.getStatus());
    }

    @FXML
    public void showTestMenuButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SceneTest.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void createTestMenuButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ListScene.fxml"));
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
        
        questionIdColumn.setCellValueFactory(new PropertyValueFactory<Question, Integer>("questionId"));
        questionColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("type"));
        
        questionIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        questionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
        //        questionIdColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, Integer>>() {
//            public void handle(TableColumn.CellEditEvent<Question, Integer> t) {
//                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId(t.getNewValue());
//            }
//        });
//        questionColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, String>>() {
//            public void handle(TableColumn.CellEditEvent<Question, String> t) {
//                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setQuestion(t.getNewValue());
//            }
//        });
//
//        answerColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, String>>() {
//            public void handle(TableColumn.CellEditEvent<Question, String> t) {
//                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAnswer(t.getNewValue());
//            }
//        });

        
        tableView.setItems(questionList);
        
        

//        String c;
//        c = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions")
//                .request(MediaType.APPLICATION_JSON)
//                .get(String.class);
//        System.out.println(c);
//        JSONArray array = null;
//
//        JSONParser parser = new JSONParser();
//        try {
//            Object object = parser.parse(c);
//            array = (JSONArray) object;
//
//            JSONObject obj2 = (JSONObject) array.get(1);
//
//            System.out.println(array.get(1));
//            System.out.println(obj2);
//            System.out.println("array: " + array);
//
//            List<Question> list = new ArrayList();
//            list.addAll(array);
//            System.out.println("Size " + list.size());
//            System.out.println("tre " + list.get(3));
//
//        } catch (Exception e) {
//
//        }
        // list for all questions

    }


//        courseIdColumn.setCellValueFactory(new PropertyValueFactory<Question, Integer>("courseId"));
//        questionIdColumn.setCellValueFactory(new PropertyValueFactory<Question, Integer>("questionId"));
//        questionColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
//        answerColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("answer"));
//        wrong1Column.setCellValueFactory(new PropertyValueFactory<Question, String>("wrong1"));
//        wrong2Column.setCellValueFactory(new PropertyValueFactory<Question, String>("wrong2"));
//        wrong3Column.setCellValueFactory(new PropertyValueFactory<Question, String>("wrong3"));
//
//        courseIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        questionIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        questionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        answerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        wrong1Column.setCellFactory(TextFieldTableCell.forTableColumn());
//        wrong2Column.setCellFactory(TextFieldTableCell.forTableColumn());
//        wrong3Column.setCellFactory(TextFieldTableCell.forTableColumn());
//
//        courseIdColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, Integer>>() {
//            public void handle(TableColumn.CellEditEvent<Question, Integer> t) {
//                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCourseId(t.getNewValue());
//            }
//        });
//        questionIdColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, Integer>>() {
//            public void handle(TableColumn.CellEditEvent<Question, Integer> t) {
//                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId(t.getNewValue());
//            }
//        });
//        questionColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, String>>() {
//            public void handle(TableColumn.CellEditEvent<Question, String> t) {
//                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setQuestion(t.getNewValue());
//            }
//        });
//
//        answerColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, String>>() {
//            public void handle(TableColumn.CellEditEvent<Question, String> t) {
//                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAnswer(t.getNewValue());
//            }
//        });
//
//        wrong1Column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, String>>() {
//            public void handle(TableColumn.CellEditEvent<Question, String> t) {
//                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setWrong1(t.getNewValue());
//            }
//        });
//
//        wrong2Column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, String>>() {
//            public void handle(TableColumn.CellEditEvent<Question, String> t) {
//                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setWrong2(t.getNewValue());
//            }
//        });
//
//        wrong3Column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, String>>() {
//            public void handle(TableColumn.CellEditEvent<Question, String> t) {
//                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setWrong3(t.getNewValue());
//            }
//        });
//        

//
//
//    }
}
