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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * FXML Controller class
 *
 * @author Loki
 */
public class ListSceneController implements Initializable {

    @FXML
    private Button addQuestionsMenuButton;
    @FXML
    private Button showTableMenuButton;
    @FXML
    private Button homeMenuButton;
    @FXML
    private Button toLeftButton;
    @FXML
    private Button toRightButton;
    @FXML
    private ListView list1;
    @FXML
    private ListView list2;

    ObservableList<Question> listLeft;
    ObservableList<Question> listRight;
    Question question;

    @FXML
    public void toLeftAction(ActionEvent event) {
        Question selectedItem = (Question) list2.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            listRight.remove(selectedItem);
            listLeft.add(selectedItem);
        }

    }
    
  
    @FXML
    public void toRightAction(ActionEvent event) {
        Question selectedItem = (Question) list1.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            listLeft.remove(selectedItem);
            listRight.add(selectedItem);
        }
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

    @FXML
    public void showTableMenuButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Table.fxml"));
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
        listLeft = FXCollections.observableArrayList();
        listRight = FXCollections.observableArrayList();
        GenericType<List<Question>> gt   = new GenericType<List<Question>>(){};
        List<Question> c;
        c = TestFx.client.target("http://localhost:8080/ExamServer/webresources/courses/1/questions")
                .request(MediaType.APPLICATION_JSON)
                .get(gt);
        System.out.println(c);
        
        for(Question q : c){
            q.getQuestion();
            System.out.println(q.getQuestion());
            listLeft.add(q);

        }
       
        
            list1.setItems(listLeft);
            list2.setItems(listRight);
            Gson gson = new Gson();
          //  Question o = gson.fromJson(c, Question.class);
        //        System.out.println(o);
        
        //    Question q = gson.fromJson(c, Question.class);
//            q = (Question) array.get(i);
//            System.out.println(q.getQuestion());
        
//        JSONArray array = null;
//
//        JSONParser parser = new JSONParser();
//        try {
//            Object object = parser.parse(c);
//            array = (JSONArray) object;
//
//        } catch (Exception e) {
//
//        }
//        for (int i = 0; i < array.size(); i++) {

        // list for all questions


        
        // JSONObject row = (JSONObject) array.get(i);
        
        //   for (Object o : array) {
  
    //        }
//           
//            Number id = (Number) row.get("id");
//            String que = (String) (row.get("question"));
//
//            question = new Question();
//            question.setId(id.intValue());
//            question.setQuestion(que);
//            listLeft.add(question);
//
      
   
            
            

         
       // }

    }
}
