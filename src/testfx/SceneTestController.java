/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import static javax.ws.rs.client.Entity.json;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

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
    private Button showQuestionsMenuButton;
    @FXML
    private Button homeMenuButton;
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
    
    public int index;
    int count = 0;
    int min = 1;
    private ObservableList<Question> questionList;
    private Question question;
    int points = 0;
    
    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
    
    //metod där vi väljer x antal frågor från listan som ska shufflas
    public static List<Question> pickNRandom(ObservableList<Question> lst, int n) {
        List<Question> copy = new LinkedList<Question>(lst);
        Collections.shuffle(copy);
        return copy.subList(0, n);
    }

    @FXML
    public void nextQuestionAction(ActionEvent event) {
        int nr = Integer.parseInt(total.getText());
        List<Question> randomPicks = pickNRandom(questionList, nr);
        question = randomPicks.get(count++);
        label.setText(question.getQuestion());
        qLeft.setText(""+min++);
        nextBtn.setText("Nästa");

        radioBtn1.setText(question.getAnswer());
        radioBtn2.setText(question.getWrong1());
        radioBtn3.setText(question.getWrong2());
        radioBtn4.setText(question.getWrong3());
        
        if(radioBtn1.isSelected()){
            points++;
        }
        
        if(count == randomPicks.size()){
        nextBtn.setText("OK");
        label.setText("Du hade " + points + " rätt av " + index + " frågor!");
        radioBtn1.setVisible(false);
        radioBtn2.setVisible(false);
        radioBtn3.setVisible(false);
        radioBtn4.setVisible(false);
        
        //cancelBtn.setVisible(false);
        }
  

//////Det funkar med array! typ...
//        c = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions")
//                .request(MediaType.APPLICATION_JSON)
//                .get(String.class);
//        //textarea.appendText(c);
//        JSONParser parser = new JSONParser();
//        try {
//            Object object = parser.parse(c);
//            JSONArray array = (JSONArray) object;
//            JSONObject obj2 = (JSONObject) array.get(1);
//            System.out.println(array.get(1));
//            System.out.println(obj2);
//            System.out.println("array: " + array);
//
//            List<Question> list = new ArrayList();
//            list = array;
//            System.out.println("Size " + list.size());
//            System.out.println("tre " + list.get(3));
//
//        } catch (Exception e) {
//
//        }
        
        
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

    @FXML
    public void showQuestionsMenuButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SceneShow.fxml"));
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
        total.setText(""+index);
                label.setWrapText(true);

        ToggleGroup radioGroup = new ToggleGroup();
        radioBtn1.setToggleGroup(radioGroup);
        radioBtn2.setToggleGroup(radioGroup);
        radioBtn3.setToggleGroup(radioGroup);
        radioBtn4.setToggleGroup(radioGroup);
        
        String c;
        c = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class
                );
        JSONArray array = null;
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(c);
            array = (JSONArray) object;
        } catch (Exception e) {

        }
        questionList = FXCollections.observableArrayList();

        for (int i = 0; i < array.size(); i++) {
            JSONObject row = (JSONObject) array.get(i);
            String qstring = (String) (row.get("question"));
            String astring = (String) (row.get("answer"));
            String w1string = (String) (row.get("wrong1"));
            String w2string = (String) (row.get("wrong2"));
            String w3string = (String) (row.get("wrong3"));
            question = new Question();
            question.setQuestion(qstring);
            question.setAnswer(astring);
            question.setWrong1(w1string);
            question.setWrong2(w2string);
            question.setWrong3(w3string);

            questionList.add(question);
            
            label.setText(question.getQuestion());
            radioBtn1.setText(question.getAnswer());
            radioBtn2.setText(question.getWrong1());
            radioBtn3.setText(question.getWrong2());
            radioBtn4.setText(question.getWrong3());

        }
 

    }

}
