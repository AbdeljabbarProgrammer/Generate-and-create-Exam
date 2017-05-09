/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire;
import com.pdfcrowd.*;
import java.io.*;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import static com.lowagie.text.html.HtmlTags.URL;
import com.sun.security.ntlm.Client;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import java.io.File;
import java.io.FileOutputStream;
import static java.lang.System.out;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;



/**
 *
 * @author abdeljabbar
 */
public class Test {
   public  void htmlToPdfFile() throws Exception {
  
   try{
        CYaHPConverter converter = new CYaHPConverter();
        File fout = new File("C:/Users/abdeljabbar/Documents/NetBeansProjects/Memoire/HTMLTEXT.pdf");//"c:/temp/x.pdf"
           FileOutputStream out = new FileOutputStream(fout); 
           Map properties = new HashMap();
           List headerFooterList = new ArrayList();
           String s="<html>\n" +
"  <head>\n" +
"\n" +
"  </head>\n" +
"  <body>\n" +
"    <p style=\"margin-top: 0\">\n" +
"      Test testing\n" +
"    </p>\n" +
"  </body>\n" +
"  <img src=\"C:\\Users\\abdeljabbar\\Pictures\\aa.png\">\n" +
"</html>";
           String str = "<HTML><HEAD></HEAD><BODY><H1>Testing</H1><FORM>" +
                   "check : <INPUT TYPE='checkbox' checked=checked/><br/>"   +
                   "</FORM></BODY></HTML>";
           
           properties.put(IHtmlToPdfTransformer.PDF_RENDERER_CLASS,IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER);
           //properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH, "C:\\Users\\abdeljabbar\\Documents\\NetBeansProjects\\Memoire\\HTMLTEXT.pdf");
InputStream is;
       is = new ByteArrayInputStream( str.getBytes( Charset.defaultCharset() ) );
           converter.convertToPdf(s,IHtmlToPdfTransformer.A4P,headerFooterList,"file:///C:/Users/abdeljabbar/Pictures/>",out,properties);
           
           out.flush();
           out.close();
   }catch(IHtmlToPdfTransformer.CConvertException | IOException e)
   {
       JOptionPane.showMessageDialog(null, e.getMessage());
   }


    

 }
    public static void main(String[] args) throws Exception 
    {
     try{
            new Test().htmlToPdfFile();
     }catch(Exception e)
     {
          JOptionPane.showMessageDialog(null, e.getCause());
     }
    
    }
}
