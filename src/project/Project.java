/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.util.List;
import java.util.ArrayList;
import accountsusers.*;
import interfaces.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import db.DB;
import db.DbException;
import db.DbIntegrityException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Project implements Valida, ConverterString{
    public static Coordenador coordenador;
    public static Membros membro;
    public int id;
    private String nomeProjeto, descricaoProjeto, inicio, fim;
    private String[] dadosProjeto;
    public String erros;
    
    public Project(String nomeProjeto, String descricaoProjeto, String inicio, String fim){
        this.dadosProjeto = new String[]{nomeProjeto, descricaoProjeto, inicio, fim};
        this.nomeProjeto = nomeProjeto;
        this.descricaoProjeto = descricaoProjeto;
        this.inicio = inicio;
        this.fim = fim;
    }
    
    public boolean validation(){
        Object[] resultado = validaCampus(this.dadosProjeto);
        this.erros = (String) resultado[1];
        return (boolean) resultado[0];
    }
    
    public int setData(){
        Connection conn = null;
        PreparedStatement st = null;
        LocalDate dateInicio = converte(this.inicio);
        LocalDate dateFim = converte(this.fim);
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
                this.id = rs.getInt(1);
                System.out.println(this.id);
        }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            DB.closeStatement(st);
            DB.closeConnection();
        }
        return this.id;
    }
         public void delete(){
            Connection conn = null;
            PreparedStatement st = null;
        try{
            conn = DB.getConnection();
            st = conn.prepareStatement(
            "DELETE FROM projeto " 
                    + "WHERE "
                    + "id = ?"
            );
        st.setInt(1, this.id);
        st.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }  
    public void showDataProjetc(){
        System.out.printf("%nNome do Projeto: %s%nDescrição do Projeto: %s%n",this.nomeProjeto, this.descricaoProjeto);
    }
}

