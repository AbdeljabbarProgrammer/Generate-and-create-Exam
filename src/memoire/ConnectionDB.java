/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire;
import java.sql.*;
import javax.swing.JOptionPane;

public class ConnectionDB 
{
   
 



        public static Connection OpenConnection(/*String dbPath*/)
        {
            try
            {
                      
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        Connection Con = DriverManager.getConnection("jdbc:sqlserver:// DESKTOP-RUNV3A7\\SQLEXPRESS;Namedatabase =DB_MEMIOR;","sa","2017");
                       
                      
                       return Con;
            }

            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            return null;  
        }
         

}
 

