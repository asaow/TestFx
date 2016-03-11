/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import static testfx.Question.RADIO_TYPE;

/**
 * FXML Controller class
 *
 * @author asa
 */
public class SceneTestController implements Initializable {

    @FXML
    private Button addQuestionsButton;
    @FXML
    private Button addQuestionsMenuButton;
    @FXML
    private Button showTableMenuButton;
    @FXML
    private Button backMenuButton;
    @FXML
    private Label total;
    @FXML
    private Label qLeft;
    @FXML
    private Button nextBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label label;
    @FXML
    private RadioButton radioBtn1;
    @FXML
    private RadioButton radioBtn2;
    @FXML
    private RadioButton radioBtn3;
    @FXML
    private RadioButton radioBtn4;

    private List<Question> radioList;
    private List<Answer> answerList;

    int index;
    int count;
    int min = 1;
    private Question question;
    int points = 0;

    @FXML
    public void nextQuestionAction(ActionEvent event) {
        if (radioBtn1.isSelected() && answerList.get(0).getCorrect()) {
            points++;
        } else if (radioBtn2.isSelected() && answerList.get(1).getCorrect()) {
            points++;
        } else if (radioBtn3.isSelected() && answerList.get(2).getCorrect()) {
            points++;
        } else if (radioBtn4.isSelected() && answerList.get(3).getCorrect()) {
            points++;
        }

        index++;
        if (index < radioList.size()) {
            qLeft.setText((index + 1) + "");

            question = radioList.get(index);
            label.setText(question.getQuestion());

            answerList = new ArrayList();
            for (Answer a : question.getAnswers()) {
                answerList.add(a);
            }

            radioBtn1.setText(answerList.get(0).getAnswer());
            radioBtn2.setText(answerList.get(1).getAnswer());
            radioBtn3.setText(answerList.get(2).getAnswer());
            radioBtn4.setText(answerList.get(3).getAnswer());

            radioBtn1.setSelected(false);
            radioBtn2.setSelected(false);
            radioBtn3.setSelected(false);
            radioBtn4.setSelected(false);

        } //beräkna resultat och byta text på label
        else {
            label.setText("Du hade " + points + " rätt av " + radioList.size() + " frågor!");
            nextBtn.setVisible(false);
            cancelBtn.setVisible(false);
            radioBtn1.setVisible(false);
            radioBtn2.setVisible(false);
            radioBtn3.setVisible(false);
            radioBtn4.setVisible(false);
        }

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
    public void showTableMenuButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Table.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void backToStartTestButtonAction(ActionEvent event) throws IOException {
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup radioGroup = new ToggleGroup();
        radioBtn1.setToggleGroup(radioGroup);
        radioBtn2.setToggleGroup(radioGroup);
        radioBtn3.setToggleGroup(radioGroup);
        radioBtn4.setToggleGroup(radioGroup);

        radioList = new ArrayList();
        GenericType<List<Question>> gt = new GenericType<List<Question>>() {
        };
        List<Question> c;
        c = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions")
                .request(MediaType.APPLICATION_JSON)
                .get(gt);
        System.out.println(c);

        for (Question q : c) {
            q.getQuestion();
            q.getAnswers();
            if (q.getType().equals(RADIO_TYPE)) {
                radioList.add(q);

            }
        }
        System.out.println(radioList.size());
        for (Question q : radioList) {
            System.out.println("frågetyp: " + q.getType());

        }
        question = radioList.get(0);
        label.setText(question.getQuestion());

        answerList = new ArrayList();
        for (Answer a : question.getAnswers()) {
            answerList.add(a);
        }

        radioBtn1.setText(answerList.get(0).getAnswer());
        radioBtn2.setText(answerList.get(1).getAnswer());
        radioBtn3.setText(answerList.get(2).getAnswer());
        radioBtn4.setText(answerList.get(3).getAnswer());
        index = 0;
        count = 0;
        total.setText("/ " + radioList.size());
        qLeft.setText("1");

    }
}
