/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import db.DB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.FXMLController;
import interfaces.ConverterString;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author gabrielbezerra
 */
public class TelaPrincipalController implements ConverterString {
    
    private TextField quantidadeMembros = new TextField();
    private Label question = new Label();
    private Button continuar = new Button();
    private static Pane paneChoice;
    private static boolean setTela = false;
    public static int idEquipe;
    public static Parent root;
    public static Scene scene; 
    public static Stage stage;
    public static String cargoAtual;
    
    public void adicionarMembro() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("popUpChoice.fxml"));
        root = loader.load();
        scene= new Scene(root);
        stage = new Stage();
        scene.getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
        setTela = true;
        montandoPopUp();
    }
    
    public void novosMembros() throws IOException{
        if(quantidadeMembros.getText().isEmpty()){
            PopUpController popError = new PopUpController();
            popError.erros = "Por favor digite a quantidade de membros";
            popError.popUpError();
            return;
        }
        stage.setWidth(790);
        stage.setHeight(430);
        System.out.println(paneChoice.getChildren().size());
        for(int i = 0; i  < 4; i++){
            paneChoice.getChildren().remove(0);
        }
        FXMLController telasMembros = new FXMLController();
        FXMLController.option = false;
        telasMembros.telaCadastroMembro(quantidadeMembros, paneChoice, idEquipe);
    }
    
    public void ajustarTela(){
        stage.setWidth(356);
        stage.setHeight(200);
        montandoPopUp();
    }
    
        public void montandoPopUp(){
        FXMLController.countEmployee = 0;
        continuar.setOnMouseClicked((MouseEvent event) -> {
            try {
                novosMembros();
            } catch (IOException ex) {
                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        continuar.setText("Continuar");
        continuar.setLayoutX(140);
        continuar.setLayoutY(125);
        quantidadeMembros.setLayoutX(94);
        quantidadeMembros.setLayoutY(80);
        question.setLayoutX(50);
        question.setLayoutY(50);
        question.setText("Quantos membros você deseja adicionar?");
        if(setTela){
            stage.setResizable(false);
            paneChoice = (Pane) root;
            stage.setScene(scene);
            stage.show();
            setTela = false;
        }
        paneChoice.getChildren().addAll(question,quantidadeMembros, continuar);
    }
        
        public static void pegarUsuarios(String cargo){
            cargoAtual = cargo;
            if(cargo.equals("membro")) cargo = "coordenador";
            else{
                cargo = "membro";
            }
            boolean user = true;
            Connection conn = null;
            Statement st = null;
            ResultSet rs = null;
            System.out.println("CARGO: "+cargo);
            System.out.println("ID: "+idEquipe);
            try{
                conn = DB.getConnection();
                st = conn.createStatement();
                rs = st.executeQuery(String.format("select * from %s "
                        + "where projeto_id = %d",cargo, idEquipe));
                while(rs.next()){
                    String nome = rs.getString("nome"),sobrenome = rs.getString("sobrenome"),cpf = rs.getString("cpf"),contato = rs.getString("contato"),email = rs.getString("email"),genero = rs.getString("genero"), inicio = rs.getString("data_inicio"), fim = rs.getString("data_fim");
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                    String dataInicio = dateFormat.format(inicio);
//                    String dataFim = dateFormat.format(fim);
//                    System.out.printf("%nNome: %s%nSobrenome: %s%nCPF: %s%nContato: %s%nEmail: %s%nGênero: %s%nData inicio: %s%nData fim: %s%n",nome,sobrenome,cpf,contato, email, genero,dataInicio,dataFim);
                      System.out.printf("%nNome: %s%nSobrenome: %s%nCPF: %s%nContato: %s%nEmail: %s%nGênero: %s%n",nome,sobrenome,cpf,contato, email, genero);
                    
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            finally{
                DB.closeStatement(st);
                DB.closeConnection();
            }
    }
    
}
