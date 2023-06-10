/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.sql.Connection;
import db.DB;
/**
 *
 * @author gabrielbezerra
 */
public class testeBanco {
    public static void main(String[] args){
        Connection conn = DB.getConnection();
        DB.closeConnection();
    }
}
