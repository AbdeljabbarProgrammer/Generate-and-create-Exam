/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import javax.swing.text.Element;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import javax.swing.undo.*;
import javax.swing.text.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.*;
import java.awt.Choice;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.image.ImageProducer;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.sql.Array;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout.Group;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.StyledEditorKit.UnderlineAction;
import com.itextpdf.text.Paragraph;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.html.HtmlTags;
import com.lowagie.text.pdf.ColumnText;
import java.awt.Dimension;
import java.awt.Scrollbar;
import static java.time.Clock.system;
import javafx.scene.control.ScrollBar;
import javax.swing.JEditorPane;
import org.jsoup.Jsoup;




/**
 *
 * @author abdeljabbar
 */
public class Exercises extends javax.swing.JFrame {
    
     ArrayList<String> listExo = new ArrayList();
     ArrayList<String> listnameFile = new ArrayList();
     int NbrPartie;
    int idextad;
    String idexdeg ="";
    String NameModule;
    int idexpar;
    String idexmodule;
    ButtonGroup groupB =null;
    Choice degch= new Choice();
    Choice degsave= new Choice();
    int item=0;
    String Exoarray[][];
    String []listItem ={"Easy", "very easy", "Average", "Difficult"};
    ComboBoxModel comboBox=null;
    enum BulletActionType {INSERT, REMOVE};
    enum NumbersActionType {INSERT, REMOVE};
    private static final char BULLET_CHAR = '\u2022';
    private static final String BULLET_STR = new String(new char [] {BULLET_CHAR});
    private static final String NUMBERS_ATTR = "NUMBERS";
    private static final String BULLET_STR_WITH_SPACE = BULLET_STR + " ";
    private static final int BULLET_LENGTH = BULLET_STR_WITH_SPACE.length();
    private static final String ELEM = AbstractDocument.ElementNameAttribute;
    private static final String COMP = StyleConstants.ComponentElementName;
    Boolean bool=true;
    private String pictureButtonName;
    public static ArrayList<TextEditorArea > list = new ArrayList<TextEditorArea >();
//final  ArrayList<java.awt.Font> FontList = new ArrayList<java.awt.Font>();
    UndoManager manager;
    final JPopupMenu popup;
    PreparedStatement presta = null;
    ResultSet reset = null;
    Connection con = null;
     PreparedStatement prestInt = null;
    ResultSet resetInt = null;
    Connection conInt = null;
    static int indexArea;

    public Exercises() 
    {
        initComponents();
        Exoarray = new  String[4][4];
        
        for(int i=0;i<4;i++)
       {
           for(int j=0;j<4;j++)
           {
               Exoarray[i][j]="0";
              
           }
       }
         for(int i=0;i<24;i++)
         {
             listnameFile.add(i,String.valueOf(i));
           
         }
        //jTabbedPane1.setAutoscrolls(true);
       //list.get(jTabbedPane1.getSelectedIndex()).setLineWrap(true);
        groupB = new ButtonGroup();
        groupB.add(EditExo);
        groupB.add(EditInt);
        comboBox=listDeg.getModel();
        jButton5.addActionListener(new BulletActionListener(BulletActionType.INSERT));
        jButton6.addActionListener(new BulletActionListener(BulletActionType.REMOVE));
       
        jButton7.addActionListener(new NumbersActionListener(NumbersActionType.INSERT));
        jButton10.addActionListener(new NumbersActionListener(NumbersActionType.REMOVE));
        this.manager = new UndoManager();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("NEW DOCUMENT");
        conInt=con = ConnectionDB.OpenConnection();
        
        File.setMnemonic(KeyEvent.VK_F);
        New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, ActionEvent.CTRL_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        edit.setMnemonic(KeyEvent.VK_F);
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        sellect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        Format.setMnemonic(KeyEvent.VK_O);
        NewButton.setToolTipText("new document");
        SaveButton.setToolTipText("Save document");
        OpenButton.setToolTipText("Open document");
      
        CutButton.setToolTipText("cut text");
        CopyButton.setToolTipText("Copy text");
        jButton8.setToolTipText("Paste text");
        jButton9.setToolTipText("sellect All");
        popup = new JPopupMenu();
        JMenuItem cut = new JMenuItem("Cut");
        popup.add(cut);
        JMenuItem Copy = new JMenuItem("Copy");
        popup.add(Copy);
        JMenuItem Paste = new JMenuItem("Paste");
        popup.add(Paste);
        popup.addSeparator();
        JMenuItem Select = new JMenuItem("Select All");
        popup.add(Select);

    }
class CStyleDocument extends DefaultStyledDocument
{
    private  Style primaryStyle;

    public CStyleDocument() {
        super();
         primaryStyle = this.addStyle("Primary", null);
    }
    public Style getAttrStyle()
    {
        return primaryStyle;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        super.insertString(offs, str, primaryStyle); 

    }

}
CStyleDocument styleDocument;
    protected void UpdateButtons() {
        
    }

    /*  public void fun(){list.get(jTabbedPane1.getSelectedIndex()).getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                    public void undoableEditHappened(UndoableEditEvent e) {
                        manager.addEdit(e.getEdit());}});}
  public void textArea()
    {
        list.get(jTabbedPane1.getSelectedIndex()).setLineWrap(true);
        list.get(jTabbedPane1.getSelectedIndex()).setWrapStyleWord(true);
        list.get(jTabbedPane1.getSelectedIndex()).addMouseListener(new MouseAdapter() {
       
        public void mouseRelease(MouseEvent e)
        {
            if(SwingUtilities.isRightMouseButton(e))
            {
                if(manager.canRedo())
                {
                    redo.setEnabled(true);
                }
                else
                {
                    redo.setEnabled(false);
                }
                if(manager.canUndo())
                {
                    undo.setEnabled(true);
                }
                else
                {
                    undo.setEnabled(false);
                }
            }
        }
});  
    }*/

