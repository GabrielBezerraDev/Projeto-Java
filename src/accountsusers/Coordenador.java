/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accountsusers;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author gabrielbezerra
 */

public class Coordenador extends Users{
    public List<Membros> membros;
    
    public Coordenador(String nome, String sobrenome, String cpf, String contato, String email, String senha, String confirmaSenha, String genero, String inicio, String fim, int projeto_id){
        super(nome,sobrenome,cpf, contato, email,senha,confirmaSenha, genero, inicio, fim, "coordenador",projeto_id);
        this.membros = new ArrayList<>();
    }
   
    public void showCoordenador(){
        System.out.printf("Nome: %s%nSobrenome: %s%nCPF: %s%nEmail: %s%nSenha: %s%nConfirmar Senha: %s", this.nome, this.sobrenome, this.cpf, this.email, this.senha, this.confirmaSenha);
    }
    
//    public void criarMembros(String nome, String sobrenome, String cpf,String email, String senha, String confirmarSenha) {
//          System.out.printf("Nome: %s%nSobrenome: %s%nCPF: %s%nEmail: %s%nSenha: %s%nConfirmar Senha: %s", nome, sobrenome, cpf, email, senha, confirmarSenha);
//          this.membros.add(new Membros(nome, sobrenome, cpf, email, senha, confirmarSenha));
//    }
}
