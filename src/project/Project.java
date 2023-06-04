/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.util.List;
import java.util.ArrayList;
import accountsusers.*;
import interfaces.Valida;

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
    
    public void showDataProjetc(){
        System.out.printf("%nNome do Projeto: %s%nDescrição do Projeto: %s%n",this.nomeProjeto, this.descricaoProjeto);
    }
}

