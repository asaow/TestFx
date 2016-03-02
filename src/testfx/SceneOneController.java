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

/**
 * FXML Controller class
 *
 * @author Loki
 */
public class SceneOneController implements Initializable {
@FXML
private Button loginBtn;

    @FXML
    public void loginButtonAction(ActionEvent event) throws IOException{
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

    }    
    
}