    public void MethodCut() {
        TextEditorArea target = list.get(jTabbedPane1.getSelectedIndex());
        int startPos = target.getSelectionStart();
        int endPos = target.getSelectionEnd();
        String text = target.getText();
        target.setText(text.substring(0, startPos) + text.substring(endPos));
    }

    public final void addEditorTextTab(String title) 
    {
        TextEditorArea T = new TextEditorArea();
        JScrollPane Scroll = new JScrollPane(T);
        list.add(T);
        Scroll.setHorizontalScrollBar(null);
        jTabbedPane1.addTab(title, Scroll);        
    }

    public void removeSelectedTextEditorTab() {
        int selectedTabIndex = jTabbedPane1.getSelectedIndex();
        jTabbedPane1.removeTabAt(selectedTabIndex);
        list.remove(selectedTabIndex);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jToolBar1 = new javax.swing.JToolBar();
        NewButton = new javax.swing.JButton();
        OpenButton = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();
        CutButton = new javax.swing.JButton();
        CopyButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jToolBar3 = new javax.swing.JToolBar();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        textAlignComboBox = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        EditExo = new javax.swing.JRadioButton();
        EditInt = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        NumPar = new javax.swing.JTextField();
        listDeg = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        nameM = new javax.swing.JTextField();
        MenuBar = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        New = new javax.swing.JMenuItem();
        open = new javax.swing.JMenuItem();
        close = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        save = new javax.swing.JMenuItem();
        saveas = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exit = new javax.swing.JMenuItem();
        edit = new javax.swing.JMenu();
        undo = new javax.swing.JMenuItem();
        redo = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        cut = new javax.swing.JMenuItem();
        copy = new javax.swing.JMenuItem();
        paste = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        sellect = new javax.swing.JMenuItem();
        Format = new javax.swing.JMenu();
        wtext = new javax.swing.JCheckBoxMenuItem();
        Font = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        jMenu5.setText("jMenu5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        NewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoire/add_document_2.png"))); // NOI18N
        NewButton.setFocusable(false);
        NewButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        NewButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        NewButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NewButtonMousePressed(evt);
            }
        });
        NewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(NewButton);

        OpenButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoire/1490436849_Open.png"))); // NOI18N
        OpenButton.setFocusable(false);
        OpenButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        OpenButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        OpenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(OpenButton);

        SaveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoire/disk_save.png"))); // NOI18N
        SaveButton.setFocusable(false);
        SaveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SaveButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(SaveButton);

        CutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoire/cut.png"))); // NOI18N
        CutButton.setFocusable(false);
        CutButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CutButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CutButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(CutButton);

        CopyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoire/1490438295_copy.png"))); // NOI18N
        CopyButton.setFocusable(false);
        CopyButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CopyButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CopyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CopyButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(CopyButton);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoire/18191569_1462308327175694_2058891131_n.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoire/_Paste.png"))); // NOI18N
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);

        jButton9.setBackground(new java.awt.Color(51, 255, 51));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoire/Select All-32.png"))); // NOI18N
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton9);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoire/1491732493_pdfs.png"))); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton11);

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoire/1493869111_List.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoire/1493869197_image.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jToolBar3.setRollover(true);

        jButton3.setText("Picture Delete");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton3);

        jButton6.setText("Bulltes Remove");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton6);

        jButton7.setText("NumberInsert");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton7);

        jButton10.setText("NumberRemmove");
        jToolBar3.add(jButton10);

        textAlignComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TextAlign", "Left", "Center", "Justified" }));
        textAlignComboBox.setMaximumSize(new java.awt.Dimension(100, 50));
        textAlignComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                textAlignComboBoxItemStateChanged(evt);
            }
        });
        textAlignComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAlignComboBoxActionPerformed(evt);
            }
        });
        jToolBar3.add(textAlignComboBox);

        jButton1.setText("UnderLine");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton1);

        EditExo.setText("Edit Exercises");
        EditExo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditExoActionPerformed(evt);
            }
        });

        EditInt.setText("Edit Intertete");
        EditInt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditIntActionPerformed(evt);
            }
        });

        jLabel1.setText("Degree of difficulty");

        jLabel2.setText("part");

        NumPar.setToolTipText("");
        NumPar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumParActionPerformed(evt);
            }
        });

        listDeg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Easy", "very easy", "Average", "Difficult" }));
        listDeg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                listDegItemStateChanged(evt);
            }
        });
        listDeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listDegActionPerformed(evt);
            }
        });

        jLabel4.setText("   NameModule");

        nameM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EditExo)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(EditInt, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(NumPar, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(listDeg, javax.swing.GroupLayout.Alignment.LEADING, 0, 144, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EditExo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EditInt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(NumPar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(listDeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameM, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(179, Short.MAX_VALUE))
        );

        File.setText("File");
        File.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileActionPerformed(evt);
            }
        });

        New.setText("New");
        New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewActionPerformed(evt);
            }
        });
        File.add(New);

        open.setText("Open");
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });
        File.add(open);

        close.setText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });
        File.add(close);
        File.add(jSeparator1);

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        File.add(save);

        saveas.setText("Save As");
        saveas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveasActionPerformed(evt);
            }
        });
        File.add(saveas);
        File.add(jSeparator2);

        exit.setText("Exit");
        File.add(exit);

        MenuBar.add(File);

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        undo.setText("Undo");
        undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoActionPerformed(evt);
            }
        });
        edit.add(undo);

        redo.setText("Redo");
        redo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoActionPerformed(evt);
            }
        });
        edit.add(redo);
        edit.add(jSeparator3);

        cut.setText("Cut");
        cut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutActionPerformed(evt);
            }
        });
        edit.add(cut);

        copy.setText("Copy");
        copy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyActionPerformed(evt);
            }
        });
        edit.add(copy);

        paste.setText("Paste");
        paste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteActionPerformed(evt);
            }
        });
        edit.add(paste);
        edit.add(jSeparator4);

        sellect.setText("Sellect All");
        sellect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellectActionPerformed(evt);
            }
        });
        edit.add(sellect);

        MenuBar.add(edit);

        Format.setText("Format");
        Format.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                FormatMenuSelected(evt);
            }
        });

        wtext.setText("Warp text");
        wtext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wtextActionPerformed(evt);
            }
        });
        Format.add(wtext);

        Font.setText("Font");
        Font.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FontMouseClicked(evt);
            }
        });
        Font.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FontActionPerformed(evt);
            }
        });
        Format.add(Font);

        MenuBar.add(Format);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(634, 634, 634)
                        .addComponent(jLabel3)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(472, 472, 472))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewActionPerformed
        try {
            // TODO add your handling code here:
            addEditorTextTab("New Exercise ");
        } catch (Exception ex) {
            Logger.getLogger(Exercises.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_NewActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        // TODO add your handling code here:
        removeSelectedTextEditorTab();
    }//GEN-LAST:event_closeActionPerformed

    private void saveasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveasActionPerformed

    private void NewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewButtonActionPerformed
        try {
            // TODO add your handling code here:
            addEditorTextTab("New Exercise");
        } catch (Exception ex) {

        }


    }//GEN-LAST:event_NewButtonActionPerformed

    private void FileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FileActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_FileActionPerformed

    private void NewButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewButtonMousePressed
        // TODO add your handling code here:


    }//GEN-LAST:event_NewButtonMousePressed

    private void OpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenButtonActionPerformed
        // TODO add your handling code here:
        Filechoose filech = new Filechoose();
        filech.getFileChooser1().showOpenDialog(this);
        File f = filech.getFileChooser1().getSelectedFile();
        String filename = f.getAbsolutePath();

        try {
            FileReader reader = new FileReader(filename);
            BufferedReader br = new BufferedReader(reader);
            //javax.swing.JTextArea t = new javax.swing.JTextArea ();
           
               TextEditorArea  FileText    = new TextEditorArea();
            FileText.read(br, null);
            jTabbedPane1.addTab("Open File",FileText);
            br.close();
            FileText  .requestFocus();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_OpenButtonActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        // TODO add your handling code here

        try {
             if(EditExo.isSelected())
             {
                 
                    int par = Integer.valueOf(NumPar.getText());
                    String name =nameM.getText();
                    if(par==idexpar&& name.equals(idexmodule)&& listDeg.getSelectedItem().toString().equals(idexdeg)&&jTabbedPane1.getSelectedIndex()==idextad)
                    {
                        String query = "UPDATE [DB_MEMIOR].[dbo].[Exercics] SET TextEXO=?,DegDiff=?,Partie=?,NameMod=?";
                        presta = con.prepareStatement(query);
                        presta.setString(1, list.get(jTabbedPane1.getSelectedIndex()).getText());
                        presta.setString(2, listDeg.getSelectedItem().toString());
                        presta.setInt(3, par);
                        presta.setString(4, name);
                        presta.execute(); 
                    }
                    else
                    {
                        
                       
                        String query = "INSERT INTO [DB_MEMIOR].[dbo].[Exercics] (TextEXO,DegDiff,Partie,NameMod,UseID)values (?,?,?,?,?)";
                        presta = con.prepareStatement(query);
                        presta.setString(1, list.get(jTabbedPane1.getSelectedIndex()).getText());
                        presta.setString(2, listDeg.getSelectedItem().toString());
                        presta.setInt(3, par);
                        presta.setString(4, name);
                        presta.setInt(5,1/*Login.id_user*/);
                        presta.execute();
                    }
                    idexdeg =listDeg.getSelectedItem().toString();
                    idextad =jTabbedPane1.getSelectedIndex();
                    idexpar=par;
                    idexmodule=name;
             }
            else if(EditInt.isSelected())
                     {
                            
                            String qeury = "select Count(*) from [DB_MEMIOR].[dbo].[Intertete] where NameMod = ?";
           
                            presta = con.prepareStatement(qeury);
                            presta.setString(1,nameM.getText());
                            reset = presta.executeQuery();
                            
                                if (reset.next()) 
                                   {
                                       if( reset.getInt(1)!=0)
                                      {
                                        String query = "UPDATE [DB_MEMIOR].[dbo].[Intertete] SET TextInt=?";
                                        presta = con.prepareStatement(query);
                                        presta.setString(1, list.get(jTabbedPane1.getSelectedIndex()).getText());
                                        presta.execute(); 
                                       } 
                                       else
                                       { 
                                           
                                            String query = "INSERT INTO [DB_MEMIOR].[dbo].[Intertete] (TextInt,NameMod)values (?,?)";
                                            presta = con.prepareStatement(query);
                                            presta.setString(1, list.get(jTabbedPane1.getSelectedIndex()).getText());
                                            presta.setString(2, nameM.getText());
                                            presta.execute();
                                            
                                        }   
                                            
                                    }
                                    
                     }
                else
                     {
                         JOptionPane.showMessageDialog(null, "Please specify what exercise you will write or Intertete");
                     }
            JOptionPane.showMessageDialog(null, "insert valide");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_SaveButtonActionPerformed

    private void NumParActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumParActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NumParActionPerformed

    private void nameMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameMActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:

        /*undo.addEdit(evt.getEdit());
        undoAction.updateUndoState();
        redoAction.updateRedoState();*/
    }//GEN-LAST:event_editActionPerformed

    private void CutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CutButtonActionPerformed
        // TODO add your handling code here:
        //MethodCut();
        list.get(jTabbedPane1.getSelectedIndex()).cut();

    }//GEN-LAST:event_CutButtonActionPerformed

    private void cutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutActionPerformed
        // TODO add your handling code here:
        MethodCut();
    }//GEN-LAST:event_cutActionPerformed

    private void redoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_redoActionPerformed

    private void undoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoActionPerformed


    }//GEN-LAST:event_undoActionPerformed

    private void CopyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CopyButtonActionPerformed
        // TODO add your handling code here:
        list.get(jTabbedPane1.getSelectedIndex()).copy();

    }//GEN-LAST:event_CopyButtonActionPerformed

    private void copyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyActionPerformed
        // TODO add your handling code here:
        list.get(jTabbedPane1.getSelectedIndex()).copy();
    }//GEN-LAST:event_copyActionPerformed

    private void pasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteActionPerformed
        // TODO add your handling code here:
        list.get(jTabbedPane1.getSelectedIndex()).paste();
    }//GEN-LAST:event_pasteActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        list.get(jTabbedPane1.getSelectedIndex()).paste();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        list.get(jTabbedPane1.getSelectedIndex()).selectAll();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:

        try {
            int par = Integer.valueOf(NumPar.getText());
            int name = Integer.valueOf(nameM.getText());
            //jTabbedPane1.getSelectedIndex();

            String query = "INSERT INTO [DB_MEMIOR].[dbo].[exo] (TextEXO,DegDiff,Partie,IdMod)values (?,?,?,?)";

            presta = con.prepareStatement(query);
            presta.setString(1, list.get(jTabbedPane1.getSelectedIndex()).getText());
            presta.setString(2, listDeg.getSelectedItem().toString());
            presta.setInt(3, par);
            presta.setInt(4, name);
            presta.execute();
            JOptionPane.showMessageDialog(null, "insert valide");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

    }//GEN-LAST:event_saveActionPerformed

    private void openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openActionPerformed
        // TODO add your handling code here:
        Filechoose filech = new Filechoose();
        filech.getFileChooser1().showOpenDialog(this);
        File f = filech.getFileChooser1().getSelectedFile();
        String filename = f.getAbsolutePath();

        try {
            FileReader reader = new FileReader(filename);
            BufferedReader br = new BufferedReader(reader);
            //javax.swing.JTextArea t = new javax.swing.JTextArea ();
            TextEditorArea textPane = new TextEditorArea();
            textPane.read(reader, null);
             textPane.setContentType("text/html");
            jTabbedPane1.addTab("Open File", textPane);
            br.close();
            textPane.requestFocus();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_openActionPerformed

    private void sellectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellectActionPerformed
        // TODO add your handling code here:
        list.get(jTabbedPane1.getSelectedIndex()).selectAll();
    }//GEN-LAST:event_sellectActionPerformed

    private void wtextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wtextActionPerformed
        // TODO add your handling code here:
        //if(wtext.isSelected())
        //list.get(jTabbedPane1.getSelectedIndex()).setLineWrap(true);
    }//GEN-LAST:event_wtextActionPerformed

    private void FontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FontActionPerformed
        // TODO add your handling code here:
        indexArea = jTabbedPane1.getSelectedIndex();
      JFontChooser newFont = new JFontChooser();
             newFont.setVisible(true);
        if (newFont == null) 
        {		
            GetSelectedTextPane().requestFocusInWindow();
            return;
        }
			
                    /* SimpleAttributeSet attr = new SimpleAttributeSet();
			StyleConstants.setFontFamily(attr, newFont.getFont().getFamily());
                        StyleConstants.setFontSize(attr,newFont.getFont().getSize());
                        
			GetSelectedTextPane().setCharacterAttributes(attr, false);*/
                    //  غيرتها تجيبا1
                    StyledDocument doc = (StyledDocument)GetSelectedTextPane().getDocument();
                    SimpleAttributeSet atts = new SimpleAttributeSet();
                    StyleConstants.setFontFamily(atts, newFont.getFont().getFamily());//Underline( atts, true );
                    StyleConstants.setFontSize(atts, newFont.getFont().getSize());
                    if(newFont.getFont().isBold())
                    StyleConstants.setBold(atts, true);
                    if(newFont.getFont().isItalic())
                    StyleConstants.setItalic(atts, true);
                    if(newFont.getFont().isItalic()&& newFont.getFont().isBold())
                    {
                    StyleConstants.setItalic(atts, true);
                    StyleConstants.setBold(atts, true);
                    }
                    doc.setCharacterAttributes( GetSelectedTextPane().getSelectionStart(), GetSelectedTextPane().getSelectionEnd() - GetSelectedTextPane().getSelectionStart(), atts, false );
                    GetSelectedTextPane().requestFocusInWindow();
			
       
    }//GEN-LAST:event_FontActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
