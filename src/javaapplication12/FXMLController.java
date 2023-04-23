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
import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLController implements Initializable {
    
    private int countEmployee = 0;
    
    @FXML
     List<Pane> employee = new ArrayList<>();
    
    @FXML
    private GridPane painel;
    
    @FXML
    private Label animation;
    
    @FXML
    private TextField amountEmployee;
    
    @FXML
    private  ImageView image;
    
    @FXML
    private Pane programador, supervisionar, cadastroPessoa, cadastroProjeto, main;
    
    @FXML
   private  String elemento = "", texto = "O MELHOR PARA SUA EQUIPE.";
    
    @FXML
    private int i = 0;
    
    @FXML
    boolean realese = false, end = false;
    
    @FXML
    public void supervisor(){
        supervisionar.setVisible(true);
        image.setVisible(false);
        programador.setVisible(false);
        animation.setVisible(false);
    }
    
    @FXML
    public void animation(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                String[] teste = texto.split("");
                elemento += teste[i];
                 Platform.runLater(() -> {
                     animation.setText(elemento);
                 });
                 i++;
                if(i != texto.length() ) animation();
            }
        };
        timer.schedule(task, 80);
    }
    
    @FXML
    public void teste(){
        Timer timer = new Timer();
        TimerTask task;
        task = new TimerTask( ){
            @Override
            public void run() {
                if(realese == false){
                    Platform.runLater(() -> {
                          animation.setText(elemento.substring(0, elemento.length()-1));
                          realese = true;
                          teste();
                     });
                }
                else{
                          Platform.runLater(() -> {
                            animation.setText(elemento);
                            realese=false;
                            teste();
                     });
                }
            }
        };
        if(end == false)  { 
            timer.schedule(task, 3000);
            end = true;
        }
        else{
            timer.schedule(task, 500);
        }
    }
    
    @FXML
    public void funcionario(){
        supervisionar.setVisible(false);
        image.setVisible(false);
        programador.setVisible(true);
        animation.setVisible(false);
    }
    
    @FXML
    public void cadastro() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML1.fxml"));
        Parent root = loader.load();
        Scene scene = painel.getScene();
        Stage stage = (Stage) painel.getScene().getWindow();
        stage.setMinHeight(500); 
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
        scene.setRoot(root);
    }
    
    @FXML
    public void cadastrado(){
        cadastroPessoa.setVisible(false);
        cadastroProjeto.setVisible(true);
    }
    
    public void definido(){
        if(countEmployee != Integer.parseInt(amountEmployee.getText()) && Integer.parseInt(amountEmployee.getText()) != 0) {
            cadastroProjeto.setVisible(false);
            System.out.println("Verdadeiro");
            System.out.println( Integer.parseInt(amountEmployee.getText()));
            employee.add(new Pane());
            employee.get(countEmployee).setId(String.format("employee%d", countEmployee));
            employee.get(countEmployee).setPrefSize(356,361);
            main.getChildren().add(employee.get(countEmployee));
            Label test = new Label();
            test.setLayoutX(28);
            test.setLayoutY(85);
            test.setText(String.format("employee%d", countEmployee));
            Button button = new Button();
            button.setText("Próximo");
            button.setLayoutX(269);
            button.setLayoutY(307);
            button.setOnMouseClicked((MouseEvent event) -> {
                definido();
            });
            employee.get(countEmployee).getChildren().add(button);
            employee.get(countEmployee).getChildren().add(test);
            if(countEmployee != 0){
                  employee.get(countEmployee-1).setVisible(false);
            }
            countEmployee++;
        }
    }
           
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Platform.runLater(() -> {
//                Scene scene = painel.getScene();
//                scene.setOnMouseClicked(event -> {
//                Node teste = (Node) event.getTarget();
//                System.out.println(teste.getId());
//        });
                supervisionar.setVisible(false);
                programador.setVisible(false);
                animation();
                teste();
    });
    }    
    
}
