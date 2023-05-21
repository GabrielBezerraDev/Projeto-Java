/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accountsusers;
/**
 *
 * @author Jonathan
 */

abstract class Users {
    protected String nome, sobrenome, cpf, email, senha, confirmaSenha;
    
    protected Users (String nome, String sobrenome, String cpf, String email, String senha, String confirmaSenha){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.confirmaSenha = confirmaSenha;
    }

    public boolean validacao(){
        boolean resultado = false;
        if(validacaoSenha() && validacaoEmail()){
            resultado = true;
        }

        return resultado;
    }

    protected boolean validacaoSenha(){
        boolean resultado = false;
        if((this.senha != null && this.senha.length() > 4) && this.senha.equals(this.confirmaSenha)){
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

    protected boolean validacaoCpf(){
        boolean resultado = false, validaPrimeiroDigito = false, validaSegundoDigito = false;
        int cont1 = 10,cont2 = 11, soma1 = 0, soma2 = 0;
        int[] primeiroCalculoCpf = new int[9];
        int[] segundoCalculoCpf = new int[10];

        int[] cpf = new int[11];
        for(int i = 0; i < cpf.length; i++){
            cpf[i] = Integer.parseInt(this.cpf.substring(i, i+1));
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

        return resultado;
    }



}
