/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class UndoRedo {
private Document editorPaneDocument;
protected UndoHandler undoHandler = new UndoHandler();
protected UndoManager undoManager = new UndoManager();
private UndoAction undoAction = null;
private RedoAction redoAction = null;
    class UndoHandler implements UndoableEditListener {
    
    public void undoableEditHappened(UndoableEditEvent e) {
        undoManager.addEdit(e.getEdit());
         undoAction().update();
        redoAction.update();
    }
}

class UndoAction extends AbstractAction {
    public UndoAction() {
        super("Undo");
        setEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            undoManager.Undo();
        } catch (CannotUndoException ex) {
            ex.printStackTrace();
        }

        update();
        redoAction.update();
    }

    protected void update() {
        if (undoManager.canUndo()) {
            setEnabled(true);
            putValue(Action.NAME, undoManager.getUndoPresentationName());
        } else {
            setEnabled(false);
            putValue(Action.NAME, "Undo");
        }
    }
}

class RedoAction extends AbstractAction {
    public RedoAction() {
        super("Redo");
        setEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            undoManager.redo();
        } catch (CannotRedoException ex) {
            ex.printStackTrace();
        }

        update();
        undoAction.update();
    }

    protected void update() {
        if (undoManager.canRedo()) {
            setEnabled(true);
            putValue(Action.NAME, undoManager.getRedoPresentationName());
        } else {
            setEnabled(false);
            putValue(Action.NAME, "Redo");
        }
    }

        private static class update {

            public update() {
            }
        }
    }
    
}
