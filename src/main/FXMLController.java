/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

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
import accountsusers.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import project.Project;
import interfaces.*;


public class FXMLController implements Initializable, ConsultarDB {
    public static List<Membros> deleteMembros = new ArrayList<>();
    private String[] generos = {"Masculino","Femino","Outro"};
    PopUpController popUp = new PopUpController();
    public static Project project;
    private boolean visiblePeople = true;
    private int countEmployee = 0;
    private int i = 0;
    
    
    @FXML
     List<Pane> employee = new ArrayList<>();
        
    @FXML
    private Button loginCoordenador;
    
    @FXML
    private ChoiceBox<String> generoCoordenador;
    
    @FXML
    private ChoiceBox choiceBox = new ChoiceBox();
    
    @FXML
    public GridPane painel = new GridPane();
    
    @FXML
    public static Scene sceneGrid;
    
    @FXML
    private Label animation;
    
    @FXML
    private TextField senhaValidaCoordenador, cpfValidaCoordenador, amountEmployee, nomeCoordenador, sobrenomeCoordenador, cpfCoordenador, contatoCoordenador, emailCoordenador, senhaCoordenador, confirmarSenhaCoordenador, nomeProjeto;
    
    @FXML
    private TextArea descricaoProjeto;
    
    @FXML
    private DatePicker inicioProjeto, fimProjeto, inicioGerenciamento, terminioGerenciamento, inicioDataMembro = new DatePicker(), fimDataMembro = new DatePicker();;
    @FXML
    private Text tittleDescription;
    @FXML
    private  ImageView image;
    
    @FXML
    private Pane programador, supervisionar, main, paneCoordenador, paneMembro, paneLogin;
    
    @FXML
    private ScrollPane cadastroPessoa, cadastroProjeto;
    
    @FXML
    private List<ScrollPane> scrollPane = new ArrayList<>();
    
    @FXML
   private  String elemento = "", texto = "O MELHOR PARA SUA EQUIPE.";
   
    @FXML
    boolean realese = false, end = false;
    
    @FXML
    private static Scene scene;
    
    @FXML
    public void supervisor(){
        TextField fieldCpfCoordenador = (TextField) paneCoordenador.getChildren().get(3);
        TextField fieldSenhaCoordenador = (TextField) paneCoordenador.getChildren().get(4);
        Button buttonCoordenador = (Button) paneCoordenador.getChildren().get(5);
        buttonCoordenador.setOnMouseClicked((MouseEvent event) -> {
            entrar(fieldCpfCoordenador,fieldSenhaCoordenador,"coordenador");
        });
        supervisionar.setVisible(true);
        image.setVisible(false);
        programador.setVisible(false);
        animation.setVisible(false);
    }
    
        @FXML
    public void funcionario(){
        TextField fieldCpfMembro = (TextField) paneMembro.getChildren().get(3);
        TextField fieldSenhaMembro= (TextField) paneMembro.getChildren().get(4);
        Button buttonMembro = (Button) paneMembro.getChildren().get(5);
        buttonMembro.setOnMouseClicked((MouseEvent event) -> {
            entrar(fieldCpfMembro,fieldSenhaMembro, "membro");
        });
        supervisionar.setVisible(false);
        image.setVisible(false);
        programador.setVisible(true);
        animation.setVisible(false);
    }
    
