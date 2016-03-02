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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

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
    private TextField courseIdText;
    @FXML
    private TextField questionIdText;
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

    public ObservableList<Question> questionList;

    // Adds a new question to the list
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        // Checks if all textFields are not empty
        if (courseIdText.getText() != null && !courseIdText.getText().trim().isEmpty()
                && questionIdText.getText() != null && !questionIdText.getText().trim().isEmpty()
                && questionText.getText() != null && !questionText.getText().trim().isEmpty()
                && answerText.getText() != null && !answerText.getText().trim().isEmpty()
                && wrong1Text.getText() != null && !wrong1Text.getText().trim().isEmpty()
                && wrong2Text.getText() != null && !wrong2Text.getText().trim().isEmpty()
                && wrong3Text.getText() != null && !wrong3Text.getText().trim().isEmpty()) {

            int c = Integer.parseInt(courseIdText.getText());
            int q = Integer.parseInt(questionIdText.getText());

            question = new Question(c, q,
                    questionText.getText(),
                    answerText.getText(),
                    wrong1Text.getText(),
                    wrong2Text.getText(),
                    wrong3Text.getText());

            questionList.add(question);

            courseIdText.clear();
            questionIdText.clear();
            answerText.clear();
            questionText.clear();
            wrong1Text.clear();
            wrong2Text.clear();
            wrong3Text.clear();

        } else {
            label.setText("Please enter valid info");
        }
    }

    // Deletes a question when selected from the tableView
    @FXML
    private void deleteQuestion(ActionEvent event) throws IOException {
        Question selected = (Question) tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            questionList.remove(selected);
        }

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
        Parent root = FXMLLoader.load(getClass().getResource("SceneShow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addQuestionsMenuButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SceneTwo.fxml"));
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
        // list for all books
        questionList = FXCollections.observableArrayList();
        courseIdColumn.setCellValueFactory(new PropertyValueFactory<Question, Integer>("courseId"));
        questionIdColumn.setCellValueFactory(new PropertyValueFactory<Question, Integer>("questionId"));
        questionColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
        answerColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("answer"));
        wrong1Column.setCellValueFactory(new PropertyValueFactory<Question, String>("wrong1"));
        wrong3Column.setCellValueFactory(new PropertyValueFactory<Question, String>("wrong2"));
        wrong3Column.setCellValueFactory(new PropertyValueFactory<Question, String>("wrong3"));

        courseIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        questionIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        questionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        answerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        wrong1Column.setCellFactory(TextFieldTableCell.forTableColumn());
        wrong2Column.setCellFactory(TextFieldTableCell.forTableColumn());
        wrong3Column.setCellFactory(TextFieldTableCell.forTableColumn());

        courseIdColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, Integer>>() {
            public void handle(TableColumn.CellEditEvent<Question, Integer> t) {
                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCourseId(t.getNewValue());
            }
        });
        questionIdColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, Integer>>() {
            public void handle(TableColumn.CellEditEvent<Question, Integer> t) {
                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId(t.getNewValue());
            }
        });
        questionColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, String>>() {
            public void handle(TableColumn.CellEditEvent<Question, String> t) {
                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setQuestion(t.getNewValue());
            }
        });

        answerColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, String>>() {
            public void handle(TableColumn.CellEditEvent<Question, String> t) {
                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAnswer(t.getNewValue());
            }
        });

        wrong1Column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, String>>() {
            public void handle(TableColumn.CellEditEvent<Question, String> t) {
                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setWrong1(t.getNewValue());
            }
        });

        wrong2Column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, String>>() {
            public void handle(TableColumn.CellEditEvent<Question, String> t) {
                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setWrong2(t.getNewValue());
            }
        });

        wrong2Column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Question, String>>() {
            public void handle(TableColumn.CellEditEvent<Question, String> t) {
                ((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setWrong3(t.getNewValue());
            }
        });

        tableView.setItems(questionList);

    }

}
