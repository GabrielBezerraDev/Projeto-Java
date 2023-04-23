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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXMLController implements Initializable {
    
    private boolean visibleProject = true;
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
    private Text tittleDescription;
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
        tittleDescription.setText("Projeto");
        cadastroPessoa.setVisible(false);
        cadastroProjeto.setVisible(true);
    }
    //X = 381 e Y = 137
    @FXML
    public void next(){
        if(countEmployee != Integer.parseInt(amountEmployee.getText()) && Integer.parseInt(amountEmployee.getText()) != 0) {
            int layoutX = 0;
            int layoutY = 0;
            Label[] labelsEmployees = new Label[6];
            TextField[] textInput = new TextField[6];
            Label description = new Label();
            TextArea textArea = new TextArea();
            String[] campos = {"Nome*", "Sobrenome*","CPF*","Email*","Senha*","Confirmar senha*"}; 
            cadastroProjeto.setVisible(false);
            visibleProject = false;
            System.out.println("Verdadeiro");
            System.out.println( Integer.parseInt(amountEmployee.getText()));
            employee.add(new Pane());
            employee.get(countEmployee).setId(String.format("employee%d", countEmployee));
            employee.get(countEmployee).setPrefSize(356,328);
            main.getChildren().add(employee.get(countEmployee));
            description.setLayoutX(27);
            description.setLayoutY(187);
            textArea.setLayoutX(27);
            textArea.setLayoutY(208);
            textArea.setPrefSize(317, 100);
            description.setText("Descrição da função");
            Button buttonPrevious = new Button();
            Button buttonNext = new Button();
            buttonPrevious.setText("Anterior");
            buttonPrevious.setLayoutX(30);
            buttonPrevious.setLayoutY(315);
            buttonNext.setText("Próximo");
            buttonNext.setLayoutX(269);
            buttonNext.setLayoutY(315);
            buttonNext.setOnMouseClicked((MouseEvent event) -> {
                next();
            });
            buttonPrevious.setOnMouseClicked((MouseEvent event) -> {
                previous();
            });
            for (int i = 0; i < labelsEmployees.length; i++){
                textInput[i] = new TextField();
                labelsEmployees[i] = new Label();
                layoutY += 50;
                if(i == 0 || i == 3 ) layoutY = 0;
                if(i > 2) layoutX = 165;
                textInput[i].setLayoutX(27+layoutX);
                textInput[i].setLayoutY(57+layoutY);
                labelsEmployees[i].setLayoutX(27+layoutX);
                labelsEmployees[i].setLayoutY(40+layoutY);
                labelsEmployees[i].setText(campos[i]);
                employee.get(countEmployee).getChildren().add(textInput[i]);
                employee.get(countEmployee).getChildren().add(labelsEmployees[i]);
            }
            tittleDescription.setText(String.format("%dºmembro:", countEmployee+1));
            employee.get(countEmployee).getChildren().add(textArea);
            employee.get(countEmployee).getChildren().add(description);
            employee.get(countEmployee).getChildren().add(buttonNext);
            employee.get(countEmployee).getChildren().add(buttonPrevious);
            if(countEmployee != 0){
                  employee.get(countEmployee-1).setVisible(false);
            }
            countEmployee++;
        }
    }
    
    @FXML
    public void previous(){
        if(!employee.isEmpty()){
            countEmployee-=1;
            employee.get(countEmployee).setVisible(false);
            employee.remove(countEmployee);
        }
        if(countEmployee == 0 && visibleProject == false){
            cadastroProjeto.setVisible(true);
            visibleProject = true;
            tittleDescription.setText("Projeto");
            return;
        }
        else if(employee.isEmpty()){
            cadastroProjeto.setVisible(false);
            cadastroPessoa.setVisible(true);
            tittleDescription.setText("Coodernador");
            return;
        }
       tittleDescription.setText(String.format("%dºmembro:", countEmployee));
        employee.get(countEmployee-1).setVisible(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Platform.runLater(() -> {
                Scene scene = painel.getScene();
                scene.setOnMouseClicked(event -> {
                Node teste = (Node) event.getTarget();
                System.out.println(teste.getId());
        });
                supervisionar.setVisible(false);
                programador.setVisible(false);
                animation();
                teste();
    });
    }    
    
}