Color newColor =
				JColorChooser.showDialog(this, "Choose a color", Color.BLACK);
			if (newColor == null) {
			
				GetSelectedTextPane().requestFocusInWindow();
				return;
			}
			
			/*SimpleAttributeSet attr = new SimpleAttributeSet();
			StyleConstants.setForeground(attr, newColor);
			GetSelectedTextPane().setCharacterAttributes(attr, false);
			GetSelectedTextPane().requestFocusInWindow();*/
      
                 // غيرتها تجريبيا2      
StyledDocument doc = (StyledDocument)GetSelectedTextPane().getDocument();
SimpleAttributeSet atts = new SimpleAttributeSet();
StyleConstants.setForeground(atts, newColor);//Underline( atts, true );

doc.setCharacterAttributes( GetSelectedTextPane().getSelectionStart(), GetSelectedTextPane().getSelectionEnd() - GetSelectedTextPane().getSelectionStart(), atts, false );
GetSelectedTextPane().requestFocusInWindow();
    }//GEN-LAST:event_jButton2ActionPerformed

    private TextEditorArea GetSelectedTextPane() {

        return list.get(jTabbedPane1.getSelectedIndex());

    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try{
            File pictureFile = choosePictureFile();
			
			if (pictureFile == null) {
			
				GetSelectedTextPane().requestFocusInWindow();
				return;
			}
                        
    //StyledDocument doc = (StyledDocument)GetSelectedTextPane().getDocument();
 HTMLEditorKit hek = new HTMLEditorKit();

    GetSelectedTextPane().setEditorKit(hek);

    HTMLDocument doc = (HTMLDocument) GetSelectedTextPane().getDocument();

    doc.insertString(0, "Test testing", null);

    Element[] roots = doc.getRootElements();
    Element body = null;
    for (int i = 0; i < roots[0].getElementCount(); i++) {
      Element element = roots[0].getElement(i);
      if (element.getAttributes().getAttribute(StyleConstants.NameAttribute) == HTML.Tag.BODY) {
        body = element;
        break;
      }
    }

    doc.insertAfterEnd(body,"<img src=" + pictureFile.getAbsolutePath().toString()/*ClassLoader.getSystemResource("aa.png").toString()*/
            + ">");
    // The image must first be wrapped in a style
   //Style style = doc.addStyle("img", null);
  //StyleConstants.setIcon(style, new ImageIcon(pictureFile.getAbsolutePath()));
 
    // Insert the image at the end of the text
   
/*SimpleAttributeSet atts = new SimpleAttributeSet();
StyleConstants.setIcon(atts,new ImageIcon(pictureFile.getAbsolutePath()) );//Alignment(atts,StyleConstants.ALIGN_RIGHT);//Underline( atts, true );

doc.setCharacterAttributes( GetSelectedTextPane().getSelectionStart(), GetSelectedTextPane().getSelectionEnd() - GetSelectedTextPane().getSelectionStart(), atts, false );
GetSelectedTextPane().requestFocusInWindow();*/
   
			//ImageIcon icon = new ImageIcon(pictureFile.getPath());			
			/*JButton picButton = new JButton(icon);
			picButton.setBorder(new LineBorder(Color.WHITE));
			picButton.setMargin(new Insets(0,0,0,0));
			picButton.setAlignmentY(.9f);
			picButton.setAlignmentX(.9f);
			picButton.addFocusListener(new PictureFocusListener());
			picButton.setName("PICTURE_ID_" + new Random().nextInt());
			GetSelectedTextPane().insertComponent(picButton);*/
                        //ImageProducer F=null;
                       // GetSelectedTextPane().createImage(F);
                        //GetSelectedTextPane().insertIcon (new ImageIcon(pictureFile.getAbsolutePath()));
			//GetSelectedTextPane().requestFocusInWindow();
        }
        catch(Exception e)
        {}
    }//GEN-LAST:event_jButton4ActionPerformed

    private void textAlignComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_textAlignComboBoxItemStateChanged
       
			if ((evt.getStateChange() != ItemEvent.SELECTED) ||
				(textAlignComboBox.getSelectedIndex() == 0)) {
			
				return;
			}
			
			String alignmentStr = (String) evt.getItem();			
			int newAlignment = textAlignComboBox.getSelectedIndex() - 1;
			// New alignment is set based on these values defined in StyleConstants:
			// ALIGN_LEFT 0, ALIGN_CENTER 1, ALIGN_RIGHT 2, ALIGN_JUSTIFIED 3
			textAlignComboBox.setAction(new StyledEditorKit.AlignmentAction(alignmentStr, newAlignment));	
			textAlignComboBox.setSelectedIndex(0); // initialize to (default) select
			GetSelectedTextPane().requestFocusInWindow();
    }//GEN-LAST:event_textAlignComboBoxItemStateChanged

    private void textAlignComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAlignComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textAlignComboBoxActionPerformed