    @FXML
    public void animation(){
        animation.setStyle("-fx-font-size: 25px;-fx-font-weight: bold;");
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
    public void ponto(){
        Timer timer = new Timer();
        TimerTask task;
        task = new TimerTask( ){
            @Override
            public void run() {
                if(realese == false){
                    Platform.runLater(() -> {
                          animation.setText(elemento.substring(0, elemento.length()-1));
                          realese = true;
                          ponto();
                     });
                }
                else{
                          Platform.runLater(() -> {
                            animation.setText(elemento);
                            realese=false;
                            ponto();
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
        
    public void entrar(TextField cpf, TextField senha, String cargo){
        try {
            if(!(boolean) consultarDB(cargo,cpf.getText())[0]){
                popUp.erros = "CPF ou senha incorreto.";
                popUp.popUpError();
                return;
            }
            else if(!((String) consultarDB(cargo,cpf.getText())[1]).equals((String) senha.getText())){
                popUp.erros = "CPF ou senha incorreto.";
                popUp.popUpError();
                return;
            }
            System.out.println(paneCoordenador.getChildren());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaPrincipal.fxml"));
            Parent root = loader.load();
            scene = painel.getScene();
            Stage stage = (Stage) painel.getScene().getWindow();
            stage.setMinHeight(500);
            stage.setWidth(1153);
            stage.setHeight(756);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void cadastro() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML1.fxml"));
        Parent root = loader.load();
        scene = painel.getScene();
        Stage stage = (Stage) painel.getScene().getWindow();
        stage.setMinHeight(500); 
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
        scene.setRoot(root);
        sceneGrid = (Scene) root.getChildrenUnmodifiable().get(0).getScene();
    }
    
    @FXML
    public void cadastrado() throws IOException{
        String formattedDateInit = "";       
        String formattedDateEnd = "";
        if(inicioProjeto.getValue() != null) formattedDateInit = converteDatas(inicioProjeto.getValue());
        if(fimProjeto.getValue() != null) formattedDateEnd = converteDatas(fimProjeto.getValue());
        project = new Project(nomeProjeto.getText(), descricaoProjeto.getText(), formattedDateInit, formattedDateEnd);
        if(!project.validation()){
            popUpError(project.erros);
            return;
        }
        project.setData();
        tittleDescription.setText("Coordenador");
        cadastroPessoa.setVisible(true);
        cadastroProjeto.setVisible(false);
    }
    
    @FXML
    public void next() throws IOException{
        if(countEmployee == 0){
            String formattedDateInit = "";       
            String formattedDateEnd = "";
            if(inicioGerenciamento.getValue() != null) formattedDateInit = converteDatas(inicioGerenciamento.getValue());
            if(terminioGerenciamento.getValue() != null) formattedDateEnd = converteDatas(terminioGerenciamento.getValue());
            project.coordenador = new Coordenador(nomeCoordenador.getText(),sobrenomeCoordenador.getText(),cpfCoordenador.getText(), contatoCoordenador.getText(), emailCoordenador.getText(), senhaCoordenador.getText(), confirmarSenhaCoordenador.getText(), generoCoordenador.getValue(), formattedDateInit, formattedDateEnd, project.id);
            System.out.println("Será feito uma nova instância e essa instância será validada");
            project.coordenador.teste();
            if(!project.coordenador.validacao()){
                popUpError(project.coordenador.erros);
                return;
            }
            if((boolean)project.coordenador.consultandoDB()[0]){
                popUp.erros = "Coordenador já existe";
                popUp.popUpError();
                return;
            }
            project.coordenador.setData();
        }
        if(countEmployee > 0){
            //Pegar o dados do Painel anterior (que eu acabei de criar).
            System.out.println("PEGANDO DADOS");
            TextField [] dadosMembros = new TextField[7];
            int countDados = 0;
            for(int i = 0; i < (employee.get(countEmployee-1).getChildren()).size(); i++){
                if(employee.get(countEmployee-1).getChildren().get(i) instanceof TextField){
                        dadosMembros[countDados] = (TextField) employee.get(countEmployee-1).getChildren().get(i);
                        countDados++;
                }
            }
            String dataInicio = "";
            String dataFim = "";
            if(inicioDataMembro.getValue() != null) dataInicio = converteDatas(inicioDataMembro.getValue());
            if(fimDataMembro.getValue() != null) dataFim = converteDatas(fimDataMembro.getValue());
            project.membro = new Membros(dadosMembros[0].getText(),dadosMembros[1].getText(),dadosMembros[2].getText(),dadosMembros[3].getText(),dadosMembros[4].getText(),dadosMembros[5].getText(),dadosMembros[6].getText(),(String) choiceBox.getValue(),dataInicio,dataFim, project.id);
            if(!project.membro.validacao()){
                popUpError(project.membro.erros);
                return;
            }
            if((boolean)project.membro.consultandoDB()[0]){
                popUp.erros = "Membro já existe";
                popUp.popUpError();
                return;
            }
            project.membro.setData();
            deleteMembros.add(project.membro);
        }
        System.out.println(amountEmployee.getText());
        if(!amountEmployee.getText().isEmpty()){
            if(countEmployee != Integer.parseInt(amountEmployee.getText()) && Integer.parseInt(amountEmployee.getText()) != 0) {
                int layoutX = 0;
                int layoutY = 0;
                Label[] labelsEmployees = new Label[7];
                TextField[] textInput = new TextField[7];
                Label description = new Label();
                Label genero = new Label();
                TextArea textArea = new TextArea();
                Label inicioMembro = new Label();
                Label fimMembro = new Label();
                String[] campos = {"Nome*", "Sobrenome*","CPF*","Número de contato*","Email*","Senha*","Confirmar senha*"}; 
                cadastroPessoa.setVisible(false);
                visiblePeople = false;
                System.out.println("Verdadeiro");
                System.out.println( Integer.parseInt(amountEmployee.getText()));
                scrollPane.add(new ScrollPane());
                scrollPane.get(countEmployee).setPrefSize(420, 400);
                employee.add(new Pane());
                scrollPane.get(countEmployee).setContent(employee.get(countEmployee));
                employee.get(countEmployee).setId(String.format("employee%d", countEmployee));
                employee.get(countEmployee).setPrefSize(356,540); 
                main.getChildren().add(scrollPane.get(countEmployee));
                genero.setLayoutX(205);
                genero.setLayoutY(186);
                genero.setText("Gênero*");
                description.setLayoutX(27);
                description.setLayoutY(246);
                inicioMembro.setText("Início contribuição*");
                fimMembro.setText("Fim contribuição");
                inicioMembro.setLayoutX(27);
                inicioMembro.setLayoutY(396);
                fimMembro.setLayoutX(205);
                fimMembro.setLayoutY(396);
                inicioDataMembro.setLayoutX(27);
                inicioDataMembro.setLayoutY(416);
                inicioDataMembro.setPrefSize(109, 24);
                fimDataMembro.setLayoutX(205);
                fimDataMembro.setLayoutY(416);
                fimDataMembro.setPrefSize(109, 24);
                textArea.setLayoutX(27);
                textArea.setLayoutY(266);
                textArea.setPrefSize(317, 100);
                textArea.setWrapText(true);
                description.setText("Descrição da função");
                choiceBox.setLayoutX(205);
                choiceBox.setLayoutY(206);
                choiceBox.getItems().addAll(generos);
                Button buttonPrevious = new Button();
                Button buttonNext = new Button();
                buttonPrevious.setText("Anterior");
                buttonPrevious.setLayoutX(30);
                buttonPrevious.setLayoutY(486);
                buttonNext.setText("Próximo");
                buttonNext.setLayoutX(269);
                buttonNext.setLayoutY(486);
                buttonNext.setOnMouseClicked((MouseEvent event) -> {
                    try {
                        next();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                buttonPrevious.setOnMouseClicked((MouseEvent event) -> {
                    previous();
                });
                for (int i = 0; i < labelsEmployees.length; i++){
                    textInput[i] = new TextField();
                    labelsEmployees[i] = new Label();
                    layoutY += 50;
                    if(i == 0 || i == 4 ) layoutY = 0;
                    if(i > 3) layoutX = 178;
                    textInput[i].setLayoutX(27+layoutX);
                    textInput[i].setLayoutY(57+layoutY);
                    labelsEmployees[i].setLayoutX(27+layoutX);
                    labelsEmployees[i].setLayoutY(40+layoutY);
                    labelsEmployees[i].setText(campos[i]);
                    employee.get(countEmployee).getChildren().addAll(textInput[i], labelsEmployees[i]);
                }
                tittleDescription.setText(String.format("%dºmembro:", countEmployee+1));
                employee.get(countEmployee).getChildren().addAll(textArea,description,buttonNext,buttonPrevious,choiceBox,genero, inicioMembro, fimMembro, inicioDataMembro, fimDataMembro);
                if(countEmployee != 0){
                      scrollPane.get(countEmployee-1).setVisible(false);
                }
                countEmployee++;
            }
            else if((countEmployee == Integer.parseInt(amountEmployee.getText()) || amountEmployee.getText() == null) && Integer.parseInt(amountEmployee.getText()) != 0){
                System.out.println("ENTROU PORRA");
                popUp.popUpWarnings();
            }
        }
        else{
            popUp.popUpWarnings();
        }
    }
    
    @FXML
    public void previous(){
        if(countEmployee > 0){

        }
        if(!scrollPane.isEmpty()){
            //deletar membro
            if(!deleteMembros.isEmpty()){
                deleteMembros.get(deleteMembros.size()-1).delete();
                System.out.println(deleteMembros.get(deleteMembros.size()-1).nome);
                deleteMembros.remove(deleteMembros.size()-1);
            }

            countEmployee-=1;
            scrollPane.get(countEmployee).setVisible(false);
            scrollPane.remove(countEmployee);
            employee.remove(countEmployee);
        }
        if(countEmployee == 0 && visiblePeople == false){
            //deletar coordenador
            project.coordenador.delete();
            cadastroPessoa.setVisible(true);
            visiblePeople = true;
            tittleDescription.setText("Coordenador");
            return;
        }
        else if(scrollPane.isEmpty()){
            //deletar projeto
            project.delete();
            cadastroProjeto.setVisible(true);
            cadastroPessoa.setVisible(false);
            tittleDescription.setText("Projeto");
            return;
        }
        tittleDescription.setText(String.format("%dºmembro:", countEmployee));
        scrollPane.get(countEmployee-1).setVisible(true);
    }
    
    public String converteDatas(LocalDate data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = data.format(formatter);       
        return formattedDate;
    }
    
    public void popUpError(String error) throws IOException{
        popUp.erros = error;
        popUp.popUpError();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() ->{
                paneLogin = (Pane) painel.getChildren().get(1);
                paneCoordenador = (Pane) paneLogin.getChildren().get(0);
                paneMembro = (Pane) paneLogin.getChildren().get(1);
                cadastroPessoa.setVisible(false);
                cadastroProjeto.setVisible(true);
                generoCoordenador.getItems().addAll(generos);
        });
        try{
                 Platform.runLater(() -> {
                scene = painel.getScene();
                scene.setOnMouseClicked(event -> {
                Node teste = (Node) event.getTarget();
                System.out.println(teste.getId());
        });
                supervisionar.setVisible(false);
                programador.setVisible(false);
                animation();
                ponto();
        });
        }
        finally{
           System.out.println("Próxima tela");
        }
    }    
    
}