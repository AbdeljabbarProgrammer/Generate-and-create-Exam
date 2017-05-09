/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire;

/**
 *
 * @author abdeljabbar
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfPageEventHelper;
import java.io.File;
import java.io.IOException;

public class HeaderFooter extends
      PdfPageEventHelper {
 
  public static void main(String[] args) throws DocumentException, IOException {
      String pdfFilePath = "e:/Set Header and Footer in Pdf Using Itext Example.pdf";
      FileOutputStream fos = new FileOutputStream(new File(pdfFilePath));
      Document document = new Document();
      PdfWriter pdfWriter = PdfWriter.getInstance(document, fos);
 
      Rectangle rectangle = new Rectangle(30, 30, 550, 800);
      pdfWriter.setBoxSize("rectangle", rectangle);
      HeaderAndFooterPdfPageEventHelper headerAndFooter =new HeaderAndFooterPdfPageEventHelper();
      pdfWriter.setPageEvent(headerAndFooter);
      document.open();
      document.add(new Paragraph("This is Header and Footer in Pdf Using Itext Example"));
      document.close();
      fos.close();
      System.out.println("PDF created in >> " + pdfFilePath);
  }
}
 
