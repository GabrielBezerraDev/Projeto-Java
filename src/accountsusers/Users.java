/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accountsusers;
import java.util.Arrays;


/**
 *
 * @author Jonathan
 */
public class Users {
    private String nome, sobreNome, cpf, email, senha, confirmaSenha;
    public Users (String nome, String sobreNome, String cpf, String email, String senha, String confirmaSenha){
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.confirmaSenha = confirmaSenha;
    }

    protected boolean validacao(){
        boolean resultado = false;
        if(validacaoSenha() && validacaoEmail() && validacaoCpf()){
            resultado = true;
        }

        return resultado;
    }

    protected boolean validacaoSenha(){
        boolean resultado = false;
        if(this.senha != null && this.senha.length() > 4 && this.senha == this.confirmaSenha){
            resultado = true;
        }
        return resultado;
    }

    protected boolean validacaoEmail(){
        boolean resultado = false;
        if(this.email != null && this.email.contains("@") && this.email.contains(".")){
                    resultado = true;
                }
        return resultado;
    }

    public boolean validacaoCpf(){
        boolean resultado = false;
        int[] primeiroCalculoCpf = new int[9];
        for(int i = 0; i < primeiroCalculoCpf.length; i++){
            primeiroCalculoCpf[i] = Integer.parseInt(this.cpf.substring(i, i+1));
        }
        return resultado;
    }



}
