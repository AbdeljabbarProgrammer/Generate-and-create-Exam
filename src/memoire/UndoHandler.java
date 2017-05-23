/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

/**
 *
 * @author abdeljabbar
 */
public class UndoHandler implements UndoableEditListener
{
  private final UndoManager undoManager;
  private UndoAction undoAction;
  private RedoAction redoAction;
    public UndoHandler(UndoManager undoManager, UndoAction undoAction, RedoAction redoAction){
    
        super();
        this.undoManager = undoManager;
    }
  /**
   * Messaged when the Document has created an edit, the edit is added to
   * <code>undoManager</code>, an instance of UndoManager.
   */
  public void undoableEditHappened(UndoableEditEvent e)
  {
    undoManager.addEdit(e.getEdit());
    undoAction.update();
    redoAction.update();
  }

}
