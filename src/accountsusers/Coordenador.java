/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accountsusers;
import java.util.ArrayList;
import java.util.List;
import accountsusers.Membros;
/**
 *
 * @author gabrielbezerra
 */

public class Coordenador {
    List<Membros> membros = new ArrayList<>();

   
    public void criarMembros() {
        for(int i = 0; i < 3 /*membros.size()*/; i++){
           membros.add(new Membros());
        }
    }
}
