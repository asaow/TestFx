/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 * FXML Controller class
 *
 * @author Loki
 */
public class CheckBoxSceneController implements Initializable {

    @FXML
    private TextField questionText;
    @FXML
    private TextField answerText4;
    @FXML
    private TextField answerText1;
    @FXML
    private TextField answerText2;
    @FXML
    private TextField answerText3;
    @FXML
    private TextField answerText5;
    @FXML
    private TextField answerText6;

    @FXML
    private CheckBox checkbox1;
    @FXML
    private CheckBox checkbox2;
    @FXML
    private CheckBox checkbox3;
    @FXML
    private CheckBox checkbox4;
    @FXML
    private CheckBox checkbox5;
    @FXML
    private CheckBox checkbox6;
    @FXML
    private Button addButton;
    Question question;

    @FXML
    public void addQuestionButtonAction(ActionEvent event) throws IOException {
        question = new Question();
        question.setQuestion(questionText.getText());

        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        Answer answer3 = new Answer();
        Answer answer4 = new Answer();
        Answer answer5 = new Answer();
        Answer answer6 = new Answer();

        answer1.setAnswer(answerText1.getText());
        answer2.setAnswer(answerText2.getText());
        answer3.setAnswer(answerText3.getText());
        answer4.setAnswer(answerText4.getText());
        answer5.setAnswer(answerText5.getText());
        answer6.setAnswer(answerText6.getText());

        if (checkbox1.isSelected()) {
            answer1.setCorrect(Boolean.TRUE);
        } else {
            answer1.setCorrect(Boolean.FALSE);
        }

        if (checkbox2.isSelected()) {
            answer2.setCorrect(Boolean.TRUE);
        } else {
            answer2.setCorrect(Boolean.FALSE);
        }

        if (checkbox3.isSelected()) {
            answer3.setCorrect(Boolean.TRUE);
        } else {
            answer3.setCorrect(Boolean.FALSE);
        }

        if (checkbox4.isSelected()) {
            answer4.setCorrect(Boolean.TRUE);
        } else {
            answer4.setCorrect(Boolean.FALSE);
        }

        if (checkbox5.isSelected()) {
            answer5.setCorrect(Boolean.TRUE);
        } else {
            answer5.setCorrect(Boolean.FALSE);
        }
        if (checkbox6.isSelected()) {
            answer6.setCorrect(Boolean.TRUE);
        } else {
            answer6.setCorrect(Boolean.FALSE);
        }
        
        question.getAnswer().add(answer1);
        question.getAnswer().add(answer2);
        question.getAnswer().add(answer3);
        question.getAnswer().add(answer4);
        question.getAnswer().add(answer5);
        question.getAnswer().add(answer6);


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
        answerText5.clear();
        answerText6.clear();
        
        checkbox1.setSelected(false);
        checkbox2.setSelected(false);
        checkbox3.setSelected(false);
        checkbox4.setSelected(false);
        checkbox5.setSelected(false);
        checkbox6.setSelected(false);


    }
        @FXML
        private Button radioButton;

        @FXML
        public void cancelButtonAction
        (ActionEvent event) throws IOException
        {
            Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }

        @FXML
        public void showTableMenuButtonAction
        (ActionEvent event) throws IOException
        {
            Parent root = FXMLLoader.load(getClass().getResource("Table.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }

        @FXML
        public void homeMenuButtonAction
        (ActionEvent event) throws IOException
        {
            Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }

        @FXML
        public void showRadioButtonAction
        (ActionEvent event) throws IOException
        {
            Parent root = FXMLLoader.load(getClass().getResource("RadioButtonScene.fxml"));
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
        public void initialize
        (URL url, ResourceBundle rb
        
        
    

        
        
    

) {
        // TODO
    }    
    
}