private StyledDocument getEditorDocument() {
	
		StyledDocument doc = (DefaultStyledDocument) GetSelectedTextPane().getDocument();
		return doc;
	}
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
      
			StyledDocument doc = (DefaultStyledDocument) GetSelectedTextPane().getDocument();
			ElementIterator iterator = new ElementIterator(doc);
			javax.swing.text.Element element;
			
			while ((element = iterator.next()) != null) {
			
				AttributeSet attrs = element.getAttributes();
			
				if (attrs.containsAttribute(ELEM , COMP)) {
				 JButton button;
                                     button =  (JButton) StyleConstants.getComponent(attrs);
                               
                                   if (button.getName().equals(pictureButtonName)) {
                                        
                                        try {
                                            doc.remove(element.getStartOffset(), 1); // length = 1
                                        }
                                        catch (BadLocationException ex_) {
                                            
                                           // throw new RuntimeException(ex_);
                                        }
                                    }
                            }
			}
	
			GetSelectedTextPane().requestFocusInWindow();
			pictureButtonName = null;
		
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
     
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         
    }//GEN-LAST:event_jButton6ActionPerformed

    private void FormatMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_FormatMenuSelected
        
    }//GEN-LAST:event_FormatMenuSelected

    private void FontMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FontMouseClicked
      
    }//GEN-LAST:event_FontMouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
    try{
            String intertete=""; 
            NameModule = nameM.getText();
            String qeury1 = "select *from [DB_MEMIOR].[dbo].[Intertete] where NameMod = ?";
            prestInt = conInt.prepareStatement(qeury1);
            prestInt.setString(1,NameModule);
            resetInt = prestInt.executeQuery();
            if(resetInt.next()){intertete = resetInt.getString("TextInt");}
            String qeury = "select * from [DB_MEMIOR].[dbo].[Module] where NameMod = ? ";
           
            presta = con.prepareStatement(qeury);
            presta.setString(1,NameModule);
            reset = presta.executeQuery();
                if (reset.next()) 
                {
                   
                   NbrPartie =reset.getInt("NbrPartie");
                  int NbrDeg = reset.getInt("NbrDeg");
                  Algorithem algo;
                   algo = new Algorithem();
                  algo.FillArray(NameModule,1,NbrPartie,NbrPartie,Exoarray);
                   int IndexSuj=0;
                  for(int i=0;i<NbrDeg;i++)
                   {
                    for(int j=0;j<NbrDeg;j++)
                       {   if(i==j) continue;
                           if(NbrPartie>2)
                           {
                                for(int k=0;k<NbrDeg;k++)
                                {
                                    if(k==i||k==j) continue;
                                    if(NbrPartie >3)
                                    {
                                        for(int t=0;t < NbrDeg;t++)
                                        {
                                            if(t==i|| t==j||t==k)continue;
                                            if(NbrPartie==4)
                                            
                                                
                                                {
                                                       listExo.add(0,intertete);
                                                       listExo.add(1,Exoarray[i][0]);
                                                       listExo.add(2,Exoarray[j][1]);
                                                       listExo.add(3,Exoarray[k][2]);
                                                       listExo.add(4,Exoarray[t][3]);
                                                       CreatePdf(listExo,listnameFile.get(IndexSuj));
                                                       IndexSuj++;
                                                       

                                               }
                                           
                                           
                                        }
                                    }
                                    else
                                    {
                                       listExo.add(0,intertete);
                                       listExo.add(1,Exoarray[i][0]);
                                       listExo.add(2,Exoarray[j][1]);
                                       listExo.add(3,Exoarray[k][2]);
                                        CreatePdf(listExo,listnameFile.get(IndexSuj));
                                                       IndexSuj++;
                                    }
                                }
                           }
                           else
                           {
                              listExo.add(0,intertete);
                              listExo.add(1,Exoarray[i][0]);
                              listExo.add(2,Exoarray[j][1]);
                              CreatePdf(listExo,listnameFile.get(IndexSuj));
                                                       IndexSuj++;
                           }
                       }
                       
                   }
                }
            
       
   }
   catch(Exception e)
   {
           JOptionPane.showMessageDialog(null, e.getCause());
   }
            
            
       
    }//GEN-LAST:event_jButton11ActionPerformed

    @SuppressWarnings("empty-statement")
    private void listDegItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listDegItemStateChanged
  
       
        
    }//GEN-LAST:event_listDegItemStateChanged

    private void listDegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listDegActionPerformed
        // TODO add your handling code here:
         if(listDeg.getItemCount()>1)
        {
            listDeg.removeItemAt(listDeg.getSelectedIndex());
            item++;
      }
         else
         {
          
            // DefaultComboBoxModel model = new DefaultComboBoxModel();
            // model.setSelectedItem(null);
            // listDeg = new JComboBox(model);
            // listDeg.setModel(model);
             listDeg.removeItemAt(listDeg.getSelectedIndex());
   
            listDeg.addItem("Easy");
            listDeg.addItem("very easy");
            listDeg.addItem("Average");
            listDeg.addItem("Difficult");
        }
        
         /*  Object tempItem = listDeg.getSelectedItem();
           if (!((CanEnable) tempItem).isEnabled()) {
               listDeg.setSelectedItem(curItem);
           } else {
               curItem = tempItem;
           }
       */
    }//GEN-LAST:event_listDegActionPerformed

    private void EditIntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditIntActionPerformed
        // TODO add your handling code here:
        listDeg.setEnabled(false);
        NumPar.setEnabled(false);
        GetSelectedTextPane().setText("<p><pre></pre></p>");
    }//GEN-LAST:event_EditIntActionPerformed

    private void EditExoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditExoActionPerformed
         listDeg.setEnabled(true);
         NumPar.setEnabled(true);
    }//GEN-LAST:event_EditExoActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        StyledDocument doc = (StyledDocument)GetSelectedTextPane().getDocument();
