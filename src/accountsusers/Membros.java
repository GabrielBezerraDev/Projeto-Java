/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accountsusers;

/**
 *
 * @author gabrielbezerra
 */
public class Membros extends Users{
    public Membros(String nome, String sobrenome, String cpf, String contato, String email, String senha, String confirmarSenha, String genero, String inicio, String fim, int projeto_id){
        super(nome,sobrenome,cpf, contato, email,senha,confirmarSenha, genero, inicio, fim, "membro", projeto_id);
    }
}
