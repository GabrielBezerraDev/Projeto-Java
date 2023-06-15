/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import db.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gabrielbezerra
 */
public class telaPrincipalController {
    
//    public void pegarUsuarios(){
//        String senha = null;
//        boolean user = true;
//        Connection conn = null;
//        Statement st = null;
//        ResultSet rs = null;
//        try{
//            conn = DB.getConnection();
//            st = conn.createStatement();
//            rs = st.executeQuery(String.format("select * from %s "
//                    + "where cpf = \"%s\"",cargo, cpf));
//            if(!rs.next()){
//                user = false;
//            }
//            else{
//                senha = rs.getString("senha");
//            }
//        }
//        catch(SQLException e){
//            e.printStackTrace();
//        }
//        finally{
//            DB.closeStatement(st);
//            DB.closeConnection();
//        }
//    }
}
