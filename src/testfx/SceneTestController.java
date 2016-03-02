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
import java.util.List;
import java.util.Map;
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
    int idCounter = 1;

    @FXML
    public void nextQuestionAction(ActionEvent event) {

        idCounter++;
        String que = String.valueOf(idCounter);

        String c;
        Question q;
        Gson gson = new Gson();
        WebTarget base = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions");
        WebTarget qId = base.path(que);
        c = qId.request(MediaType.APPLICATION_JSON)
                .get(String.class);
        q = gson.fromJson(c, Question.class);

        label.setText(q.getQuestion());
////Det funkar med array! typ...
        c = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        //textarea.appendText(c);
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(c);
            JSONArray array = (JSONArray) object;
            JSONObject obj2 = (JSONObject) array.get(1);
            System.out.println(array.get(1));
            System.out.println(obj2);
            System.out.println("array: " + array);

            List<Question> list = new ArrayList();
            list = array;
            System.out.println("Size " + list.size());
            System.out.println("tre " + list.get(3));

        } catch (Exception e) {

        }

//        Question q;
//        Gson gson = new Gson();
//        String a = "http://localhost:8080/ExamServer/webresources/courses/1/questions/";
//
//        for (int i = 1; i < 8; i++) {
//            String b = a + i;
//            System.out.println(b);
//
//       
//            String cc;
//            c = TestFx.client.target(b)
//                    .request(MediaType.APPLICATION_JSON)
//                    .get(String.class);
//
//            q = gson.fromJson(c, Question.class);
//   //         label.setText(q.getQuestion());
//            System.out.println(q.getQuestion());
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

        ToggleGroup radioGroup = new ToggleGroup();
        radioBtn1.setToggleGroup(radioGroup);
        radioBtn2.setToggleGroup(radioGroup);
        radioBtn3.setToggleGroup(radioGroup);
        radioBtn4.setToggleGroup(radioGroup);

        String c;
        Question q;
        Gson gson = new Gson();

        c = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions/1")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        q = gson.fromJson(c, Question.class);
        label.setText(q.getQuestion());
        radioBtn1.setText(q.getAnswer());
        radioBtn2.setText(q.getWrong1());
        radioBtn3.setText(q.getWrong2());
        radioBtn4.setText(q.getWrong3());

    }

}
