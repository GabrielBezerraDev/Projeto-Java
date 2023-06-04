/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accountsusers;
import interfaces.Valida;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Jonathan
 */

public class Users implements Valida{
    protected String nome, sobrenome, cpf, contato, email, senha, confirmaSenha, genero, inicio, fim;
    protected String[] dadosUser;
    public static String erros;
    protected Users (String nome, String sobrenome, String cpf, String contato, String email, String senha, String confirmaSenha, String genero, String inicio, String fim){
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
    }

    public boolean validacao(){
        boolean resultado = false;
        if(!validaCampus(this.dadosUser)) return resultado;
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
    
//    public boolean validacaoCampos(){
//        String campo = "";
//        boolean resultado = false;
//         for(int i = 0; i < this.dadosUser.length; i++){
//             if(this.dadosUser[i].isEmpty()) {
//                 switch(i){
//                     case 0: campo = "Nome"; break;
//                     case 1: campo = "Sobrenome"; break;
//                     case 2: campo = "cpf"; break;
//                     case 3: campo = "Email"; break;
//                     case 4: campo = "Senha"; break;
//                     case 5: campo = "Confirmar senha"; break;
//                 }
//                 this.erros = String.format("O campo \"%s\" está faltando.",campo);
//                 System.out.println("Campo vazio");
//                 break;
//             }
//             if(!this.dadosUser[i].isEmpty() && i == this.dadosUser.length-1) resultado = true;
//         }
//         return resultado;
//    }
    
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
            this.erros = "As senhas são diferentes.";
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
        if(this.cpf.length() != 11) return resultado;
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
            this.erros = "CPF inválido.";
        }
        return resultado;
    }



}
