/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 *
 * @author abdeljabbar
 */
public class UndoAction extends AbstractAction
{
    private UndoManager undoManager;
    private RedoAction redoAction;         
  public UndoAction(UndoManager undoManager, RedoAction redoAction)
  {
    super("Undo");
    setEnabled(false);
    this.undoManager = undoManager;
  }

  public void actionPerformed(ActionEvent e)
  {
    try
    {
      undoManager.undo();
    }
    catch (CannotUndoException ex)
    {
      // TODO deal with this
      //ex.printStackTrace();
    }
    update();
    redoAction.update();
  }

  protected void update()
  {
    if (undoManager.canUndo())
    {
      setEnabled(true);
      putValue(Action.NAME, undoManager.getUndoPresentationName());
    }
    else
    {
      setEnabled(false);
      putValue(Action.NAME, "Undo");
    }
  }
}