SimpleAttributeSet atts = new SimpleAttributeSet();
StyleConstants.setUnderline(atts, true);

doc.setCharacterAttributes( GetSelectedTextPane().getSelectionStart(), GetSelectedTextPane().getSelectionEnd() - GetSelectedTextPane().getSelectionStart(), atts, false );
GetSelectedTextPane().requestFocusInWindow();
    }//GEN-LAST:event_jButton1ActionPerformed
    public void getjtabbedPane() {
        //static int inte=  jTabbedPane1.getSelectedIndex();
    }

    /**
     * @param args the command line arguments
     */
  



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CopyButton;
    private javax.swing.JButton CutButton;
    private javax.swing.JRadioButton EditExo;
    private javax.swing.JRadioButton EditInt;
    private javax.swing.JMenu File;
    private javax.swing.JMenuItem Font;
    private javax.swing.JMenu Format;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem New;
    private javax.swing.JButton NewButton;
    private javax.swing.JTextField NumPar;
    private javax.swing.JButton OpenButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JMenuItem close;
    private javax.swing.JMenuItem copy;
    private javax.swing.JMenuItem cut;
    private javax.swing.JMenu edit;
    private javax.swing.JMenuItem exit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JComboBox<String> listDeg;
    private javax.swing.JTextField nameM;
    private javax.swing.JMenuItem open;
    private javax.swing.JMenuItem paste;
    private javax.swing.JMenuItem redo;
    private javax.swing.JMenuItem save;
    private javax.swing.JMenuItem saveas;
    private javax.swing.JMenuItem sellect;
    private javax.swing.JComboBox<String> textAlignComboBox;
    private javax.swing.JMenuItem undo;
    private javax.swing.JCheckBoxMenuItem wtext;
    // End of variables declaration//GEN-END:variables
