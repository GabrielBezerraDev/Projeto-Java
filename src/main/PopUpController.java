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
import java.util.logging.Level;
import java.util.logging.Logger;
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
        static private Stage stage,stage2;
        @FXML
        private Parent root, root2;
        @FXML
        private Scene scene, scene2;
        @FXML
        private FXMLLoader fxmlLoader, fxmlLoader2;
        @FXML
        private Pane pane, pane2;
        @FXML
        private Pane paneTitulo;

        @FXML
        public void popUpError() throws IOException {
            fxmlLoader2 = new FXMLLoader(getClass().getResource("popup.fxml"));
            root2 = fxmlLoader2.load();
            pane2 = (Pane) root2.getChildrenUnmodifiable().get(1);
            System.out.println(pane2.getChildren());
            Label text = (Label) pane2.getChildren().get(2);
            text.setText(erros);
            text.setStyle("-fx-font-weight: bold;");
            scene2 = new Scene(root2);
            scene2.getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
            stage2= new Stage();
            stage2.setMinWidth(790); 
            stage2.setMinHeight(345); 
            stage2.setResizable(false); 
            stage2.setScene(scene2);
            stage2.show();
        } 
        
        @FXML
        public void popUpWarnings(boolean option, Pane tela) throws IOException {
             fxmlLoader2 = new FXMLLoader(getClass().getResource("popup.fxml"));
             root2 = fxmlLoader2.load();
            pane2 = (Pane) root2.getChildrenUnmodifiable().get(1);
            for(int i = 0; i < pane2.getChildren().size(); i++){
                pane2.getChildren().remove(0);
            }
            paneTitulo = (Pane) root2.getChildrenUnmodifiable().get(0);
            Label titulo = (Label) paneTitulo.getChildren().get(0);
            titulo.setText("FEITO! :)");
            Button continuar = new Button();
            Button voltar = new Button();
            continuar.setOnMouseClicked((MouseEvent event) -> {
                 try {
                     if(option){
                        fxmlLoader = new FXMLLoader(getClass().getResource("FXML.fxml"));
                        root = fxmlLoader.load();
                        scene =  FXMLController.sceneGrid;
                        stage = (Stage) FXMLController.sceneGrid.getWindow();
                        stage.setMinWidth(790); 
                        stage.setMinHeight(345); 
                        stage.setWidth(790);
                        stage.setHeight(345);
                        stage.setScene(scene);
                        scene.getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
                        scene.setRoot(root);
                     }
                     else{
                         Stage telaAtual = (Stage) tela.getScene().getWindow();
                         telaAtual.close();
                     }
                     fechar();
                 } catch (IOException ex) {
                     Logger.getLogger(PopUpController.class.getName()).log(Level.SEVERE, null, ex);
                 }
            });
            voltar.setText("Voltar");
            voltar.setOnMouseClicked((MouseEvent event) -> {
                if(!FXMLController.membros.isEmpty()){
                    FXMLController.membros.get(FXMLController.membros.size()-1).delete();
                    FXMLController.membros.remove(FXMLController.membros.size()-1);
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
            pane2.getChildren().addAll(text,voltar, continuar);
            scene2 = new Scene(root2);
            scene2.getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
            stage2 = new Stage();
            stage2.setScene(scene2);
            stage2.setMinHeight(345); 
            stage2.setResizable(false); 
            stage2.show();
        }
       
       
        public void fechar(){
            stage2.close();
        }
        
        @FXML
        public void initialize(URL url, ResourceBundle rb) {
            Platform.runLater(() -> {

            });
        }
        
}