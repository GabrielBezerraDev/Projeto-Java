/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.util.List;
import java.util.ArrayList;
import accountsusers.*;
import interfaces.Valida;
import java.sql.PreparedStatement;
import java.sql.Connection;
import db.DB;
import db.DbException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Project implements Valida{
    public List<Coordenador> coordenador;
    public List<Membros> membros;
    private String nomeProjeto, descricaoProjeto, inicio, fim;
    private String[] dadosProjeto;
    public static String erros;
    
    public Project(String nomeProjeto, String descricaoProjeto, String inicio, String fim){
        this.dadosProjeto = new String[]{nomeProjeto, descricaoProjeto, inicio, fim};
        this.nomeProjeto = nomeProjeto;
        this.descricaoProjeto = descricaoProjeto;
        this.inicio = inicio;
        this.fim = fim;
        this.coordenador = new ArrayList<>();
        this.membros = new ArrayList<>();
    }
    
    public boolean validation(){
        return validaCampus(this.dadosProjeto);
    }
    
    public void setData(){
        Connection conn = null;
        PreparedStatement st = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateInicio = LocalDate.parse(this.inicio, formatter);
        LocalDate dateFim = LocalDate.parse(this.fim, formatter);
        try{
            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO projeto"
                    +"(nome_do_projeto, descricao_do_projeto, data_inicio, data_fim)"
                    +"VALUES"
                    +"(?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );
        st.setString(1, this.nomeProjeto);
        st.setString(2,this.descricaoProjeto);
        st.setDate(3, Date.valueOf(dateInicio));
        st.setDate(4, Date.valueOf(dateFim));
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        while(rs.next()){
                int id = rs.getInt(1);
                System.out.println(id);
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
    
    public void showDataProjetc(){
        System.out.printf("%nNome do Projeto: %s%nDescrição do Projeto: %s%n",this.nomeProjeto, this.descricaoProjeto);
    }
}

