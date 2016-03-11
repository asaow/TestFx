/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import static testfx.Question.RADIO_TYPE;

/**
 * FXML Controller class
 *
 * @author Loki
 */
//Controller till RadiobuttonScene 
public class SceneTwoController implements Initializable {

    @FXML
    private TextField answerText4;
    @FXML
    private TextField questionText;
    @FXML
    private TextField answerText1;
    @FXML
    private TextField answerText2;
    @FXML
    private TextField answerText3;
    @FXML
    private Button showTableMenuButton;
    @FXML
    private Button showTestMenuButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button checkBoxButton;
    @FXML
    private Label label;
    @FXML
    private Button addButton;
    @FXML
    private Button menuAddBtn;
    @FXML
    private Button createTestMenuButton;
    @FXML
    private Button homeMenuButton;
    @FXML
    private Button showQuestionsMenuButton;
    @FXML
    private RadioButton radiobutton1;
    @FXML
    private RadioButton radiobutton2;
    @FXML
    private RadioButton radiobutton3;
    @FXML
    private RadioButton radiobutton4;

    Question question;

    @FXML
    public void addQuestionButtonAction(ActionEvent event) throws IOException {
        question = new Question();
        question.setType(RADIO_TYPE);
        question.setQuestion(questionText.getText());

        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        Answer answer3 = new Answer();
        Answer answer4 = new Answer();

        answer1.setAnswer(answerText1.getText());
        answer2.setAnswer(answerText2.getText());
        answer3.setAnswer(answerText3.getText());
        answer4.setAnswer(answerText4.getText());

        if (radiobutton1.isSelected()) {
            answer1.setCorrect(Boolean.TRUE);
        } else {
            answer1.setCorrect(Boolean.FALSE);
        }

        if (radiobutton2.isSelected()) {
            answer2.setCorrect(Boolean.TRUE);
        } else {
            answer2.setCorrect(Boolean.FALSE);
        }

        if (radiobutton3.isSelected()) {
            answer3.setCorrect(Boolean.TRUE);
        } else {
            answer3.setCorrect(Boolean.FALSE);
        }

        if (radiobutton4.isSelected()) {
            answer4.setCorrect(Boolean.TRUE);
        } else {
            answer4.setCorrect(Boolean.FALSE);
        }

        question.getAnswers().add(answer1);
        question.getAnswers().add(answer2);
        question.getAnswers().add(answer3);
        question.getAnswers().add(answer4);

        Question q;
        q = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions")
                // .path(courseIdText.getText()+"questions")
                .request()
                .post(Entity.entity(question, MediaType.APPLICATION_JSON), Question.class);
        System.out.println(q.getQuestion());

        questionText.clear();
        answerText1.clear();
        answerText2.clear();
        answerText3.clear();
        answerText4.clear();
        
        radiobutton1.setSelected(true);
        radiobutton2.setSelected(false);
        radiobutton3.setSelected(false);
        radiobutton4.setSelected(false);

    }

    @FXML
    public void cancelButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void createTestMenuButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartTestScene.fxml"));
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
    public void showCheckBoxButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CheckBoxScene.fxml"));
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
        ToggleGroup radioGroup = new ToggleGroup();
        radiobutton1.setToggleGroup(radioGroup);
        radiobutton2.setToggleGroup(radioGroup);
        radiobutton3.setToggleGroup(radioGroup);
        radiobutton4.setToggleGroup(radioGroup);

        radiobutton1.setSelected(true);
    }

}
