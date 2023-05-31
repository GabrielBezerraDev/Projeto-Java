/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author gabrielbezerra
 */
public class PopUpController {
        
        public  String erros;
        @FXML
        static private Stage stage;
        @FXML
        private Scene scene;
        
        @FXML
        public void popUp() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popup.fxml"));
            Parent root = fxmlLoader.load();
            Pane pane = (Pane) root.getChildrenUnmodifiable().get(1);
            System.out.println(pane.getChildren().get(2));
            Label text = (Label) pane.getChildren().get(2);
            text.setText(erros);
            text.setStyle("-fx-font-weight: bold;");
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
            stage = new Stage();
            stage.setMinWidth(790); 
            stage.setMinHeight(345); 
            stage.setResizable(false); 
            stage.setScene(scene);
            stage.show();
    } 
       
       
        public void fechar(){
            stage.close();
        }
        
        @FXML
        public void initialize(URL url, ResourceBundle rb) {
            Platform.runLater(() -> {

            });
        }
        
}
