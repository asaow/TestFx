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
import javafx.stage.Stage;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 * FXML Controller class
 *
 * @author Loki
 */
public class SceneController implements Initializable {

    @FXML
    private Button addQuestionsMenuButton;
    @FXML
    private Button showQuestionsMenuButton;
    
    @FXML
    private Button showTableMenuButton;
    
    @FXML
    private Button showTestMenuButton;
    
    @FXML
    private Button homeMenuButton;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    public void addQuestionsMenuButtonAction(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("SceneTwo.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
            stage.setScene(scene);
            stage.show(); 
    }
    
     @FXML
    public void showQuestionsMenuButtonAction(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("SceneTest.fxml"));
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}