private File choosePictureFile() {
		
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
								"PNG, JPG & GIF Images", "png", "jpg", "gif");
			chooser.setFileFilter(filter);
			
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			
				return chooser.getSelectedFile();
			}
			else {
				return null;
			}
		}
private class PictureFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {

			JButton button = (JButton) e.getComponent();
			button.setBorder(new LineBorder(Color.GRAY));
			pictureButtonName = button.getName();
		}
		
		@Override
		public void focusLost(FocusEvent e) {

			((JButton) e.getComponent()).setBorder(new LineBorder(Color.WHITE));
		}
	}
	private class BulletActionListener implements ActionListener {

		private BulletActionType bulletActionType;
		
		public BulletActionListener(BulletActionType actionType) {
		
			bulletActionType = actionType;
		}

		/*
		 * Common routine for insert and remove bullet actions. This routine
		 * loops thru the selected text and inserts or removes a bullet.
		 * - For insert action: inserts a bullet at the beginning of each para
		 * of selected text. The paras already bulleted or numbered are ignored.
		 * - For remove bullet action: removes the bullet in case a para is 
		 * bulleted for the selected text.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
		
			String selectedText = GetSelectedTextPane().getSelectedText();
			
			if ((selectedText == null) || (selectedText.trim().isEmpty())) {

				GetSelectedTextPane().requestFocusInWindow();
				return;
			}
			
			StyledDocument doc = getEditorDocument();			
			javax.swing.text.Element paraEle = doc.getParagraphElement(GetSelectedTextPane().getSelectionStart());
			int paraEleStart = paraEle.getStartOffset();
			int paraEleEnd = 0;
			
			BULLETS_PARA_LOOP:
			do {
				paraEle = doc.getParagraphElement(paraEleStart);
				paraEleEnd = paraEle.getEndOffset();
				
				if ((paraEleEnd - paraEleStart) <= 1) { // empty line/para
				
					paraEleStart = paraEleEnd;
					paraEle = doc.getParagraphElement(paraEleStart);
					continue BULLETS_PARA_LOOP;
				}

				switch (bulletActionType) {
				
					case INSERT:
						if ((! isBulletedPara(paraEleStart)) &&
								(! isNumberedPara(paraEleStart))) {
				
                                    try {
                                        insertBullet(paraEleStart, paraEleStart);
                                    } catch (BadLocationException ex) {
                                        Logger.getLogger(Exercises.class.getName()).log(Level.SEVERE, null, ex);
                                    }
						}
						
						break; // switch
				
					case REMOVE:
						if (isBulletedPara(paraEleStart)) {
				
							removeBullet(paraEleStart, BULLET_LENGTH);
						}
				}

				// Get the updated para element details after bulleting
				paraEle = doc.getParagraphElement(paraEleStart);
				paraEleEnd = paraEle.getEndOffset();

				paraEleStart = paraEleEnd;

			} while (paraEleEnd <= GetSelectedTextPane().getSelectionEnd());
			// BULLETS_PARA_LOOP
			
			GetSelectedTextPane().requestFocusInWindow();
                }
        }
        private boolean isBulletedPara(int paraEleStart) {
						
		if (getParaFirstCharacter(paraEleStart) == BULLET_CHAR) {
			
			return true;
		}
			
		return false;
	}
	
	
	private char getParaFirstCharacter(int paraEleStart) {
		String firstChar = "";
			
		try {
			firstChar = GetSelectedTextPane().getText(paraEleStart, 1);
		}
		catch (BadLocationException ex) {
			
			throw new RuntimeException(ex);
		}
			
		return firstChar.charAt(0);
	}
		
	
	
		
	private boolean isFirstCharNumber(int paraEleStart) 
        {
			
		if (Character.isDigit(getParaFirstCharacter(paraEleStart))) {
			
			return true;
		}
		
		return false;
	}

	
	
		
	
	
	
private void insertBullet(int insertPos, int attributesPos) throws BadLocationException
        {
								
            getEditorDocument().insertString(insertPos,BULLET_STR_WITH_SPACE,getParaStartAttributes(attributesPos));
	}
private boolean isNumberedPara(int paraEleStart)
{

		AttributeSet attrSet = getParaStartAttributes(paraEleStart);		
		Integer paraNum = (Integer) attrSet.getAttribute(NUMBERS_ATTR);

		if ((paraNum == null) || (! isFirstCharNumber(paraEleStart))) {

			return false;
		}

		return true;
	}
private AttributeSet getParaStartAttributes(int pos) {
	
		StyledDocument doc = (DefaultStyledDocument) GetSelectedTextPane().getDocument();
		javax.swing.text.Element	charEle = doc.getCharacterElement(pos);
		return charEle.getAttributes();
	}
private void removeBullet(int removePos, int length) {

		try {
			getEditorDocument().remove(removePos, length);
		}
		catch(BadLocationException ex) {
				
			throw new RuntimeException(ex);
		}
	}
private class NumbersActionListener implements ActionListener {

		private NumbersActionType numbersActionType;
		private int n;
	
		public NumbersActionListener(NumbersActionType actionType) {
		
			numbersActionType = actionType;
		}

		/*
		 * Common routine for insert and remove numbers actions. This routine
		 * loops thru the selected text and inserts or removes a number.
		 * - For insert action: inserts a number at the beginning of each para
		 * of selected text. The paras already bulleted or numbered are ignored.
		 *  Note that the numbering always starts from 1.
		 * - For remove action: removes the number in case a para is numbered
		 * for the selected text.
		 */		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			StyledDocument doc = getEditorDocument();
			String selectedText = GetSelectedTextPane().getSelectedText();
			
			if ((selectedText == null) || (selectedText.trim().isEmpty())) {

				GetSelectedTextPane().requestFocusInWindow();
				return;
			}
			
			javax.swing.text.Element paraEle = doc.getParagraphElement(GetSelectedTextPane().getSelectionStart());
			int paraEleStart = paraEle.getStartOffset();
			int paraEleEnd = 0;
			boolean firstPara = true;
			
			NUMBERS_PARA_LOOP:
			do {
				paraEle = doc.getParagraphElement(paraEleStart);
				paraEleEnd = paraEle.getEndOffset();
				
				if ((paraEleEnd - paraEleStart) <= 1) { // empty line
				
					if (firstPara) {
					
						firstPara = false;
						n = 0;
					}

					paraEleStart = paraEleEnd;
					paraEle = doc.getParagraphElement(paraEleStart);
					continue NUMBERS_PARA_LOOP;
				}

				switch (numbersActionType) {
				
					case INSERT:
					
						if (isBulletedPara(paraEleStart)) {
						
							break; // switch
						}
					
						if (firstPara) {
					
							firstPara = false;
							n = 0;
						}
						
						if (isNumberedPara(paraEleStart)) {
				
							// remove any existing number
							removeNumber(paraEleStart, getNumberLength(paraEleStart));
						}
					
						if (! isNumberedPara(paraEleStart)) {
				
							Integer nextN = new Integer(++n);
                                    try {
                                        insertNumber(paraEleStart, paraEleStart, nextN);
                                    } catch (BadLocationException ex) {
                                        Logger.getLogger(Exercises.class.getName()).log(Level.SEVERE, null, ex);
                                    }
						}
						
						break; // switch
				
					case REMOVE:
					
						if (isNumberedPara(paraEleStart)) {
				
							removeNumber(paraEleStart, getNumberLength(paraEleStart));
						}
				}

				// Get the updated para element details after numbering
				paraEle = doc.getParagraphElement(paraEleStart);
				paraEleEnd = paraEle.getEndOffset();

				paraEleStart = paraEleEnd;

			} while (paraEleEnd <= GetSelectedTextPane().getSelectionEnd());
			// NUMBERS_PARA_LOOP

			GetSelectedTextPane().requestFocusInWindow();
		}
	}
