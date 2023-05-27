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

/**
 *
 * @author gabrielbezerra
 */
public class PopUpController {
        
        @FXML
        static private Stage stage;
        @FXML
        private Scene scene;
        @FXML
        private Button buttonClose;
        
        public void popUp() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popup.fxml"));
            Parent root = fxmlLoader.load();
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
            buttonClose.setText("FOI");
        }
        
        @FXML
        public void initialize(URL url, ResourceBundle rb) {

        }
        
}
