/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;
import accountsusers.*;
/**
 *
 * @author gabrielbezerra
 */
public interface Valida {
    default boolean validaCampus(String[] campos){
        boolean resultado = false;
        String campo = "";
        for(int i = 0; i < campos.length; i++){
            if(campos[i].isEmpty()){
                if(campos.length == 7){
                switch(i){
                     case 0: campo = "Nome"; break;
                     case 1: campo = "Sobrenome"; break;
                     case 2: campo = "cpf"; break;
                     case 3: campo = "contato"; break;
                     case 4: campo = "Email"; break;
                     case 5: campo = "Senha"; break;
                     case 6: campo = "Confirmar senha"; break;
                 }
                }
                else{
                switch(i){
                     case 0: campo = "Nome do Projeto"; break;
                     case 1: campo = "Descrição de projeto"; break;
                     case 2: campo = "Inicío do projeto"; break;
                     case 3: campo = "Fim do projeto"; break;
                }
             }
             Users.erros = String.format("O campo \"%s\" está faltando.",campo);
             System.out.println("Campo vazio");
             return resultado;
            }
        }
        resultado = true;
        return resultado;
}}


