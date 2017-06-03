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
public class Algorithem {

    static PreparedStatement prest = null;
    static ResultSet reset = null;
    static Connection con = null;
    static String deg;
    // static int par;
    // static String degFirst;
    // static int parFirst;
    private final ExamModel Exam;

    public Algorithem(ExamModel exam) {
        Exam = exam;
        con = ConnectionDB.OpenConnection();
    }

    public String[][] CreateArray(int NumberPartie, int NumberDegree) {
        String[][] arrayExo = new String[NumberPartie][NumberDegree];
        return arrayExo;
    }

    public boolean FillArray(int NumberPartie, int NumberDegree, String[][] arrayExo, ArrayList<String> list) throws SQLException {
        String exo_count_qeury = "select Count(*) from [DB_MEMIOR].[dbo].[Exercise] where IDEXM = ?";

        prest = con.prepareStatement(exo_count_qeury);
        prest.setInt(1, Exam.Id);
        reset = prest.executeQuery();

        if (reset.next()) {
            if (reset.getInt(1) == 0) {
                JOptionPane.showMessageDialog(null, "There is no Exercice Belongs to this Exam!");
 return false;
            } else if (reset.getInt(1) < NumberDegree * NumberPartie) {
                JOptionPane.showMessageDialog(null, "Please Complete all Exercies of the rest parties and degrees!");
 return false;
            }

           
        } else {
            return false;

        }
    

    // String [] Degree = new String[]{"Easy","Average","Difficult","very Difficult"};
    //arrayExo = new String[NumberPartie][NumberDegree];
    for (int i = 1; i <= NumberPartie ;i++) {
            for (int j = 1; j <= NumberDegree; j++) {

            try {

                String qeury = "select * from [DB_MEMIOR].[dbo].[Exercise] where DegEXO = ? and  PartEXO = ? and  IDEXM = ?";

                prest = con.prepareStatement(qeury);
                prest.setString(1, list.get(j - 1));
                prest.setInt(2, i);
                prest.setInt(3, Exam.Id);
                reset = prest.executeQuery();
                if (reset.next()) {
                    //if(reset.getInt(1)!= 0)
                    arrayExo[i - 1][j - 1] = reset.getString("ContentEXO");

                } else {
                    JOptionPane.showMessageDialog(null, "There is no Exercice Belongs to this Exam!");
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
                return false;
            }
        }
    }

return true;
    }

    
}
