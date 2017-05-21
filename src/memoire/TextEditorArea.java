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
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SizeRequirements;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.ParagraphView;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.InlineView;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import memoire.UndoRedo.RedoAction;
import memoire.UndoRedo.UndoAction;
import memoire.UndoRedo.UndoHandler;

/**
 *
 * @author abdeljabbar
 */
public final class TextEditorArea extends javax.swing.JEditorPane{
    UndoManager UndoManager ;
Document editorPaneDocument;
protected UndoHandler undoHandler = new UndoHandler();
protected UndoManager undoManager = new UndoManager();
 UndoAction undoAction = new UndoAction();
 RedoAction redoAction = new RedoAction();
KeyStroke undoKeystroke = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.META_MASK);
KeyStroke redoKeystroke = KeyStroke.getKeyStroke(KeyEvent.VK_Y, Event.META_MASK);
class UndoHandler implements UndoableEditListener
{

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

class UndoAction extends AbstractAction
{
  public UndoAction()
  {
    super("Undo");
    setEnabled(false);
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

       // @Override
        /*public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }*/
    }

class RedoAction extends AbstractAction
{
  public RedoAction()
  {
    super("Redo");
    setEnabled(false);
  }

  public void actionPerformed(ActionEvent e)
  {
    try
    {
      undoManager.redo();
    }
    catch (CannotRedoException ex)
    {
      // TODO deal with this
      ex.printStackTrace();
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
     public TextEditorArea()
    {
       super(); 
        //JTextPane textPane = new JTextPane();
        
        //this.setAutoscrolls(true);
       // InitializeUndoRedo();
        //new JScrollPane(this);
        this.getInputMap().put(undoKeystroke, "undoKeystroke");
        this.getActionMap().put("undoKeystroke", undoAction);

//redoAction = new RedoAction();
        this.getInputMap().put(redoKeystroke, "redoKeystroke");
        this.getActionMap().put("redoKeystroke", redoAction);
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
     public void InitializeUndoRedo1(){
       editorPaneDocument= this.getDocument();
        editorPaneDocument.addUndoableEditListener(undoHandler);
     }
    
   /*public void InitializeUndoRedo(){
     UndoManager = new UndoManager();
        getDocument().addUndoableEditListener((UndoableEditEvent e) -> {
            //UndoManager.addEdit(e.getEdit());
     });
    }

    void Undo() {
        UndoManager.undo();
    }    
    void Redo() {
        UndoManager.redo();
    }
    public void underline()
    {
        this.requestDefaultFocus();
    }*/
  
  

//undoAction = new UndoAction();

}
