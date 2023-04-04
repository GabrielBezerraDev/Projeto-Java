/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaapplication12;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.util.List;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputControl;

public class FXMLController implements Initializable {

    @FXML
    private TextField nome, email;
    
    @FXML
    TextInputControl sobrenome = new TextField();;
    
    @FXML
    private AnchorPane painel;
    
    @FXML
    public void clicar(){
        List<TextField> form = new ArrayList<>();
        form.add(nome);
        form.add(email);
        double height = 100;
        for(int i = 0; i < 3; i++){
            Label novoLabel = new Label();
            novoLabel.setLayoutX(400);
            novoLabel.setLayoutY(height);
            novoLabel.setText(form.get(i).getText());
            painel.getChildren().add(novoLabel);
            height+= 20;
        }
    }   
        
    @FXML
    public void cadastro() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML1.fxml"));
        Parent root = loader.load();
        Scene scene = painel.getScene(); 
        scene.getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
        scene.setRoot(root);
    }
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sobrenome.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.printf("%s",observable);
        });
    }    
    
}
