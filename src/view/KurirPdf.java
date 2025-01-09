/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import model.Kurir;

/**
 *
 * @author bhadr
 */
public class KurirPdf {
    public void exportPdf(List<Kurir> kurirs, String fileName) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("user.dir") + File.separator + fileName + ".pdf"));

            document.open();

            float[] columnDefinitionSize = {33.33f, 33.33f, 33.33f};
            PdfPTable table = new PdfPTable(columnDefinitionSize);
            table.setWidthPercentage(100);

            Font font = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);

            table.addCell(new Phrase("No", font));
            table.addCell(new Phrase("ID Kurir", font));
            table.addCell(new Phrase("Name", font));

            int no = 1;
            for (Kurir kurir : kurirs) {
                table.addCell(new Phrase(String.valueOf(no++), font));
                table.addCell(new Phrase("#" + String.valueOf(kurir.getId()), font));
                table.addCell(new Phrase(kurir.getNamaKurir(), font));
            }

            document.add(table);
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        } finally {
            document.close();
        }
    }
}
