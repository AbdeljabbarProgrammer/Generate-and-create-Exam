/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire;


import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SizeRequirements;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.ParagraphView;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.InlineView;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.CompoundEdit;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;




/**
 *
 * @author abdeljabbar
 */
public final class TextEditorArea extends javax.swing.JEditorPane {

     public TextEditorArea()
    {
       super(); 
       //InitializeUndoRedo();
      
       setContentType("text/HTML");
       setMinimumSize(new Dimension(0, 0)); 
       setEditorKit(new HTMLEditorKit(){ 
           @Override 
           public ViewFactory getViewFactory(){ 
 
               return new HTMLFactory(){ 
                   @Override
                   public View create(Element e){ 
                      View v = super.create(e); 
                      if(v instanceof InlineView){ 
                          return new InlineView(e){ 
                              @Override
                              public int getBreakWeight(int axis, float pos, float len) { 
                                  return GoodBreakWeight; 
                              } 
                              @Override
                              public View breakView(int axis, int p0, float pos, float len) { 
                                  if(axis == View.X_AXIS) { 
                                      checkPainter(); 
                                      int p1 = getGlyphPainter().getBoundedPosition(this, p0, pos, len); 
                                      if(p0 == getStartOffset() && p1 == getEndOffset()) { 
                                          return this; 
                                      } 
                                      return createFragment(p0, p1); 
                                  } 
                                  return this; 
                                } 
                            }; 
                      } 
                      else if (v instanceof ParagraphView) { 
                          return new ParagraphView(e) { 
                              @Override
                              protected SizeRequirements calculateMinorAxisRequirements(int axis, SizeRequirements r) { 
                                  if (r == null) { 
                                        r = new SizeRequirements(); 
                                  } 
                                  float pref = layoutPool.getPreferredSpan(axis); 
                                  float min = layoutPool.getMinimumSpan(axis); 
                                  // Don't include insets, Box.getXXXSpan will include them. 
                                    r.minimum = (int)min; 
                                    r.preferred = Math.max(r.minimum, (int) pref); 
                                    r.maximum = Integer.MAX_VALUE; 
                                    r.alignment = 0.5f; 
                                  return r; 
                                } 
 
                            }; 
                        } 
                      return v; 
                    } 
                }; 
            } 
        }); 
        
        
        //setText("<HTML>   <h1>Mise en forme par les styles</h1> <h2>un premier petite exemple</h2> <p claas=\"rouge\">un <i>texte <b>quelconque </b><b>de</b> petit</i> taile</p> <h2>un premier petite exemple2</h2> <p class=\"bleu\">un texte quelconque de petit taile un texte quelconque de petit taile</p>  <HTML/>");

    }
    /*public TextEditorArea() 
    {
     super();
    InitializeUndoRedo();
    }*/
    /* public void InitializeUndoRedo1(){
       
        getDocument().addUndoableEditListener(undoHandler);
     }*/
     
   
   public void InitializeUndoRedo(){
    /*   editorPaneDocument = getDocument();
editorPaneDocument.addUndoableEditListener(undoHandler);

        KeyStroke undoKeystroke = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.META_MASK);
KeyStroke redoKeystroke = KeyStroke.getKeyStroke(KeyEvent.VK_Y, Event.META_MASK);
undoManager = new UndoManager();*/
/*undoAction = new UndoAction(undoManager,redoAction);
getInputMap().put(undoKeystroke, "undoKeystroke");
getActionMap().put("undoKeystroke", undoAction);*/

/*redoAction = new RedoAction(undoManager,undoAction);
getInputMap().put(redoKeystroke, "redoKeystroke");
getActionMap().put("redoKeystroke", redoAction);*/

         setEditorKit(new StyledEditorKit());
        
        //undoManager.refreshControls();
    }

  public void Undo() {
      //  undoManager.undo();
    }    
    public void Redo() {
      //  undoManager.redo();
    }
  /* class MyCompoundEdit extends CompoundEdit {
        boolean isUnDone=false;
        public int getLength() {
            return edits.size();
        }
 
        public void undo() throws CannotUndoException {
            super.undo();
            isUnDone=true;
        }
        public void redo() throws CannotUndoException {
            super.redo();
            isUnDone=false;
        }
        public boolean canUndo() {
            return edits.size()>0 && !isUnDone;
        }

        public boolean canRedo() {
            return edits.size()>0 && isUnDone;
        }
 
    }
    class UndoManager extends AbstractUndoableEdit implements UndoableEditListener {
        String lastEditName=null;
        ArrayList<MyCompoundEdit> edits=new ArrayList<MyCompoundEdit>();
        MyCompoundEdit current;
        int pointer=-1;
 
        public void undoableEditHappened(UndoableEditEvent e) {
            UndoableEdit edit=e.getEdit();
            if (edit instanceof AbstractDocument.DefaultDocumentEvent) {
                try {
                    AbstractDocument.DefaultDocumentEvent event=(AbstractDocument.DefaultDocumentEvent)edit;
                    int start=event.getOffset();
                    int len=event.getLength();
                    String text=event.getDocument().getText(start, len);
                    boolean isNeedStart=false;
                    if (current==null) {
                        isNeedStart=true;
                    }
                    else if (text.contains("\n")) {
                        isNeedStart=true;
                    }
                    else if (lastEditName==null || !lastEditName.equals(edit.getPresentationName())) {
                        isNeedStart=true;
                    }
 
                    while (pointer<edits.size()-1) {
                        edits.remove(edits.size()-1);
                        isNeedStart=true;
                    }
                    if (isNeedStart) {
                        createCompoundEdit();
                    }
 
                    current.addEdit(edit);
                    lastEditName=edit.getPresentationName();
 
                    refreshControls();
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }
        }
 
        public void createCompoundEdit() {
            if (current==null) {
                current= new MyCompoundEdit();
            }
            else if (current.getLength()>0) {
                current= new MyCompoundEdit();
            }
 
            edits.add(current);
            pointer++;
        }
 
        public void undo() throws CannotUndoException {
            if (!canUndo()) {
                throw new CannotUndoException();
            }
 
            MyCompoundEdit u=edits.get(pointer);
            u.undo();
            pointer--;
 
            refreshControls();
        }
 
        public void redo() throws CannotUndoException {
            if (!canRedo()) {
                throw new CannotUndoException();
            }
 
            pointer++;
            MyCompoundEdit u=edits.get(pointer);
            u.redo();
 
            refreshControls();
        }
 
        public boolean canUndo() {
            return pointer>=0;
        }

        public boolean canRedo() {
            return edits.size()>0 && pointer<edits.size()-1;
        }
 
        public void refreshControls() {
            btnUndo.setEnabled(canUndo());
            btnRedo.setEnabled(canRedo());
        }
    }
  */
}
