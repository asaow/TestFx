/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

import com.google.gson.Gson;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import static org.glassfish.hk2.utilities.reflection.Pretty.array;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

/**
 * FXML Controller class
 *
 * @author Loki
 */
public class SceneShowController implements Initializable {

    @FXML
    private TextArea textarea;
    @FXML
    private Button showQuestionsMenuButton;

    @FXML
    private Button addQuestionsButton;

    @FXML
    private Button addQuestionsMenuButton;

    @FXML
    private Button showTableMenuButton;

    @FXML
    private Button homeMenuButton;

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
    public void showTestMenuButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SceneTest.fxml"));
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
        // TODO
        String c;
        Question q;
        Gson gson = new Gson();

//        c = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions/1")
//                .request(MediaType.APPLICATION_JSON)
//                .get(String.class);
//        q = gson.fromJson(c, Question.class);
//        textarea.appendText(q.getQuestion());


     
//Det funkar med array! typ...
        c = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        textarea.appendText(c);
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

//            textarea.appendText(((Question) array.get(0)).toString());
//            System.out.println(array.get(0).toString());
        } catch (Exception e) {

        }


//         String cc = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions")
//                .request(MediaType.APPLICATION_JSON)
//                .get(String.class);
//        JSONParser jo = new JSONParser();
//        try{
//            Object object = jo.parse(cc);
//        JSONArray array = (JSONArray)object;
//        textarea.appendText(array.get(0).toString());
//        
//Post fråga
//        Question que= new Question(1, 9, "frågaaaaa9", "räääättt","Feeel1", "Feeel2", "Feeel3" );
//        Question question;
//        question = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions")
//                .request()
//                .post(Entity.entity(que, MediaType.APPLICATION_JSON) , Question.class);
//                System.out.println(question.getQuestion());


Course ccc = new Course(5, "helo", 9);
        Course course;
        course = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses")
                .request()
                .post(Entity.entity( ccc, MediaType.APPLICATION_JSON) , Course.class);
        System.out.println(course.getName());
//        }
//        catch(Exception e)
//        {
//        }
    }

}
