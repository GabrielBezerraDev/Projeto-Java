/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;
import accountsusers.*;
import project.Project;
/**
 *
 * @author gabrielbezerra
 */
public interface Valida {
    default Object[] validaCampus(String[] campos){
        boolean resultado = false;
        Object[] resposta = new Object[2];
        String campo = "";
        for(int i = 0; i < campos.length; i++){
            if(campos[i] == null || campos[i].isEmpty()){
                if(campos.length == 10){
                switch(i){
                     case 0: campo = "Nome"; break;
                     case 1: campo = "Sobrenome"; break;
                     case 2: campo = "cpf"; break;
                     case 3: campo = "contato"; break;
                     case 4: campo = "Email"; break;
                     case 5: campo = "Senha"; break;
                     case 6: campo = "Confirmar senha"; break;
                     case 7: campo = "Genero"; break;
                     case 8: campo = "Início gerenciamento"; break;
                     case 9: campo = "Termino gerenciamento"; break;
                 }
                    //userErro = String.format("O campo \"%s\" está faltando.",campo);
                    resposta[1] = String.format("O campo \"%s\" está faltando.",campo);
                }
                else{
                switch(i){
                     case 0: campo = "Nome do Projeto"; break;
                     case 1: campo = "Descrição de projeto"; break; 
                     case 2: campo = "Inicío do projeto"; break;
                     case 3: campo = "Fim do projeto"; break;
                }
                resposta[1] = String.format("O campo \"%s\" está faltando.",campo);
             }
             System.out.println("Campo vazio");
             resposta[0] = resultado;
             return resposta;
            }
        }
        resultado = true;
        resposta[0] = resultado;
        return resposta;
}
    
//    default String error(){
//        return error[0];
//    }
}


