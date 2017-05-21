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
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.html.HtmlTags;
import static com.lowagie.text.html.HtmlTags.HTML;
import com.lowagie.text.pdf.ColumnText;
import java.awt.Dimension;
import java.awt.Scrollbar;
import static java.awt.SystemColor.text;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.time.Clock.system;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import javafx.scene.control.ScrollBar;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;
//import org.jsoup;
import org.jsoup.Jsoup;
//import org.jsoup.jsoup.jsoup;
import static org.jsoup.nodes.Document.OutputSettings.Syntax.xml;
import org.jsoup.nodes.Entities.EscapeMode;



/**
 *
 * @author abdeljabbar
 */
public class Test {
    public String MD5(String md5) {
   try {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        byte[] array = md.digest(md5.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
       }
        return sb.toString();
    } catch (java.security.NoSuchAlgorithmException e) {
    }
    return null;
}
    
        public void createPdf(String file) throws IOException, DocumentException {
    Document document = new Document();
  PdfWriter pdfwrite;
            pdfwrite =  PdfWriter.getInstance(document, new FileOutputStream("Application Mobiles700.pdf"));
    document.open();
    //String css = readCSS();
    document.newPage();
   // for (String htmlfile : HTML) 
    //{
        //String html = Utilities.readFileToString(htmlfile);
        //ElementList list = XMLWorkerHelper.parseToElementList(file,null);
           
        XMLWorkerHelper.getInstance().parseXHtml(pdfwrite,document,new ByteArrayInputStream(file.getBytes()));
      //  for (Element e : list)
       //{
           //*** document.add(list.get(0));
        
       // }
        //document.newPage();
    //}
    document.close();
}


    public static void main(String[] args) throws DocumentException, IOException, ParseException  
    {
     /*String str="html>\n" +
"  <head>\n" +
"    <style>\n" +
"  \n" +
"</style>\n" +
"  </head>\n" +
"  <body>\n" +
"    <table align=\"center\" style=\"width: 700pt\">\n" +
"      <tr>\n" +
"        <td colspan=\"4\">\n" +
"          <p align=\"center\">\n" +
"            Universite d elOued\n" +
"          </p>\n" +
"        </td>\n" +
"      </tr>\n" +
"      <tr>\n" +
"<td>\n" +
"\n" +
"</td>\n" +             
"        <td>\n" +
"          <p>Faculte: Sciences Exactes</p>\n" +
"        </td>\n" +
"        <td style=\"width: 230pt\" rowspan=\"3\">\n" +
"          \n" +
"        </td>\n" +
"        <td text-align:=\"center\">\n" +
"          <p>Date:07/05/2017</p>\n" +
"        </td>\n" +
"      </tr>\n" +
"      <tr>\n" +
"<td>\n" +
"\n" +
"</td>\n" + 
"        <td>\n" +
"         <p> Departement: d informatique</P>\n" +
"        </td>\n" +

"        <td text-align:=\"center\">\n" +
"          <p >Duree:1h:30</p>\n" +
"        </td>\n" +
"      </tr>\n" +
"      <tr>\n" +
"       <td>\n" +
"       \n" +
"       </td>\n" + 
"        <td>\n" +
"          <p>Niveau: 3eme annee LMD</p>\n" +
"        </td>\n" +

"        <td text-align:=\"center\">\n" +
"          <p >Module: Application mobiles</p>\n" +
"        </td>\n" +
"      </tr>\n" +
"      <tr>\n" +
"        <td colspan=\"4\">\n" +
"          <p align=\"center\">\n" +
"            Examen\n" +
"          </p>\n" +
"        </td>\n" +
"      </tr>\n" +
"      <tr>\n" +
"        <td colspan=\"4\">\n" +
"          <hr align=\"center\" style=\"width: 700pt\">\n" +
"          \n" +
"        </td>\n" +
"      </tr>\n" +
"    </table>\n" +
"  </body>\n" +
"</html>";

       
            org.jsoup.nodes.Document document = Jsoup.parseBodyFragment(str);
document.outputSettings().syntax(xml);//escapeMode(EscapeMode.extended);
String st = document.body().html();
System.out.println(st);
 new Test().createPdf(st);*/
        /*SimpleDateFormat format;
        format = new SimpleDateFormat("yyyy/MM/dd");
        java.sql.Date date =   (java.sql.Date) format.parse("2017/05/18");
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        System.out.println(sqlDate);*/
      /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   String convertedCurrentDate =sdf.format(sdf.parse("2017-05-18"));
    
    System.out.println(convertedCurrentDate);*/

      
                           
                           JTextField tf= new JTextField();
                          
                         if(tf.getText().isEmpty())
                                System.out.println("ف");
                      else
                         {
                             System.out.println("م");
                         }
   //String convertedCurrentDate =sdf.format(sdf.parse("2013-09-18"));
    }
}
