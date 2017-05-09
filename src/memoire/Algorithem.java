/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire;

import static com.graphbuilder.math.PascalsTriangle.reset;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.StringReader;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.Clock.system;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author abdeljabbar
 */
public class Algorithem
{
    static PreparedStatement prest = null;
    static ResultSet reset = null;
    static Connection con = null;
    static String deg;
   // static int par;
   // static String degFirst;
   // static int parFirst;
    public  Algorithem()
    {
        con = ConnectionDB.OpenConnection();
    }        
    public String[][] CreateArray(int NumberPartie,int NumberDegree)
    {
          String  [][]arrayExo = new String[NumberPartie][NumberDegree];
         return arrayExo ;
    }
public  void FillArray(String NameModule,int IDUser,int NumberPartie,int NumberDegree,String[][]arrayExo) throws SQLException
{
     
     String [] Degree = new String[]{"Easy","very easy","Average","Difficult"};
    //arrayExo = new String[NumberPartie][NumberDegree];
     
     for(int i=1;i<=NumberPartie;i++)
     {
         for(int j=1;j<=NumberDegree;j++)
            {
            
             try{
                 
                    String qeury = "select * from [DB_MEMIOR].[dbo].[Exercics] where DegDiff = ? and  Partie = ? and NameMod =? and UseID = ?";
           
                    prest = con.prepareStatement(qeury);
                    prest.setString(1,Degree[j-1]);
                    prest.setInt(2,i);
                    prest.setString(3,NameModule);
                    prest.setInt(4,IDUser);
                    reset = prest.executeQuery();
                        if (reset.next()) 
                        {
                            if(reset.getInt(1)!= 0)
                                arrayExo[i-1][j-1] =reset.getString("TextExo");
               
                        } 
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
     }
    
}
 public static void main(String[] args) throws SQLException {
        // TODO code application logic here
       //ConnectionDB.OpenConnection();
     Algorithem lo = new Algorithem();
        String  array[][]=new String[4][4];
       for(int i=0;i<4;i++)
       {
           for(int j=0;j<4;j++)
           {
               array[i][j]="0";
              System.out.print(array[i][j]);
           }
       }
       lo.FillArray("informatique",1,4,4,array);
       for(int i=0;i<4;i++)
       {
           for(int j=0;j<4;j++)
           {
               String s=array[i][j];
              System.out.print(s);
           }
       }
    }
}

