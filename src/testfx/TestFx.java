/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Client;



/**
 *
 * @author Loki
 */
public class TestFx extends Application {
    public static Client client;
    
    @Override
    public void start(Stage stage) throws Exception {
          
        client = ClientBuilder.newClient();    
        Parent root = FXMLLoader.load(getClass().getResource("SceneShow.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("SceneCascadeStyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
