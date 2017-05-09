/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;


public class Lis {

    static String FILENAME = "fil.pdf";

    public static void main(String[] args) {
        try {
            System.out.println(printMem());

            Rectangle rectPage = new Rectangle(0, 0, 210, 297);

            FileOutputStream fos = new FileOutputStream(new File(FILENAME));
            Document d = new Document(rectPage, 0, 0, 0, 0);
            PdfWriter writer = PdfWriter.getInstance(d, fos);


            d.open();




            int pageNo = 200;

            for (int i = 0; i < pageNo; i++) {
                d.newPage();

                PdfContentByte cb;
                PdfTemplate tp;
                Graphics2D g2;


                cb = writer.getDirectContent();

                tp = cb.createTemplate(rectPage.getWidth(),
                        rectPage.getHeight());
                g2 = tp.createGraphicsShapes(rectPage.getWidth(),
                        rectPage.getHeight());

                paintRand(g2);

                g2.dispose();
                cb.addTemplate(tp, 0, 0);

                System.out.println(i + ") " + printMem());
                writer.flush();


            }


            d.close();

            System.out.println(printMem());
            System.out.println("done");






        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (OutOfMemoryError mem) {
            System.err.println("OUT OF MEMORY!!!!!!! " + printMem());
            mem.printStackTrace();
        }
    }






    private static String printMem() {
        final long usedMem = Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory();
        String ram = "RAM: " + (usedMem / 1024 / 1024) + "MB / "
                + (Runtime.getRuntime().totalMemory() / 1024 / 1024) + "MB";
        if (Runtime.getRuntime().maxMemory() != Long.MAX_VALUE) {
            ram += " (max limit: " + Runtime.getRuntime().maxMemory() / 1024
                    / 1024 + "MB)";
        } else {
            ram += " (no max limit)";
        }

        return ram;
    }

    private static Random rand = new Random();

    private static List<Color> colors = new ArrayList<Color>()
    {
        {
            add(new Color(33, 33, 33));
            add(new Color(33, 33, 36));
            add(new Color(33, 33, 44));
            add(new Color(33, 33, 38));
        }
    };

    private static void paintRand(Graphics2D g2) {
        int count = 10000;

        for (int i = 0; i < count; i++) {
            g2.setColor(colors.get(rand.nextInt(colors.size())));
            g2.fillOval(rand.nextInt(210), rand.nextInt(210), rand.nextInt(55), rand.nextInt(55));
        }
    }

    private static void paintRand(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}