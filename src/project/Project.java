/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.util.List;
import java.util.ArrayList;
import accountsusers.*;

public class Project {
    public List<Coordenador> coordenador;
    public List<Membros> membros;
    
    public Project(){
        this.coordenador = new ArrayList<>();
        this.membros = new ArrayList<>();
    }
}

