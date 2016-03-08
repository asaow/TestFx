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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Loki
 */
//används inte!!!
public class RadioButtonController {

    @FXML
    private Button showTableMenuButton;
    @FXML
    private Button showTestMenuButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button checkBoxButton;
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
    public void cancelButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

//     @FXML
//    public void showTestMenuButtonAction(ActionEvent event) throws IOException{
//       Parent root = FXMLLoader.load(getClass().getResource("SceneTest.fxml"));
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
//            stage.setScene(scene);
//            stage.show(); 
//    }
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
    public void homeMenuButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void showCheckBoxButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CheckBoxScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the controller class.
     */
  //  @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
