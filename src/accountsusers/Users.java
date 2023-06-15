/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accountsusers;
import db.DB;
import db.DbIntegrityException;
import interfaces.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Jonathan
 */

public class Users implements Valida, ConverterString, VerificarEfetivo{
    public String nome, sobrenome, cpf, contato, email, senha, confirmaSenha, genero, inicio, fim, cargo;
    public int projeto_id, id;
    protected String[] dadosUser;
    public String erros;
    protected Users (String nome, String sobrenome, String cpf, String contato, String email, String senha, String confirmaSenha, String genero, String inicio, String fim, String cargo, int projeto_id){
        this.dadosUser = new String[]{nome,sobrenome,cpf,contato,email,senha,confirmaSenha,genero, inicio, fim};
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.contato = contato;
        this.email = email;
        this.senha = senha;
        this.confirmaSenha = confirmaSenha;
        this.genero = genero;
        this.inicio = inicio;
        this.fim = fim;
        this.cargo = cargo;
        this.projeto_id = projeto_id;
    }

    public boolean validacao(){
        boolean resultado = false;
        if(!(boolean)validaCampus(this.dadosUser)[0]){
        Object[] result = validaCampus(this.dadosUser);
        this.erros = (String) result[1];
        return (boolean) result[0];
        } 
        boolean[] validando = {validacaoEmail(), validacaoCpf(),validacaoSenha()};
        for(int i = 0; i < validando.length; i++){
            if(validando[i] && i == validando.length-1){
                resultado = true;
            }
            else if(!validando[i]){
                break;
            }
        }
        return resultado;
    }
    
    
    protected boolean validacaoSenha(){
        boolean resultado = false;
        if(this.senha.length() >= 4 && this.senha.equals(this.confirmaSenha)){
            resultado = true;
            return resultado;
        }
        if(!resultado && this.senha.length() < 4){
            this.erros = "O tamanho da sua senha deve ser maior do que 4 caracteres.";
        }
        else{
            this.erros  = "As senhas são diferentes.";
        }
        return resultado;
    }

    protected boolean validacaoEmail(){
        boolean resultado = false;
        if(this.email.contains("@") && this.email.contains(".")){
            resultado = true;
        }
        else{
            this.erros = "Email inválido.";
        }
        return resultado;
    }

    protected boolean validacaoCpf(){
        boolean resultado = false, validaPrimeiroDigito = false, validaSegundoDigito = false;
        if(this.cpf.length() != 11){
            this.erros = "O tamanho do CPF deve ser de 11 caracteres.";
            return resultado;
        };
        int cont1 = 10,cont2 = 11, soma1 = 0, soma2 = 0;
        int[] primeiroCalculoCpf = new int[9];
        int[] segundoCalculoCpf = new int[10];

        int[] cpf = new int[11];
        for(int i = 0; i < cpf.length; i++){
            cpf[i] = Integer.parseInt(this.cpf.substring(i, i+1));
            System.out.println(Integer.parseInt(this.cpf.substring(i, i+1)));
        }
        
        for(int i = 0; i < primeiroCalculoCpf.length; i++){
            primeiroCalculoCpf[i] = Integer.parseInt(this.cpf.substring(i, i+1));

            primeiroCalculoCpf[i] = primeiroCalculoCpf[i]*cont1;
            cont1--;
            
            soma1 = soma1 + primeiroCalculoCpf[i];
        }


        if((soma1 % 11 < 2) && (cpf[9] == 0)){
            validaPrimeiroDigito = true;
        }
        
        else if ((soma1 % 11 > 2) && ((cpf[9]) == (11 - soma1 % 11))){
            validaPrimeiroDigito = true;
        }

        for(int i = 0; i < segundoCalculoCpf.length; i++){
            segundoCalculoCpf[i] = Integer.parseInt(this.cpf.substring(i, i+1));

            segundoCalculoCpf[i] = segundoCalculoCpf[i]*cont2;
            cont2--;

            soma2 = soma2 + segundoCalculoCpf[i];
        }

        if((soma2 % 11 < 2) && (cpf[10] == 0)){
            validaSegundoDigito = true;
        }

        else if ((soma2 % 11 >2) && ((cpf[10]) == (11 - soma2 % 11))){
            validaSegundoDigito = true;
        }

        if(validaPrimeiroDigito && validaSegundoDigito){
            resultado = true;
        }
        if(!resultado){
            this.erros  = "CPF inválido.";
        }
        return resultado;
    }

    public void teste(){
        System.out.println("Aqui o erro: "+ this.erros);
        for(int i = 0; i < this.dadosUser.length; i++){
            System.out.println(this.dadosUser[i]);
        }
    }
    
        public void setData(){
        Connection conn = null;
        PreparedStatement st = null;
        LocalDate dateInicio = converte(this.inicio);
        LocalDate dateFim = converte(this.fim);
        try{
            conn = DB.getConnection();
            st = conn.prepareStatement(
            String.format("INSERT INTO %s"
                    +"(nome, sobrenome, cpf, contato, email, senha, genero, data_inicio, data_fim, projeto_id)"
                    +"VALUES"
                    +"(?,?,?,?,?,?,?,?,?,?)",this.cargo),Statement.RETURN_GENERATED_KEYS);
        st.setString(1, this.nome);
        st.setString(2,this.sobrenome);
        st.setString(3, this.cpf);
        st.setString(4, this.contato);
        st.setString(5, this.email);
        st.setString(6, this.senha);
        st.setString(7, this.genero);
        st.setDate(8, Date.valueOf(dateInicio));
        st.setDate(9, Date.valueOf(dateFim));
        st.setInt(10, this.projeto_id);
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        while(rs.next()){
                this.id = rs.getInt(1);
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
    
    public Object[] consultandoDB(){
        return consultarDB(this.cargo,this.cpf);
    }    
    
    public void delete(){
            Connection conn = null;
            PreparedStatement st = null;
        try{
            conn = DB.getConnection();
            st = conn.prepareStatement(
            String.format("DELETE FROM %s "
                    + "WHERE "
                    + "id = ? "
                    + "AND "
                    + "projeto_id = ? ",this.cargo)
            );
        st.setInt(1,this.id);
        st.setInt(2, this.projeto_id);
        st.executeUpdate();
        }
        catch(SQLException e){
            throw new DbIntegrityException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}

