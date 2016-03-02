/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * FXML Controller class
 *
 * @author Loki
 */
public class SceneTwoController implements Initializable {
    @FXML
    private Button showTableMenuButton;
    @FXML
    private Button showTestMenuButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label label;
    @FXML
    private Button addButton;
    @FXML
    private Button menuAddBtn;
    @FXML
    private TextField answerText;
    @FXML
    private TextField questionText;
    @FXML
    private TextField wrong1Text;
    @FXML
    private TextField wrong2Text;
    @FXML
    private TextField wrong3Text;
    @FXML
    private Button homeMenuButton;
        @FXML
    private Button showQuestionsMenuButton;
    

    @FXML
    public void cancelButtonAction(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
    }
    
     @FXML
    public void showTestMenuButtonAction(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("SceneTest.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
            stage.setScene(scene);
            stage.show(); 
    }
    
    @FXML
    public void showTableMenuButtonAction(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("Table.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
            stage.setScene(scene);
            stage.show(); 
    }
    
        @FXML
    public void homeMenuButtonAction(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
            stage.setScene(scene);
            stage.show(); 
    }
    
        @FXML
    public void showQuestionsMenuButtonAction(ActionEvent event) throws IOException{
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

        
    }

}