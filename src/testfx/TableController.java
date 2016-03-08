/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
    private Button createTestButton;
    @FXML
    private TextField courseIdText;
    @FXML
    private TextField questionText;
    @FXML
    private TextField answerText;
    @FXML
    private TextField wrong1Text;
    @FXML
    private TextField wrong2Text;
    @FXML
    private TextField wrong3Text;
    @FXML
    private Button addBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Label label;
    @FXML
    private TableView<Question> tableView;
    @FXML
    private TableColumn<Question, Integer> courseIdColumn;
    @FXML
    private TableColumn<Question, Integer> questionIdColumn;
    @FXML
    private TableColumn<Question, String> questionColumn;
    @FXML
    private TableColumn<Question, String> answerColumn;
    @FXML
    private TableColumn<Question, String> wrong1Column;
    @FXML
    private TableColumn<Question, String> wrong2Column;
    @FXML
    private TableColumn<Question, String> wrong3Column;
    @FXML
    private TableColumn<Question, Boolean> checkColumn;
    @FXML
    private TextField numberText;

    public ObservableList<Question> questionList;

    // Adds a new question
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        question = new Question();
        question.setCourseId(Integer.parseInt(courseIdText.getText()));
        question.setQuestion(questionText.getText());
        //question.setAnswer(answerText.getText());
        //question.setWrong1(wrong1Text.getText());
        // question.setWrong2(wrong2Text.getText());
        //question.setWrong3(wrong3Text.getText());

        //questionList.add(question);
        courseIdText.clear();
        answerText.clear();
        questionText.clear();
        wrong1Text.clear();
        wrong2Text.clear();
        wrong3Text.clear();

        Answer answer = new Answer();
        answer.setAnswer("svar hehe");
        answer.setCorrect(Boolean.TRUE);
        // answer.setQuestion(question);
        question.getAnswer().add(answer);
        Question q;
        q = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions")
                // .path(courseIdText.getText()+"questions")
                .request()
                .post(Entity.entity(question, MediaType.APPLICATION_JSON), Question.class);
        System.out.println(q.getQuestion());

    }

    // Deletes a question when selected from the tableView
    @FXML
    private void deleteQuestion(ActionEvent event) throws IOException {
//        Question selected = (Question) tableView.getSelectionModel().getSelectedItem();
//        questionList.remove(selected);
//
//        Response r = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions")
//                .path(String.valueOf(selected.getId()))
//                .request(MediaType.APPLICATION_JSON)
//                .delete();
//        System.out.println("deleted " + r.getStatus());

        Question c;
        c = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions/1")
                .request(MediaType.APPLICATION_JSON)
                .get(Question.class);
    }

    //skickar numret som användaren matat in till nästa scen
    @FXML
    public void createButtonAction(ActionEvent event) throws IOException {
        int nr = Integer.parseInt(numberText.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SceneTest.fxml"));
        fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> controllerClass) {
                if (controllerClass == SceneTestController.class) {
                    SceneTestController controller = new SceneTestController();
                    controller.setIndex(nr);
                    return controller;
                } else {
                    try {
                        return controllerClass.newInstance();
                    } catch (Exception exc) {
                        throw new RuntimeException(exc); // just bail
                    }
                }
            }
        });
        Parent root1 = fxmlLoader.load();
        Scene scene = new Scene(root1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

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
    public void showQuestionsMenuButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CheckBoxScene.fxml"));
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

//        String c;
//        c = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions")
//                .request(MediaType.APPLICATION_JSON)
//                .get(String.class);
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
//        questionList = FXCollections.observableArrayList();
//
//        for (int i = 0; i < array.size(); i++) {
//            JSONObject row = (JSONObject) array.get(i);
//            String que = (String) (row.get("question"));
//            String answer = (String) (row.get("answer"));
//            String w1 = (String) (row.get("wrong1"));
//            String w2 = (String) (row.get("wrong2"));
//            String w3 = (String) (row.get("wrong3"));
//            Number course = (Number) row.get("courseId");
//
//            Question ball = new Question();
//            ball.setQuestion(que);
//            ball.setAnswer(answer);
//            ball.setWrong1(w1);
//            ball.setWrong2(w2);
//            ball.setWrong3(w3);
//            ball.setCourseId(course.intValue());
//            questionList.add(ball);
    }

//        Course ccc = new Course(5, "helo", 9);
//        Course course;
//        course = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses")
//                .request()
//                .post(Entity.entity( ccc, MediaType.APPLICATION_JSON) , Course.class);
//        System.out.println(course.getName());
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
//        //Checkbox integer
//    checkColumn.setCellFactory(CheckBoxTableCell.forTableColumn(new Callback<Integer, ObservableValue<Boolean>>() {
//            @Override
//            public ObservableValue<Boolean> call(Integer item) {
//       BooleanProperty observable = new SimpleBooleanProperty();
//                observable.addListener((obs, wasSelected, isNowSelected) -> 
//                    System.out.println("Check box for "+item+" changed from "+wasSelected+" to "+isNowSelected)
//                );
//                return observable ;
//                }
//        }));
//
//        tableView.setItems(questionList);
//
//    }
}
