/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_de_gerenciamento;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Rafael
 */
public class BDConexao {
    
    public static Connection getConnection(){
    Connection con = null;
    
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con  = DriverManager.getConnection("jdbc:derby://localhost:1527/gestao_de_taxas","root","1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    
    } 
}
