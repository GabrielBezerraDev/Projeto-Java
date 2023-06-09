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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
        Pane pane;
        @FXML
        private Pane paneTitulo;

        @FXML
        public void popUpError() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popup.fxml"));
            Parent root = fxmlLoader.load();
            pane = (Pane) root.getChildrenUnmodifiable().get(1);
            System.out.println(pane.getChildren());
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
        
        @FXML
        public void popUpWarnings() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popup.fxml"));
            Parent root = fxmlLoader.load();
            pane = (Pane) root.getChildrenUnmodifiable().get(1);
            for(int i = 0; i < pane.getChildren().size(); i++){
                pane.getChildren().remove(0);
            }
            paneTitulo = (Pane) root.getChildrenUnmodifiable().get(0);
            Label titulo = (Label) paneTitulo.getChildren().get(0);
            titulo.setText("FEITO! :)");
            Button continuar = new Button();
            Button voltar = new Button();
            voltar.setText("Voltar");
            voltar.setOnMouseClicked((MouseEvent event) -> {
                if(!FXMLController.deleteMembros.isEmpty()){
                    FXMLController.deleteMembros.get(FXMLController.deleteMembros.size()-1).delete();
                    FXMLController.deleteMembros.remove(FXMLController.deleteMembros.size()-1);
                }
                else{
                    FXMLController.project.coordenador.delete();
                }
                fechar();
            });
            continuar.setText("Continuar");
            voltar.setLayoutX(84);
            voltar.setLayoutY(270);
            continuar.setLayoutX(475);
            continuar.setLayoutY(270);
            Label text = new Label();
            text.setLayoutX(84);
            text.setLayoutY(70);
            text.prefHeight(100);
            text.prefWidth(10);
            text.setTextFill(Color.rgb(255, 255, 255));
            text.setStyle("-fx-background-color: red; -fx-padding: 50px; -fx-background-radius: 18px;-fx-font-size: 20px; -fx-font-weight: bold;-fx-fill:white;");
            text.setWrapText(true);
            text.setText("Cadastros criados com sucesso,\ndeseja voltar para tela principal?");
            pane.getChildren().addAll(text,voltar, continuar);
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
