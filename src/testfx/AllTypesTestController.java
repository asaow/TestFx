package testfx;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import static testfx.Question.CHECKBOX_TYPE;
import static testfx.Question.RADIO_TYPE;

/**
 * FXML Controller class
 *
 * @author asa
 */
public class AllTypesTestController implements Initializable {

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
    private TextArea textarea;
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
    private List<Question> c;
    private List<Answer> answerList;

    int index;
    int count;
    private Question question;
    int points;

    @FXML
    public void nextQuestionAction(ActionEvent event) {

        // kolla typ av fråga och rätta den med respektive metod
        if (c.get(index).getType().equals(RADIO_TYPE)) {
            radioButtonQuestionCheck();
        } else if (c.get(index).getType().equals(CHECKBOX_TYPE)) {
            checkBoxQuestionCheck();
        }

        // öka index, dvs nästa fråga
        index++;
        if (index < c.size()) {
            qLeft.setText((index + 1) + "");

            // Hämtar frågan  på plats index, och dess lista med svar
            getQuestionAndAnswers(index);

            if (c.get(index).getType().equals(RADIO_TYPE)) {
                showRadioButtonQuestion();
            } else if (c.get(index).getType().equals(CHECKBOX_TYPE)) {
                showCheckBoxQuestion();
            }

        } //beräkna resultat och byta text på 
        else {
            label.setText("Du hade " + points + " rätt av " + c.size() + " frågor!");
            nextBtn.setVisible(false);
            cancelBtn.setVisible(false);
            qLeft.setVisible(false);
            total.setVisible(false);
            setRadioButtonsVisible(false);
            setCheckBoxesVisible(false);
        }

    }

    // Hämtar frågan  på plats index, och dess lista med svar
    private void getQuestionAndAnswers(int index) {
        question = c.get(index);
        answerList = new ArrayList();
        for (Answer a : question.getAnswers()) {
            answerList.add(a);
        }

    }

    // Visar fråga och svar till radiobutton-frågor
    private void showRadioButtonQuestion() {
        label.setText(question.getQuestion());

        radioBtn1.setText(answerList.get(0).getAnswer());
        radioBtn2.setText(answerList.get(1).getAnswer());
        radioBtn3.setText(answerList.get(2).getAnswer());
        radioBtn4.setText(answerList.get(3).getAnswer());

        // Gör radiobuttons synliga och omarkerade
        setRadioButtonsVisible(true);
        radioBtn1.setSelected(false);
        radioBtn2.setSelected(false);
        radioBtn3.setSelected(false);
        radioBtn4.setSelected(false);

        // Gör checkboxes osynliga
        setCheckBoxesVisible(false);

    }

    // Visar fråga och svar till checkbox-frågor
    private void showCheckBoxQuestion() {
        label.setText(question.getQuestion());

        checkbox1.setText(answerList.get(0).getAnswer());
        checkbox2.setText(answerList.get(1).getAnswer());
        checkbox3.setText(answerList.get(2).getAnswer());
        checkbox4.setText(answerList.get(3).getAnswer());
        checkbox5.setText(answerList.get(4).getAnswer());

        // Gör checkboxes synliga och omarkerade
        setCheckBoxesVisible(true);
        checkbox1.setSelected(false);
        checkbox2.setSelected(false);
        checkbox3.setSelected(false);
        checkbox4.setSelected(false);
        checkbox5.setSelected(false);

        // Gör radiobuttons osynliga
        setRadioButtonsVisible(false);
    }

    // Kontrollerar svar från radiobuttons
    private void radioButtonQuestionCheck() {
        if (radioBtn1.isSelected() && answerList.get(0).getCorrect()) {
            points++;
        } else if (radioBtn2.isSelected() && answerList.get(1).getCorrect()) {
            points++;
        } else if (radioBtn3.isSelected() && answerList.get(2).getCorrect()) {
            points++;
        } else if (radioBtn4.isSelected() && answerList.get(3).getCorrect()) {
            points++;
        }
    }

    // Kontrollerar svar från checkboxes
    private void checkBoxQuestionCheck() {
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

    }

    // Kontrollerar synlighet för radiobuttons
    private void setRadioButtonsVisible(Boolean visible) {
        radioBtn1.setVisible(visible);
        radioBtn2.setVisible(visible);
        radioBtn3.setVisible(visible);
        radioBtn4.setVisible(visible);
    }

    // Kontrollerar synlighet för checkboxes
    private void setCheckBoxesVisible(Boolean visible) {
        checkbox1.setVisible(visible);
        checkbox2.setVisible(visible);
        checkbox3.setVisible(visible);
        checkbox4.setVisible(visible);
        checkbox5.setVisible(visible);
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
    public void initialize(URL url, ResourceBundle rb) {
        index = 0;
        count = 0;
        points = 0;

        ToggleGroup radioGroup = new ToggleGroup();
        radioBtn1.setToggleGroup(radioGroup);
        radioBtn2.setToggleGroup(radioGroup);
        radioBtn3.setToggleGroup(radioGroup);
        radioBtn4.setToggleGroup(radioGroup);

//        läs in lista med Question-object
        GenericType<List<Question>> gt = new GenericType<List<Question>>() {
        };

        c = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions")
                .request(MediaType.APPLICATION_JSON)
                .get(gt);

        // Hämtar första frågan (på plats 0), och dess lista med svar
        getQuestionAndAnswers(0);
        // Kolla typ av fråga
        if (c.get(0).getType().equals(RADIO_TYPE)) {
            showRadioButtonQuestion();
        } else if (c.get(0).getType().equals(CHECKBOX_TYPE)) {
            showCheckBoxQuestion();
        }
        // Visar nuvarande fråga / totalt antal frågor
        total.setText("/ " + c.size());
        qLeft.setText("1");

    }

}
