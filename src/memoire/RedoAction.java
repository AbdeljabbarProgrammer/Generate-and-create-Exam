/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

/**
 *
 * @author abdeljabbar
 */
public class RedoAction extends AbstractAction
{    
    private final UndoAction undoAction;
    private final UndoManager undoManager;
  public RedoAction(UndoManager undoManager,UndoAction undoAction)
  {
    super("Redo");
    setEnabled(false);
    this.undoAction = undoAction;
    this.undoManager = undoManager;
  }

  public void actionPerformed(ActionEvent e)
  {
    try
    {
      undoManager.redo();
    }
    catch (CannotRedoException ex)
    {
    }
    update();
    undoAction.update();
  }

  protected void update()
  {
    if (undoManager.canRedo())
    {
      setEnabled(true);
      putValue(Action.NAME, undoManager.getRedoPresentationName());
    }
    else
    {
      setEnabled(false);
      putValue(Action.NAME, "Redo");
    }
  }

    
}
