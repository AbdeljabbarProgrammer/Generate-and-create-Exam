/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire;

import java.sql.Date;
import javax.swing.table.TableModel;

/**
 *
 * @author abdeljabbar
 */
public class ExamModel {

    int Id;
    String Name;
    int PartsCount;
    String ModuleName;

    private int rowIndex;
    private TableModel model;
    public Date Date;

    public ExamModel(int rowIndex, TableModel model) {
        this.rowIndex = rowIndex;
        this.model = model;

        Id = Integer.valueOf(model.getValueAt(rowIndex, 0).toString());
        Name = model.getValueAt(rowIndex, 1).toString();
        PartsCount = Integer.valueOf(model.getValueAt(rowIndex, 5).toString());
        ModuleName = model.getValueAt(rowIndex, 3).toString();
        Date = Date.valueOf(model.getValueAt(rowIndex, 2).toString());
    }

    public String getName() {
        return Name;
    }
    
     public String getModuleName() {
        return ModuleName;
    }
     
       public int getId() {
        return Id;
    }
       
          public int getPartsCount() {
        return PartsCount;
    }

}