private int getNumberLength(int paraEleStart) {
	
		Integer num = getParaNumber(paraEleStart);
		int len = num.toString().length() + 2; // 2 = dot + space after number
		return len;
	}

	private Integer getParaNumber(int paraEleStart) {
		
		AttributeSet attrSet = getParaStartAttributes(paraEleStart);		
		Integer paraNum = (Integer) attrSet.getAttribute(NUMBERS_ATTR);
		return paraNum;
	}
        private void removeNumber(int removePos, int length) {
				
		try {
			getEditorDocument().remove(removePos, length);
		}
		catch(BadLocationException ex) {
				
			throw new RuntimeException(ex);
		}
	}
        private void insertNumber(int insertPos, int attributesPos, Integer num) throws BadLocationException {

            getEditorDocument().insertString(insertPos,
                    getNumberString(num),
                    getNumbersAttributes(attributesPos, num));
	}
        private String getNumberString(Integer nextNumber) {
		
		return new String(nextNumber.toString() + "." + " ");
	}
		
	private AttributeSet getNumbersAttributes(int paraEleStart, Integer number) {
		
		AttributeSet attrs1 = getParaStartAttributes(paraEleStart);
		SimpleAttributeSet attrs2 = new SimpleAttributeSet(attrs1);
		attrs2.addAttribute(NUMBERS_ATTR, number);
		return attrs2;
	}
   public void CreatePdf(ArrayList<String> list, String nameFile)
   {
      try
      {
            
            
            com.itextpdf.text.Document document =
            new com.itextpdf.text.Document( com.itextpdf.text.PageSize.A4);
            String fileNameWithPath = "Examen01"+nameFile+".pdf";
            FileOutputStream fos = new FileOutputStream( fileNameWithPath );
            com.itextpdf.text.pdf.PdfWriter pdfWriter =
            com.itextpdf.text.pdf.PdfWriter.getInstance( document, fos );
            Rectangle rectangle = new Rectangle(30, 30, 550, 800);
            pdfWriter.setBoxSize("rectangle", rectangle);
           
            HeaderAndFooterPdfPageEventHelper headerAndFooter =new HeaderAndFooterPdfPageEventHelper();
            //pdfWriter.setPageEvent(headerAndFooter);
            document.open();
            com.itextpdf.text.html.simpleparser.HTMLWorker htmlWorker =
            new com.itextpdf.text.html.simpleparser.HTMLWorker( document );
                                                 
                                                        try{ 
                                                           document.newPage();
                                                           //Rectangle page = document.getPageSize();
                                                         //PdfPTable head = new PdfPTable(1);
                                                        //head.setHorizontalAlignment(10);
                                                       // document.add(new Paragraph(Jsoup.parse(list.get(0)).text()));
                                                      // headerAndFooter.onStartPage(pdfWriter, document,Jsoup.parse(list.get(0)).text());
                                                       //System.out.println(list.get(0));
                                                           for(int j=0;j< NbrPartie+1;j++)
                                                            {
                                                                      
                                                                
                                                               // System.out.println(Jsoup.parse(list.get(j)).text());
                                                                /*if(j=0)*/ htmlWorker.parse(new StringReader(list.get(j)));
                                                                           

                                                                 
                                                           }
                                                             
 
                                                           
                                                           
                                                            //head.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
                                                             //float number=page.getHeight() - document.topMargin()+ head.getTotalHeight();
                                                            //head.writeSelectedRows(0,(int)page.getWidth(), document.leftMargin(),page.getHeight() - document.topMargin()+ head.getTotalHeight(),pdfWriter.getDirectContent());
                                                            //pdfWriter.getPageNumber();
                                                            pdfWriter.flush();
                                                            document.close(); 
                                                            fos.close();
                                                         }
                                                         catch(Exception e)
                                                          {
                                                               System.out.println("nnn");
                                                              JOptionPane.showMessageDialog(null, e.getMessage());
                                                          }
                                                         
                                                        
                                                         }
catch(Exception e)
 {
    JOptionPane.showMessageDialog(null,e.toString());
 }
   }


    private void updateButtons() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
      
        ConnectionDB.OpenConnection();
       Exercises e= new Exercises();
       
        e.setVisible(true);
        //e.CreatePdf(h,"b11");
        
     }
}
