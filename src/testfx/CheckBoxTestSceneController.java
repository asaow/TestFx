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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import static testfx.Question.CHECKBOX_TYPE;

/**
 * FXML Controller class
 *
 * @author Grupp 2
 */
public class CheckBoxTestSceneController implements Initializable {

    @FXML
    private Button addQuestionsMenuButton;
    @FXML
    private Button showTableMenuButton;
    @FXML
    private Button homeMenuButton;
    @FXML
    private Button backMenuButton;
    @FXML
    private Button startTestMenuButton;
        
    @FXML
    private Button nextButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label label;
    @FXML
    private Label qLeft;
    @FXML
    private Label total;
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

    private List<Question> checkboxList;
    private List<Answer> answerList;
    private Question question;
    private int index;
    int count;
    private int points = 0;

    @FXML
    public void nextQuestionAction(ActionEvent event) {
        Boolean ok1 = false, ok2 = false, ok3 = false, ok4 = false, ok5 = false;

        if (checkbox1.isSelected() == answerList.get(0).getCorrect()) {
            ok1 = true;
        }
        if (checkbox2.isSelected() == answerList.get(1).getCorrect()) {
            ok2 = true;

        }
        if (checkbox3.isSelected() == answerList.get(2).getCorrect()) {
            ok3 = true;

        }
        if (checkbox4.isSelected() == answerList.get(3).getCorrect()) {
            ok4 = true;

        }
        if (checkbox5.isSelected() == answerList.get(4).getCorrect()) {
            ok5 = true;

        }
        if (ok1 && ok2 && ok3 && ok4 && ok5) {
            points++;
        }

        index++;
        if (index < checkboxList.size()) {
            qLeft.setText((index + 1) + "");

            question = checkboxList.get(index);
            label.setText(question.getQuestion());

            answerList = new ArrayList();
            for (Answer a : question.getAnswers()) {
                answerList.add(a);
            }

            checkbox1.setText(answerList.get(0).getAnswer());
            checkbox2.setText(answerList.get(1).getAnswer());
            checkbox3.setText(answerList.get(2).getAnswer());
            checkbox4.setText(answerList.get(3).getAnswer());
            checkbox5.setText(answerList.get(4).getAnswer());

            checkbox1.setSelected(false);
            checkbox2.setSelected(false);
            checkbox3.setSelected(false);
            checkbox4.setSelected(false);
            checkbox5.setSelected(false);

        } //visa resultat och dölj knappar mm 
        else {
            label.setText("Du hade " + points + " rätt av " + checkboxList.size() + " frågor!");
            nextButton.setVisible(false);
            cancelButton.setVisible(false);
            checkbox1.setVisible(false);
            checkbox2.setVisible(false);
            checkbox3.setVisible(false);
            checkbox4.setVisible(false);
            checkbox5.setVisible(false);
            qLeft.setVisible(false);
            total.setVisible(false);

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
    public void startTestMenuButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartTestScene.fxml"));
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
    public void showTableMenuButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Table.fxml"));
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
    public void initialize(URL url, ResourceBundle rb
    ) {
        checkboxList = new ArrayList();

        GenericType<List<Question>> gt = new GenericType<List<Question>>() {
        };
        List<Question> c;
        c = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions/")
                .request(MediaType.APPLICATION_JSON)
                .get(gt);
        System.out.println(c);

        for (Question q : c) {
            q.getQuestion();
            q.getAnswers();
            if (q.getType().equals(CHECKBOX_TYPE)) {
                checkboxList.add(q);
            }
        }
        question = checkboxList.get(0);
        label.setText(question.getQuestion());

        answerList = new ArrayList();
        for (Answer a : question.getAnswers()) {
            answerList.add(a);
        }
        checkbox1.setText(answerList.get(0).getAnswer());
        checkbox2.setText(answerList.get(1).getAnswer());
        checkbox3.setText(answerList.get(2).getAnswer());
        checkbox4.setText(answerList.get(3).getAnswer());
        checkbox5.setText(answerList.get(4).getAnswer());

        index = 0;
        count = 0;
        total.setText("/ " + checkboxList.size());
        qLeft.setText("1");
    }

}

